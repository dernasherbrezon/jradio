package ru.r2cloud.jradio.at03;

import java.io.IOException;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class At03BeaconTest {

	@Test
	public void testStacie() throws IOException {
		byte[] data = ViterbiTest.hexStringToByteArray("c04f4e3033415479020020000000010800011a1c00c0a600ffc0a6b5a601ff00d8a4c6000012d332c932ffffffff");
		At03Beacon beacon = new At03Beacon();
		beacon.readExternal(data);
		AssertJson.assertObjectsEqual("At03Beacon-Stacie.json", beacon);
	}

	@Test
	public void testObc2() throws IOException {
		byte[] data = ViterbiTest.hexStringToByteArray("564f4e303341542fde904100000000000000000000060100d9fbfffffefff07f80740000a1300000636269680000");
		At03Beacon beacon = new At03Beacon();
		beacon.readExternal(data);
		AssertJson.assertObjectsEqual("At03Beacon-Obc2.json", beacon);
	}

	@Test
	public void testObc1() throws IOException {
		byte[] data = ViterbiTest.hexStringToByteArray("534F4E30334154868765860068000001FF7FF43A000000008383847AFCFC90320F484891EC5E0701003870010000");
		At03Beacon beacon = new At03Beacon();
		beacon.readExternal(data);
		AssertJson.assertObjectsEqual("At03Beacon-Obc1.json", beacon);
	}

}
