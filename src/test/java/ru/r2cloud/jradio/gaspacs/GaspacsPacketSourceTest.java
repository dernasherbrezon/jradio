package ru.r2cloud.jradio.gaspacs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.BeaconInputStream;
import ru.r2cloud.jradio.jy1sat.Jy1satSsdvPacketSourceTest;
import ru.r2cloud.ssdv.SsdvDecoder;
import ru.r2cloud.ssdv.SsdvImage;
import ru.r2cloud.ssdv.SsdvPacket;

public class GaspacsPacketSourceTest {

	@Test
	public void testSuccess() throws Exception {
		List<GaspacsBeacon> beacons = new ArrayList<>();
		try (BeaconInputStream<GaspacsBeacon> bis = new BeaconInputStream<>(GaspacsPacketSourceTest.class.getClassLoader().getResourceAsStream("gaspacs.bin"), GaspacsBeacon.class)) {
			while (bis.hasNext()) {
				beacons.add(bis.next());
			}
		}
		// this is a real-world packets received from space
		// they are unsorted for some reason
		// extract them into List for SsdvDecoder to sort and product better picture
		List<SsdvPacket> packets = new ArrayList<>();
		GaspacsPacketSource source = new GaspacsPacketSource(beacons.iterator());
		while (source.hasNext()) {
			packets.add(source.next());
		}
		int total = 0;
		SsdvDecoder decoder = new SsdvDecoder(packets);
		while (decoder.hasNext()) {
			SsdvImage image = decoder.next();
			total++;
			Jy1satSsdvPacketSourceTest.assertSsdvImage("gaspacs", image);
		}
		assertEquals(1, total);
	}

}
