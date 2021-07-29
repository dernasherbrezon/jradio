package ru.r2cloud.jradio.demod;

import java.io.IOException;

import org.jtransforms.fft.FloatFFT_1D;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.util.MathUtils;
import ru.r2cloud.jradio.util.MaximumLengthSequence;

public abstract class AbstractBer {

	private static int MLS_BITS = 16;
	private static final FloatFFT_1D FFT = new FloatFFT_1D((int) Math.pow(2, MLS_BITS));

	private final float[] fftData;
	private final int[] inputData;
	protected final float sampleRate = 48000.0f;
	protected final float sps = 5.0f;
	private final float[] curComplex = new float[2];

	public AbstractBer() {
		byte[] mls = MaximumLengthSequence.generate(MLS_BITS);

		// make prn length exactly 2**MLS_BITS
		inputData = convertTo2pow16Array(mls);
		fftData = convertToComplex(inputData);

		FFT.complexForward(fftData);
		conjugate(fftData);
	}

	public double calculateBer(float ebno) throws IOException {
		ArrayByteInput input = new ArrayByteInput(true, inputData);
		input.getContext().setSampleRate(sampleRate / sps);
		ByteInput demod = createModulatorDemodulator(input, ebno);
		SoftToHard s2h = new SoftToHard(demod);
		int drop = 2;
		float sum = 0.0f;
		int total = 0;
		for (int i = 0; i < 50; i++) {
			float[] curBatch = new float[fftData.length];
			for (int j = 0; j < curBatch.length; j += 2) {
				// populate only Re part
				// shift from [0;1] interval to [-1;1] interval
				curBatch[j] = 2 * s2h.readByte() - 1.0f;
			}

			FFT.complexForward(curBatch);

			float[] multiplied = new float[curBatch.length];
			for (int j = 0; j < curBatch.length; j += 2) {
				MathUtils.multiply(curComplex, curBatch[j], curBatch[j + 1], fftData[j], fftData[j + 1]);
				multiplied[j] = curComplex[0];
				multiplied[j + 1] = curComplex[1];
			}

			for (int j = 0; j < multiplied.length; j++) {
				multiplied[j] = (float) (multiplied[j] * Math.pow((1.0 / inputData.length), 2));
			}

			FFT.complexInverse(multiplied, false);

			float maxMagnitude = 1e-12f;
			for (int j = 0; j < multiplied.length / 2; j++) {
				float magnitude = (float) Math.sqrt(multiplied[2 * j] * multiplied[2 * j] + multiplied[2 * j + 1] * multiplied[2 * j + 1]);
				maxMagnitude = Float.max(maxMagnitude, magnitude);
			}

			float result = maxMagnitude * -0.5f + 0.5f;
			if (i >= drop) {
				sum += result;
				total++;
			}
		}
		s2h.close();
		return sum / total;
	}

	public abstract ByteInput createModulatorDemodulator(ByteInput input, float ebno);

	private static void conjugate(float[] input) {
		for (int i = 1; i < input.length; i += 2) {
			input[i] = -input[i];
		}
	}

	private static float[] convertToComplex(int[] input) {
		float[] output = new float[2 * (input.length + 1)];
		for (int i = 0; i < input.length; i++) {
			// populate only Re part
			// shift from [0;1] interval to [-1;1] interval
			output[2 * i] = 2 * input[i] - 1;
		}
		output[2 * (input.length)] = 1;
		return output;
	}

	private static int[] convertTo2pow16Array(byte[] input) {
		int[] result = new int[input.length + 1];
		for (int i = 0; i < input.length; i++) {
			result[i] = input[i];
		}
		result[result.length - 1] = 1;
		return result;
	}
}
