package ru.r2cloud.jradio.pwsat2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.util.MathUtils;

public class PT1000SensorsTest {

	@Test
	public void testCalculation() {
		int raw = 250;
		float result = (raw / 1024.0f) * 3.0f;
		float pt1000_resistance = result / ((3.0f - result) / 3320.68f);
		assertEquals(18.60f, MathUtils.round(PT1000Sensors.pt1000_res_to_temp(pt1000_resistance), 1), 0.0f);
	}

}
