package ru.r2cloud.jradio.sink;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import ru.r2cloud.jradio.FloatInput;

public class WavFileSink implements Closeable {

	private final int sampleSizeInBits;
	private final FloatInput source;

	public WavFileSink(FloatInput source) {
		this(source, source.getContext().getSampleSizeInBits());
	}

	// 16 bits are better while storing float output
	// 8 bits will produce dc offset in the result file due to rounding
	public WavFileSink(FloatInput source, int sampleSizeInBits) {
		if (sampleSizeInBits != 8 && sampleSizeInBits != 16) {
			throw new IllegalArgumentException("unsupported number of sample size in bits: " + sampleSizeInBits);
		}
		this.source = source;
		this.sampleSizeInBits = sampleSizeInBits;
	}

	public void process(OutputStream os) throws IOException {
		AudioFormat format = new AudioFormat(source.getContext().getSampleRate(), sampleSizeInBits, source.getContext().getChannels(), isSigned(), false);
		AudioInputStream audioInputStream;
		long length;
		if (source.getContext().getTotalSamples() != null) {
			length = source.getContext().getTotalSamples();
		} else {
			length = AudioSystem.NOT_SPECIFIED;
		}
		audioInputStream = new AudioInputStream(new FloatInputStream(source, sampleSizeInBits), format, length);
		AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, os);
		audioInputStream.close();
	}

	private boolean isSigned() {
		switch (sampleSizeInBits) {
		case 8:
			return false;
		case 16:
			return true;
		default:
			throw new IllegalArgumentException("unsupported sample size: " + sampleSizeInBits);
		}
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

}
