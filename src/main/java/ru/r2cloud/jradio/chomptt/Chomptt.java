package ru.r2cloud.jradio.chomptt;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Chomptt extends BeaconSource<ChompttBeacon> {

	public Chomptt(ByteInput input) {
		super(new HdlcReceiver(new NrziDecode(new SoftToHard(input)), 1000));
	}

	@Override
	protected ChompttBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		ChompttBeacon result = new ChompttBeacon();
		result.readExternal(raw);
		return result;
	}

}
