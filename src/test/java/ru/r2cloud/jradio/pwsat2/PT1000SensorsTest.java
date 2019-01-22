package ru.r2cloud.jradio.pwsat2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import ru.r2cloud.jradio.util.MathUtils;

public class PT1000SensorsTest {

	@Test
	public void testCalculation() {
		int raw = 250;
		float result = (raw / 1024.0f) * 3.0f;
		float resistance = result / ((3.0f - result) / 3320.68f);
		assertEquals(18.60f, MathUtils.round(PT1000Sensors.getTemperature(resistance), 1), 0.0f);
	}

	@Test
	public void testOutOfRange() {
		assertNull(PT1000Sensors.getTemperature(600.0f));
	}

}
