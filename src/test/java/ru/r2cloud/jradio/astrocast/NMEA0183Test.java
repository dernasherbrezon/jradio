package ru.r2cloud.jradio.astrocast;

import java.text.ParseException;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class NMEA0183Test {

	@Test
	public void test() throws ParseException, Exception {
		String data = "$GPRMC,220516.38,A,5133.82,N,02311.12,W,13606,054.7,270816,020.3,W";
		AssertJson.assertObjectsEqual("NMEA0183.json", new NMEA0183(data));
	}

	@SuppressWarnings("unused")
	@Test(expected = NumberFormatException.class)
	public void testInvalidFormat() throws ParseException {
		new NMEA0183("$GPRMC,220516.38,A,513@.82,N,02311.12,W,13606,054.7,270816,020.3,W");
	}
}
