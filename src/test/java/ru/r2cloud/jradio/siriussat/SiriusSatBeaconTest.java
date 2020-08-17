package ru.r2cloud.jradio.siriussat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class SiriusSatBeaconTest {

	@Test
	public void testBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("A464829C8C4060A4A66266A6406300F016420200010042008C140212EB0AA60002001B003A000000000004000100070007000600070000200000141F2707000020FA385FBA04070787851F0FF40E21FA385F260700001C00431F");
		SiriusSatBeacon result = new SiriusSatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SiriusSatBeacon-short.json", result);
	}
	
}
