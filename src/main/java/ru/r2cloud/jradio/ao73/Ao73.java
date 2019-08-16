package ru.r2cloud.jradio.ao73;

import java.io.IOException;

import ru.r2cloud.jradio.ao40.Ao40BeaconSource;
import ru.r2cloud.jradio.ao40.Ao40CorrelateAccessCodeTag;
import ru.r2cloud.jradio.demod.BpskDemodulator;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Ao73 extends Ao40BeaconSource<Ao73Beacon> {

	public Ao73(BpskDemodulator bpsk) {
		super(new Ao40CorrelateAccessCodeTag(bpsk, 8));
	}

	@Override
	protected Ao73Beacon parseAo40Beacon(byte[] raw) throws UncorrectableException, IOException {
		Ao73Beacon result = new Ao73Beacon();
		result.readExternal(raw);
		return result;
	}

}
