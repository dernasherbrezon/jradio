package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class SmogPSignalling extends BeaconSource<SmogPSignallingBeacon> {

	public SmogPSignalling(MessageInput input) {
		super(input);
		if (!input.getContext().getSoftBits()) {
			throw new IllegalArgumentException("expected soft bits");
		}
	}

	@Override
	protected SmogPSignallingBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		SmogPSignallingBeacon result = new SmogPSignallingBeacon();
		result.readExternal(UnpackedToPacked.packSoft(raw, 0, raw.length / 8));
		return result;
	}

}
