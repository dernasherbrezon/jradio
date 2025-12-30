package ru.r2cloud.jradio.blocks;

public class Syncword {

	private long syncword;
	private long mask;
	private int length;
	private int[] syncwordBits;

	public Syncword(String syncwordBinary) {
		length = syncwordBinary.length(); // # of bytes in string
		if (length > 64) {
			throw new IllegalArgumentException("syncwords with length: " + length + " is unsupported");
		}

		// set len bottom bits to 1.
		mask = (~0L >>> (64 - length));

		syncword = 0;
		syncwordBits = new int[length];
		for (int i = 0; i < length; i++) {
			syncwordBits[i] = (Byte.valueOf(String.valueOf(syncwordBinary.charAt(i))) & 1);
			syncword = (syncword << 1) | syncwordBits[i];
		}
	}

	public long correlate(long dataRegister) {
		long wrongBits = (dataRegister ^ syncword) & mask;
		return calc(wrongBits);
	}

	public long getSyncword() {
		return syncword;
	}

	public float calculateSnr(byte[] syncwordSoftBits) {
		float amplitude = 0.0f;
		for (int i = 0; i < syncwordBits.length; i++) {
			// -syncwordSoftBits for 0 give positive soft bit
			amplitude += (syncwordBits[i] > 0) ? syncwordSoftBits[i] : -syncwordSoftBits[i];
		}
		amplitude /= syncwordBits.length; // calculate average
		amplitude = Math.abs(amplitude); // make sure positive

		float noisePower = 1e-40f;
		for (int i = 0; i < syncwordBits.length; i++) {
			float d = syncwordSoftBits[i] - ((syncwordBits[i] > 0) ? amplitude : -amplitude);
			noisePower += d * d;
		}

		return (float) (10.0f * Math.log10(syncwordBits.length * amplitude * amplitude / noisePower));
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
