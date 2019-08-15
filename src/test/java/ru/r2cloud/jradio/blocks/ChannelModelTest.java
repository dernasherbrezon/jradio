package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayFloatInput;
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

	@Test
	public void testNoOpChannel() throws Exception {
		ChannelModel model = new ChannelModel(new ArrayFloatInput(1.0f, 2.0f, 3.0f), 0.0f, 0.0f, 1.0f, null, 42);
		assertEquals(1.0, model.readFloat(), 0.0);
		assertEquals(2.0, model.readFloat(), 0.0);
		assertEquals(3.0, model.readFloat(), 0.0);
		model.close();
	}
}
