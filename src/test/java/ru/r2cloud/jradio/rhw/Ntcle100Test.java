package ru.r2cloud.jradio.rhw;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Ntcle100Test {

	@Test
	public void testSuccess() {
		assertEquals(11.60052490234375f, Ntcle100.calculate(2686), 0.0f);
	}

}
