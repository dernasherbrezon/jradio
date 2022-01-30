package ru.r2cloud.jradio.delfipq;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class DelfiPqBeaconTest {

	@Test
	public void testObcTelemetry() throws Exception {
		byte[] data = new byte[] { -114, -92, -98, -86, -100, -120, -32, -120, -104, -116, -110, -96, -94, 97, 3, -16, 0, 8, 26, 1, 80, 2, 0, 1, 32, -47, 0, 78, 0, 0, 2, 35, 64, 0, 20, -104, 67, 2, 0, 58, -64, 12, -127, 0, 10 };
		DelfiPqBeacon result = new DelfiPqBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("DelfiPqBeaconObcTelemetry.json", result);
	}
	
	@Test
	public void testCommsTelemetry() throws Exception {
		byte[] data = new byte[] { -114, -92, -98, -86, -100, -120, -32, -120, -104, -116, -110, -96, -94, 97, 3, -16, 0, 8, 40, 1, 80, 2, 0, 4, 0, 55, 0, 14, 0, 0, 2, 34, 78, 0, 21, 41, -22, 2, 0, 106, -4, 12, -123, 0, 53, 0, 94, -1, -113, 1, 9, 0, 0, 0, 0, 0, 0, -1, 116 };
		DelfiPqBeacon result = new DelfiPqBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("DelfiPqBeaconCommsTelemetry.json", result);
	}

}
