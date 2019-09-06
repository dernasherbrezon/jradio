package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;

public class ConvolutionalDeinterleaverTest {

	private static final Pattern SPACE = Pattern.compile("[ ]+");

	@Test
	public void testSuccess() throws Exception {
		ConvolutionalDeinterleaver block = new ConvolutionalDeinterleaver(new ArrayByteInput(loadData("ConvolutionalDeinterleaver.txt")), 2, 3);
		// initial sync
		for (int i = 0; i < 12; i++) {
			assertEquals(0, block.readByte());
		}
		for (int i = 1; i < 13; i++) {
			assertEquals(i, block.readByte());
		}
		block.close();
	}

	private static int[] loadData(String resource) throws IOException {
		try (BufferedReader r = new BufferedReader(new InputStreamReader(ConvolutionalDeinterleaverTest.class.getClassLoader().getResourceAsStream(resource)))) {
			String line = null;
			List<String[]> data = new ArrayList<>();
			int numberOfDigits = 0;
			while ((line = r.readLine()) != null) {
				String[] digits = SPACE.split(line.trim());
				if (numberOfDigits == 0) {
					numberOfDigits = digits.length;
				} else if (numberOfDigits != digits.length) {
					throw new IllegalArgumentException("invalid line: " + line);
				}
				data.add(digits);
			}
			int[] array = new int[numberOfDigits * data.size()];
			for (int i = 0; i < data.size(); i++) {
				String[] curData = data.get(i);
				for (int j = 0; j < curData.length; j++) {
					String curDigit = curData[curData.length - 1 - j];
					int digit;
					if (curDigit.trim().equals("_")) {
						digit = 0;
					} else {
						digit = Integer.valueOf(curDigit.trim());
					}
					array[j * data.size() + i] = digit;
				}
			}
			return array;
		}
	}

}
