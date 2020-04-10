package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.InputStreamSource;

public class DcBlockerTest {

	@Test
	public void test() throws Exception {
		Context context = new Context();
		context.setChannels(1);
		try (DcBlocker source = new DcBlocker(new InputStreamSource(DcBlockerTest.class.getClassLoader().getResourceAsStream("lmsdd.bin"), context), 32, true)) {
			TestUtil.assertFloatInput("dcBlocker.bin", source);
		}
	}

}
