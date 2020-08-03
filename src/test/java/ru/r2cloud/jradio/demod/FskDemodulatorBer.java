package ru.r2cloud.jradio.demod;

import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.blocks.ChannelModel;
import ru.r2cloud.jradio.blocks.GfskModulator;

public class FskDemodulatorBer extends AbstractBer {

	private static final float DEVIATION = 5000.0f;

	//  0.464642196893692 0.45515409111976624 0.448173850774765 0.4315589368343353 0.42116960883140564 0.4035743176937103 0.36749520897865295 0.3469298779964447 0.30818018317222595 0.2598072588443756 0.23442141711711884 0.1941588670015335 0.17280197143554688 0.1468486785888672 0.12578964233398438 0.10595575720071793 0.08748722076416016 0.07074260711669922 0.055741310119628906 0.0427093505859375 0.031928058713674545 0.022994359955191612 0.015883760526776314 0.010438595898449421 0.006632483098655939 0.004058197606354952 0.0023822735529392958 0.0013411790132522583 7.019005715847015E-4 3.6303140223026276E-4 1.605314464541152E-4 6.325667345663533E-5 2.1614134311676025E-5 7.627531886100769E-6 0.0
	public static void main(String[] args) throws Exception {
		StringBuilder b = new StringBuilder();
		FskDemodulatorBer fskBer = new FskDemodulatorBer();
		for (float ebno = 0.0f; ebno < 17.01f; ebno += 0.5f) {
			b.append(" ").append(fskBer.calculateBer(ebno));
		}
		System.out.println(b.toString());
		ConcurrencyUtils.shutdownThreadPoolAndAwaitTermination();
	}

	@Override
	public ByteInput createModulatorDemodulator(ByteInput input, float ebno) {
		FloatInput next = new GfskModulator(input, sps, (float) (2 * Math.PI * DEVIATION / sampleRate), 1.0f);
		next = new ChannelModel(next, (float) (Math.sqrt(sps) / Math.pow(10, (ebno / 20))), 0, 1.0f, null, 42);
		return new FskDemodulator(next, (int) (sampleRate / sps), DEVIATION, 1, 2000);
	}

}
