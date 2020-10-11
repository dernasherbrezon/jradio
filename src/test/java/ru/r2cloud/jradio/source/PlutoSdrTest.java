package ru.r2cloud.jradio.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.EOFException;

import org.junit.Test;

public class PlutoSdrTest {
	
	private PlutoSdr sdr;

	@Test
	public void basic() throws Exception {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeByte(154);
		dos.writeByte(255);
		dos.writeByte(207);
		dos.writeByte(0);

		sdr = new PlutoSdr(new ByteArrayInputStream(baos.toByteArray()), 0.0f, 10L);
		assertEquals(-0.0498046875, sdr.readFloat(), 0.0000001);
		assertEquals(0.0f, sdr.getContext().getCurrentSample().getValue(), 0.0f);
		assertEquals(0.10107421875, sdr.readFloat(), 0.0000001);
		try {
			sdr.readFloat();
			fail("eof expected");
		} catch (EOFException e) {
			// do nothing
		}
	}
}
