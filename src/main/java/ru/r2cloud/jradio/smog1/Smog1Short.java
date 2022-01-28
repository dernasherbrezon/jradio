package ru.r2cloud.jradio.smog1;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.crc.Crc16Arc;
import ru.r2cloud.jradio.fec.ViterbiSoft;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.smogp.SmogPShort;
import ru.r2cloud.jradio.smogp.SmogPShortCorrelate;

public class Smog1Short extends BeaconSource<Smog1Beacon> {

	private static final int RS_LENGTH = 128 + 32;
	private final ViterbiSoft viterbi;

	public Smog1Short(ByteInput input) {
		super(new SmogPShortCorrelate(input, 8));
		this.viterbi = new ViterbiSoft((byte) 0x4f, (byte) 0x6d, true, ((RS_LENGTH + 1) * 8) * 2);
	}

	@Override
	protected Smog1Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		Smog1Beacon result = new Smog1Beacon();
		byte[] data = SmogPShort.fecDecode(viterbi, raw);
		if (Crc16Arc.calculate(data) != 0) {
			throw new UncorrectableException("crc mismatch");
		}
		result.readExternal(data);
		return result;
	}

}