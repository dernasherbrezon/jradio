package ru.r2cloud.jradio.beesat4;

import java.io.IOException;

import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.tubix20.TUBiX20;

public class Beesat4 extends TUBiX20<Beesat4Beacon> {

	public Beesat4(TaggedStreamToPdu input) {
		super(input);
	}

	@Override
	protected Beesat4Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		Beesat4Beacon beacon = new Beesat4Beacon();
		beacon.readExternal(raw);
		return beacon;
	}

}
