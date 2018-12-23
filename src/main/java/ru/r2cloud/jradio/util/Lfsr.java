package ru.r2cloud.jradio.util;

public class Lfsr {

	private final int mask;
	private final int seed;
	private final int length;

	private int shiftRegister;

	public Lfsr(int mask, int seed, int length) {
		if (length > 31) {
			throw new IllegalArgumentException("length must be <= 31");
		}
		this.mask = mask;
		this.seed = seed;
		this.length = length;
		this.shiftRegister = seed;
	}

	public int nextBit() {
		int output = shiftRegister & 1;
		int newbit = (int) (popCount(shiftRegister & mask) % 2);
		shiftRegister = ((shiftRegister >> 1) | (newbit << length));
		return output;
	}

	public byte nextBitDescramble(byte input) {
		int output = (int) (popCount(shiftRegister & mask) % 2) ^ ((input & 0xFF) & 1);
		int newbit = (input & 0xFF) & 1;
		shiftRegister = ((shiftRegister >> 1) | (newbit << length));
		return (byte)output;
	}

	public void reset() {
		shiftRegister = seed;
	}

	private static long popCount(long x) {
		long r = x - ((x >> 1) & 033333333333) - ((x >> 2) & 011111111111);
		return ((r + (r >> 3)) & 030707070707) % 63;
	}

}
