package ru.r2cloud.jradio.smog1;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.smogp.SmogPSignallingBeacon;

public class Smog1Signalling extends BeaconSource<SmogPSignallingBeacon> {

	public Smog1Signalling(ByteInput input) {
		super(new CorrelateSyncword(input, 8, "0010110111010100101000111001111000011010010101010110101111001011", 64 * 8));
	}

	@Override
	protected SmogPSignallingBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		SmogPSignallingBeacon result = new SmogPSignallingBeacon();
		result.readExternal(UnpackedToPacked.pack(raw));
		return result;
	}

}
