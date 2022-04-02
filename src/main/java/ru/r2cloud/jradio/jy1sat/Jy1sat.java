package ru.r2cloud.jradio.jy1sat;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.ao40.Ao40BeaconSource;
import ru.r2cloud.jradio.ao40.Ao40CorrelateAccessCodeTag;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Jy1sat extends Ao40BeaconSource<Jy1satBeacon> {

	public Jy1sat(ByteInput bpsk) {
		super(new Ao40CorrelateAccessCodeTag(bpsk, 8));
	}

	@Override
	protected Jy1satBeacon parseAo40Beacon(byte[] raw) throws UncorrectableException, IOException {
		Jy1satBeacon result = new Jy1satBeacon();
		result.readExternal(raw);
		return result;
	}

}
