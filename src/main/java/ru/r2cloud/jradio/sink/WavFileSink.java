package ru.r2cloud.jradio.sink;

import java.io.IOException;
import java.io.OutputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import ru.r2cloud.jradio.FloatInput;

public class WavFileSink {

	public static void process(FloatInput source, OutputStream os) throws IOException {
		AudioFormat format = new AudioFormat(source.getContext().getSampleRate(), source.getContext().getSampleSizeInBits(), source.getContext().getChannels(), false, false);
		AudioInputStream audioInputStream;
		long length;
		if (source.getContext().getTotalSamples() != null) {
			length = source.getContext().getTotalSamples();
		} else {
			length = AudioSystem.NOT_SPECIFIED;
		}
		audioInputStream = new AudioInputStream(new FloatInputStream(source), format, length);
		AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, os);
		audioInputStream.close();
	}

}
