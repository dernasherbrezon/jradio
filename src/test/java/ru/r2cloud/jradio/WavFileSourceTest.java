package ru.r2cloud.jradio;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.WavFileSource;

public class WavFileSourceTest {

	private WavFileSource source;

	@Test
	public void test() throws Exception {
		source = new WavFileSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("aausat-4.wav"));
		try (InputStream is = WavFileSourceTest.class.getClassLoader().getResourceAsStream("WavFileSource.bin")) {
			Float expected = null;
			while ((expected = readFloat(is)) != null) {
				float actual = source.readFloat();
				assertEquals(expected, actual, 0.0);
			}
		}
	}

	static Float readFloat(InputStream is) throws IOException {
		Integer readInt = readInt(is);
		if( readInt == null ) {
			return null;
		}
		return Float.intBitsToFloat(readInt);
	}

	// little endian
	private static Integer readInt(InputStream is) throws IOException {
		int ch1 = is.read();
		int ch2 = is.read();
		int ch3 = is.read();
		int ch4 = is.read();
		if ((ch1 | ch2 | ch3 | ch4) < 0) {
			return null;
		}
		return ((ch4 << 24) + (ch3 << 16) + (ch2 << 8) + (ch1 << 0));
	}

	@After
	public void stop() throws Exception {
		if (source != null) {
			source.close();
		}
	}

}
