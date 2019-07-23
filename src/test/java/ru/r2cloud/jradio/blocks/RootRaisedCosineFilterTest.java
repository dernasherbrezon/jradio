package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.InputStreamSource;

public class RootRaisedCosineFilterTest {

	@Test
	public void test() throws Exception {
		Context context = new Context();
		context.setSampleRate(222222f);
		try (FloatInput source = new RootRaisedCosineFilter(new InputStreamSource(RootRaisedCosineFilterTest.class.getClassLoader().getResourceAsStream("AGC.bin"), context), 1.0f, 72000f, 0.6f, 361)) {
			TestUtil.assertFloatInput("RRCF.bin", source);
		}
	}

}
