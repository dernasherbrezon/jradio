package ru.r2cloud.jradio.at03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import ru.r2cloud.jradio.fec.ViterbiTest;

public class At03BeaconTest {

	@Test
	public void testStacie() throws IOException {
		byte[] data = ViterbiTest.hexStringToByteArray("c04f4e3033415479020020000000010800011a1c00c0a600ffc0a6b5a601ff00d8a4c6000012d332c932ffffffff");
		At03Beacon beacon = new At03Beacon();
		beacon.readExternal(data);
		assertEquals(BeaconType.STACIE, beacon.getType());
		StacieBeacon s = beacon.getStacieBeacon();
		assertNotNull(s);
		assertEquals(StacieOperationalMode.Normal, s.getMode());
		assertEquals(28, s.getBeaconInterval());
		assertEquals(13018328, s.getsTime());
	}

	@Test
	public void testObc2() throws IOException {
		byte[] data = ViterbiTest.hexStringToByteArray("564f4e303341542fde904100000000000000000000060100d9fbfffffefff07f80740000a1300000636269680000");
		At03Beacon beacon = new At03Beacon();
		beacon.readExternal(data);
		assertEquals(BeaconType.OBC2, beacon.getType());
		OBC2Beacon s = beacon.getObc2Beacon();
		assertNotNull(s);
	}

}
