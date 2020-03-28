package ru.r2cloud.jradio.itasat1;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Itasat1 extends BeaconSource<Itasat1Beacon> {

	public Itasat1(ByteInput input) {
		super(new HdlcReceiver(new NrziDecode(input), 10000));
	}

	@Override
	protected Itasat1Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		Itasat1Beacon result = new Itasat1Beacon();
		result.readExternal(raw);
		return result;
	}

}
