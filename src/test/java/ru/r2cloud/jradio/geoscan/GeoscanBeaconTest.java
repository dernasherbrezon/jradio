package ru.r2cloud.jradio.geoscan;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class GeoscanBeaconTest {

	@Test
	public void testAx25Telemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("848A82869E9C60A4A66460A640E103F0535A0565BD047C087AEB98EA0C0D0617800802040F791DEE050100000000000000000000000000000000000000000000");
		GeoscanBeacon result = new GeoscanBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("GeoscanBeaconAx25.json", result);
	}
}
