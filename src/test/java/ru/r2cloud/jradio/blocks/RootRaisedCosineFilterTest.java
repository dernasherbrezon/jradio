package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.source.InputStreamSource;

public class RootRaisedCosineFilterTest {

	private RootRaisedCosineFilter source;

	@Test
	public void test() throws Exception {
		Context context = new Context();
		context.setSampleRate(222222f);
		source = new RootRaisedCosineFilter(new InputStreamSource(RootRaisedCosineFilterTest.class.getClassLoader().getResourceAsStream("AGC.bin"), context), 1.0f, 72000f, 0.6f, 361);
		try (InputStreamSource is = new InputStreamSource(RootRaisedCosineFilterTest.class.getClassLoader().getResourceAsStream("RRCF.bin"))) {
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
