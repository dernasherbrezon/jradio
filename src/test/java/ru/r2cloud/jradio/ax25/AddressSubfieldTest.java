package ru.r2cloud.jradio.ax25;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import org.junit.Test;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class AddressSubfieldTest {

	@Test
	public void testEmptyCallsign() throws Exception {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(new byte[] { 64, 64, 64, 64, 64, 64, 1 }));
		AddressSubfield address = new AddressSubfield(dis);
		assertEquals("", address.getCallsign());
	}

	@Test
	public void testCallsign() throws Exception {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(new byte[] { -82, -92, 104, -86, -116, 64, 1 }));
		AddressSubfield address = new AddressSubfield(dis);
		assertEquals("WR4UF", address.getCallsign());
	}

	@SuppressWarnings("unused")
	@Test(expected = UncorrectableException.class)
	public void testInvalidCallsign() throws Exception {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(new byte[] { -37, 37, -37, 16, 111, 16, 16 }));
		new AddressSubfield(dis);
	}
}
