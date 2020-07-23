package ru.r2cloud.jradio.demod;

import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.ChannelModel;
import ru.r2cloud.jradio.blocks.GfskModulator;

public class FskDemodulatorBer extends AbstractBer {

	private static final float DEVIATION = 5000.0f;

	// 0.48944981465208126 0.4864993181473812 0.48337440911478324 0.4784145417823782 0.4793552036199095 0.47113714401131085 0.4644955480627511 0.45951184059112105 0.4517337858448734 0.44376457759064614 0.4353985338960803 0.42658952533850303 0.41750613816168375 0.40687363658398645 0.38809723386420786 0.3786613624660494 0.3719948707074991 0.3525544570453329 0.34525733214833965 0.32533772519222803 0.3141310495273491 0.3180992002585023 0.2975108093910373 0.29361719326253677 0.29016224516598305
	// 0.29667488182295665 0.27802128991859737 0.26868746024213175 0.2850562637629043 0.28468377449989896 0.27095705571041895 0.27501944581940135 0.26372793679339646 0.27093476635891633 0.2910631678888148
	public static void main(String[] args) throws Exception {
		StringBuilder b = new StringBuilder();
		FskDemodulatorBer fskBer = new FskDemodulatorBer();
		int drop = 0;
		float sum = 0.0f;
		int total = 0;
		for (float ebno = 0.0f; ebno < 17.01f; ebno += 0.5f) {
			for (int i = 0; i < 10; i++) {
				double curBer = fskBer.calculateBer(ebno);
				if (i >= drop) {
					sum += curBer;
					total++;
				}
			}
			double ber = sum / total;
			b.append(" ").append(ber);
		}
		System.out.println(b.toString());
		ConcurrencyUtils.shutdownThreadPoolAndAwaitTermination();
	}

	@Override
	public ByteInput createModulatorDemodulator(ByteInput input, float ebno) {
		GfskModulator mod = new GfskModulator(input, sps, (float) (2 * Math.PI * DEVIATION / sampleRate), 0.35f);
		ChannelModel channel = new ChannelModel(mod, (float) (Math.sqrt(sps) / Math.pow(10, (ebno / 20))), 0, 1.0f, null, 42);
		FskDemodulator demod = new FskDemodulator(channel, 24000, DEVIATION, 0.175f, 1, 2000);
		return demod;
	}

}
