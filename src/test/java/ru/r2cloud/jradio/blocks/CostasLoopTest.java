package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;

public class CostasLoopTest {

	private CostasLoop source;

	@Test
	public void test() throws Exception {
		source = new CostasLoop(new InputStreamSource(CostasLoopTest.class.getClassLoader().getResourceAsStream("RRCF.bin")), 0.015f, 4, false);
		try (InputStreamSource is = new InputStreamSource(CostasLoopTest.class.getClassLoader().getResourceAsStream("costas.bin"))) {
			int index = 0;
			while (true) {
				float expected = is.readFloat();
				float actual = source.readFloat();
				assertEquals("failed at index: " + index, expected, actual, 0.0001f);
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
