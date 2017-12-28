package ru.r2cloud.jradio;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.source.WavFileSourceTest;

public class BinarySlicerTest {
	
	private BinarySlicer source;

	@Test
	public void test() throws Exception {
		source = new BinarySlicer(new ClockRecoveryMM(new WavFileSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("aausat-4.wav")), 20.0f, (float) (0.25 * 0.175 * 0.175), 0.005f, 0.175f, 0.005f));
		try (InputStream is = BinarySlicerTest.class.getClassLoader().getResourceAsStream("BinarySlicer.bin")) {
			int expected = -1;
			while( (expected = is.read()) != -1 ) {
				byte actual = source.readByte();
				assertEquals(expected, actual);
			}
		}
	}
	
	@After
	public void stop() throws Exception {
		if (source != null) {
			source.close();
		}
	}
	
}
