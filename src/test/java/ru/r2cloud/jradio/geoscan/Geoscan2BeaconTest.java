package ru.r2cloud.jradio.geoscan;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Geoscan2BeaconTest {

	@Test
	public void testAx25Telemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("848A82869E9C60A4A66A62A600E103F0011EA3F86703126F007F011E10502006000A0A5601010000000000000000000000000000000000000D67204F019A7F00004501000C000000");
		Geoscan2Beacon result = new Geoscan2Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Geoscan2BeaconAx25.json", result);
	}

	
	@Test
	public void testFile() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("0498431CEE316F6B6FB628000010009AD3559D0AF46AF5B06EF465134A7B9E51A826D26B2A65A563BCA720AAB20E2B2622061CD4445640CFD0E8EACC75F4E7CE13A9C55988000000");
		Geoscan2Beacon result = new Geoscan2Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Geoscan2BeaconFile.json", result);
	}
	
}
