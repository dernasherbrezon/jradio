package ru.r2cloud.jradio.blocks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.jtransforms.fft.FloatFFT_1D;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.source.InputStreamSource;

public class GfskPreambleDetector implements FloatInput {

	private final int inputSamplesPerBit;
	private final float toneSnrDb;

	private final float[] window;
	private final float[] fftIn;
	private final float[] swappedFft;
	private final float[] normalized;
	private final FloatFFT_1D fft;
	private final int toneHalfband;
	private int maxFrequencyBin;
	private int strideSize;
	private final float[] gmskTaps;
	private final BufferedFloatInput bufferedInput;
	private final FrequencyXlatingFIRFilter xlating;
	private int current = 0;

	public GfskPreambleDetector(FloatInput input, int baud, float bt, int decimation, float frequencyLimit, float toneSnrDb) {
		this.inputSamplesPerBit = (int) (input.getContext().getSampleRate() / baud);
		int windowSize;
		if (inputSamplesPerBit < 8) { // 4
			windowSize = 256;
			strideSize = 100;
		} else if (inputSamplesPerBit < 16) { // 10
			windowSize = 512;
			strideSize = 200;
		} else if (inputSamplesPerBit < 32) { // 20
			windowSize = 1024;
			strideSize = 400;
		} else if (inputSamplesPerBit < 64) { // 40, 50
			windowSize = 2048;
			strideSize = 800;
		} else {
			// FIXME
			windowSize = 2048;
			strideSize = 800;
		}
		maxFrequencyBin = (int) Math.ceil(frequencyLimit / (input.getContext().getSampleRate() / windowSize));
		this.toneSnrDb = (float) (1.0f / Math.pow(10.0f, 0.1f * toneSnrDb));
		window = Window.WIN_GUASSIAN.build(windowSize, 0.4f);
		fftIn = new float[windowSize * 2];
		swappedFft = new float[windowSize * 2];
		fft = new FloatFFT_1D(windowSize);
		normalized = new float[windowSize];
		toneHalfband = (windowSize + inputSamplesPerBit) / (2 * inputSamplesPerBit) - 2;
		maxFrequencyBin = Math.min(maxFrequencyBin, windowSize / 2 - toneHalfband - 1);
		// 6.375f is copied from smogcli2
		int numberOfTaps = (int) (inputSamplesPerBit * 6.375f);
		// make un-even
		if (numberOfTaps % 2 == 0) {
			numberOfTaps++;
		}
		// 2 is for complex numbers
		bufferedInput = new BufferedFloatInput(input, 2 * strideSize, 2 * windowSize);
		gmskTaps = Firdes.gaussian(1.0, inputSamplesPerBit, bt, numberOfTaps);
		xlating = new FrequencyXlatingFIRFilter(bufferedInput, gmskTaps, decimation, 0.0f);
	}

	@Override
	public float readFloat() throws IOException {
		if (current >= 2 * strideSize) {
			checkPreamble();
			current = 0;
		}
		float result = xlating.readFloat();
		current++;
		return result;
	}

	private void checkPreamble() {

		for (int i = 0; i < window.length; i++) {
			fftIn[2 * i] = bufferedInput.getBuffer()[2 * i] * window[i];
			fftIn[2 * i + 1] = bufferedInput.getBuffer()[2 * i + 1] * window[i];
		}

		fft.complexForward(fftIn);

		int halfSpectrum = (window.length - window.length / 2) * 2;
		System.arraycopy(fftIn, window.length, swappedFft, 0, halfSpectrum);
		System.arraycopy(fftIn, 0, swappedFft, halfSpectrum, window.length);

		for (int i = 0; i < normalized.length; i++) {
			normalized[i] = swappedFft[2 * i] * swappedFft[2 * i] + swappedFft[2 * i + 1] * swappedFft[2 * i + 1];
		}

		// sum everything before the search interval
		double sum = 0.0;
		for (int i = window.length / 2 - maxFrequencyBin - toneHalfband; i < window.length / 2 - maxFrequencyBin + toneHalfband; i++) {
			sum += normalized[i];
		}

		float toneFreq = 0.0f;
		float maxToneSnrDb = -99.0f;
		boolean toneFound = false;

		// do the search within ±maxFrequencyBin
		for (int i = window.length / 2 - maxFrequencyBin; i < window.length / 2 + maxFrequencyBin; i++) {
			float a0 = normalized[i - toneHalfband];
			float a1 = normalized[i - 1] + normalized[i] + normalized[i + 1];
			float a2 = normalized[i + toneHalfband];

			sum += a2;

			if (a1 * toneSnrDb > (sum - a1)) {
				float currentToneFreq = (float) i / window.length - 0.5f;
				float currentToneSnrDb = (float) (10.0f * Math.log10(a1 / Math.max(sum - a1, 1e-40f)));
				if (currentToneSnrDb > maxToneSnrDb) {
					maxToneSnrDb = currentToneSnrDb;
					toneFreq = currentToneFreq;
				}
				toneFound = true;
			}

			sum -= a0;
		}

		// FIXME set new tone freq
		if (toneFound) {
//			System.out.println(getContext().getCurrentSample().getValue() + " " + toneFreq * 100_000 + " " + maxToneSnrDb);
//			System.out.println("\t\t" + toneFreq + " " + maxToneSnrDb);
		}

	}

	@Override
	public void close() throws IOException {
		xlating.close();
	}

	@Override
	public Context getContext() {
		return xlating.getContext();
	}

}
