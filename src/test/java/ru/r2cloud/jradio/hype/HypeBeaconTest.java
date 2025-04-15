package ru.r2cloud.jradio.hype;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class HypeBeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("004859504500006EAA05190A3215242400000501000000370013BE3400000000000040");
		HypeBeacon result = new HypeBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("HypeBeacon.json", result);
	}
}
