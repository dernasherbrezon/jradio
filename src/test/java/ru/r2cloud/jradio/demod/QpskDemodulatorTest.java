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

public class QpskDemodulatorTest {
	
	private static final float BITS_PER_SYMBOL = 2.0f;

	// the latest result is:
	//  0.0782460427747696 0.07589222443793703 0.3487594067509032 0.07095304275593256 0.06880453665062598 0.06640637478835894 0.06398226325214972 0.06192452573930342 0.05952706601628094 0.05736379318606546 0.0553727588547698 0.0532663266426454 0.051295234368136475 0.049403953674121405 0.047242970123022844 0.04534514440937962 0.04351872935217254 0.041640285298270914 0.03989267119264442 0.03812767827711241 0.03637785898821738 0.03468238124641556 0.033341525599833756 0.03172600495484696 0.03020216691607046 0.028508570355243124 0.027232605971205335 0.025876043646772165 0.024497664462046757 0.02338332623717945 0.02202201501787271 0.020772020601078037 0.019794904598654687 0.01858034823533175 0.017381229431828633 0.016464398625017248 0.015379585078172808 0.014586641812239738 0.013475218437330634 0.012744184372200179 0.011909309642050694 0.01109817640669911 0.010363036303630363 0.009545240881314572 0.008985635385640187 0.008277638972516559 0.007673841722013283 0.0070068307596268895 0.0064668236442676835 0.0060111979573132905 0.005552754166066369 0.005015959416055473 0.004661423940449033 0.004269741173833601 0.0038536050370050153 0.003476419588033271 0.003205211596001241 0.0029060749375053787 0.0026828457979089612 0.0024458539572996332 0.0022067698294031815 0.0020425359870622707 0.001789367076446405 0.0016502938303630046 0.001378105240402803 0.001234100817929593 0.001147011664287902 0.001022941105817352 8.998656707752858E-4 8.15774986236925E-4 7.036628504050816E-4 6.285770337169521E-4 5.244804240684467E-4 4.444382160810559E-4 3.9937381388980084E-4 3.7334721290800443E-4 3.062903632643746E-4 2.772617350779135E-4 2.262198857289285E-4 1.9719048600948515E-4 1.6416317819912992E-4 1.5914960062458712E-4 1.2512337164444142E-4 1.0410222838827728E-4 9.809908007087157E-5 7.207135063713076E-5 7.20706292166323E-5 4.604678353206858E-5 3.5034824615667976E-5 3.403389776217112E-5 3.2031583140977006E-5 2.5025325629537093E-5 2.702643185034964E-5 2.0020220422626852E-5 1.1010944879209935E-5 1.1010790574763269E-5 7.007007007007007E-6 9.008882758399782E-6 3.002990979015099E-6 8.007927848570084E-6 3.0029729432137815E-6

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
