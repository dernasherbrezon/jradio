package ru.r2cloud.jradio.crc;

public class Crc16Ccitt {

	public static int calculate(byte[] data) {
		return calculate(data, 0, data.length);
	}

	public static int calculate(byte[] data, int offset, int length) {
		int crc = 0;
		for (int i = offset; i < (offset + length); ++i) {
			crc ^= data[i] << 8;
			for (int j = 0; j < 8; ++j) {
				if ((crc & 0x8000) > 0) {
					crc = (crc << 1) ^ 0x1021;
				} else {
					crc = crc << 1;
				}
			}
		}
		return crc & 0xFFFF;
	}

	// crc-16/x-25
	public static int calculateReverse(byte[] data) {
		return calculateReverse(data, 0, data.length);
	}

	public static int calculateReverse(byte[] data, int offset, int length) {
		int crc16 = 0xFFFF;

		for (int i = offset; i < (offset + length); ++i) {
			int mask = 1;

			for (int j = 0; j < 8; ++j) {
				int feedback = 0;

				if ((data[i] & mask) != 0)
					feedback = 1;

				feedback = (feedback ^ crc16) & 1;

				crc16 = (crc16 >> 1) & 0x7FFF;

				if (feedback != 0)
					crc16 ^= 0x8408;

				mask = mask << 1;
			}
		}
		return (crc16 ^ 0xFFFF);
	}

	public static int calculateReverseLsbBits(byte[] data, int offset, int length) {
		int crc16 = 0xFFFF;

		for (int i = offset; i < (offset + length); ++i) {
			int feedback = 0;

			if (data[i] != 0) {
				feedback = 1;
			}

			feedback = (feedback ^ crc16) & 1;

			crc16 = (crc16 >> 1) & 0x7FFF;

			if (feedback != 0) {
				crc16 ^= 0x8408;
			}
		}
		return (crc16 ^ 0xFFFF);
	}

	private Crc16Ccitt() {
		// do nothing
	}

}
