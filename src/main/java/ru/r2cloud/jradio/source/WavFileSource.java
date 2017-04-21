package ru.r2cloud.jradio.source;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import ru.r2cloud.jradio.FloatInput;

public class WavFileSource implements FloatInput {

	private AudioInputStream ais;
	private byte[] buf;

	public WavFileSource(InputStream is) throws UnsupportedAudioFileException, IOException {
		ais = AudioSystem.getAudioInputStream(is);
		if (ais.getFormat().getSampleSizeInBits() != 16) {
			throw new UnsupportedAudioFileException("unsupported sample size in bits: " + ais.getFormat().getSampleSizeInBits());
		}
		buf = new byte[ais.getFormat().getFrameSize()];
	}

	@Override
	public float readFloat() throws IOException {
		int bytesRead = ais.read(buf, 0, buf.length);
		if (bytesRead == -1) {
			throw new EOFException();
		}
		short s = (short) ((buf[1] << 8) | (buf[0] & 0xff));
		return ((float) s / Short.MAX_VALUE);
	}

	@Override
	public void close() throws IOException {
		ais.close();
	}
}
