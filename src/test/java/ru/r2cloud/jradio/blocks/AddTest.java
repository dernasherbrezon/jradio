package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayFloatInput;
import ru.r2cloud.jradio.Context;

public class AddTest {

	@Test
	public void testComplex() throws Exception {
		Add add = new Add(new ArrayFloatInput(1.1f, 2.1f), new ArrayFloatInput(2.3f, 4.4f));
		assertEquals(3.4f, add.readFloat(), 0.0f);
		assertEquals(6.5f, add.readFloat(), 0.0f);
		add.close();
	}

	@Test
	public void testFloat() throws Exception {
		Context ctx = new Context();
		ctx.setChannels(1);
		Add add = new Add(new ArrayFloatInput(ctx, 1.1f), new ArrayFloatInput(ctx, 2.3f));
		assertEquals(3.4f, add.readFloat(), 0.0f);
		add.close();
	}

}
