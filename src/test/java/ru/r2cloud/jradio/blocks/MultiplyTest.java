package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayFloatInput;

public class MultiplyTest {

	@Test
	public void test() throws Exception {
		Multiply multiply = new Multiply(new ArrayFloatInput(2.0f, 0.0f), new ArrayFloatInput(2.0f, 0.0f));
		assertEquals(4.0f, multiply.readFloat(), 0.0f);
		multiply.close();
	}

}
