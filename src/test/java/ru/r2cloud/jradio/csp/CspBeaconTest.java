package ru.r2cloud.jradio.csp;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class CspBeaconTest {

	@Test
	public void testEofException() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"00239282162902F0612C415027750DEB0DE80DFD00D800AB01840803FFFF000000000000FFFFFFFF0124006A2073007801E0FFCB0000A24000000167FF900383FFD6FFD90095005100EB0180F4C9E335F542F6D2FA6FF911FFE7F89401130000000000F4000001CC98012806000000FF000000000000000000000000000000000200FFCCF9AC01731835000404EE000071FFEAFE");
		TestCspBeacon beacon = new TestCspBeacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("CspBeaconEof.json", beacon);
	}

	private static class TestCspBeacon extends CspBeacon {

		@Override
		public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
			throw new EOFException();
		}

	}
	
}
