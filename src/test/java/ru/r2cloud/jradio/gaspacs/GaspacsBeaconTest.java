package ru.r2cloud.jradio.gaspacs;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class GaspacsBeaconTest {

	@Test
	public void testTelemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("474153504143530061807d14000000000000000000000000000000000000000042ca000042ca000042ca000047415350414353");
		GaspacsBeacon result = new GaspacsBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("GaspacsBeaconTelemetry.json", result);
	}

	@Test
	public void testTtncData() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("474153504143530161832dd002001700000000000000000000000042473333431c0000431c0000431c000040c333334120000040c333333ff00000408000004080000040d00000403333334033333340d00000403333334033333340d000004033333347415350414353");
		GaspacsBeacon result = new GaspacsBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("GaspacsBeaconTtncdata.json", result);
	}

	@Test
	public void testax25Beacon() throws Exception {
		byte[] data = new byte[] { -2, -15, 110, -112, -96, -68, -91, 106, -6, -15, -2, -50, 69, 42, 38, -104, 1, -62, -24, 27, 25, 21, -78, 4, -87, 19, -14, -15, -111, 102, -120, 83, 81, -28, 113, -29, -106, 111, -102, -104, -112, 17, -91, -66, 93, -47, -32, -96, 17, 97, -119, -97, 78, 19, -10,
				-32, 54, -122, 76, -104, 80, 0 };
		GaspacsBeacon result = new GaspacsBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("GaspacsBeaconax25.json", result);
	}

}
