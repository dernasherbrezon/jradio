package ru.r2cloud.jradio;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import ru.r2cloud.jradio.blocks.AccessCode;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class PhaseAmbiguityResolverTest {
	
	@Test
	public void testMarkersRotation() {
		PhaseAmbiguityResolver resolver = new PhaseAmbiguityResolver(Long.parseLong("0000001101011101010010011100001001001111111100100110100001101011", 2));
		Set<String> result = resolver.getSynchronizationMarkers();
		assertTrue(result.contains("1010100111110111111000110110100011100101010110001100001011000001"));
		assertTrue(result.contains("0000001110101110100001101100000110001111111100011001010010010111"));
		assertTrue(result.contains("0101011000001000000111001001011100011010101001110011110100111110"));
		assertTrue(result.contains("0101011011111011110100111001010011011010101001001100000111000010"));
		assertTrue(result.contains("0000001101011101010010011100001001001111111100100110100001101011"));
		assertTrue(result.contains("1010100100000100001011000110101100100101010110110011111000111101"));
		assertTrue(result.contains("1111110001010001011110010011111001110000000011100110101101101000"));
		assertTrue(result.contains("1111110010100010101101100011110110110000000011011001011110010100"));
	}

	@Test
	public void test8Bit() {
		PhaseAmbiguityResolver resolver = new PhaseAmbiguityResolver(0b00_10_01_11, 8);
		Set<String> result = resolver.getSynchronizationMarkers();
		assertTrue(result.contains("11100100"));
		AccessCode ac = new AccessCode("11100100");
		assertEquals(0, ac.correlate(228L));
	}
	
	@Test
	public void testSoftRotation() {
		PhaseAmbiguityResolver resolver = new PhaseAmbiguityResolver(Long.parseLong("0000001101011101010010011100001001001111111100100110100001101011", 2));
		//14 180 187 114 139 216 39 29
		//1  0   0   1   0   0   1   1
		//0e b4  bb  72  8b  d8  27 1d
		//no changes
		byte[] rawData = ViterbiTest.hexStringToByteArray("0eb4 bb72 8bd8 271d");
		resolver.rotateSoft(rawData, Long.parseLong("0000001101011101010010011100001001001111111100100110100001101011", 2));
		assertArrayEquals(ViterbiTest.hexStringToByteArray("0eb4 bb72 8bd8 271d"), rawData);

		//1 1 0 0 1 0 0 1
		rawData = ViterbiTest.hexStringToByteArray("0eb4 bb72 8bd8 271d");
		resolver.rotateSoft(rawData, -6267835942693028291L);
		assertArrayEquals(ViterbiTest.hexStringToByteArray("4b0e 8dbb 278b e227"), rawData);

		//0 1 1 0 1 1 0 0 
		rawData = ViterbiTest.hexStringToByteArray("0eb4 bb72 8bd8 271d");
		resolver.rotateSoft(rawData, -242431053861644396L);
		assertArrayEquals(ViterbiTest.hexStringToByteArray("f14b 448d 7427 d8e2"), rawData);

		//0 0 1 1 0 1 1 0 
		rawData = ViterbiTest.hexStringToByteArray("0eb4 bb72 8bd8 271d");
		resolver.rotateSoft(rawData, 6267835942693028290L);
		assertArrayEquals(ViterbiTest.hexStringToByteArray("b4f1 7244 d874 1dd8"), rawData);

		//0 1 1 0 0 0 1 1 
		rawData = ViterbiTest.hexStringToByteArray("0eb4 bb72 8bd8 271d");
		resolver.rotateSoft(rawData, 265297593963222167L);
		assertArrayEquals(ViterbiTest.hexStringToByteArray("b40e 72bb d88b 1d27"), rawData);

		//1 1 0 0 0 1 1 0 
		rawData = ViterbiTest.hexStringToByteArray("0eb4 bb72 8bd8 271d");
		resolver.rotateSoft(rawData, 6199236322388294974L);
		assertArrayEquals(ViterbiTest.hexStringToByteArray("0e4b bb8d 8b27 27e2"), rawData);

		//1 0 0 1 1 1 0 0 
		rawData = ViterbiTest.hexStringToByteArray("0eb4 bb72 8bd8 271d");
		resolver.rotateSoft(rawData, -265297593963222168L);
		assertArrayEquals(ViterbiTest.hexStringToByteArray("4bf1 8d44 2774 e2d8"), rawData);

		//0 0 1 1 1 0 0 1 
		rawData = ViterbiTest.hexStringToByteArray("0eb4 bb72 8bd8 271d");
		resolver.rotateSoft(rawData, -6199236322388294975L);
		assertArrayEquals(ViterbiTest.hexStringToByteArray("f1b4 4472 74d8 d81d"), rawData);
	}
	
}
