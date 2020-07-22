package ru.r2cloud.jradio.demod;

import java.io.EOFException;
import java.io.IOException;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.blocks.ChannelModel;
import ru.r2cloud.jradio.blocks.GfskModulator;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;

public class FskDemodulatorBer {

	//  0.48944981465208126 0.4864993181473812 0.48337440911478324 0.4784145417823782 0.4793552036199095 0.47113714401131085 0.4644955480627511 0.45951184059112105 0.4517337858448734 0.44376457759064614 0.4353985338960803 0.42658952533850303 0.41750613816168375 0.40687363658398645 0.38809723386420786 0.3786613624660494 0.3719948707074991 0.3525544570453329 0.34525733214833965 0.32533772519222803 0.3141310495273491 0.3180992002585023 0.2975108093910373 0.29361719326253677 0.29016224516598305 0.29667488182295665 0.27802128991859737 0.26868746024213175 0.2850562637629043 0.28468377449989896 0.27095705571041895 0.27501944581940135 0.26372793679339646 0.27093476635891633 0.2910631678888148
	public static void main(String[] args) throws Exception {
		StringBuilder b = new StringBuilder();
		for (float ebno = 0.0f; ebno < 17.01f; ebno += 0.5f) {
			double ber = calculateBer(ebno);
			b.append(" ").append(ber);
		}
		System.out.println(b.toString());
	}

	private static double calculateBer(float ebno) throws IOException {
		float sps = 5.0f;
		float deviation = 5000.0f;
		float sampleRate = 1200.0f;
		int[] data = new int[100000];
		for (int i = 0; i < data.length; i++) {
			if (i % 2 == 0) {
				data[i] = 1;
			} else {
				data[i] = 0;
			}
		}
		ArrayByteInput input = new ArrayByteInput(data);
		input.getContext().setSampleRate(sampleRate);
		UnpackedToPacked pack = new UnpackedToPacked(input, 1, Endianness.GR_MSB_FIRST);
		GfskModulator mod = new GfskModulator(pack, sps, (float) (2 * Math.PI * deviation / sampleRate / sps), 1.0f);
		ChannelModel channel = new ChannelModel(mod, EbN0_to_noise_voltage(ebno, sps), 0, 1.0f, null, 42);
		FskDemodulator demod = new FskDemodulator(channel, 1200, deviation, 0.175f, 1, 2000);
		SoftToHard s2h = new SoftToHard(demod);
		for (int i = 0; i < 1000; i++) {
			s2h.readByte();
		}
		int numberOfFailures = 0;
		int total = 0;
		int prev = 0;
		while (!Thread.interrupted()) {
			try {
				byte cur = s2h.readByte();
				if (cur == 0 && prev == 0) {
					numberOfFailures++;
				} else if (cur == 1 && prev == 1) {
					numberOfFailures++;
				}
				prev = cur;
				total++;
			} catch (EOFException e) {
				break;
			}
		}
		s2h.close();
		return ((double) numberOfFailures / total);
	}

	private static float EbN0_to_noise_voltage(float ebno, float sps) {
		return (float) (1.0 / Math.sqrt(1 / sps * Math.pow(10, (ebno / 10))));
	}
}
