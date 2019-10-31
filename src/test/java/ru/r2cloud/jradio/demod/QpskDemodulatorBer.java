package ru.r2cloud.jradio.demod;

import java.io.EOFException;
import java.io.IOException;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.blocks.ChannelModel;
import ru.r2cloud.jradio.blocks.Constellation;
import ru.r2cloud.jradio.blocks.ConstellationModulator;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;

public class QpskDemodulatorBer {
	
	private static final float BITS_PER_SYMBOL = 2.0f;

	// the latest result is:
	//  0.07728632110785935 0.07499309262292128 0.07218581881644678 0.07001477430152388 0.06780773796060362 0.06581294814215605 0.06314843574882321 0.06099325554866212 0.05885372986151004 0.056830783766579525 0.054865635790421856 0.052439881253840935 0.0507230280325953 0.04845511239912899 0.04659272203906214 0.04475175501984719 0.042829580320348726 0.04116848859410712 0.03927605196385262 0.03750567873660869 0.03583503990679406 0.03425610315060076 0.03252688548814355 0.031224289206020654 0.02955373105343976 0.02812511260855465 0.026705233553191276 0.025600046434547302 0.024356995730319537 0.023077077037007385 0.02167307230362719 0.020473057236482576 0.01956445149072973 0.018268967077225494 0.01718698389502237 0.016203967820261064 0.015228756600095273 0.014294207252994058 0.013451891472061302 0.01250963473107839 0.011718398065152931 0.010840300978239329 0.010177118698353724 0.009480912057333894 0.008824471492632928 0.008156912204838693 0.0074859919164499645 0.006985594401114652 0.006477412638512127 0.005942395695291324 0.005452255974759668 0.005031790264331644 0.004588441611479912 0.004177893797018822 0.0038928149586594865 0.00348647252663653 0.003152158462779705 0.002879787634430128 0.002659617063190179 0.0023752612487087706 0.0021080405832846196 0.001952847393634378 0.0017707814060945312 0.0015645739614051737 0.0014433683131608766 0.0012592794278987452 0.001105105105105105 9.819977096730279E-4 8.508167841127483E-4 8.087812827191067E-4 7.056929905166874E-4 5.895825075174272E-4 5.124991741956666E-4 4.5243886569972875E-4 4.053924198624268E-4 3.5033562152542137E-4 3.133064136725718E-4 2.4824675727673306E-4 2.2222177733377912E-4 2.0620207803647575E-4 1.5916011507576623E-4 1.3913858202769958E-4 1.4214214214214215E-4 1.0310392876019027E-4 8.208241074038335E-5 7.507537567717988E-5 6.006030054174391E-5 4.604576949487791E-5 4.5045045045045046E-5 4.004004004004004E-5 3.1030968906969155E-5 2.1020600188184422E-5 1.801809016252317E-5 1.701674447656494E-5 9.008954901171765E-6 8.007959912152679E-6 8.007975944040265E-6 4.004028052220534E-6 9.008846687447073E-6 5.004904806710577E-6 1.001001001001001E-6

	public static void main(String[] args) throws Exception {
		StringBuilder b = new StringBuilder();
		for (float ebno = 0.0f; ebno < 10.01f; ebno += 0.1f) {
			double ber = calculateBer(ebno);
			b.append(" ").append(ber);
		}
		System.out.println(b.toString());
	}

	private static double calculateBer(float ebno) throws IOException {
		float sps = 5.0f;
		float sampleRate = 1200.0f;
		int[] data = new int[1000000];
		for (int i = 0; i < data.length; i++) {
			data[i] = 1;
		}
		ArrayByteInput input = new ArrayByteInput(data);
		input.getContext().setSampleRate(sampleRate);
		UnpackedToPacked pack = new UnpackedToPacked(input, 1, Endianness.GR_MSB_FIRST);
		Constellation qpsk = new Constellation(new float[] { -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f }, new int[] { 0, 1, 3, 2 }, 4, 1);
		ConstellationModulator constel = new ConstellationModulator(pack, qpsk, sps, false, true, 0.35f);
		ChannelModel channel = new ChannelModel(constel, EbN0_to_noise_voltage(ebno, sps), 0, 1.0f, null, 42);
		QpskDemodulator qpskDemod = new QpskDemodulator(channel, (int) (sampleRate / BITS_PER_SYMBOL), qpsk);
		SoftToHard s2h = new SoftToHard(qpskDemod);
		for (int i = 0; i < 1000; i++) {
			s2h.readByte();
		}
		int numberOfZeroes = 0;
		int total = 0;
		while (!Thread.interrupted()) {
			try {
				if (s2h.readByte() == 0) {
					numberOfZeroes++;
				}
				total++;
			} catch (EOFException e) {
				break;
			}
		}
		s2h.close();
		return ((double) numberOfZeroes / total);
	}

	private static float EbN0_to_noise_voltage(float ebno, float sps) {
		return (float) (1.0 / Math.sqrt(BITS_PER_SYMBOL / sps * Math.pow(10, (ebno / 10))));
	}
}
