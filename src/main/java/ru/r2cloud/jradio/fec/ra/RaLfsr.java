package ru.r2cloud.jradio.fec.ra;

public class RaLfsr {

	private final int mask;
	private final int offset;
	private final int state;
	private final int length;
	private final int highbit;

	private int currentState;

	public RaLfsr(int mask, int offset, int seed, int length, int highbit) {
		this.mask = mask;
		this.offset = offset;
		this.state = seed;
		this.length = length;
		this.currentState = seed;
		this.highbit = highbit;
	}

	public int next() {
		int result;
		do {
			result = currentState & 0x1;
			currentState >>= 1;
			currentState ^= (-result) & mask;
		} while (currentState > length);

		result = currentState - 1;
		if (result < offset) {
			result += length;
		}
		result -= offset;
		return result;
	}

	public int prev() {
		int b;

		/* this loop runs at most twice on average */
		do {
			b = currentState >> highbit;
			currentState <<= 1;
			currentState ^= (-b) & (0x01 | mask << 1);
		} while (currentState > length);

		b = currentState - 1;
		if (b < offset) {
			b += length;
		}
		b -= offset;
		return b;
	}

	public void reset() {
		currentState = state;
	}

}
