package ru.r2cloud.jradio.fec;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class HammingTest {

	@Test
	public void testNoError() throws Exception {
		assertEquals(0b0010_1100, Hamming.decode12_8(0b0010_1100_1000));
	}

	@Test
	public void testCorrect1Error() throws Exception {
		assertEquals(0b0010_1100, Hamming.decode12_8(0b0011_1100_1000));
	}
	
	@Test(expected = UncorrectableException.class)
	public void testUnableToCorrect() throws Exception {
		Hamming.decode12_8(0b0011_1100_1100);
	}
	
}
