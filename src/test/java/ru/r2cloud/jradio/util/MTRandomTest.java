package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MTRandomTest {

	@Test
	public void test() {
		MTRandom random = new MTRandom(true);
		random.setSeed(42);
		assertEquals(1608637542L, random.next(32) & 0xFFFFFFFFL);
		assertEquals(3421126067L, random.next(32) & 0xFFFFFFFFL);
		assertEquals(4083286876L, random.next(32) & 0xFFFFFFFFL);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidSeed() {
		MTRandom random = new MTRandom(true);
		random.setSeed(new int[0]);
	}
	
	@Test
	public void testNonCompatileMode() {
		MTRandom random = new MTRandom(42);
		assertEquals(3030895498L, random.next(32) & 0xFFFFFFFFL);
	}

}
