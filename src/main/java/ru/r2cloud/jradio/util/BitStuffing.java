package ru.r2cloud.jradio.util;

import java.io.IOException;
import java.util.Arrays;

public class BitStuffing {

	public static byte[] destuffOnes(byte[] data, int run) throws IOException {
		byte[] result = new byte[data.length];
		int outputSize = 0;
		int currentRun = 0;
		int outputBitPosition = 0;
		int outputByte = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < 8; j++) {
				int inputBit = ((data[i] & 0xFF) >> (7 - j)) & 0x1;
				if (currentRun == run) {
					if (inputBit == 1) {
						throw new IOException("unexpected long run of ones");
					}
					currentRun = 0;
					continue;
				}

				if (inputBit == 0) {
					currentRun = 0;
				} else {
					currentRun += 1;
				}

				if (outputBitPosition > 7) {
					result[outputSize] = (byte) outputByte;
					outputBitPosition = 0;
					outputSize++;
					outputByte = 0;
				}

				outputByte = outputByte << 1;
				outputByte = outputByte | inputBit;
				outputBitPosition++;
			}
		}
		// outputBitPosition might be more than 0. But this byte should be skipped
		return Arrays.copyOfRange(result, 0, outputSize);
	}

	private BitStuffing() {
		// do nothing
	}

}
