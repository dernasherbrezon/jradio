package ru.r2cloud.jradio.sink;

import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.IOException;

import org.jtransforms.fft.FloatFFT_1D;

import ru.r2cloud.jradio.FloatInput;

public class Spectogram {

	private final int numHertzPerPixel;
	private final int numRowsPerSecond;

	public Spectogram(int numHertzPerPixel) {
		this(numHertzPerPixel, 1);
	}

	public Spectogram(int numHertzPerPixel, int numRowsPerSecond) {
		if (numHertzPerPixel <= 0) {
			throw new IllegalArgumentException("numPixelsPer100Hz should be positive: " + numHertzPerPixel);
		}
		if (numRowsPerSecond <= 0) {
			throw new IllegalArgumentException("numRowsPerSecond should be positive: " + numRowsPerSecond);
		}
		this.numHertzPerPixel = numHertzPerPixel;
		this.numRowsPerSecond = numRowsPerSecond;
	}

	public BufferedImage process(FloatInput source) throws IOException {
		// 1 pixel = 1 fft bucket = x hz
		int width = (int) (source.getContext().getSampleRate() / numHertzPerPixel);
		// height == numRowsPerSecond pixels per second
		int height = (int) (source.getContext().getTotalSamples() / source.getContext().getSampleRate()) * numRowsPerSecond;
		if (height == 0) {
			throw new IllegalArgumentException("not enough data in source: " + source.getContext().getTotalSamples());
		}
		FloatFFT_1D fft = new FloatFFT_1D(width);

		float[] tempResults = new float[height * width];
		for (int i = 0; i < tempResults.length; i++) {
			tempResults[i] = Float.NEGATIVE_INFINITY;
		}
		float sum = 0.0f;
		int tempResultsSize = 0;

		int numberOfFftPerRow = (int) (source.getContext().getSampleRate() / (width * numRowsPerSecond));
		int skipOnEveryRow = (int) source.getContext().getSampleRate() % (width * numRowsPerSecond);

		float iNormalizationFactor = (float) 1 / width;

		float[] complexBuf = new float[width * 2];
		int currentRow = 0;
		// skip samples which were not fitted into height.
		while (currentRow < height) {
			try {
				for (int k = 0; k < numberOfFftPerRow; k++) {
					for (int i = 0; i < complexBuf.length; i += 2) {
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
						tempResults[currentRow * width + j] = Math.max(tempResults[currentRow * width + j], (float) (10.0 * Math.log10((real * real) + (img * img) + 1e-20)));
					}
				}

				int length = width / 2;
				for (int i = 0; i < length; i++) {
					// swap 2 halfes
					float temp = tempResults[currentRow * width + i];
					tempResults[currentRow * width + i] = tempResults[currentRow * width + length + i];
					tempResults[currentRow * width + length + i] = temp;

					sum += tempResults[currentRow * width + i] + tempResults[currentRow * width + length + i];
					tempResultsSize += 2;
				}

				currentRow++;

				// skip at the end of second
				if (currentRow % numRowsPerSecond == 0) {
					for (int i = 0; i < skipOnEveryRow; i++) {
						source.readFloat();
						if (source.getContext().getChannels() == 2) {
							source.readFloat();
						}
					}
				}
			} catch (EOFException e) {
				break;
			}
		}
		if( tempResultsSize == 0 ) {
			throw new IllegalArgumentException("tempResultsSize cannot be 0");
		}
		double mean = sum / tempResultsSize;
		double standardDeviation = 0.0;
		for (float curValue : tempResults) {
			standardDeviation += Math.pow(curValue - mean, 2);
		}
		standardDeviation = Math.sqrt(standardDeviation / tempResultsSize);
		double min = mean - 2 * standardDeviation;
		double max = mean + 6 * standardDeviation;

		SpectogramPalette palette = new SpectogramPalette((float) max, (float) min, 0x000000, 0x0000e7, 0x0094ff, 0x00ffb8, 0x2eff00, 0xffff00, 0xff8800, 0xff0000, 0xff007c);

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				image.setRGB(j, height - i - 1, palette.getRGB(tempResults[i * width + j]));
			}
		}
		return image;
	}

}
