package ru.r2cloud.jradio.util;

import java.io.EOFException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class RepeatedWavSource implements FloatInput {

	private AudioInputStream ais = null;
	private byte[] buf;
	private int frameSize;
	private int currentRepeats = 0;
	private final int numberOfRepeats;
	private final String classpathSource;
	private Context context = new Context();

	public RepeatedWavSource(String classpathSource, int numberOfRepeats) {
		this.classpathSource = classpathSource;
		this.numberOfRepeats = numberOfRepeats;
	}

	@Override
	public float readFloat() throws IOException {
		if (ais == null) {
			try {
				ais = AudioSystem.getAudioInputStream(RepeatedWavSource.class.getClassLoader().getResourceAsStream(classpathSource));
				frameSize = ais.getFormat().getFrameSize();
				buf = new byte[ais.getFormat().getFrameSize()];
				context = new Context();
				context.setTotalSamples(ais.getFrameLength());
				context.setSampleRate(ais.getFormat().getSampleRate());
				context.setChannels(ais.getFormat().getChannels());
				context.setSampleSizeInBits(ais.getFormat().getSampleSizeInBits());
			} catch (UnsupportedAudioFileException e) {
				throw new IOException(e);
			}
		}
		int bytesRead = ais.read(buf, 0, buf.length);
		if (bytesRead == -1) {
			currentRepeats++;
			if (currentRepeats < numberOfRepeats) {
				ais = null;
				return readFloat();
			} else {
				throw new EOFException();
			}
		}
		short s = (short) ((buf[1] << 8) | (buf[0] & 0xff));
		return ((float) s / Short.MAX_VALUE);
	}

	public int getFrameSize() {
		return frameSize;
	}

	@Override
	public void close() throws IOException {
		ais.close();
	}
	
	@Override
	public Context getContext() {
		return context;
	}

}
