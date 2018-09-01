package ru.r2cloud.jradio.meteor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ImageChannelUtilTest {

	@Test
	public void testNormal() {
		assertEquals(0, ImageChannelUtil.calculateMissingRows(0, 12068, 0, 12068 + 14));
	}

	@Test
	public void testNormalIncrement() {
		assertEquals(0, ImageChannelUtil.calculateMissingRows(14, 12068, 14 + 14, 12068 + 1));
	}

	@Test
	public void testWrapSequence() {
		assertEquals(0, ImageChannelUtil.calculateMissingRows(0, ImageChannelUtil.MAX_SEQUENCE_COUNT, 0, 0 + 14));
	}

	@Test
	public void testWrapToSecondLine() {
		assertEquals(1, ImageChannelUtil.calculateMissingRows(0, 12068, 0, 12068 + 14 + 1));
	}

	@Test
	public void testWrapSequenceAndWrapToSecondLine() {
		assertEquals(1, ImageChannelUtil.calculateMissingRows(0, ImageChannelUtil.MAX_SEQUENCE_COUNT, 0, 0 + 14 + 1));
	}

	@Test
	public void testSkip2Lines() {
		assertEquals(2, ImageChannelUtil.calculateMissingRows(0, 12068, 0, 12068 + 14 + 1 + 14 + 14 + 14 + 1));
	}

	@Test
	public void testSameLine() {
		assertEquals(0, ImageChannelUtil.calculateMissingRows(14, 12068, 0, 12068 + 13));
	}

}
