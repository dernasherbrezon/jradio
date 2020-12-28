package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.ccsds.Scrambler;
import ru.r2cloud.jradio.crc.Crc16Ccitt;

public class NgHam implements MessageInput {

	private static final Logger LOG = LoggerFactory.getLogger(NgHam.class);

	private static final int NGH_SIZE_TAG_MAX_ERROR = 6;
	private static final int SIZE_TAG_BYTES = 3;
	private static final int[] NGH_PL_PAR_SIZE = new int[] { 47, 79, 111, 159, 191, 223, 255 };
	private static final int[] NGH_PAR_SIZE = new int[] { 16, 16, 16, 32, 32, 32, 32 };

	private final MessageInput input;
	private final List<AccessCode> codes = new ArrayList<>();

	public NgHam(MessageInput input) {
		this.input = input;
		codes.add(new AccessCode("001110110100100111001101"));
		codes.add(new AccessCode("010011011101101001010111"));
		codes.add(new AccessCode("011101101001001110011010"));
		codes.add(new AccessCode("100110111011010010101110"));
		codes.add(new AccessCode("101000001111110101100011"));
		codes.add(new AccessCode("110101100110111011111001"));
		codes.add(new AccessCode("111011010010011100110100"));
	}

	@Override
	public byte[] readBytes() throws IOException {
		while (!Thread.currentThread().isInterrupted()) {
			byte[] packet = input.readBytes();
			packet = UnpackedToPacked.pack(packet);
			long size = ((packet[0] & 0xFF) << 16) | ((packet[1] & 0xFF) << 8) | (packet[2] & 0xFF);
			int index = findIndex(size);
			if (index < 0) {
				continue;
			}

			if (packet.length - SIZE_TAG_BYTES < NGH_PL_PAR_SIZE[index]) {
				continue;
			}

			Scrambler.shuffle(packet, SIZE_TAG_BYTES, NGH_PL_PAR_SIZE[index]);
			// TODO reed solomon
			int length = NGH_PL_PAR_SIZE[index] - NGH_PAR_SIZE[index] - (packet[SIZE_TAG_BYTES] & 0b11111);

			int actual = Crc16Ccitt.calculateReverse(packet, SIZE_TAG_BYTES, length - 2);
			int expected = (packet[SIZE_TAG_BYTES + length - 2] & 0xFF) << 8 | (packet[SIZE_TAG_BYTES + length - 1] & 0xFF);
			if (actual != expected) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("crc mismatch");
				}
				continue;
			}

			byte[] result = new byte[length - 2 - 1];
			System.arraycopy(packet, SIZE_TAG_BYTES + 1, result, 0, result.length);
			return result;
		}
		throw new EOFException();
	}

	private int findIndex(long size) {
		long minMatched = NGH_SIZE_TAG_MAX_ERROR;
		int result = -1;
		for (int i = 0; i < codes.size(); i++) {
			long curMatched = codes.get(i).correlate(size);
			if (curMatched == 0) {
				return i;
			}
			if (curMatched > NGH_SIZE_TAG_MAX_ERROR) {
				continue;
			}
			if (curMatched < minMatched) {
				minMatched = curMatched;
				result = i;
			}
		}
		return result;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}

}
