package ru.r2cloud.jradio.source;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import ru.r2cloud.jradio.FloatInput;

public class WavFileSource implements FloatInput {

	private final AudioInputStream ais;
	private final byte[] buf;
	private int currentBufIndex = 0;

	public WavFileSource(InputStream is) throws UnsupportedAudioFileException, IOException {
		ais = AudioSystem.getAudioInputStream(is);
		if (ais.getFormat().getSampleSizeInBits() != 16 && ais.getFormat().getSampleSizeInBits() != 8) {
			throw new UnsupportedAudioFileException("unsupported sample size in bits: " + ais.getFormat().getSampleSizeInBits());
		}
		buf = new byte[ais.getFormat().getFrameSize()];
	}

	@Override
	public float readFloat() throws IOException {
		if (currentBufIndex == 0 || currentBufIndex >= buf.length) {
			currentBufIndex = 0;
			int bytesRead = ais.read(buf, 0, buf.length);
			if (bytesRead == -1) {
				throw new EOFException();
			}
		}
		if (ais.getFormat().getSampleSizeInBits() == 16) {
			short s = (short) ((buf[currentBufIndex + 1] << 8) | (buf[currentBufIndex] & 0xff));
			currentBufIndex += 2;
			return ((float) s / Short.MAX_VALUE);
		} else {
			short s = (short)(buf[currentBufIndex] & 0xFF);
			currentBufIndex += 1;
			return ((float) s / 128) - 1;
		}
	}

	public AudioFormat getFormat() {
		return ais.getFormat();
	}

	public long getFrameLength() {
		return ais.getFrameLength();
	}

	@Override
	public void close() throws IOException {
		ais.close();
	}
}
