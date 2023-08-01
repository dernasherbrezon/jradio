package ru.r2cloud.jradio.sink;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.jtransforms.fft.FloatFFT_1D;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.RxMetadata;

public class SnrCalculator {

	public static void enrichSnr(FloatInput source, List<Beacon> beacons, long bandwidth, int decimation) throws IOException {

		int fftBinHz = (int) (bandwidth / 20);
		int numberOfBins = (int) (source.getContext().getSampleRate() / fftBinHz);
		int halfOfNumberOfBins = numberOfBins / 2;

		FloatFFT_1D fft = new FloatFFT_1D(numberOfBins);

		float[] complexBuf = new float[numberOfBins * 2];
		float[] row = new float[numberOfBins];
		float iNormalizationFactor = (float) 1 / numberOfBins;

		int numberOfBinsInTheSignal = (int) (bandwidth / fftBinHz);
		int half = numberOfBinsInTheSignal / 2;
		int signalStartBinIndex = numberOfBins / 2 - half;
		int signalEndBinIndex = signalStartBinIndex + numberOfBinsInTheSignal;
		int noiseStartBinIndex = signalStartBinIndex - half;
		int noiseEndBinIndex = signalEndBinIndex + half;

		long processed = 0;
		for (Beacon cur : beacons) {
			long begin = cur.getBeginSample() / decimation;
			long end = cur.getEndSample() / decimation;

			skipSamples(source, begin - processed);
			long beaconLengthInSamples = end - begin;
			// Can be non zero
			// Currently each fft bin accumulate max of all the power from duration of a
			// signal
			// Then signal power calculated as an average of all fft bins, i.e.:
			// avg (max)
			// With snrCalculations > 1 the formula will be: avg ( avg ( max ) )
			int snrCalculations = 1;
			int accumulateTimes = (int) ((float) beaconLengthInSamples / numberOfBins / snrCalculations);

			float sumSnr = 0.0f;
			int snrTotal = 0;

			for (int calcs = 0; calcs < snrCalculations; calcs++) {
				Arrays.fill(row, Float.NEGATIVE_INFINITY);
				for (int k = 0; k < accumulateTimes && 0 < beaconLengthInSamples; k++) {
					for (int i = 0; i < complexBuf.length && 0 < beaconLengthInSamples; i += 2, beaconLengthInSamples--) {
						complexBuf[i] = source.readFloat();
						if (source.getContext().getChannels() == 2) {
							complexBuf[i + 1] = source.readFloat();
						} else {
							complexBuf[i + 1] = 0.0f;
						}
					}
					fft.complexForward(complexBuf);
					for (int i = 0, j = 0; i < complexBuf.length; i += 2, j++) {
						float real = complexBuf[i] * iNormalizationFactor;
						float img = complexBuf[i + 1] * iNormalizationFactor;
						row[j] = Math.max(row[j], (float) ((real * real) + (img * img)));
					}
				}

				for (int i = 0; i < halfOfNumberOfBins; i++) {
					// swap 2 halfes
					float temp = row[i];
					row[i] = row[halfOfNumberOfBins + i];
					row[halfOfNumberOfBins + i] = temp;
				}

				// can happen when spurious signal happened outside of the signal bandwidth
				// while no signal present. I.e. Pnoise > Psignal
				float curDelta = calculateSnr(row, noiseStartBinIndex, noiseEndBinIndex, signalStartBinIndex, signalEndBinIndex);
				if (Float.isNaN(curDelta)) {
					continue;
				}
				sumSnr += curDelta;
				snrTotal++;
			}

			if (snrTotal > 0) {
				RxMetadata meta = new RxMetadata();
				meta.setSnr(sumSnr / snrTotal);
				cur.setRxMeta(meta);
			}

			// skip remaining
			skipSamples(source, beaconLengthInSamples);
			processed = end;
		}

		source.close();
	}

	// Algorithm assumes noise is half bandwidth to the left and to the right of the
	// signal
	private static float calculateSnr(float[] row, int noiseStart, int noiseEnd, int signalStart, int signalEnd) {
		float noise = 0.0f;
		float signal = 0.0f;
		for (int i = Math.max(0, noiseStart); i < Math.min(row.length, noiseEnd); i++) {
			if (i >= signalStart && i <= signalEnd) {
				signal += row[i];
			} else if (i >= noiseStart && i <= noiseEnd) {
				noise += row[i];
			}
		}
		if (noise == 0.0f) {
			return 0.0f;
		}
		return (float) (10 * Math.log10((signal - noise) / noise));
	}

	private static void skipSamples(FloatInput input, long samples) throws IOException {
		// I/Q signal
		for (int i = 0; i < samples * input.getContext().getChannels(); i++) {
			input.readFloat();
		}
	}

	private SnrCalculator() {
		// do nothing
	}

}
