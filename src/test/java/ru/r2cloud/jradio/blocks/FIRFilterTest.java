package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FIRFilterTest {

	@Test
	public void testSuccess() {
		float actual = new FIRFilter(new float[] { -3.3477397E-7f, -8.41565E-7f, 5.4569597E-7f }).filter(new float[] { 0.210882900f, 0.093813896f, 0.031220436f });
		assertTrue("the actual is " + String.valueOf(actual), Float.compare(2.5675671E-8f, actual) == 0);
	}

}
