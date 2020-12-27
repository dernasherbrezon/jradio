package ru.r2cloud.jradio.demod;

import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.blocks.ChannelModel;
import ru.r2cloud.jradio.blocks.GfskModulator;

public class FskDemodulatorBer extends AbstractBer {

	private static final float DEVIATION = 5000.0f;

	//  0.38064923882484436 0.3625205457210541 0.33519110083580017 0.2887226641178131 0.2657359540462494 0.23486773669719696 0.2004944533109665 0.1767110824584961 0.14827220141887665 0.1300617903470993 0.11054865270853043 0.09154733270406723 0.07188542932271957 0.05683835223317146 0.04373900219798088 0.032525379210710526 0.023324331268668175 0.01618512161076069 0.010719613172113895 0.0067618656903505325 0.004054066259413958 0.0022703756112605333 0.0011994006345048547 5.903231794945896E-4 2.724304795265198E-4 1.1571124196052551E-4 4.8952799261314794E-5 1.6208738088607788E-5 6.67509948470979E-6 1.9073486328125E-6 6.35782896551973E-7 0.0 0.0 0.0 0.0
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
