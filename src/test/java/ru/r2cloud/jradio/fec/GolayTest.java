package ru.r2cloud.jradio.fec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class GolayTest {

	private Golay g = new Golay();

	@Test
	public void testEncodeDecode() throws UncorrectableException {
		int data = 0b0101_0101_0101;
		int result = g.encode(data);
		assertEquals(0b1000_0101_1011_0101_0101_0101, result);
		result = g.decode(result);
		assertEquals(data, result);
	}

	@Test
	public void testCorrect3() throws UncorrectableException {
		int result = g.decode(0b1000_0101_1011_1011_0101_0101);
		assertEquals(0b0101_0101_0101, result);
	}

	@Test(expected = UncorrectableException.class)
	public void testCorrect4() throws UncorrectableException {
		g.decode(0b1000_0101_1011_1010_0101_0101);
	}

	@Test(expected = UncorrectableException.class)
	public void testDetect6() throws UncorrectableException {
		g.decode(0b1000_0101_1011_1010_1001_0101);
	}

	@Test
	public void testDetect7() throws UncorrectableException {
		int result = g.decode(0b1000_0101_1011_1010_1001_1101);
		assertFalse(0b1000_0101_1011_0101_0101_0101 == result);
	}

}
