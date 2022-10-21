package ru.r2cloud.jradio.ax25;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Ax25BeaconTest {

	@Test
	public void testEofException() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"9CA6AA4040406086AA84A682A86103F08EFFFFFFFF0A0601C916E800000000F10F0000FF16C69C050042524B204D57205645523A30325F31320000000000000E0000D105000000071A0000CD0A8A001004000000202009600000000023B1CE01326DB100B0927427250000000000000000000000000016040C0F0F0F8F0F0F001214B76C12600AB8380E0C000C00006500F4139D0E1416120060106F20D920");
		TestAx25Beacon beacon = new TestAx25Beacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("Ax25BeaconEof.json", beacon);
	}

	private static class TestAx25Beacon extends Ax25Beacon {

		@Override
		public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
			throw new EOFException();
		}

	}

}
