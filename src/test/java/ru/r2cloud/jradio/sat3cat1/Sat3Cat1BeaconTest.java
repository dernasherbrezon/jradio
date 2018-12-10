package ru.r2cloud.jradio.sat3cat1;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class Sat3Cat1BeaconTest {

	@Test
	public void testDecodeState() throws Exception {
		byte[] data = new byte[] { 0, -80, 92, 2, 91, 121, 88, -1, 22, 33, -44, 22, 3, -79, 22, 33, -44, 22, 33, -44, 22, 34, 2, 22, 34, 14, 22, 31, -17, 22, 0, 37, 22, 0, 9, 22, 0, 0, 22, 0, 4, 22, 0, 47, 22, 0, 15, 22, 1, 28, 22, 1, 14, 22, 1, 23, 22, 1, 24, 22, 1, 39, 22, 1, 20, 22, 0, 11, 22, 0, 103, 22, 0, 0, 22, 0, 0, 22, 0, 22, 22, 1, 95, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		Sat3Cat1Beacon beacon = new Sat3Cat1Beacon();
		beacon.readExternal(data);
		assertEquals(255, beacon.getSensorId());
		assertEquals(1543658361, beacon.getSpacecraftTime());
		assertEquals(88, beacon.getStateOfCharge());
		assertEquals(BeaconType.BEACON_TYPE_STATE, beacon.getType());
		assertEquals(6, beacon.getCurrentSensors().size());
		assertFirstValue(1543658340, 37.0f, beacon.getCurrentSensors());
		assertEquals(7, beacon.getVoltSensors().size());
		assertFirstValue(1543658340, 8.66f, beacon.getVoltSensors());
		assertEquals(7, beacon.getTemperaturSensors().size());
		assertFirstValue(1543658340, 284.0f, beacon.getTemperaturSensors());
		assertEquals(6, beacon.getIrradianceSensors().size());
		assertFirstValue(1543658340, 0.103f, beacon.getIrradianceSensors());
	}

	private static void assertFirstValue(long expectedTime, float expectedValue, List<MetricValue> sensors) {
		MetricValue first = sensors.get(0);
		assertEquals(expectedTime, first.getTime());
		assertEquals(expectedValue, first.getValue(), 0.0f);
	}

}
