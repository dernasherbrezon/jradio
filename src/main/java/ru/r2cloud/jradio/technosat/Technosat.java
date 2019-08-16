package ru.r2cloud.jradio.technosat;

import java.io.IOException;

import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.tubix20.TUBiX20;

public class Technosat extends TUBiX20<TechnosatBeacon> {

	public Technosat(TaggedStreamToPdu input) {
		super(input);
	}

	@Override
	protected TechnosatBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		TechnosatBeacon beacon = new TechnosatBeacon();
		beacon.readExternal(raw);
		return beacon;
	}

}
