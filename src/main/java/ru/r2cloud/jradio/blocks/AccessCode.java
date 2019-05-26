package ru.r2cloud.jradio.blocks;

public class AccessCode {

	private long code;
	private long mask;
	private int length;

	public AccessCode(String accessCodeBinary) {
		length = accessCodeBinary.length(); // # of bytes in string
		if (length > 64) {
			throw new IllegalArgumentException("access code with length: " + length + " is unsupported");
		}

		// set len bottom bits to 1.
		mask = (~0L >>> (64 - length));

		code = 0;
		for (int i = 0; i < length; i++) {
			code = (code << 1) | (Byte.valueOf(String.valueOf(accessCodeBinary.charAt(i))) & 1);
		}
	}

	public long correlate(long dataRegister) {
		long wrongBits = (dataRegister ^ code) & mask;
		return calc(wrongBits);
	}

	public long getAccessCode() {
		return code;
	}
	
	public int getLength() {
		return length;
	}

	private static long calc(long value) {
		int retVal = (int) (value & 0x00000000FFFFFFFFl);
		retVal = (retVal & 0x55555555) + (retVal >> 1 & 0x55555555);
		retVal = (retVal & 0x33333333) + (retVal >> 2 & 0x33333333);
		retVal = (retVal + (retVal >> 4)) & 0x0F0F0F0F;
		retVal = (retVal + (retVal >> 8));
		retVal = (retVal + (retVal >> 16)) & 0x0000003F;
		long retVal64 = retVal;
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
