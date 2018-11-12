package ru.r2cloud.jradio.detection;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jtransforms.fft.FloatFFT_1D;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.blocks.FIRFilter;
import ru.r2cloud.jradio.blocks.Firdes;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.util.CircularArray;

public class PeakDetection {

	private final int numHertzPerPixel;
	private final Float noiseFloorDb;
	private final Integer maxPeaks;
	private final float[] taps = Firdes.lowPass(1.0, 2000, 1000, 100, Window.WIN_HAMMING, 6.76);
	private final FIRFilter filter = new FIRFilter(taps);

	public PeakDetection(int numHertzPerPixel, Float noiseFloorDb, Integer maxPeaks) {
		if (numHertzPerPixel <= 0) {
			throw new IllegalArgumentException("numPixelsPer100Hz should be positive: " + numHertzPerPixel);
		}
		this.numHertzPerPixel = numHertzPerPixel;
		this.noiseFloorDb = noiseFloorDb;
		this.maxPeaks = maxPeaks;
	}

	public List<PeakInterval> process(FloatInput source) throws IOException {
		List<PeakInterval> result = new ArrayList<>();
		int width = (int) (source.getContext().getSampleRate() / numHertzPerPixel);
		FloatFFT_1D fft = new FloatFFT_1D(width);

		float iNormalizationFactor = (float) 1 / width;

		float[] complexBuf = new float[width * 2];
		float[] fftResult = new float[width];
		for (int i = 0; i < fftResult.length; i++) {
			fftResult[i] = Float.NEGATIVE_INFINITY;
		}
		long currentSample = 0;
		float[] temp = new float[fftResult.length - taps.length / 2];
		CircularArray arr = new CircularArray(taps.length);

		while (true) {
			try {
				PeakInterval curInterval = new PeakInterval();
				for (int i = 0; i < complexBuf.length; i += 2) {
					complexBuf[i] = source.readFloat();
					if (source.getContext().getChannels() == 2) {
						complexBuf[i + 1] = source.readFloat();
					} else {
						complexBuf[i + 1] = 0.0f;
					}
				}
				currentSample += width;
				curInterval.setSampleEnd(currentSample);

				fft.complexForward(complexBuf);
				for (int i = 0, j = 0; i < complexBuf.length; i += 2, j++) {
					float real = complexBuf[i] * iNormalizationFactor;
					float img = complexBuf[i + 1] * iNormalizationFactor;
					fftResult[j] = (float) (10.0 * Math.log10((real * real) + (img * img) + 1e-20));
				}

				int length = width / 2;
				int half = taps.length / 2;
				for (int i = 0; i < fftResult.length; i++) {
					// original algorithm swapped 2 halfs of result using array
					// copy and third array.
					// replaced it with juggling with index below
					int index;
					if (i < length) {
						index = length + i;
					} else {
						index = i - length;
					}
					arr.add(fftResult[index]);
					if (i >= half) {
						temp[i - half] = filter.filter(arr);
					}
					fftResult[index] = Float.NEGATIVE_INFINITY;
				}
				temp[0] = temp[1];

				List<Peak> peaksP = PeakDetectionUtil.getPersistentHomology(temp);
				List<Peak> filtered = new ArrayList<>();
				for (Peak cur : peaksP) {
					cur.setValue(temp[cur.getIndex()]);
					cur.setFrequency(numHertzPerPixel * (cur.getIndex() - length));
					boolean valueIsOk = noiseFloorDb == null || cur.getValue() > noiseFloorDb;
					boolean sizeIsOk = maxPeaks == null || filtered.size() < maxPeaks;
					if (valueIsOk && sizeIsOk) {
						filtered.add(cur);
					}
				}
				curInterval.setPeaks(filtered);
				result.add(curInterval);
			} catch (EOFException e) {
				break;
			}
		}
		return result;
	}

}
