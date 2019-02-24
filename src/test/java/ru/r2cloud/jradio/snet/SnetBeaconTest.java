package ru.r2cloud.jradio.snet;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class SnetBeaconTest {

	@Test
	public void testDeserializeAdcs() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("F35010E000002C3998390D4601010F0214000004045308EAFF1800EBFF2EF244012A069A3C5DB785AE00000000000000000000000000000000E0C7E925E3110000001F607B");
		SnetBeacon beacon = new SnetBeacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("SnetBeacon-adcs.json", beacon);
	}

}
