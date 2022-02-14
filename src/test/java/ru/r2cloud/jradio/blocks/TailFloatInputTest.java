package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.ArrayFloatInput;
import ru.r2cloud.jradio.Context;

public class TailFloatInputTest {

	private TailFloatInput input;

	@Test
	public void testSuccess() throws Exception {
		Context ctx = new Context();
		ctx.setChannels(2);
		ctx.setTotalSamples(1L);
		ArrayFloatInput data = new ArrayFloatInput(ctx, new float[] { 1.0f, 2.0f });
		input = new TailFloatInput(data, 2);
		assertEquals(3, input.getContext().getTotalSamples().intValue());
		assertEquals(1.0f, input.readFloat(), 0.0f);
		assertEquals(2.0f, input.readFloat(), 0.0f);
		
		assertEquals(0.0f, input.readFloat(), 0.0f);
		assertEquals(0.0f, input.readFloat(), 0.0f);
		
		assertEquals(0.0f, input.readFloat(), 0.0f);
		assertEquals(0.0f, input.readFloat(), 0.0f);
		try {
			input.readFloat();
			fail("EOF expected");
		} catch (EOFException e) {
			// ignore
		}
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
