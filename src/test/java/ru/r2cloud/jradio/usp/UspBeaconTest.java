package ru.r2cloud.jradio.usp;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class UspBeaconTest {

	@Test
	public void testEofException() throws Exception {
		byte[] data = { 8, -1, 27, 0, -92, 100, -126, -100, -116, 64, 96, -92, -90, 96, 96, -90, 64, 111, 0, -16, -31, -1, 2, 0, 1, 0, 3, 0, 0, 38, 6, 61, 86, -113, 23, -116, 104, 84, -30, 119, -60, -1, -65, 0, 0, 0, 0, 0 };
		TestUspBeacon beacon = new TestUspBeacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("UspBeaconEof.json", beacon);
	}

	private static class TestUspBeacon extends UspBeacon {

		@Override
		public void readBeacon(int etherType, DataInputStream dis) throws IOException, UncorrectableException {
			throw new EOFException();
		}

	}

}
