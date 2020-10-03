package ru.r2cloud.jradio.alsat1n;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Alsat1n extends BeaconSource<Alsat1nBeacon> {

	public Alsat1n(ByteInput input) {
		super(new HdlcReceiver(new Descrambler(new NrziDecode(new SoftToHard(input)), 0x21, 0, 16), 10000, Header.LENGTH_BYTES, true));
	}

	@Override
	protected Alsat1nBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		Alsat1nBeacon result = new Alsat1nBeacon();
		result.readExternal(raw);
		return result;
	}
}
