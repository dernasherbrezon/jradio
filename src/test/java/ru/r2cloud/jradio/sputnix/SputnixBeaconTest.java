package ru.r2cloud.jradio.sputnix;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class SputnixBeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("A464829C8C4060A4A66660A6406300F01642020001004200070007000500000002000000E3FF00001E0000000000040004000400050000200000E81E55D00000F8E50465D3040C0BFF7B2118F40EF9E5046520D000001E00FC1E");
		SputnixBeacon result = new SputnixBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SputnixBeacon.json", result);
	}

	@Test
	public void testFileData() throws Exception {
		byte[] data = new byte[] { -92, 100, -126, -100, -116, 64, 96, -92, -90, 102, 96, -90, 64, 99, 0, -16, 36, 12, 15, 0, 1, 0, -64, 0, 1, 118, 1, 0, 0, 42, 115, 55, -54, 112, -83, -52, -49, -112, 101, 4, -115, -64, 9, -110, 96, -88, -37, -70, -63, 99, 59, 90, -51, 35, 44, 2, 70, -91, 48, 36,
				70, -45, 123, 114, -104, -23, -4, 24, -56, 111, -52, 120, -10, 84, 11, 30, -13, 123, 18, -70, -72, -8, -14, -53, -88, -53, 60, -15, -57, -34, 84, -110, -122, -54, -57, -31, -99, -36, -128, -118, 58, 35, 43, -74, -75, -53, -89, -6, 30, 107, 55, -90, 120, -4, -98, 60, -81, -116, -86,
				118, 93, 101, -96, -19, 38, 112, 60, -91, -91, 85, -106, -102, 104, 126, 118, 17, -18, -79, -53, -29, -14, 99, -36, 119, 126, -84, -11, -67, 22, 16, -68, 92, 41, 92, 78, 22, 61, 51, -78, -62, 29, -124, 32, 18, -115, -61, 55, 40, 65, -78, 68, -126, 44, -93, -108, 36, -110, -27, -39,
				-78, 97, 36, -88, -75, -41, 109, 37, 77, -84, -128, -112, 44, 20, -88, 92, -128, 49, 100, 96, 93, 44, 38, -79, 1, -42, -78, -28, 68, 40, 65, -92, 21, 33, -39, 67, 101 };
		SputnixBeacon result = new SputnixBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SputnixBeaconFileData.json", result);
	}

	@Test
	public void tesFileHeader() throws Exception {
		byte[] data = new byte[] { -92, 100, -126, -100, -116, 64, 96, -92, -90, 102, 96, -90, 64, 99, 0, -16, 32, 12, 15, 0, 1, 0, 21, 0, 1, 7, -69, 0, 0, 0, 0, 0, 0, 0, 53, 50, 45, 99, 111, 114, 114, 46, 106, 112, 103 };
		SputnixBeacon result = new SputnixBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SputnixBeaconFileHeader.json", result);
	}

	@Test
	public void tesFileSize() throws Exception {
		byte[] data = new byte[] { -92, 100, -126, -100, -116, 64, 96, -92, -90, 102, 96, -90, 64, 99, 0, -16, 43, 12, 15, 0, 1, 0, 4, 0, 101, 112, 0, 0 };
		SputnixBeacon result = new SputnixBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SputnixBeaconFileSize.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(SputnixBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SputnixTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FileHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FileSize.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FileData.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
