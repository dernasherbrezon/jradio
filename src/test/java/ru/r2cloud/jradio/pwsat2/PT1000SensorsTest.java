package ru.r2cloud.jradio.pwsat2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class PT1000SensorsTest {

	@Test
	public void testCalculation() {
		assertEquals(18.630083f, PT1000Sensors.getTemperature(1072.571f), 0.0f);
	}

	@Test
	public void testOutOfRange() {
		assertNull(PT1000Sensors.getTemperature(600.0f));
	}

}
