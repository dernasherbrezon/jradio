package ru.r2cloud.jradio.ao73;

import java.io.IOException;

import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.ao40.Ao40BeaconSource;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Ao73 extends Ao40BeaconSource<Ao73Beacon> {

	public Ao73(MessageInput input) {
		super(input);
	}

	@Override
	protected Ao73Beacon parseAo40Beacon(byte[] raw) throws UncorrectableException, IOException {
		Ao73Beacon result = new Ao73Beacon();
		result.readExternal(raw);
		return result;
	}

}
