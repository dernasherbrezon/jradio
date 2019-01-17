package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;

public class RmsAgcTest {

	private RmsAgc source;

	@Test
	public void testSucces() throws Exception {
		WavFileSource wav = new WavFileSource(new BufferedInputStream(RmsAgcTest.class.getClassLoader().getResourceAsStream("stereo.wav")));
		source = new RmsAgc(wav, 1e-2f, 0.5f);
		try (InputStreamSource is = new InputStreamSource(RmsAgcTest.class.getClassLoader().getResourceAsStream("rmsagc.bin"))) {
			while (true) {
				float expected = is.readFloat();
				float actual = source.readFloat();
				assertEquals(expected, actual, 0.0001f);
			}
		} catch (EOFException e) {
			// do nothing
		}
	}
	
	@After
	public void stop() throws Exception {
		if (source != null) {
			source.close();
		}
	}
	
}
