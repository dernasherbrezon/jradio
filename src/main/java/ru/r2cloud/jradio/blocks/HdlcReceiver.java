package ru.r2cloud.jradio.blocks;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.crc.Crc16Ccitt;

public class HdlcReceiver implements MessageInput {

	private static final Logger LOG = LoggerFactory.getLogger(HdlcReceiver.class);

	// flag is 01111110. last bit is always discarded
	private final static int FLAG_LENGTH = 7;
	private final static int FCS_LENGTH = 2;
	private final ByteInput input;
	private final byte[] window;

	public HdlcReceiver(ByteInput input, int maxLength) {
		this.input = input;
		this.window = new byte[((maxLength + FCS_LENGTH) * 8)];
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
					window[packetLength] = curBit;
					packetLength++;
					if (packetLength >= window.length) {
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
						byte[] frameWithCrc = unpackedToPacked(packetLength);
						byte[] frame = Arrays.copyOfRange(frameWithCrc, 0, frameWithCrc.length - 2);
						int crc = extractFcs(frameWithCrc);
						if (Crc16Ccitt.calculateReverse(frame) != crc) {
							packetLength = 0;
							ones = 0;
							continue;
						}
						return frame;
					}

				} else {
					if (foundStartFlag) {
						window[packetLength] = curBit;
						packetLength++;
						if (packetLength >= window.length) {
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

	private static int extractFcs(byte[] frameWithCrc) {
		int b1 = (frameWithCrc[frameWithCrc.length - 1] & 0xFF);
		int b2 = (frameWithCrc[frameWithCrc.length - 2] & 0xFF);
		return b1 << 8 | b2;
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
