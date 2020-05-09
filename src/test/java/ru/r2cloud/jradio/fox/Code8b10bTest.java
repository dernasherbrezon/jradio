package ru.r2cloud.jradio.fox;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Code8b10bTest {

	@Test
	public void testEncodeDecode() throws Exception {
		byte data = (byte)0xca;
		int result = Code8b10b.encode(data);
		assertEquals(data, Code8b10b.decode(result));
	}
	
	@Test(expected = UncorrectableException.class)
	public void testUncorrectable() throws Exception {
		Code8b10b.decode(0x000);
	}

}
