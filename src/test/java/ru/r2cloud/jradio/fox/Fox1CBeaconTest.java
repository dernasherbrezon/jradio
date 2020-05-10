package ru.r2cloud.jradio.fox;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Fox1CBeaconTest {

	@Test
	public void testPayloadRealtime() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("930310004010000000C30B00000000000000BBEBB53BECB226FCBE5F077773177936B76A820700011000038620A5A27A6218A05E002B0FC50B01123840360020");
		Fox1CBeacon result = new Fox1CBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Fox1CBeaconRealtime.json", result);
	}

	@Test
	public void testMaxValues() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("9303280040200000001B0C00000000000000FF7FDDBA2DDACB2DDA5BF88672D8876F4886FFFFFF055000503848D232BC409BAB64F0C0000200594F002529002A");
		Fox1CBeacon result = new Fox1CBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Fox1CBeaconMaxValues.json", result);
	}

	@Test
	public void testMinValues() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("930378083D30000000DC0A000000000000000000000010000110000000000000000000008A060000000045250C95F24EE98462597000000000004F002529002A");
		Fox1CBeacon result = new Fox1CBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Fox1CBeaconMinValues.json", result);
	}

}
