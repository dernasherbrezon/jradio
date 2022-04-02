package ru.r2cloud.jradio.nayif1;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.ao40.Ao40BeaconSource;
import ru.r2cloud.jradio.ao40.Ao40CorrelateAccessCodeTag;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Nayif1 extends Ao40BeaconSource<Nayif1Beacon> {

	public Nayif1(ByteInput bpsk) {
		super(new Ao40CorrelateAccessCodeTag(bpsk, 8));
	}

	@Override
	protected Nayif1Beacon parseAo40Beacon(byte[] raw) throws UncorrectableException, IOException {
		Nayif1Beacon result = new Nayif1Beacon();
		result.readExternal(raw);
		return result;
	}

}
