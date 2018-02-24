package ru.r2cloud.jradio;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;
import java.io.FileInputStream;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import ru.r2cloud.jradio.blocks.ClockRecoveryMMComplex;
import ru.r2cloud.jradio.source.InputStreamSource;

public class ClockRecoveryMMComplexTest {

	private ClockRecoveryMMComplex source;

	@Test
	//ignore since rounding error for d_mu accumulates
	@Ignore
	public void testLRPT() throws Exception {
		// float omega = (float) ((222222 * 1.0) / (72000 * 1.0));
		float omega = 3.08642f;
		source = new ClockRecoveryMMComplex(new InputStreamSource(ClockRecoveryMMComplexTest.class.getClassLoader().getResourceAsStream("costas.bin")), omega, (float) (0.001 * 0.001 / 4), 0.5f, 0.001f, 0.005f);
		try (InputStreamSource is = new InputStreamSource(new FileInputStream("clockmm.bin"))) {
			int index = 0;
			while (true) {
				float expected = is.readFloat();
				float actual = source.readFloat();
				assertEquals("failure at index: " + index, expected, actual, 0.0001f);
				index++;
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
