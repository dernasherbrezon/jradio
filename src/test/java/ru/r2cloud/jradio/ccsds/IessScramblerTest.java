package ru.r2cloud.jradio.ccsds;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import ru.r2cloud.jradio.fec.ViterbiTest;

public class IessScramblerTest {

	@Test
	public void shuffle() {
		byte[] data = ViterbiTest.hexStringToByteArray("eec1d49d7082582c93ada7b746ce5a97");
		IessScrambler.shuffle(data);
		assertArrayEquals(ViterbiTest.hexStringToByteArray("0ce61f1d3c243bdedba525848b931a56"), data);
	}

}
