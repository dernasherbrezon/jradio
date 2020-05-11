package ru.r2cloud.jradio.fox;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Fox1DBeaconTest {

	@Test
	public void testRadExperiment() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("3C0250390B4000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		Fox1DBeacon result = new Fox1DBeacon();
		result.readBeacon(data);	
		AssertJson.assertObjectsEqual("Fox1DBeaconRadExp.json", result);
	}
	
	@Test
	public void testPayloadRealtime() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("3C0200390B10000000660A00000000000000532000F020005B1000011000011000011000AF0800CDB973B00136C1C28831488859F001031C00711338F8010020");
		Fox1DBeacon result = new Fox1DBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Fox1DBeaconRealtime.json", result);
	}

	@Test
	public void testMaxValues() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("3C02883A0B20000000FD0B00000000000000C03EDABF4DD8A84DD881088A80388988E887FFFFFFCADB94CCE195E422C70B2DC06040F700020027460039820128");
		Fox1DBeacon result = new Fox1DBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Fox1DBeaconMaxValues.json", result);
	}

	@Test
	public void testMinValues() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("3C02B8380B30000000540A000000000000000000000100000100000000000000000000005A07004E105E94610BA7A24914824054B00149000000460039820128");
		Fox1DBeacon result = new Fox1DBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Fox1DBeaconMinValues.json", result);
	}

}
