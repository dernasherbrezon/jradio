package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.InputStreamSource;

public class RmsAgcTest {

	@Test
	public void test() throws Exception {
		Context ctx = new Context();
		ctx.setChannels(1);
		try (FloatInput source = new RmsAgc(new InputStreamSource(RmsAgcTest.class.getClassLoader().getResourceAsStream("polyphase.bin"), ctx), 2e-2f / 2, 1.0f)) {
			TestUtil.assertFloatInput("rmsf.bin", source);
		}
	}

}
