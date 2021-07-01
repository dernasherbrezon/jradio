package ru.r2cloud.jradio.grifex;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class GrifexBeaconTest {

	@Test
	public void testBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"968870A6A0A66086A240404040E103F0ABCD0000004203F500B47548186760E40408000A00090001002200A038EE7B9D03010000000047470700F0470F007300000000D3AA240086076B07030D60002800FD0CBB0E0D00A40E03068507B40E0F00660D20001400FF0C950054007D076A0D8800FD0CBB00FB09A101360F880090079107EA0AB200120F3E0088078D07D7075600290F140094079207EB072200160F08008E078E07FDFC9F07A0079F0700C04A002200A3FF7D00A1FFE6FF9B00E6FF2500DD000F00DA00070D080DBD0C950C650A420A710B600B3100A30321046600B8338B334C20001000010000009502000001000000FF723D00BEFFFFFFFF98BCB0151C40");
		GrifexBeacon result = new GrifexBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("GrifexBeacon.json", result);
	}

	@Test
	public void testUnknownPayload() throws Exception {
		byte[] data = { -106, -120, 112, -90, -96, -90, 96, -122, -94, 64, 64, 64, 64, -31, 3, -16, -85, -51, 0, 0, 0, 66, 1, -19, 0, -86, 95, 0, -36, 0, 15, 105, -17, 2, -118, 28, -9, 87, -26, 120, -122, 98, 42, 76, -26, -34, -48, 126, -49, -124, -10, -77, -63, -116, -98, -70, 118, 40, -104, 84,
				97, 18, 46, 62, 116, -12, 75, -13, -3, 125, 23, 112, 4, 31, 106, 60, -33, -67, 95, 124, -121, -48, 104, 4, -106, -84, 70, 16, -73, -30, -89, 46, -68, 96, -6, -55, 64, 122, 96, 40, 119, 88, -111, 43, 60, 20, 115, -17, -13, 51, 53, 46, -25, -26, 66, -81, 95, 71, -78, -127, 83, -114,
				-90, 102, 86, -126, -102, -109, -22, 79, 39, -51, 119, 3, 47, 59, 62, 74, 66, 95, 65, -100, -31, 86, -118, -71, -98, -9, -24, 60, 89, -121, -17, -42, -93, 55, -120, 75, -61, -59, -60, 79, 45, 111, 76, 88, 57, 46, 62, -68, 127, 63, 38, -87, -11, -39, -73, 19, 55, -49, -76, 63, 123,
				-8, 88, -5, 74, -34, 85, -9, 103, 127, -89, -62, -120, 69, -11, 69, 87, 2, -101, -29, 52, 3, -121, -30, 48, 115, -69, 13, 98, 13, 115, 67, -25, -30, 7, -45, -110, 72, -79, -79, 126, 48, 23, 39, 49, 90, 88, -123, -100, -23, 127, 72, 47, 99, -51, -121, -100, -79, 126, -79, -41, 87,
				-45, -57, -7, -52, 67, 1, 0, 0, 0 };

		GrifexBeacon result = new GrifexBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("GrifexBeacon-unknown.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(GrifexBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(MxlHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(GrifexTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}
