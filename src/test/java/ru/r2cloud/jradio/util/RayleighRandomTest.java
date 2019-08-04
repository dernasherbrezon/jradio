package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RayleighRandomTest {

	@Test
	public void test() {
		RayleighRandom random = new RayleighRandom(5489);
		assertEquals(0.253161, random.nextFloat(), 0.00001f);
		assertEquals(-0.293219, random.nextFloat(), 0.00001f);
		assertEquals(0.0845902, random.nextFloat(), 0.00001f);
		assertEquals(-0.0570855, random.nextFloat(), 0.00001f);
	}

}
