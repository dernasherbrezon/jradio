package ru.r2cloud.jradio.source;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

public class WavFileSourceTest {

	private WavFileSource source;

	@Test
	public void testStereo() throws Exception {
		source = new WavFileSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("stereo.wav"));
		assertEquals(0.0012512588873505592, source.readFloat(), 0.0);
		assertEquals(-0.0001831110566854477, source.readFloat(), 0.0);
		assertEquals(-0.00009155552834272385, source.readFloat(), 0.0);
	}

	@Test
	public void test() throws Exception {
		source = new WavFileSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("aausat-4.wav"));
		try (InputStreamSource is = new InputStreamSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("WavFileSource.bin"))) {
			while (true) {
				float expected = is.readFloat();
				float actual = source.readFloat();
				assertEquals(expected, actual, 0.0);
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
