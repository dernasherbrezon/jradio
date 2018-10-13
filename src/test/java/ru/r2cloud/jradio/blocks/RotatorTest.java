package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RotatorTest {

	private Rotator r;

	@Test
	public void testRotator() {
		r = new Rotator(new float[] { 1.0f, 0.0f }, new float[] { (float) Math.cos(2 * Math.PI / 1003), (float) Math.sin(2 * Math.PI / 1003) });
		assertRotate(1.0f, 0.0f, 1.0f, 0.0f);
		assertRotate(1.0f, 0.0f, 0.999980f, 0.006264f);
		assertRotate(1.0f, 0.0f, 0.999922f, 0.012528f);
		assertRotate(1.0f, 0.0f, 0.999823f, 0.018792f);
	}

	private void assertRotate(float in1, float in2, float out1, float out2) {
		float[] input = new float[2];
		float[] output = new float[2];
		input[0] = in1;
		input[1] = in2;
		r.rotate(output, input);
		assertEquals(out1, output[0], 0.00001);
		assertEquals(out2, output[1], 0.00001);
	}

}
