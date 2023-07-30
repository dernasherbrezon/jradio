package ru.r2cloud.jradio.mrc100;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class Mrc100BeaconTest {

	@Test
	public void testFileDl() throws Exception {
		byte[] data = new byte[] { 18, 103, 6, -124, 0, 0, 0, 1, 104, 15, 0, -101, -10, 0, 0, 52, 14, 0, 45, 79, 0, 127, 45, -128, 24, 63, 46, -96, 58, 14, 127, 46, -96, 46, 14, -64, 44, 80, 0, -1, 45, -96, 29, 14, 0, 45, 81, 0, 127, 45, -128, 27, -65, 45, -128, 23, 63, 46, -96, 58, 14, 127, 46,
				-96, 46, 14, -65, 46, -96, 52, 14, -64, 44, 82, 0, -1, 45, -96, 35, 14, 0, 45, 83, 0, 127, 45, -128, 34, -65, 45, -128, 23, 63, 46, -96, 58, 14, 127, 46, -96, 52, 14, -65, 46, -96, 58, 14, -64, 44, 84, 0, -1, 45, -96, 35, 14, 0, 45, 85, -105, -46, 33, -63, 86, -122, 126 };
		Mrc100Beacon result = new Mrc100Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Mrc100Beacon-FileDl.json", result);
	}

	@Test
	public void testTelemetry1() throws Exception {
		byte[] data = new byte[] { 1, 52, -23, 47, -67, -65, 100, -28, -16, 7, 0, 96, -128, -128, -128, -128, -128, -128, -128, 4, -30, 9, 0, 2, 0, 0, 2, 0, 0, 79, 48, 0, 45, 48, -120, 116, -56, 105, -48, -2, 72, 109, 0, 25, 96, 6, 48, 34, -80, 2, -56, 5, -16, 111, 0, 0, 0, 0, -94, -94, -126, -94,
				-94, -126, -126, -126, -126, -94, -126, -126, -94, -126, -126, -126, -126, -126, -94, -94, -126, -126, -126, -126, -127, -127, -127, -126, 124, -126, -126, -94, -94, 126, 0, 0, 0, 0, 0, 0, 0, 15, 0, 23, 0, 31, 0, 42, 32, 126, 13, 126, 7, 127, -1, 127, -1, 106, -1, 12, 127, 35, 102,
				-71, 81, -47, 96, -72 };
		Mrc100Beacon result = new Mrc100Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Mrc100Beacon-Telemetry1.json", result);
	}

	@Test
	public void testTelemetry2() throws Exception {
		byte[] data = new byte[] { 2, 64, -78, 119, -68, -65, 100, 35, 1, 16, 117, -93, 0, 16, 117, -126, -102, -48, 22, 60, 48, 32, 116, 96, 5, 40, 104, 28, 0, 0, 0, 28, 0, 0, 0, 28, 0, 0, 0, -100, 2, -56, 0, -95, 2, 80, 105, 92, 2, 88, 0, -100, 75, 0, 12, -5, 6, -24, 103, -101, 127, -112, 32, 27,
				0, 0, 0, -101, 0, 24, 0, 91, 1, 24, 0, -37, 1, 88, 0, -70, 0, -48, 103, 58, 0, 40, 104, 26, 0, 0, 0, 23, 1, 88, 0, 86, 7, 72, 2, -106, 7, 104, 2, 18, 3, 32, 1, -47, 0, 88, 0, 39, 0, -128, 117, 39, 0, -48, 116, -50, 59, 123, 101, -31, -103, 54 };
		Mrc100Beacon result = new Mrc100Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Mrc100Beacon-Telemetry2.json", result);
	}

	@Test
	public void testTelemetry3() throws Exception {
		byte[] data = new byte[] { 3, -36, -125, 120, -68, -65, 100, 18, 1, 56, 0, -114, 1, 112, 0, 13, 0, 0, 0, 9, 0, 0, 0, -33, 63, 9, 6, -33, 63, 9, 6, 127, 127, 127, 127, -127, 1, 33, 3, -71, 1, -71, 0, -31, 127, -79, 126, 73, 127, 121, 126, 97, 1, -15, 2, -87, 1, -63, 0, -7, 116, 41, 117, 89,
				117, -79, 117, -55, -1, 97, 13, 105, 2, -63, 2, -79, 4, 57, 0, 1, 30, 9, 1, -97, 58, 1, -108, 18, 1, 50, 17, 1, -113, 5, 1, 91, 42, 1, -71, 3, 1, 124, 31, 1, 26, 9, 1, 37, 10, 1, 84, 3, 1, -116, 16, -120, 0, 0, 0, -118, 58, -37, 38, -88, 11, 52 };
		Mrc100Beacon result = new Mrc100Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Mrc100Beacon-Telemetry3.json", result);
	}

	@Test
	public void testTelemetry4() throws Exception {
		byte[] data = new byte[] { 4, -28, -105, 120, -68, -65, 100, -128, -2, 18, 1, 54, -2, 97, 0, 65, 1, -111, -1, -127, 0, -7, -2, 121, 1, -55, 2, 126, 1, 46, 2, -106, 0, -34, -1, -66, 1, 70, 3, -42, 2, 127, -1, -1, -1, -1, 127, -1, -1, -1, -1, 127, -1, -1, -1, -1, 127, -1, -1, -1, -1, 127, -1,
				-1, -1, -1, 127, -1, -1, 127, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -63, -58, 0, 40, -121, 0, 13, 74, -120, -13, -2, -1, -47, 2, 42, 1, -71, 3, 1, 124, 31, 1, 26, 9, 1, 37, 10, 1, 84, 3, 1, -116, 16, -120, 0, 0, 0, -9, 21, -36, -29, 1, 25, 117 };
		Mrc100Beacon result = new Mrc100Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Mrc100Beacon-Telemetry4.json", result);
	}

	@Test
	public void testTelemetry6() throws Exception {
		byte[] data = new byte[] { 6, -91, 80, 120, -68, -65, 100, -2, -79, 102, -1, 5, 0, 54, 105, 37, 104, 126, -77, 67, 0, 0, 126, -42, 81, 26, 66, 126, 66, 59, 22, 66, 49, -73, 67, 0, 0, 49, -116, -77, -32, 64, 49, -87, 91, 19, 65, 118, 0, 86, 0, 127, -1, 127, -1, 127, 126, -2, -2, -2, -2, 126,
				124, 15, -2, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -67, -33, -125, -111, 1, -12, 52 };
		Mrc100Beacon result = new Mrc100Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Mrc100Beacon-Telemetry6.json", result);
	}

	@Test
	public void testBeacon() throws Exception {
		byte[] data = new byte[] { 9, 108, -50, 120, -68, -65, 100, 126, 107, -124, -66, 100, -100, 126, -73, -124, -66, 100, -84, 126, -104, -91, -65, 100, -98, 126, -47, -91, -65, 100, -86, 126, -6, -91, -65, 100, -66, 126, 0, -90, -65, 100, -77, 126, -72, -90, -65, 100, -92, 126, -58, -69, -65,
				100, -104, 118, 0, -2, 104, -2, 0, 71, 48, 49, 50, 0, 15, 0, 0, 0, 0, -16, -97, -102, -128, 32, 104, 116, 116, 112, 58, 47, 47, 49, 53, 50, 46, 54, 54, 46, 56, 48, 46, 52, 54, 47, 109, 114, 99, 49, 48, 48, 100, 101, 99, 47, 114, 120, 47, 32, -16, -97, -101, -80, -17, -72, -113, 0, 0,
				39, -126, -34, -95, 16, 108, 122 };

		Mrc100Beacon result = new Mrc100Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Mrc100Beacon-Beacon.json", result);
	}

	@Test
	public void testFileStart() throws Exception {
		byte[] data = new byte[] { 17, -82, 104, -126, 0, 0, 0, 1, -21, -12, 0, 17, 88, -66, 100, 68, 65, 84, 65, 76, 79, 71, 49, -95, -51, 7, 0, -21, -103, -65, 100, 85, -1, 24, 23, 0, 24, 0, -85, 0, -84, 0, -83, 0, -82, 0, -81, 0, -80, 0, -79, 0, -78, 0, -77, 0, -76, 0, -75, 0, -74, 0, -73, 0,
				-72, 0, -71, 0, -70, 0, -39, 0, -38, 0, -37, 0, -36, 0, -35, 0, -34, 0, -64, 5, 0, -96, 97, 14, 63, 6, -96, 93, 13, -1, 42, 0, 63, 43, 0, 127, 43, 0, -65, 43, 0, -1, 43, -96, -51, 3, 63, 44, -96, -58, 3, 127, 44, -96, -45, 15, -80, 50, 74, -7, 116, 102 };
		Mrc100Beacon result = new Mrc100Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Mrc100Beacon-FileStart.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(Mrc100Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FileDl.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FileDlStartBlock.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FileInfo.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SpaTruncated.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Spa.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ProgMap.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ru.r2cloud.jradio.mrc100.Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(AdcsTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(TelemetryCustom.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry6.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry5.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry4.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry3.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry2.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry1.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(UplinkFeedback.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}
