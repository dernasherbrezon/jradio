package ru.r2cloud.jradio.util;

public class Xoroshiro128p {

	private static final long[] JUMP = new long[] { 0xbeac0467eba5facbL, 0xd86b048b86aa9922L };

	private long state0;
	private long state1;

	public Xoroshiro128p(long seed) {
		state0 = seed;
		state1 = splitmix64Next(state0);
		jump();
	}

	private static long splitmix64Next(long seed) {
		long z = (seed += 0x9e3779b97f4a7c15L);
		z = (z ^ (z >> 30)) * 0xbf58476d1ce4e5b9L;
		z = (z ^ (z >> 27)) * 0x94d049bb133111ebL;
		return z ^ (z >> 31);
	}

	private void jump() {
		long s0 = 0;
		long s1 = 0;
		for (int i = 0; i < JUMP.length; ++i) {
			for (int b = 0; b < 64; ++b) {
				if ((JUMP[i] & 1L << b) == 1) {
					s0 ^= state0;
					s1 ^= state1;
				}
				next();
			}
		}
		state0 = s0;
		state1 = s1;
	}

	public long next() {
		long s0 = state0;
		long s1 = state1;
		long result = s0 + s1;

		s1 ^= s0;
		state0 = rotl(s0, 55) ^ s1 ^ (s1 << 14); // a, b
		state1 = rotl(s1, 36); // c

		return result;
	}

	private static long rotl(long x, int k) {
		return (x << k) | (x >> (64 - k));
	}
}
