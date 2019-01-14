package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.ArrayFloatInput;

public class ComplexToRealTest {

	private ComplexToReal source;

	@Test
	public void testSuccess() throws Exception {
		source = new ComplexToReal(new ArrayFloatInput(1.0f, 2.0f, 3.0f, 4.0f));
		assertEquals(1.0f, source.readFloat(), 0.0f);
		assertEquals(3.0f, source.readFloat(), 0.0f);
	}

	@After
	public void stop() throws Exception {
		if (source != null) {
			source.close();
		}
	}
}
