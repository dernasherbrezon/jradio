package ru.r2cloud.jradio.sink;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import ru.r2cloud.jradio.FloatInput;

class FloatInputStream extends InputStream {

	private final FloatInput input;
	private final int[] buf;
	private final int sampleSizeInBits;
	private boolean firstByte = true;

	FloatInputStream(FloatInput input, int sampleSizeInBits) {
		this.input = input;
		this.sampleSizeInBits = sampleSizeInBits;
		buf = new int[sampleSizeInBits / 8];
	}

	@Override
	public int read() throws IOException {
		if (sampleSizeInBits == 16) {
			if (firstByte) {
				try {
					short s = (short) (Math.round(input.readFloat() * Short.MAX_VALUE));
					buf[0] = (0xFF & s);
					buf[1] = ((s >> 8) & 0xFF);
					firstByte = false;
					return buf[0];
				} catch (EOFException e) {
					return -1;
				}
			} else {
				firstByte = true;
				return buf[1];
			}
		} else {
			try {
				return (int) ((input.readFloat() + 1) * 128);
			} catch (EOFException e) {
				return -1;
			}
		}
	}

}
