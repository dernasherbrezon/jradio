package ru.r2cloud.jradio.csp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import org.junit.Test;

public class HeaderTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = new byte[] { 0, -86, -110, 72 };
		Header header = new Header(data);
		assertEquals(10, header.getDestination());
		assertEquals(18, header.getDestinationPort());
		assertEquals(0, header.getSource());
		assertEquals(42, header.getSourcePort());
		assertEquals(Priority.CSP_PRIO_CRITICAL, header.getPriority());
		assertFalse(header.isFcrc32());
		assertFalse(header.isFfrag());
		assertTrue(header.isFhmac());
		assertFalse(header.isFrdp());
		assertFalse(header.isFxtea());
		
		Header header2 = new Header(new DataInputStream(new ByteArrayInputStream(data)));
		assertEquals(header, header2);
	}

}
