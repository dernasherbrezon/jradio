package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;
import java.io.FileInputStream;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;

public class ConstellationSoftDecoderTest {

	private ConstellationSoftDecoder source;

	@Test
	public void test() throws Exception {
		Constellation constel = new Constellation(new float[] { -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f }, new int[] { 0, 1, 3, 2 }, 4, 1);
		source = new ConstellationSoftDecoder(new InputStreamSource(new FileInputStream("/Users/dernasherbrezon/Downloads/clockmm.bin")), constel);
		try (InputStreamSource is = new InputStreamSource(new FileInputStream("/Users/dernasherbrezon/Downloads/constdec.bin"))) {
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
