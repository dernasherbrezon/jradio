package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayFloatInput;

public class DivideTest {

	@Test
	public void test() throws Exception {
		Divide block = new Divide(new ArrayFloatInput(1.0f, 2.0f), new ArrayFloatInput(2.0f, 3.0f));
		assertEquals(0.61538463f, block.readFloat(), 0.0f);
		assertEquals(0.07692308f, block.readFloat(), 0.0f);
		block.close();
	}

}
