package ru.r2cloud.jradio.blocks;

import java.io.BufferedInputStream;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.WavFileSource;

public class QuadratureDemodulationTest {
	
	@Test
	public void testSucces() throws Exception {
		try (FloatInput source = new QuadratureDemodulation(new WavFileSource(new BufferedInputStream(QuadratureDemodulationTest.class.getClassLoader().getResourceAsStream("stereo.wav"))), 0.5f)) {
			TestUtil.assertFloatInput("stereo-fm-demod.bin", source);
		}
	}
	
}
