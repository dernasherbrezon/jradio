package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.ArrayFloatInput;

public class MultiplyConstTest {

	private MultiplyConst mc;

	@Test
	public void test() throws Exception {
		mc = new MultiplyConst(new ArrayFloatInput(10.0f), 10.0f);
		assertEquals(100, mc.readFloat(), 0.0);
	}

	@After
	public void stop() throws Exception {
		mc.close();
	}

}
