package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.util.CircularArray;

public class FIRFilterTest {

	@Test
	public void testSuccess() {
		CircularArray array = new CircularArray(3);
		array.add(0.210882900f);
		array.add(0.093813896f);
		array.add(0.031220436f);
		float actual = new FIRFilter(new float[] { -3.3477397E-7f, -8.41565E-7f, 5.4569597E-7f }).filter(array);
		assertEquals(2.5675668E-8f, actual, 0.0f);
	}

}
