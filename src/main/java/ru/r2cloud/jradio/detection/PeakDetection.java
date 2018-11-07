package ru.r2cloud.jradio.detection;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jtransforms.fft.FloatFFT_1D;

import ru.r2cloud.jradio.FloatInput;

public class PeakDetection {

	private final int numHertzPerPixel;

	public PeakDetection(int numHertzPerPixel) {
		if (numHertzPerPixel <= 0) {
			throw new IllegalArgumentException("numPixelsPer100Hz should be positive: " + numHertzPerPixel);
		}
		this.numHertzPerPixel = numHertzPerPixel;
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
					// FIXME detect peaks
					// image.setRGB(i, height - currentRow - 1, palette.getRGB(fftResult[index]));
					fftResult[index] = Float.NEGATIVE_INFINITY;
				}

				result.add(curInterval);
			} catch (EOFException e) {
				break;
			}
		}
		return result;
	}

}
