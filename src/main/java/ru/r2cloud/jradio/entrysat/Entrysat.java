package ru.r2cloud.jradio.entrysat;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.demod.BpskDemodulator;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Entrysat extends BeaconSource<EntrysatBeacon> {

	public Entrysat(BpskDemodulator bpsk) {
		super(new HdlcReceiver(new Descrambler(new NrziDecode(new SoftToHard(bpsk)), 0x21, 0, 16), 10000));
	}

	@Override
	protected EntrysatBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		EntrysatBeacon result = new EntrysatBeacon();
		result.readExternal(raw);
		return result;
	}

}
