package ru.r2cloud.jradio.fec;

import java.util.HashMap;
import java.util.Map;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Hamming {
	
	private final static Map<Integer, Integer> SYNDROME_12 = new HashMap<>();
	
	static {
		SYNDROME_12.put(0b1110, 0b1000_0000_0000);
		SYNDROME_12.put(0b1101, 0b0100_0000_0000);
		SYNDROME_12.put(0b1011, 0b0010_0000_0000);
		SYNDROME_12.put(0b0111, 0b0001_0000_0000);
		SYNDROME_12.put(0b1010, 0b0000_1000_0000);
		SYNDROME_12.put(0b1001, 0b0000_0100_0000);
		SYNDROME_12.put(0b0110, 0b0000_0010_0000);
		SYNDROME_12.put(0b0101, 0b0000_0001_0000);
		SYNDROME_12.put(0b1000, 0b0000_0000_1000);
		SYNDROME_12.put(0b0100, 0b0000_0000_0100);
		SYNDROME_12.put(0b0010, 0b0000_0000_0010);
		SYNDROME_12.put(0b0001, 0b0000_0000_0001);
	}

	private final static int[] FEC_MATRIX_12b = new int[] { 
			0b1110_1100_1000, 
			0b1101_0011_0100, 
			0b1011_1010_0010, 
			0b0111_0101_0001 };

	public static int decode12_8(int coded) throws UncorrectableException {
		int fec = 0;
		for (int i = 0; i < FEC_MATRIX_12b.length; i++) {
			fec = fec << 1;
			if (!even_parity_12b(FEC_MATRIX_12b[i] & coded)) {
				fec = fec | 0x1;
			}
		}
		if (fec == 0) {
			return coded >> 4;
		}
		Integer error = SYNDROME_12.get(fec);
		if (error == null) {
			throw new UncorrectableException("unable to correct");
		}
		return (coded ^ error) >> 4;
	}

	private static boolean even_parity_12b(int val) {
		int count = 0;
		for (int i = 0; i < 12; i++) {
			if (((val >> i) & 0x1) == 1) {
				count++;
			}
		}
		return count % 2 == 0;
	}

}
