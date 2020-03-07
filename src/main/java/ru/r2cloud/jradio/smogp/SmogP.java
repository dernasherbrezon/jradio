package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.ao40.Ao40BeaconSource;
import ru.r2cloud.jradio.ao40.Ao40CorrelateAccessCodeTag;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class SmogP extends Ao40BeaconSource<SmogPBeacon> {

	public SmogP(ByteInput input) {
		super(new Ao40CorrelateAccessCodeTag(input, 8));
	}

	@Override
	protected SmogPBeacon parseAo40Beacon(byte[] raw) throws UncorrectableException, IOException {
		SmogPBeacon result = new SmogPBeacon();
		result.readExternal(raw);
		return result;
	}

}
