package ru.r2cloud.jradio.florsat;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.NgHam;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Floripasat1Downlink extends BeaconSource<Floripasat1DownlinkBeacon> {

	public Floripasat1Downlink(MessageInput input) {
		super(new NgHam(input));
	}

	@Override
	protected Floripasat1DownlinkBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		Floripasat1DownlinkBeacon result = new Floripasat1DownlinkBeacon();
		result.readExternal(raw);
		return result;
	}

}
