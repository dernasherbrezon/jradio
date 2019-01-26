package ru.r2cloud.jradio.astrocasat;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

public class NMEA0183Test {

	@Test
	public void test() throws ParseException {
		String data = "$GPRMC,220516.38,A,5133.82,N,02311.12,W,13606,054.7,270816,020.3,W";
		NMEA0183 result = new NMEA0183(data);
		assertEquals(5133.82f, result.getLatitude(), 0.0f);
		assertEquals(20.3f, result.getMagneticVariation(), 0.0f);
		assertEquals(LatitudeDirection.N, result.getLatitudeDirection());
		assertEquals(NMEA0183Source.GP, result.getSource());
	}

}
