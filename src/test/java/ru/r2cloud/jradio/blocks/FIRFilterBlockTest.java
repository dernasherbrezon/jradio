package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.InputStreamSource;

public class FIRFilterBlockTest {

	@Test
	public void test() throws Exception {
		Context ctx = new Context();
		ctx.setChannels(2);
		int samplesPerSymbol = 5;
		int nfilts = 32;
		float[] taps = Firdes.rootRaisedCosine(nfilts, nfilts, (float) 1 / samplesPerSymbol, 0.35f, 11 * samplesPerSymbol * nfilts);
		try (FloatInput source = new FIRFilterBlock(new InputStreamSource(FIRFilterBlockTest.class.getClassLoader().getResourceAsStream("constellation_modulator_diff.bin"), ctx), taps)) {
			TestUtil.assertFloatInput("firfilter.bin", source);
		}
	}
}
