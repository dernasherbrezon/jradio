package ru.r2cloud.jradio.silversat;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import org.junit.Test;

import ru.r2cloud.jradio.BeaconInputStream;
import ru.r2cloud.jradio.il2p.Il2pBeacon;
import ru.r2cloud.jradio.jy1sat.Jy1satSsdvPacketSourceTest;
import ru.r2cloud.ssdv.SsdvDecoder;
import ru.r2cloud.ssdv.SsdvImage;

public class SilversatPacketSourceTest {

	@Test
	public void testSuccess() throws Exception {
		int total = 0;
		try( BeaconInputStream<Il2pBeacon> is = new BeaconInputStream<>(new BufferedInputStream(new FileInputStream("./src/test/resources/silversat.bin")), Il2pBeacon.class) ) {
			SsdvDecoder decoder = new SsdvDecoder(new SilversatPacketSource(is));
			while (decoder.hasNext()) {
				SsdvImage image = decoder.next();
				total++;
				Jy1satSsdvPacketSourceTest.assertSsdvImage("silversat", image);
			}
			assertEquals(1, total);
		}
	}
	
}
