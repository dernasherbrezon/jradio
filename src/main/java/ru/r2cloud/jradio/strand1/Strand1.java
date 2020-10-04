package ru.r2cloud.jradio.strand1;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Strand1 extends BeaconSource<Strand1Beacon> {

	public Strand1(ByteInput input) {
		super(new HdlcReceiver(new Descrambler(new NrziDecode(new SoftToHard(input)), 0x21, 0, 16), 10000, Header.LENGTH_BYTES, true));
	}
	
	@Override
	protected Strand1Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		Strand1Beacon result = new Strand1Beacon();
		result.readExternal(raw);
		return result;
	}

}
