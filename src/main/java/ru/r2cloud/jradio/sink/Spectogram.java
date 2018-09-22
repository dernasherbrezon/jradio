package ru.r2cloud.jradio.sink;

import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.IOException;

import org.jtransforms.fft.FloatFFT_1D;

import ru.r2cloud.jradio.source.WavFileSource;

public class Spectogram {

	private final int numHertzPerPixel;
	private final int numRowsPerSecond;
	private final SpectogramPalette palette = new SpectogramPalette(0.0f, -160.0f, 0x000000, 0x0000e7, 0x0094ff, 0x00ffb8, 0x2eff00, 0xffff00, 0xff8800, 0xff0000, 0xff007c);

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

	public BufferedImage process(WavFileSource source) throws IOException {
		// 1 pixel = 1 fft bucket = x hz
		int width = (int) (source.getFormat().getSampleRate() / numHertzPerPixel);
		// height == numRowsPerSecond pixels per second
		int height = (int) ((source.getFrameLength() / source.getFormat().getSampleRate())) * numRowsPerSecond;

		FloatFFT_1D fft = new FloatFFT_1D(width);

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		int skipOnEveryRow = (int) (source.getFormat().getSampleRate() - width * numRowsPerSecond) / numRowsPerSecond;
		if (skipOnEveryRow < 0) {
			skipOnEveryRow = 0;
		}

		float iNormalizationFactor = (float) 1 / width;

		float[] previousBuf = null;
		float[] complexBuf = new float[width * 2];
		float[] fftResult = new float[width];
		int currentRow = 0;
		// skip samples which were not fitted into height.
		while (currentRow < height) {
			try {
				for (int i = 0; i < complexBuf.length; i += 2) {
					complexBuf[i] = source.readFloat();
					if (source.getFormat().getChannels() == 2) {
						complexBuf[i + 1] = source.readFloat();
					} else {
						complexBuf[i + 1] = 0.0f;
					}
				}
				// TODO apply windowing function to the previous data
				previousBuf = complexBuf;
				fft.complexForward(previousBuf);
				for (int i = 0, j = 0; i < previousBuf.length; i += 2, j++) {
					float real = previousBuf[i] * iNormalizationFactor;
					float img = previousBuf[i + 1] * iNormalizationFactor;
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
					image.setRGB(i, height - currentRow - 1, palette.getRGB(fftResult[index]));
				}

				currentRow++;

				for (int i = 0; i < skipOnEveryRow; i++) {
					source.readFloat();
					if (source.getFormat().getChannels() == 2) {
						source.readFloat();
					}
				}
			} catch (EOFException e) {
				break;
			}
		}
		return image;
	}

}
