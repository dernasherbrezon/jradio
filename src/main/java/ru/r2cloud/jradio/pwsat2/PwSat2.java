package ru.r2cloud.jradio.pwsat2;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.demod.BpskDemodulator;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class PwSat2 extends BeaconSource<PwSat2Beacon> {

	public PwSat2(BpskDemodulator bpsk) {
		super(new HdlcReceiver(new Descrambler(new NrziDecode(new SoftToHard(bpsk)), 0x21, 0, 16), 10000));
	}

	@Override
	protected PwSat2Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		PwSat2Beacon result = new PwSat2Beacon();
		result.readExternal(raw);
		return result;
	}

}
