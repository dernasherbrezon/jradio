package ru.r2cloud.jradio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import ru.r2cloud.jradio.source.InputStreamSource;

public class TestUtil {

	public static void assertByteInput(String expected, ByteInput actual) {
		try (InputStreamSource is = new InputStreamSource(TestUtil.class.getClassLoader().getResourceAsStream(expected))) {
			int index = 0;
			while (true) {
				byte expectedValue = is.readByte();
				byte actualValue = actual.readByte();
				assertEquals("failure at index: " + index, expectedValue, actualValue);
				index++;
			}
		} catch (EOFException e) {
			// do nothing
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void assertFloatInput(int skip, String expected, FloatInput actual) {
		try (InputStreamSource is = new InputStreamSource(TestUtil.class.getClassLoader().getResourceAsStream(expected))) {
			for (int i = 0; i < skip; i++) {
				is.readFloat();
			}
			int index = 0;
			while (true) {
				float expectedValue = is.readFloat();
				float actualValue = actual.readFloat();
				assertEquals("failure at index: " + index, expectedValue, actualValue, 0.0001f);
				index++;
			}
		} catch (EOFException e) {
			// do nothing
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void assertTail(float[] tail, FloatInput actual) {
		for (int i = 0; i < tail.length; i++) {
			float expectedValue = tail[i];
			float actualValue;
			try {
				actualValue = actual.readFloat();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			assertEquals("failure at index: " + i, expectedValue, actualValue, 0.0001f);
		}
		try {
			actual.readFloat();
			fail("EOF expected");
		} catch (EOFException e) {
			// ignore
		} catch (IOException e) {
			fail("failing: " + e.getMessage());
		}
	}

	public static void assertFloatInput(String expected, FloatInput actual) {
		assertFloatInput(0, expected, actual);
	}

	public static void assertImage(String expectedName, BufferedImage actual) throws IOException {
		assertNotNull(actual);
		try (InputStream is1 = TestUtil.class.getClassLoader().getResourceAsStream(expectedName)) {
			BufferedImage expected = ImageIO.read(is1);
			for (int i = 0; i < expected.getWidth(); i++) {
				for (int j = 0; j < expected.getHeight(); j++) {
					assertEquals("failure in image: " + expectedName, expected.getRGB(i, j), actual.getRGB(i, j));
				}
			}
		}
	}

	private TestUtil() {
		// do nothing
	}

}
