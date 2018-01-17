package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;
import java.io.FileInputStream;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;

public class AGCTest {

	private AGC source;

	@Test
	public void test() throws Exception {
		source = new AGC(new InputStreamSource(new FileInputStream("/Users/dernasherbrezon/Downloads/LowPassFilter.bin")), 1000e-4f, 0.5f, 1.0f, 4000.0f);
		try (InputStreamSource is = new InputStreamSource(new FileInputStream("/Users/dernasherbrezon/Downloads/AGC.bin"))) {
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
