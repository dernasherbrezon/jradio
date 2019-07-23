package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.InputStreamSource;

public class CostasLoopTest {

	@Test
	public void test() throws Exception {
		Context context = new Context();
		context.setChannels(2);
		try (FloatInput source = new CostasLoop(new InputStreamSource(CostasLoopTest.class.getClassLoader().getResourceAsStream("RRCF.bin"), context), 0.015f, 4, false)) {
			TestUtil.assertFloatInput("costas.bin", source);
		}
	}

}
