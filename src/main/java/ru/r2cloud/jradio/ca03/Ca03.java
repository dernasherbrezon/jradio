package ru.r2cloud.jradio.ca03;

import java.io.IOException;
import java.util.Arrays;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Ca03 extends BeaconSource<Ca03Beacon> {

	public Ca03(MessageInput input) {
		super(input);
	}

	@Override
	protected Ca03Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		raw = UnpackedToPacked.pack(raw);
		int length = raw[0] & 0xFF;
		byte[] data = Arrays.copyOfRange(raw, 1, length + 1);
		data = ReedSolomon.decode(data);
		Ca03Beacon current = new Ca03Beacon();
		current.readExternal(data);
		return current;
	}

}
