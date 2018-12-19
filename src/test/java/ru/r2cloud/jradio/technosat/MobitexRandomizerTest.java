package ru.r2cloud.jradio.technosat;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import ru.r2cloud.jradio.technosat.MobitexRandomizer;

public class MobitexRandomizerTest {

	//randomizer is stateful. make sure it handles full 511bit cycle. ~64 bytes
	@Test
	public void testSuccess() {
		MobitexRandomizer randomizer = new MobitexRandomizer();
		byte[] data = new byte[] { -17, -93, -18, 17, 112, 24, -116, -48, -21, -51, -82, -71, -19, -68, -124, -41, -56, 14, 122, -56, -42, -68, -53, -114, -49, 51, 37, -39, -113, 123 };
		byte[] result = new byte[] { 16, 32, 49, 6, 66, 17, -62, 1, 12, 0, 36, 40, 43, 105, 64, 19, -120, 47, 98, -122, -125, 58, 63, 82, 69, 38, -126, 53, 29, -92 };
		randomizer.shuffle(data);
		assertArrayEquals(result, data);
		data = new byte[] { -111, 83, 50, 124, -40, 40, -7, -110, 103, -32, 65, -41, -72, -20, 75, -59, 107, -3, 21, 9, 103, -38, 3, 72, 18, 85, -71, 5, -109, 104 };
		result = new byte[] { 2, 0, 2, 100, 18, 28, 70, 48, -96, -71, 38, 88, 2, -31, 38, 29, 70, -128, 65, 3, 48, 77, 115, 113, -64, 47, 83, 33, -96, -19 };
		randomizer.shuffle(data);
		assertArrayEquals(result, data);
		data = new byte[] { -17, -120, 25, -56, 126, 37, -44, 126, 71, -127, 31, 43, -69, -80, 20, -93, -39, -61, -117, -70, 34, -102, 20, -80, -19, 45, 116, -61, 17, -81 };
		result = new byte[] { 2, 18, 4, 41, -127, 34, 106, 80, 35, -109, -126, -120, 116, 43, 1, -128, 84, 104, 2, 50, -94, -40, 36, 44, 70, 32, -99, 122, 5, -124 };
		randomizer.shuffle(data);
		assertArrayEquals(result, data);
	}
}
