package ru.r2cloud.jradio.fmn1;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.demod.BpskDemodulator;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Fmn1 extends BeaconSource<Fmn1Beacon> {

	public Fmn1(BpskDemodulator bpsk) {
		super(new HdlcReceiver(new Descrambler(new NrziDecode(new SoftToHard(bpsk)), 0x21, 0, 16), 10000));
	}

	@Override
	protected Fmn1Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		Fmn1Beacon result = new Fmn1Beacon();
		result.readExternal(raw);
		return result;
	}

}
