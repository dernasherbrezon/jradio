package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.fec.ra.RaDecoder;

public class SmogPRaCoded extends BeaconSource<SmogPBeacon> {

	private final int size;

	public SmogPRaCoded(MessageInput input, int size) {
		super(input);
		this.size = size;
	}

	@Override
	protected SmogPBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		RaDecoder decoder = new RaDecoder(size);
		byte[] decoded = decoder.decode(raw);
		SmogPBeacon result = new SmogPBeacon();
		result.readExternal(decoded);
		return result;
	}

}
