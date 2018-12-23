package ru.r2cloud.jradio.csp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HeaderTest {

	@Test
	public void testSuccess() {
		byte[] data = new byte[] { 0, -86, -110, 72 };
		Header header = new Header(data);
		assertEquals(31, header.getDestination());
		assertEquals(18, header.getDestinationPort());
		assertEquals(31, header.getSource());
		assertEquals(62, header.getSourcePort());
		assertEquals(Priority.CSP_PRIO_LOW, header.getPriority());
		assertFalse(header.isFcrc32());
		assertFalse(header.isFfrag());
		assertTrue(header.isFhmac());
		assertFalse(header.isFrdp());
		assertFalse(header.isFxtea());
	}

}
