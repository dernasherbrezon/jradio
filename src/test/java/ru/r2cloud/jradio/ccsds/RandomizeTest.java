package ru.r2cloud.jradio.ccsds;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ViterbiTest;
import ru.r2cloud.jradio.fec.ccsds.Randomize;

public class RandomizeTest {

	@Test
	public void shuffle() {
		byte[] data = ViterbiTest.hexStringToByteArray("eec1d49d7082582c93ada7b746ce5a97");
		byte[] shuffled = Randomize.shuffle(data);
		byte[] result = Randomize.shuffle(shuffled);
		assertArrayEquals(data, result);
	}
	
}
