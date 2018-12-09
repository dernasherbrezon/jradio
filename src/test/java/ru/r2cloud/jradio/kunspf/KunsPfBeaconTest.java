package ru.r2cloud.jradio.kunspf;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.fec.ViterbiTest;

public class KunsPfBeaconTest {

	@Test
	public void testWodDecoding() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("009B91825B039688051504FE0505001C001B001D001C00030003000000532070002901302795003CFF7F000505C7012F0130000E0056000601540112FFBA019300A100F401B0010E0055003900FFFD8001C600A5000000010104000048F9B179");
		KunsPfBeacon beacon = new KunsPfBeacon();
		beacon.readExternal(data);
		assertEquals(260, beacon.getSunSensor4());
	}
}
