package ru.r2cloud.jradio.demod;

import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.blocks.ChannelModel;
import ru.r2cloud.jradio.blocks.GfskModulator;

public class FskDemodulatorBer extends AbstractBer {

	private static final float DEVIATION = 5000.0f;

	// 0.48585572838783264 0.48625311255455017 0.48231029510498047 0.47989621758461 0.47854170203208923 0.4721097946166992 0.46826744079589844 0.46334075927734375 0.4488007128238678 0.4430440366268158 0.4256187975406647 0.40941014885902405 0.3821808397769928 0.3423970639705658 0.3152243196964264 0.25252532958984375 0.18418629467487335 0.1326160430908203 0.09795093536376953 0.06297620385885239 0.039845146238803864 0.02709484100341797 0.015929220244288445 0.010452903807163239 0.006642020773142576
	// 0.0040594711899757385 0.002386088715866208 0.0013421314070001245 7.047622348181903E-4 3.665263357106596E-4 1.6021479677874595E-4 6.325791036942974E-5 2.1614754587062635E-5 7.310261480597546E-6 0.0
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
		return new FskDemodulator(next, (int) (sampleRate / sps), DEVIATION, 0.175f, 1, 2000);
	}

}
