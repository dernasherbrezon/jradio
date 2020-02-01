package ru.r2cloud.jradio.crc;

public class Crc16Ibm3740 {

	public static int calculate(byte[] data, int offset, int length) {
		int shiftRegister = 0xFFFF;
		for (int i = offset; i < (offset + length); ++i) {
			for (short valueMask = 0b10000000; valueMask != 0; valueMask >>= 1) {
				// push the shift register by one (to the left in this case and read the bit that is emitted (16th bit)
				shiftRegister <<= 1;
				// add the value bit to the prescribed positions (if it is 1)
				if (((data[i] & 0xFF) & valueMask) > 0) {
					// add 1 to the positions indicated by the generator polynomial (0,5,12)
					shiftRegister ^= 0b00010000_00100001;
				}
				// add the shift register output (if it is 1)
				if ((shiftRegister & 0b1_00000000_00000000) > 0) {
					// add 1 to the positions indicated by the generator polynomial (0,5,12)
					shiftRegister ^= 0b00010000_00100001;
				}
			}
			// return the shift register, reset the 16 MSB
			shiftRegister = shiftRegister & 0xFFFF;
		}
		return shiftRegister;
	}

	private Crc16Ibm3740() {
		// do nothing
	}

}
