package ru.r2cloud.jradio.sink;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.junit.Test;

import ru.r2cloud.jradio.source.WavFileSource;

public class WaterfallTest {

	@Test
	public void testSuccess() throws Exception {
		Waterfall waterfall = new Waterfall(new WavFileSource(new BufferedInputStream(new FileInputStream("/Users/dernasherbrezon/Downloads/1.wav"))), 1, 0.0, 50, 1024);
//		Waterfall waterfall = new Waterfall(new WavFileSource(WaterfallTest.class.getClassLoader().getResourceAsStream("aausat-4.wav")), 1, 0.0, 100, 1024);
		BufferedImage image = waterfall.save();
		ImageIO.write(image, "png", new FileOutputStream("test7.png"));
		waterfall.close();
	}

	public static void main(String[] args) throws Exception {
		
		AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("/Users/dernasherbrezon/Downloads/satnogs_43408_2017-12-10T17-50-11.wav")));
		System.out.println("estimated: " + ais.getFrameLength());
		ais.close();
		WavFileSource source = new WavFileSource(new BufferedInputStream(new FileInputStream("/Users/dernasherbrezon/Downloads/satnogs_43408_2017-12-10T17-50-11.wav")));
		int actual = 0;
		while( true ) {
			try {
				source.readFloat();
				actual++;
			} catch (Exception e) {
				System.out.println("actual: " + actual);
				break;
			}
		}
		source.close();
	}
}
