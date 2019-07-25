package ru.r2cloud.jradio;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;
import java.io.IOException;

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

	public static void assertFloatInput(String expected, FloatInput actual) {
		assertFloatInput(0, expected, actual);
	}

	private TestUtil() {
		// do nothing
	}

}
