package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;

public class DelayOneTest {

	private DelayOne source;

	@Test
	public void test() throws Exception {
		WavFileSource wav = new WavFileSource(DelayOneTest.class.getClassLoader().getResourceAsStream("stereo.wav"));
		source = new DelayOne(wav);
		try (InputStreamSource is = new InputStreamSource(DelayOneTest.class.getClassLoader().getResourceAsStream("delay1.bin"))) {
			int index = 0;
			while (true) {
				float expected = is.readFloat();
				float actual = source.readFloat();
				assertEquals("failed at index: " + index, expected, actual, 0.0001f);
				index++;
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
