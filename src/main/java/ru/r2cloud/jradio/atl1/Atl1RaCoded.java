package ru.r2cloud.jradio.atl1;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.fec.ra.RaDecoder;

public class Atl1RaCoded extends BeaconSource<Atl1Beacon> {

	private final int size;

	public Atl1RaCoded(MessageInput input, int size) {
		super(input);
		this.size = size;
	}

	@Override
	protected Atl1Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		RaDecoder decoder = new RaDecoder(size);
		byte[] decoded = decoder.decode(raw);
		Atl1Beacon result = new Atl1Beacon();
		result.readExternal(decoded);
		return result;
	}

}
