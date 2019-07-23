package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.InputStreamSource;

public class ConstellationSoftDecoderTest {

	@Test
	public void test() throws Exception {
		Constellation constel = new Constellation(new float[] { -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f }, new int[] { 0, 1, 3, 2 }, 4, 1);
		try (FloatInput source = new ConstellationSoftDecoder(new InputStreamSource(ConstellationSoftDecoderTest.class.getClassLoader().getResourceAsStream("clockmm.bin")), constel)) {
			TestUtil.assertFloatInput("constdec.bin", source);
		}
	}

}
