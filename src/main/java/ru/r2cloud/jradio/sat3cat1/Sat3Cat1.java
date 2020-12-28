package ru.r2cloud.jradio.sat3cat1;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.AdditiveScrambler;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.fec.rs.bch.ReedSolomon;

public class Sat3Cat1 extends BeaconSource<Sat3Cat1Beacon> {

	private final AdditiveScrambler scrambler;
	private final ReedSolomon rs = new ReedSolomon(32);

	public Sat3Cat1(MessageInput input) {
		super(input);
		scrambler = new AdditiveScrambler(0x21, 0x1ff, 8, 8);
	}

	@Override
	protected Sat3Cat1Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		raw = UnpackedToPacked.pack(raw);
		scrambler.shuffle(raw);
		byte[] data = rs.decode(raw);
		Sat3Cat1Beacon beacon = new Sat3Cat1Beacon();
		beacon.readExternal(data);
		return beacon;
	}

}
