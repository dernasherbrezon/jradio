package ru.r2cloud.jradio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DopplerValueSourceTest {

	@Test
	public void test() {
		DopplerValueSource source = new DopplerValueSource(10, 137, 5, 0L) {

			@Override
			public long getDopplerFrequency(long satelliteFrequency, long currentTimeMillis) {
				if (currentTimeMillis == 0) {
					return 0;
				} else if (currentTimeMillis == 5) {
					return 1;
				} else if (currentTimeMillis == 10) {
					return 2;
				}
				return 0;
			}
		};
		assertEquals(137, source.getValue(), 0.0);
		assertEquals(137, source.getValue(), 0.0);
		assertEquals(136, source.getValue(), 0.0);
		assertEquals(136, source.getValue(), 0.0);
		assertEquals(135, source.getValue(), 0.0);
		assertEquals(135, source.getValue(), 0.0);
		assertEquals(137, source.getValue(), 0.0);
	}

}
