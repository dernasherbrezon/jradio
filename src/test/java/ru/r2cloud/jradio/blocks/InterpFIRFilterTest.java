package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.InputStreamSource;

public class InterpFIRFilterTest {

	@Test
	public void test() throws Exception {
		Context ctx = new Context();
		ctx.setChannels(1);
		float[] taps = Firdes.gaussian(1.5, 2 * (32000f / 1200), 0.5, 12);
		try (InterpFIRFilter filter = new InterpFIRFilter(new InputStreamSource(InterpFIRFilterTest.class.getClassLoader().getResourceAsStream("polyphase.bin"), ctx), 2, taps)) {
			TestUtil.assertFloatInput("interpFirFilter.bin", filter);
		}
	}

}
