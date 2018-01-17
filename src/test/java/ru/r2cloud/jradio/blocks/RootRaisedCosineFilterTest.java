package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;
import java.io.FileInputStream;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;

public class RootRaisedCosineFilterTest {

	private RootRaisedCosineFilter source;

	@Test
	public void test() throws Exception {
		source = new RootRaisedCosineFilter(new InputStreamSource(new FileInputStream("/Users/dernasherbrezon/Downloads/AGC.bin")), 1.0f, 222222f, 72000f, 0.6f, 361);
		try (InputStreamSource is = new InputStreamSource(new FileInputStream("/Users/dernasherbrezon/Downloads/RRCF.bin"))) {
			while (true) {
				float expected = is.readFloat();
				float actual = source.readFloat();
				assertEquals(expected, actual, 0.0001f);
			}
		} catch (EOFException e) {
			// do nothing
		}
	}

	@After
	public void stop() throws Exception {
		if (source != null) {
			source.close();
		}
	}
	
}
