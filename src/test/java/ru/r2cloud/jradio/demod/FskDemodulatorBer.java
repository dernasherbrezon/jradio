package ru.r2cloud.jradio.demod;

import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.ChannelModel;
import ru.r2cloud.jradio.blocks.GfskModulator;

public class FskDemodulatorBer extends AbstractBer {

	private static final float DEVIATION = 5000.0f;

	//  0.4899139404296875 0.4891815185546875 0.488189697265625 0.487762451171875 0.4826812744140625 0.486297607421875 0.4839019775390625 0.47705078125 0.46148681640625 0.4641876220703125 0.4676666259765625 0.462799072265625 0.449371337890625 0.44012451171875 0.4237060546875 0.4059295654296875 0.4190216064453125 0.4059295654296875 0.3870086669921875 0.36053466796875 0.327239990234375 0.330474853515625 0.2930908203125 0.2332611083984375 0.1401214897632599 0.113861083984375 0.06854248046875 0.0819091796875 0.083038330078125 0.0240478515625 0.004074066877365112 0.0032501220703125 0.0024871528148651123 0.0015106201171875 0.00103759765625
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
		GfskModulator mod = new GfskModulator(input, sps, (float) (2 * Math.PI * DEVIATION / sampleRate), 0.35f);
		ChannelModel channel = new ChannelModel(mod, (float) (Math.sqrt(sps) / Math.pow(10, (ebno / 20))), 0, 1.0f, null, 42);
		FskDemodulator demod = new FskDemodulator(channel, 24000, DEVIATION, 0.175f, 1, 2000);
		return demod;
	}

}
