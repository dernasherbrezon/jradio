package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.InputStreamSource;

public class ChannelModelTest {

	@Test
	public void test() throws Exception {
		Context context = new Context();
		context.setChannels(2);
		try (FloatInput source = new ChannelModel(new InputStreamSource(ChannelModelTest.class.getClassLoader().getResourceAsStream("constellation_modulator_diff.bin"), context), 1.1f, 2.3f, 1.4f, new float[] { 1.2f, 1.1f, 0.0f, 0.0f }, 42L)) {
			TestUtil.assertFloatInput("channel_model.bin", source);
		}
	}
}
