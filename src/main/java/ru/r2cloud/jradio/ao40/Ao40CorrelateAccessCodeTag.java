package ru.r2cloud.jradio.ao40;

import java.io.IOException;
import java.util.UUID;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatValueSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.Tag;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;

public class Ao40CorrelateAccessCodeTag implements MessageInput {

	private static final int STEP = 80;
	private static final int[] SYNCWORD = new int[] { 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0 };
	private static final int[] INVERTED_SYNCWORD = new int[] { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1 };

	private final ByteInput input;
	private final byte[] window;
	private int currentIndex = 0;
	private final int threshold;

	public Ao40CorrelateAccessCodeTag(ByteInput input, int threshold) {
		this.input = input;
		this.threshold = threshold;
		window = new byte[80 * 65];
	}

	@Override
	public byte[] readBytes() throws IOException {
		while (true) {
			window[currentIndex] = input.readByte();
			currentIndex++;
			if (currentIndex >= window.length) {
				currentIndex = 0;
			}
			int matchType = match();
			if (matchType == 0) {
				continue;
			}
			byte[] result = new byte[window.length];
			System.arraycopy(window, currentIndex, result, 0, window.length - currentIndex);
			System.arraycopy(window, 0, result, window.length - currentIndex, currentIndex);
			// solve phase ambiguity. ao40 is using BPSK, thus only 180deg phase ambiguity exists
			if (matchType == 2) {
				for (int i = 0; i < result.length; i++) {
					// reverse soft bit
					result[i] = (byte) (result[i] ^ 0xFF);
				}
			}
			Tag tag = new Tag();
			tag.setId(UUID.randomUUID().toString());
			FloatValueSource currentSample = getContext().getCurrentSample();
			if (currentSample != null) {
				tag.put(CorrelateAccessCodeTag.SOURCE_SAMPLE, currentSample.getValue());
			}
			getContext().put(tag.getId(), tag);
			return result;
		}
	}

	private int match() {
		int match = 0;
		int intertedMatch = 0;
		for (int j = 0; j < SYNCWORD.length; j++) {
			int bit;
			int arrayIndex = currentIndex + j * STEP;
			if (arrayIndex >= window.length) {
				arrayIndex = arrayIndex - window.length;
			}
			if (window[arrayIndex] > 0) {
				bit = 1;
			} else {
				bit = 0;
			}
			if (bit == SYNCWORD[j]) {
				match++;
			}
			if (bit == INVERTED_SYNCWORD[j]) {
				intertedMatch++;
			}
		}
		if (match >= SYNCWORD.length - threshold) {
			return 1;
		}
		if (intertedMatch >= INVERTED_SYNCWORD.length - threshold) {
			return 2;
		}
		return 0;
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
