package ru.r2cloud.jradio.sink;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.jtransforms.fft.FloatFFT_1D;
import org.junit.Test;

import ru.r2cloud.jradio.WavFileSourceTest;
import ru.r2cloud.jradio.source.WavFileSource;

public class WaterfallTest {

	@Test
	public void testSuccess() throws Exception {
		BufferedImage image = new BufferedImage(1024, 800, BufferedImage.TYPE_INT_RGB);
		Waterfall waterfall = new Waterfall(new WavFileSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("aausat-4.wav")), 1024, 1, 0.0, 44100, 10);
		waterfall.save(image);
		ImageIO.write(image, "png", new FileOutputStream("test.png"));
		waterfall.close();
	}

	public static void main(String[] args) throws Exception {

		FloatFFT_1D fft = new FloatFFT_1D(1024);
		float[] test = new float[1024 * 2];
		for (int i = 0; i < 1024 * 2; i+=2) {
			test[i] = (float)Math.random();
			test[i+1] = 0.0f;
		}
		fft.complexForward(test);
		System.out.println(Arrays.toString(test));

		double d = 10.0 * Math.log10((0 / 1024) * (0 / 1024) + 1e-20);
		System.out.println(d);
	}

	private static void volk_32fc_s32f_x2_power_spectral_density_32f_generic() {

	}
}
