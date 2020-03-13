package ru.r2cloud.jradio.blocks;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.LongValueSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.Tag;
import ru.r2cloud.jradio.crc.Crc16Ccitt;

public class HdlcReceiver implements MessageInput {

	private static final Logger LOG = LoggerFactory.getLogger(HdlcReceiver.class);

	// flag is 01111110. last bit is always discarded
	private static final int FLAG_LENGTH = 7;
	private static final int FCS_LENGTH = 2;
	private final ByteInput input;
	private final byte[] window;

	public HdlcReceiver(ByteInput input, int maxLengthBytes) {
		this.input = input;
		this.window = new byte[((maxLengthBytes + FCS_LENGTH) * 8) + FLAG_LENGTH];
	}

	@Override
	public byte[] readBytes() throws IOException {
		int ones = 0;
		boolean foundStartFlag = false;
		int packetLength = 0;
		while (true) {
			byte curBit = input.readByte();
			if (curBit == 1) {
				ones++;
				if (foundStartFlag) {
					if (packetLength < window.length) {
						window[packetLength] = curBit;
						packetLength++;
					} else {
						if (LOG.isDebugEnabled()) {
							LOG.debug("found a packet with more than max length: {}. discarding it", packetLength);
						}
						foundStartFlag = false;
						packetLength = 0;
					}
				}
			} else {
				if (ones == 5) {
					// destuffing
				} else if (ones > 5) { // i.e 6
					if (!foundStartFlag) {
						foundStartFlag = true;
					} else {
						// pop back 7bits of the last flag
						packetLength = packetLength - FLAG_LENGTH;
						if (packetLength % 8 != 0 || packetLength / 8 <= 2) {
							packetLength = 0;
							ones = 0;
							continue;
						}
						byte[] frame = unpackedToPacked(packetLength - 2 * 8);
						int crc = extractFcs(packetLength);
						if (Crc16Ccitt.calculateReverse(frame) != crc) {
							packetLength = 0;
							ones = 0;
							continue;
						}
						Tag tag = new Tag();
						tag.setId(UUID.randomUUID().toString());
						LongValueSource currentSample = getContext().getCurrentSample();
						if (currentSample != null) {
							tag.put(CorrelateAccessCodeTag.SOURCE_SAMPLE, currentSample.getValue());
						}
						getContext().put(tag.getId(), tag);
						return frame;
					}

				} else {
					if (foundStartFlag) {
						if (packetLength < window.length) {
							window[packetLength] = curBit;
							packetLength++;
						} else {
							if (LOG.isDebugEnabled()) {
								LOG.debug("found a packet with more than max length: {}. discarding it", packetLength);
							}
							foundStartFlag = false;
							packetLength = 0;
						}
					}
				}
				ones = 0;
			}
		}
	}

	private int extractFcs(int packetLength) {
		int result = 0;
		for (int i = 0, j = packetLength - 1; i < 16; i++, j--) {
			result = result << 1;
			result = result | window[j];
		}
		return result;
	}

	private byte[] unpackedToPacked(int packetLength) {
		byte[] frame = new byte[packetLength / 8];
		for (int i = 0; i < frame.length; i++) {
			int curByte = 0;
			for (int j = 0; j < 8; j++) {
				// LSB
				curByte = curByte | (window[i * 8 + j] << j);
			}
			frame[i] = (byte) curByte;
		}
		return frame;
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}

	@Override
	public void close() throws IOException {
		input.close();
	}
}
