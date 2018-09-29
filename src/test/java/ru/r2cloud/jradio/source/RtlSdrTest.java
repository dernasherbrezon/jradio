package ru.r2cloud.jradio.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

public class RtlSdrTest {

	private RtlSdr rtl;

	@Test
	public void basic() throws Exception {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeByte(10);
		dos.writeByte(20);
		dos.writeByte(30);
		dos.writeByte(40);

		rtl = new RtlSdr(new ByteArrayInputStream(baos.toByteArray()), 0.0f);
		assertEquals(-0.9171875, rtl.readFloat(), 0.0000001);
		assertEquals(-0.8390625, rtl.readFloat(), 0.0000001);
		assertEquals(-0.7609375, rtl.readFloat(), 0.0000001);
		assertEquals(-0.6828125, rtl.readFloat(), 0.0000001);
		try {
			rtl.readFloat();
			fail("eof expected");
		} catch (EOFException e) {
			// do nothing
		}
	}
	
	@After
	public void stop() throws Exception {
		if (rtl != null) {
			rtl.close();
		}
	}

}
