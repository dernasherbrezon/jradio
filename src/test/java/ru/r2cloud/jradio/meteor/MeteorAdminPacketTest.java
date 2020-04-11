package ru.r2cloud.jradio.meteor;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class MeteorAdminPacketTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = new byte[] { 2, 24, -89, -93, -110, -35, -102, -65, 8, 44, 22, 20, 0, 0, 85, 101, 102, 65, 0, 29, 0, 7, -101, -81, -113, 4, 0, 2, 0, 61, -52, 105, -18, 127, 121, 65, -127, -43, -104, 25, 100, 1, -78, 58, -16, 72, -8, 69, 23, -114 };
		MeteorAdminPacket packet = new MeteorAdminPacket(data);
		AssertJson.assertObjectsEqual("MeteorAdminPacket.json", packet);
	}

}
