package ru.r2cloud.jradio.atl1;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.fec.ViterbiSoft;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.smogp.SmogPShort;
import ru.r2cloud.jradio.smogp.SmogPShortCorrelate;

public class Atl1Short extends BeaconSource<Atl1Beacon> {

	private static final int RS_LENGTH = 128 + 32;

	private final ViterbiSoft viterbi;

	public Atl1Short(ByteInput input) {
		super(new SmogPShortCorrelate(input, 8));
		this.viterbi = new ViterbiSoft((byte) 0x4f, (byte) 0x6d, true, ((RS_LENGTH + 1) * 8) * 2);
	}

	@Override
	protected Atl1Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		Atl1Beacon result = new Atl1Beacon();
		result.readExternal(SmogPShort.fecDecode(viterbi, raw));
		return result;
	}

}
