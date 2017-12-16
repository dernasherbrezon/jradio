package ru.r2cloud.jradio.sink;

import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;

import org.jtransforms.fft.FloatFFT_1D;

import ru.r2cloud.jradio.source.WavFileSource;

public class Waterfall implements Closeable {

	private final int d_fftsize;
	private final int d_wintype;
	private final double d_center_freq;
	private final WavFileSource source;
	private final FloatFFT_1D fft;
	private final int numRowsPerSecond;
	private final WaterfallPalette palette = new WaterfallPalette(0.0f, -160.0f, 0x000000, 0x0000e7, 0x0094ff, 0x00ffb8, 0x2eff00, 0xffff00, 0xff8800, 0xff0000, 0xff007c);

	public Waterfall(WavFileSource source, int d_wintype, double d_center_freq, int numRowsPerSecond, int width) {
		this.source = source;
		this.d_fftsize = width;
		this.d_wintype = d_wintype;
		this.d_center_freq = d_center_freq;
		this.fft = new FloatFFT_1D(d_fftsize);
		this.numRowsPerSecond = numRowsPerSecond;
	}

	public BufferedImage save() throws IOException {
		int height = (int) (source.getFrameLength() / (source.getFormat().getSampleRate() / numRowsPerSecond));
		BufferedImage image = new BufferedImage(d_fftsize, height, BufferedImage.TYPE_INT_RGB);

		int maxPossibleNumberOfBlocksPerRow = (int) (source.getFormat().getSampleRate() / numRowsPerSecond);
		int skipOnEveryRow = maxPossibleNumberOfBlocksPerRow - d_fftsize;
		//FIXME skipOnEveryRow could be negative!!

		float iNormalizationFactor = (float) 1 / d_fftsize;

		float[] previousBuf = null;
		int currentRow = 0;
		// skip samples which were not fitted into height.
		while (currentRow < height) {
			try {
				float[] newBuf = new float[d_fftsize * 2];
				for (int i = 0; i < newBuf.length; i += 2) {
					newBuf[i] = source.readFloat();
					newBuf[i + 1] = 0.0f;
				}
				// apply windowing function to the previous data
				previousBuf = newBuf;
				fft.complexForward(previousBuf);
				float[] result = new float[d_fftsize];
				for (int i = 0, j = 0; i < previousBuf.length; i += 2, j++) {
					float real = previousBuf[i] * iNormalizationFactor;
					float img = previousBuf[i + 1] * iNormalizationFactor;
					result[j] = (float) (10.0 * Math.log10((real * real) + (img * img) + 1e-20));
				}

				int length = d_fftsize / 2;
				float[] tmp = new float[length];
				System.arraycopy(result, 0, tmp, 0, length);
				System.arraycopy(result, length, result, 0, result.length - length);
				System.arraycopy(tmp, 0, result, result.length - length, length);

				System.out.println(currentRow);
				for (int i = 0; i < result.length; i++) {
					image.setRGB(i, currentRow, palette.getRGB(result[i]));
					// graphic.setColor(curColor);
					// graphic.
					// graphic.fillRect(i * blockWidth, currentRow *
					// blockHeight, blockWidth, blockHeight);
				}

				currentRow++;

				for (int i = 0; i < skipOnEveryRow; i++) {
					source.readFloat();
				}
			} catch (EOFException e) {
				break;
			}
		}
		return image;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

}
