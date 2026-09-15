package ru.r2cloud.jradio.ccsds;

public class IessScrambler {

	private static final int COUNTER_MASK = 0x1f;

	/*
	 * IESS-308 descrambler. Generator polynomial: 1 + x^14 + x^15, with an
	 * additional counter that periodically re-synchronizes the shift register
	 * based on the incoming (scrambled) bit stream.
	 */
	public static void shuffle(byte[] data) {
		int shiftRegister = 0;
		int counter = 0;
		for (int i = 0; i < data.length; i++) {
			int cur = data[i] & 0xFF;
			int result = 0;
			for (int j = 7; j >= 0; j--) {
				int inbit = (cur >> j) & 1;
				int outbit = ~(inbit ^ shiftRegister ^ (shiftRegister >> 17) ^ (counter == COUNTER_MASK ? 1 : 0)) & 1;
				result |= outbit << j;
				if ((((shiftRegister >> 19) ^ (shiftRegister >> 11)) & 1) == 1) {
					counter = 0;
				} else {
					counter = (counter + 1) & COUNTER_MASK;
				}
				shiftRegister = (shiftRegister >> 1) | (inbit << 19);
			}
			data[i] = (byte) result;
		}
	}

	private IessScrambler() {
		// do nothing
	}

}
