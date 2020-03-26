package ru.r2cloud.jradio.itasat1;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Itasat1 extends BeaconSource<Itaset1Beacon> {

	public Itasat1(ByteInput input) {
		super(new HdlcReceiver(new NrziDecode(input), 10000));
	}

	@Override
	protected Itaset1Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		Itaset1Beacon result = new Itaset1Beacon();
		result.readExternal(raw);
		return result;
	}

}
