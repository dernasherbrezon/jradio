package ru.r2cloud.jradio.netsat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class NetSatBeaconTest {

	@Test
	public void testUnknownPayload() throws Exception {
		byte[] data = new byte[] { 0, 0, -54, 0, -90, 100, 96, -120, -96, 100, -100, -90, -124, 97, 3, -16, 74, 92, 48, 0, 33, -126, 74, 9, 5, 1, 1, 0, 29, 0, 104, 0, 21, 41, 13, 23, 49, -1, 94, 1, -1, 5, 16, 113, 4, 87, 113, 4, 88, 49, -1, 89, 113, 3, 90, 113, 4, 92, 72, 93, 75, -34, 83, 81 };
		NetSatBeacon result = new NetSatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("NetSatBeaconUnknown.json", result);
	}

	@Test
	public void testSuccess() throws Exception {
		byte[] data = new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0xCA, (byte) 0x00, (byte) 0xA6, (byte) 0x64, (byte) 0x60, (byte) 0x88, (byte) 0xA0, (byte) 0x64, (byte) 0x9C, (byte) 0xA6, (byte) 0x84, (byte) 0x61, (byte) 0x03, (byte) 0xF0, (byte) 0x4A, (byte) 0x5C, (byte) 0x30, (byte) 0x00,
				(byte) 0x25, (byte) 0x82, (byte) 0x78, (byte) 0x11, (byte) 0x05, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x0E, (byte) 0x35, (byte) 0x1F, (byte) 0x00, (byte) 0x05, (byte) 0xAF, (byte) 0x2A, (byte) 0x13, (byte) 0x8A, (byte) 0x12, (byte) 0x81, (byte) 0x72, (byte) 0x01,
				(byte) 0x00, (byte) 0xF0, (byte) 0x00, (byte) 0x00, (byte) 0x42, (byte) 0x5D, (byte) 0x00, (byte) 0x00, (byte) 0x14, (byte) 0x50, (byte) 0x03, (byte) 0x60, (byte) 0x03, (byte) 0x5F, (byte) 0xFC, (byte) 0x0F, (byte) 0xFF, (byte) 0xFF, (byte) 0xFD, (byte) 0xFF, (byte) 0xF8,
				(byte) 0x0F, (byte) 0x53, (byte) 0x00, (byte) 0x53, (byte) 0x01, (byte) 0x50, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x0A, (byte) 0x00, (byte) 0x0F, (byte) 0x0E, (byte) 0x0E, (byte) 0x00, (byte) 0x80, (byte) 0xBA, (byte) 0xF6, (byte) 0x19, (byte) 0x00, (byte) 0x00,
				(byte) 0x54, (byte) 0x3C, (byte) 0xD0, (byte) 0xC7, (byte) 0x89, (byte) 0xAC };
		NetSatBeacon result = new NetSatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("NetSatBeacon.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(NetSatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(CompassHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ModelPacket.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(TelemetryPayload.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
