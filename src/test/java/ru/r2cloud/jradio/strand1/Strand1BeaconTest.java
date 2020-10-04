package ru.r2cloud.jradio.strand1;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Strand1BeaconTest {

	@Test
	public void testMag05() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("C0809E0C02890508F2DBFFFFB4070000");
		Strand1Beacon result = new Strand1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Strand1BeaconMag05.json", result);
	}

	@Test
	public void testMag03() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("C080880C02890308DD8D04005761FEFF");
		Strand1Beacon result = new Strand1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Strand1BeaconMag03.json", result);
	}

	@Test
	public void testUnixtime() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("C0807F0C02800C08A4070000E8BB0B00");
		Strand1Beacon result = new Strand1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Strand1BeaconUnixtime.json", result);
	}

	@Test
	public void testSwitchBoard() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("C080B4090266810500005B00B6");
		Strand1Beacon result = new Strand1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Strand1BeaconSwitch.json", result);
	}

	@Test
	public void testSwitchBoard2() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("C080BD090266AC0500000000B6");
		Strand1Beacon result = new Strand1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Strand1BeaconSwitch2.json", result);
	}

	@Test
	public void testBatteryVoltage() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("C080C006022C0802E501");
		Strand1Beacon result = new Strand1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Strand1BeaconVoltage.json", result);
	}

	@Test
	public void testZArrayCurrent() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("C080C506022D0A02E401");
		Strand1Beacon result = new Strand1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Strand1BeaconZCurrent.json", result);
	}

}
