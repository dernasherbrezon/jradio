package ru.r2cloud.jradio.nayif1;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.MessageInput;

public class Ao40CorrelateAccessCodeTag implements MessageInput {

	private final static int STEP = 80;
	private final static int[] SYNCWORD = new int[] { 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0 };

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
			if (match()) {
				byte[] result = new byte[window.length];
				System.arraycopy(window, currentIndex, result, 0, window.length - currentIndex);
				System.arraycopy(window, 0, result, window.length - currentIndex, currentIndex);
				return result;
			}
		}
	}

	private boolean match() {
		int match = 0;
		for (int j = 0; j < SYNCWORD.length; j++) {
			int bit;
			int arrayIndex = currentIndex + j * STEP;
			if (arrayIndex >= window.length) {
				arrayIndex = arrayIndex - window.length;
			}
			if (window[arrayIndex] < 0) {
				bit = 1;
			} else {
				bit = 0;
			}
			if (bit == SYNCWORD[j]) {
				match++;
			}
		}
		if (match >= SYNCWORD.length - threshold) {
			return true;
		}
		return false;
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
