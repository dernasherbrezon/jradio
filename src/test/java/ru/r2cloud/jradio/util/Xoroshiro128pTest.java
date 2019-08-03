package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Xoroshiro128pTest {

	@Test
	public void test() {
		Xoroshiro128p random = new Xoroshiro128p(7466763236896258944L);
		assertEquals(-5622495690183510357L, random.next());
	}
	
}
