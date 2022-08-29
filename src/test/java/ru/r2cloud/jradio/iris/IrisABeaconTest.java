package ru.r2cloud.jradio.iris;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class IrisABeaconTest {

	@Test
	public void testTelemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("849C608696AAE0849C60929EA86103F0007B7B00080ACC90001E1003192A9891F000FE02FF8900005146084494A900534C85F981000080C6DD01");
		IrisABeacon result = new IrisABeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("IrisABeacon.json", result);
	}

}
