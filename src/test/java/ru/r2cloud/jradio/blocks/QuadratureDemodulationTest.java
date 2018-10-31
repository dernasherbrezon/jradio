package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;

public class QuadratureDemodulationTest {
	
	private QuadratureDemodulation source;

	@Test
	public void testSucces() throws Exception {
		source = new QuadratureDemodulation(new WavFileSource(new BufferedInputStream(QuadratureDemodulationTest.class.getClassLoader().getResourceAsStream("stereo.wav"))), 0.5f);
		try (InputStreamSource is = new InputStreamSource(QuadratureDemodulationTest.class.getClassLoader().getResourceAsStream("stereo-fm-demod.bin"))) {
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
