package ru.r2cloud.jradio.fec;

import ru.r2cloud.jradio.crc.Crc16Ccitt;

public class Crc16CcittFec {

	public static boolean fix1bitUsingCrc(byte[] data, int expectedCrc) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < 8; j++) {
				int error = (0x1 << j);
				byte previous = data[i];
				data[i] = (byte) (data[i] ^ error);
				int crcOfFixedData = Crc16Ccitt.calculate(data);
				if (crcOfFixedData == expectedCrc) {
					return true;
				}
				data[i] = previous;
			}
		}
		return false;
	}

	private Crc16CcittFec() {
		// do nothing
	}
}
