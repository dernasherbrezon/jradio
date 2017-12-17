package ru.r2cloud.jradio.sink;

import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.IOException;

import org.jtransforms.fft.FloatFFT_1D;

import ru.r2cloud.jradio.source.WavFileSource;

public class Waterfall {

	private final int d_fftsize;
	private final FloatFFT_1D fft;
	private final int numRowsPerSecond;
	private final WaterfallPalette palette = new WaterfallPalette(0.0f, -160.0f, 0x000000, 0x0000e7, 0x0094ff, 0x00ffb8, 0x2eff00, 0xffff00, 0xff8800, 0xff0000, 0xff007c);

	public Waterfall(int numRowsPerSecond, int width) {
		this.d_fftsize = width;
		this.fft = new FloatFFT_1D(d_fftsize);
		this.numRowsPerSecond = numRowsPerSecond;
	}

	public BufferedImage process(WavFileSource source) throws IOException {
		int maxPossibleNumberOfBlocksPerRow = (int) (source.getFormat().getSampleRate() / numRowsPerSecond);
		// requested number of rows might be more than available in fft. adjust
		// height
		if (maxPossibleNumberOfBlocksPerRow < d_fftsize) {
			maxPossibleNumberOfBlocksPerRow = d_fftsize;
		}
		int height = (int) (source.getFrameLength() / maxPossibleNumberOfBlocksPerRow);
		BufferedImage image = new BufferedImage(d_fftsize, height, BufferedImage.TYPE_INT_RGB);

		int skipOnEveryRow = maxPossibleNumberOfBlocksPerRow - d_fftsize;

		float iNormalizationFactor = (float) 1 / d_fftsize;

		float[] previousBuf = null;
		float[] fftBuf = new float[d_fftsize * 2];
		float[] fftResult = new float[d_fftsize];
		int currentRow = 0;
		// skip samples which were not fitted into height.
		while (currentRow < height) {
			try {
				for (int i = 0; i < fftBuf.length; i += 2) {
					fftBuf[i] = source.readFloat();
					if (source.getFormat().getChannels() == 2) {
						fftBuf[i + 1] = source.readFloat();
					} else {
						fftBuf[i + 1] = 0.0f;
					}
				}
				// TODO apply windowing function to the previous data
				previousBuf = fftBuf;
				fft.complexForward(previousBuf);
				for (int i = 0, j = 0; i < previousBuf.length; i += 2, j++) {
					float real = previousBuf[i] * iNormalizationFactor;
					float img = previousBuf[i + 1] * iNormalizationFactor;
					fftResult[j] = (float) (10.0 * Math.log10((real * real) + (img * img) + 1e-20));
				}

				int length = d_fftsize / 2;
				float[] tmp = new float[length];
				System.arraycopy(fftResult, 0, tmp, 0, length);
				System.arraycopy(fftResult, length, fftResult, 0, fftResult.length - length);
				System.arraycopy(tmp, 0, fftResult, fftResult.length - length, length);

				for (int i = 0; i < fftResult.length; i++) {
					image.setRGB(i, height - currentRow - 1, palette.getRGB(fftResult[i]));
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
