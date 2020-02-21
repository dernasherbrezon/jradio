package ru.r2cloud.jradio.dslwp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

import org.junit.Test;

import ru.r2cloud.jradio.jy1sat.Jy1satSsdvPacketSourceTest;
import ru.r2cloud.ssdv.SsdvDecoder;
import ru.r2cloud.ssdv.SsdvImage;

public class DslwpSsdvPacketSourceTest {

	@Test
	public void testSuccess() throws Exception {
		int total = 0;
		try (InputStream is = DslwpSsdvPacketSource.class.getClassLoader().getResourceAsStream("dslwp.bin")) {
			SsdvDecoder decoder = new SsdvDecoder(new DslwpSsdvPacketSource(is));
			while (decoder.hasNext()) {
				SsdvImage image = decoder.next();
				total++;
				Jy1satSsdvPacketSourceTest.assertSsdvImage("dslwp", image);
			}
		}
		assertEquals(1, total);
	}

	@Test(expected = NoSuchElementException.class)
	public void testIllegalToCallNextFirst() throws Exception {
		try (InputStream is = DslwpSsdvPacketSource.class.getClassLoader().getResourceAsStream("dslwp.bin")) {
			new DslwpSsdvPacketSource(is).next();
		}
	}

	@Test
	public void testInvalidPayload() throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(new byte[5]);
		assertFalse(new DslwpSsdvPacketSource(bais).hasNext());
	}
}
