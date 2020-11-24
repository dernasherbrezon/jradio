package ru.r2cloud.jradio.ao40;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.util.StreamUtils;

public class Ao40CorrelateAccessCodeTagTest {

	private Ao40CorrelateAccessCodeTag input;

	@Test
	public void testCorrelator() throws Exception {
		byte[] data;
		try (DataInputStream is = new DataInputStream(Ao40CorrelateAccessCodeTagTest.class.getClassLoader().getResourceAsStream("ao40.bin"))) {
			data = StreamUtils.toByteArray(is);
		}

		InputStreamSource is = new InputStreamSource(new ByteArrayInputStream(prepend(data)));
		input = new Ao40CorrelateAccessCodeTag(is, 8);
		byte[] result = input.readBytes();
		assertNotNull(result);
		assertArrayEquals(rotatePhase180deg(data), result);
	}

	private static byte[] rotatePhase180deg(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ 0xFF);
		}
		return data;
	}

	private static byte[] prepend(byte[] data) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Random r = new Random();
		for (int i = 0; i < 9; i++) {
			baos.write((byte) r.nextInt());
		}
		try {
			baos.write(data);
		} catch (IOException e) {
			// shouldnt happen
		}
		for (int i = 0; i < 10; i++) {
			baos.write((byte) r.nextInt());
		}
		return baos.toByteArray();
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
