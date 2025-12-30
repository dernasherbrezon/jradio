package ru.r2cloud.jradio.astrocast;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.ccsds.Scrambler;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Astrocast9k6 extends BeaconSource<Astrocast9k6Beacon> {

	public Astrocast9k6(MessageInput input) {
		super(input);
		if (!input.getContext().getSoftBits()) {
			throw new IllegalArgumentException("expected soft bits");
		}
	}

	@Override
	protected Astrocast9k6Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		raw = UnpackedToPacked.packSoft(raw, 0, raw.length / 8);
		Scrambler.shuffle(raw);
		Astrocast9k6Beacon result = new Astrocast9k6Beacon();
		result.readExternal(ReedSolomon.CCSDS.decodeDualBasis(raw, 5));
		return result;
	}

}
