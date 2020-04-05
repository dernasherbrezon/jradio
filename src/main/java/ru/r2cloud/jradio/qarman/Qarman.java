package ru.r2cloud.jradio.qarman;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Qarman extends BeaconSource<QarmanBeacon> {

	public Qarman(ByteInput input) {
		super(new HdlcReceiver(new Descrambler(new NrziDecode(input), 0x21, 0, 16), 10000));
	}

	@Override
	protected QarmanBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		QarmanBeacon result = new QarmanBeacon();
		result.readExternal(raw);
		return result;
	}

}
