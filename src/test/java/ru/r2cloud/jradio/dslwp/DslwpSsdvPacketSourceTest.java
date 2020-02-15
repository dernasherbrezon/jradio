package ru.r2cloud.jradio.dslwp;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

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

}
