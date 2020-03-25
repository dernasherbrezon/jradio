package ru.r2cloud.jradio.atl1;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.ao40.Ao40BeaconSource;
import ru.r2cloud.jradio.ao40.Ao40CorrelateAccessCodeTag;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Atl1 extends Ao40BeaconSource<Atl1Beacon> {

	public Atl1(ByteInput input) {
		super(new Ao40CorrelateAccessCodeTag(input, 8));
	}

	@Override
	protected Atl1Beacon parseAo40Beacon(byte[] raw) throws UncorrectableException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
