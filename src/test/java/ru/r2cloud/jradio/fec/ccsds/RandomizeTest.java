package ru.r2cloud.jradio.fec.ccsds;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import ru.r2cloud.jradio.fec.ViterbiTest;
import ru.r2cloud.jradio.fec.ccsds.Randomize;

public class RandomizeTest {

	@Test
	public void shuffle() {
		byte[] data = ViterbiTest.hexStringToByteArray("eec1d49d7082582c93ada7b746ce5a97");
		byte[] result = new byte[data.length];
		System.arraycopy(data, 0, result, 0, data.length);
		Randomize.shuffle(result);
		Randomize.shuffle(result);
		assertArrayEquals(data, result);
	}

}
