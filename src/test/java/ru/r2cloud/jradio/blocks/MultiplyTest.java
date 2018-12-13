package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.SingleResultInput;

public class MultiplyTest {

	@Test
	public void test() throws Exception {
		Multiply multiply = new Multiply(new SingleResultInput(2.0f), new SingleResultInput(2.0f));
		assertEquals(0.0f, multiply.readFloat(), 0.0f);
		multiply.close();
	}

}
