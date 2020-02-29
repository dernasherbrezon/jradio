package ru.r2cloud.jradio.florsat;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.NgHam;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Floripasat1 extends BeaconSource<Floripasat1Beacon> {

	public Floripasat1(MessageInput input) {
		super(new NgHam(input));
	}

	@Override
	protected Floripasat1Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		Floripasat1Beacon result = new Floripasat1Beacon();
		result.readExternal(raw);
		return result;
	}

}
