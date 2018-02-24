package ru.r2cloud.jradio.blocks;

class AccessCode {

	private long accessCode;
	private long mask;

	AccessCode(String accessCodeBinary) {
		int length = accessCodeBinary.length(); // # of bytes in string
		if (length > 64) {
			throw new IllegalArgumentException("access code with length: " + length + " is unsupported");
		}

		// set len bottom bits to 1.
		mask = (~0L >>> (64 - length));

		accessCode = 0;
		for (int i = 0; i < length; i++) {
			accessCode = (accessCode << 1) | (Byte.valueOf(String.valueOf(accessCodeBinary.charAt(i))) & 1);
		}
	}

	long correlate(long dataRegister) {
		long wrong_bits = (dataRegister ^ accessCode) & mask;
		return calc(wrong_bits);
	}

	long getAccessCode() {
		return accessCode;
	}

	private static long calc(long value) {
		int retVal = (int) (value & 0x00000000FFFFFFFFl);
		retVal = (retVal & 0x55555555) + (retVal >> 1 & 0x55555555);
		retVal = (retVal & 0x33333333) + (retVal >> 2 & 0x33333333);
		retVal = (retVal + (retVal >> 4)) & 0x0F0F0F0F;
		retVal = (retVal + (retVal >> 8));
		retVal = (retVal + (retVal >> 16)) & 0x0000003F;
		long retVal64 = retVal;
		// retVal = valueVector[1];
		retVal = (int) ((value & 0xFFFFFFFF00000000l) >> 31);
		retVal = (retVal & 0x55555555) + (retVal >> 1 & 0x55555555);
		retVal = (retVal & 0x33333333) + (retVal >> 2 & 0x33333333);
		retVal = (retVal + (retVal >> 4)) & 0x0F0F0F0F;
		retVal = (retVal + (retVal >> 8));
		retVal = (retVal + (retVal >> 16)) & 0x0000003F;
		retVal64 += retVal;
		return retVal64;
	}
}
