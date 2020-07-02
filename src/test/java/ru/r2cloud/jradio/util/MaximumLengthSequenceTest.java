package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class MaximumLengthSequenceTest {

	@Test
	public void testSuccess() {
		assertArrayEquals(new byte[] { 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0 }, MaximumLengthSequence.generate(4));
		assertArrayEquals(new byte[] { 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, MaximumLengthSequence.generate(6));
	}

}
