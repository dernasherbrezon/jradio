package ru.r2cloud.jradio;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class RepeatedWavSource implements FloatInput {

	private AudioInputStream ais = null;
	private byte[] buf = new byte[2];
	
	private String classpathSource;

	public RepeatedWavSource(String classpathSource) {
		this.classpathSource = classpathSource;
	}
	
	@Override
	public float readFloat() throws IOException {
		if( ais == null ) {
			try {
				ais = AudioSystem.getAudioInputStream(RepeatedWavSource.class.getClassLoader().getResourceAsStream(classpathSource));
			} catch (UnsupportedAudioFileException e) {
				throw new IOException(e);
			}
		}
		int bytesRead = ais.read(buf, 0, buf.length);
		if (bytesRead == -1) {
			ais = null;
			return readFloat();
		}
		short s = (short) ((buf[1] << 8) | (buf[0] & 0xff));
		return ((float) s / Short.MAX_VALUE);
	}

	@Override
	public void close() throws IOException {
		ais.close();
	}

}
