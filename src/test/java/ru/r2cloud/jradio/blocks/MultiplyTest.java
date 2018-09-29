package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class MultiplyTest {

	@Test
	public void test() throws Exception {
		Multiply multiply = new Multiply(new FloatInput() {

			@Override
			public void close() throws IOException {
				// do nothing
			}

			@Override
			public float readFloat() throws IOException {
				return 2.0f;
			}
			
			@Override
			public Context getContext() {
				return null;
			}
			
		}, new FloatInput() {

			@Override
			public void close() throws IOException {
				// do nothing
			}

			@Override
			public float readFloat() throws IOException {
				return 2.0f;
			}
			
			@Override
			public Context getContext() {
				return null;
			}
		}, true);
		assertEquals(0.0f, multiply.readFloat(), 0.0f);
		multiply.close();
	}

}
