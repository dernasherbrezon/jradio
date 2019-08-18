package ru.r2cloud.jradio.demod;

import java.io.EOFException;
import java.io.IOException;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.blocks.ChannelModel;
import ru.r2cloud.jradio.blocks.Constellation;
import ru.r2cloud.jradio.blocks.ConstellationModulator;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.DifferentialDecoder;
import ru.r2cloud.jradio.blocks.DifferentialEncoder;
import ru.r2cloud.jradio.blocks.Scrambler;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;

public class BpskDemodulatorTest {

	// the latest result is:
	// 0.06132816084762024 0.06014281815093839 0.060715357830510824 0.05723764840450043 0.05604410454559105 0.05483138839228217 0.05354168418439461 0.05259880920444126 0.05113933063928709 0.05031290067873016 0.0485110595706302 0.04742406633853755 0.046253449565031995 0.04470381362789309 0.04344161538184337 0.042235190681394655 0.040915500336002 0.03965136574498108 0.03827395419170827 0.036980758874321076 0.03576471679516106 0.03452465083626458 0.03320817559320632 0.031922063864814956 0.030667983976072215 0.029506162458817396 0.02827596792141514 0.02698117960704234 0.025815634925481 0.02467455117842253 0.023493018727281583 0.022401433691756272 0.021383922285418373 0.020275153502581567 0.019246130653869948 0.01822311377509244 0.01721778114112094 0.01631470049759411 0.015403295472404098 0.014559791206000892 0.013683921467350412 0.012825068626479428 0.012039300397928875 0.011265186330025715 0.010585511412832944 0.009885816615899589 0.009207476357356187 0.008531805416111532 0.0078741523666334 0.007301250141390401 0.00676004605906999 0.006236192539191417 0.005722015294520793 0.005235198552162298 0.004818117724233497 0.004441076655785862 0.00405669496476668 0.0037223629730989537 0.0033780210415609367 0.0030733852048417413 0.0027987791877334195 0.002531513793196644 0.0022742583385301604 0.0020680535772021618 0.0018665201211469657 0.001677999586923148 0.0015131692437256863 0.0013476715712035384 0.0012118700536299211 0.001068060584160071 9.369303718592563E-4 8.405012844421177E-4 7.644257414279045E-4 6.59321372789847E-4 5.772398658534591E-4 4.894860596572397E-4 4.184154865781721E-4 3.5868951201876797E-4 3.116427946283993E-4 2.6259408926397246E-4 2.3423259296481407E-4 2.011997913928531E-4 1.75173947730096E-4 1.4614512210625152E-4 1.201192784434944E-4 1.0410337465102847E-4 8.708647687153343E-5 7.207156706609663E-5 5.605566327363071E-5 4.9048705364426876E-5 4.104075346819392E-5 3.4033795558990075E-5 2.4023855688698877E-5 2.1020873727611517E-5 1.7016897779495038E-5 1.4013915818407678E-5 1.0009939870291199E-5 5.004969935145599E-6 4.0039759481164794E-6 4.0039759481164794E-6 3.0029819610873596E-6
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
		int[] data = new int[1000000];
		for (int i = 0; i < data.length; i++) {
			data[i] = 1;
		}
		ArrayByteInput input = new ArrayByteInput(data);
		input.getContext().setSampleRate(1200.0f);
		Scrambler scrambler = new Scrambler(input, 0x21, 0x00, 16);
		DifferentialEncoder diffEncoder = new DifferentialEncoder(scrambler, 2);
		UnpackedToPacked pack = new UnpackedToPacked(diffEncoder, 1, Endianness.GR_MSB_FIRST);
		Constellation bpsk = new Constellation(new float[] { -1, 0, 1, 0 }, new int[0], 2, 1);
		ConstellationModulator constel = new ConstellationModulator(pack, bpsk, sps, false, true, 0.35f);
		ChannelModel channel = new ChannelModel(constel, EbN0_to_noise_voltage(ebno, sps), 0, 1.0f, null, 42);
		BpskDemodulator bpskDemod = new BpskDemodulator(channel, 1200, 1, 0, 2000.0f, false);
		SoftToHard s2h = new SoftToHard(bpskDemod);
		DifferentialDecoder diffDecoder = new DifferentialDecoder(s2h, 2);
		Descrambler descrambler = new Descrambler(diffDecoder, 0x21, 0x00, 16);
		for (int i = 0; i < 1000; i++) {
			descrambler.readByte();
		}
		int numberOfZeroes = 0;
		int total = 0;
		while (!Thread.interrupted()) {
			try {
				if (descrambler.readByte() == 0) {
					numberOfZeroes++;
				}
				total++;
			} catch (EOFException e) {
				break;
			}
		}
		descrambler.close();
		return ((double) numberOfZeroes / (total * 6));
	}

	private static float EbN0_to_noise_voltage(float ebno, float sps) {
		return (float) (1.0 / Math.sqrt(1 / sps * Math.pow(10, (ebno / 10))));
	}

}
