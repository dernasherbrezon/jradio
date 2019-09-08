package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayFloatInput;
import ru.r2cloud.jradio.Context;

public class FIRFilterBlockComplexTest {

	@SuppressWarnings({ "unused", "resource" })
	@Test(expected = IllegalArgumentException.class)
	public void testArguments() {
		new FIRFilterBlockComplex(new ArrayFloatInput(new float[] { 0.0f, 0.1f }), new float[1]);
	}

	@SuppressWarnings({ "unused", "resource" })
	@Test(expected = IllegalArgumentException.class)
	public void testArguments2() {
		Context ctx = new Context();
		ctx.setChannels(1);
		ArrayFloatInput input = new ArrayFloatInput(ctx, new float[] { 0.0f, 0.1f });
		new FIRFilterBlockComplex(input, new float[2]);
	}
}
