package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.InputStreamSource;

public class FrequencyModulatorTest {

	@Test
	public void test() throws Exception {
		Context ctx = new Context();
		ctx.setChannels(1);
		try (FrequencyModulator filter = new FrequencyModulator(new InputStreamSource(FrequencyModulatorTest.class.getClassLoader().getResourceAsStream("polyphase.bin"), ctx), 1.2f)) {
			TestUtil.assertFloatInput("freqmod.bin", filter);
		}
	}
}
