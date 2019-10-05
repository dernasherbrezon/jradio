package ru.r2cloud.jradio.sink;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.source.WavFileSource;

public class OutputStreamSinkTest {

	@Test
	public void testSuccess() throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (OutputStreamSink sink = new OutputStreamSink(new WavFileSource(OutputStreamSinkTest.class.getClassLoader().getResourceAsStream("stereo.wav")))) {
			sink.process(baos);
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		try (InputStream is1 = new BufferedInputStream(OutputStreamSinkTest.class.getClassLoader().getResourceAsStream("stereo.bin"))) {
			while (true) {
				int expected = is1.read();
				int actual = bais.read();
				assertEquals(expected, actual);
				if (expected == -1) {
					break;
				}
			}
		}
	}

	@Test
	public void testSaveByte() throws Exception {
		byte[] expected = new byte[] { 1, 2, 3, 4 };
		ArrayByteInput input = new ArrayByteInput(expected);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (OutputStreamSink sink = new OutputStreamSink(input)) {
			sink.process(baos);
		}
		assertArrayEquals(expected, baos.toByteArray());
	}

}
