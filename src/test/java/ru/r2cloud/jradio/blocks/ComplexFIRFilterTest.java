package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.util.CircularComplexArray;

public class ComplexFIRFilterTest {

	@Test
	public void testComplex() {
		float[] complexTaps = Firdes.convertToComplex(Firdes.lowPass(1.0, 1.0, 0.1, 0.01, Window.WIN_HAMMING, 6.0));

		ComplexFIRFilter filter = new ComplexFIRFilter(complexTaps);
		float[] result = new float[2];
		//number were taken from gnuradio test output
		CircularComplexArray array = new CircularComplexArray(complexTaps.length / 2);
		array.add(1.0f, 1.0f);
		filter.filterComplexTaps(result, array.getHistoryReal(), array.getHistoryImg(), array.getCurrentPos());
		assertEquals(-6.241107190021916e-19, result[0], 0.00001);
		assertEquals(-6.241107190021916e-19, result[1], 0.00001);
		
		array.add(2.0f, 2.0f);
		filter.filterComplexTaps(result, array.getHistoryReal(), array.getHistoryImg(), array.getCurrentPos());
		assertEquals(-0.00012610940029844642, result[0], 0.00001);
		assertEquals(-0.00012610940029844642, result[1], 0.00001);
		 
		array.add(3.0f, 3.0f);
		filter.filterComplexTaps(result, array.getHistoryReal(), array.getHistoryImg(), array.getCurrentPos());
		assertEquals(-0.0004592110344674438, result[0], 0.00001);
		assertEquals(-0.0004592110344674438, result[1], 0.00001);
		
		array.add(4.0f, 4.0f);
		filter.filterComplexTaps(result, array.getHistoryReal(), array.getHistoryImg(), array.getCurrentPos());
		assertEquals(-0.001003113342449069, result[0], 0.00001);
		assertEquals(-0.001003113342449069, result[1], 0.00001);
	}
}
