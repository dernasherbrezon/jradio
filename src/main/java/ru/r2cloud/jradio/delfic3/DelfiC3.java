package ru.r2cloud.jradio.delfic3;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.demod.BpskDemodulator;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class DelfiC3 extends BeaconSource<DelfiC3Beacon> {

	private static final int MAX_MESSAGE_SIZE = 1200;

	public DelfiC3(BpskDemodulator bpsk) {
		super(new HdlcReceiver(new SoftToHard(bpsk), MAX_MESSAGE_SIZE));
	}

	@Override
	protected DelfiC3Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		if (raw.length < 39) {
			return null;
		}
		DelfiC3Beacon result = new DelfiC3Beacon();
		result.readExternal(raw);
		return result;
	}

}
