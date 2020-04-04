package ru.r2cloud.jradio.swampsat2;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Swampsat2 extends BeaconSource<Swampsat2Beacon> {
	
	public Swampsat2(ByteInput input) {
		super(new HdlcReceiver(new Descrambler(new NrziDecode(input), 0x21, 0, 16), 10000));
	}

	@Override
	protected Swampsat2Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		Swampsat2Beacon result = new Swampsat2Beacon();
		result.readExternal(raw);
		return result;
	}

}
