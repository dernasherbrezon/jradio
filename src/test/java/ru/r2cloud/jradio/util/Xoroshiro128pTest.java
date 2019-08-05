package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

public class Xoroshiro128pTest {

	@Test
	public void test() {
		Xoroshiro128p random = new Xoroshiro128p(42L);
		assertEquals("3520422898491873512", toUnsignedBigInteger(random.next()).toString(10));
		assertEquals("16123393100820228811", toUnsignedBigInteger(random.next()).toString(10));
	}

	private static BigInteger toUnsignedBigInteger(long i) {
		if (i >= 0L) {
			return BigInteger.valueOf(i);
		} else {
			int upper = (int) (i >>> 32);
			int lower = (int) i;
			// return (upper << 32) + lower
			return (BigInteger.valueOf(Integer.toUnsignedLong(upper))).shiftLeft(32).add(BigInteger.valueOf(Integer.toUnsignedLong(lower)));
		}
	}

}
