package ru.r2cloud.jradio.smog1;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.ao40.Ao40BeaconSource;
import ru.r2cloud.jradio.ao40.Ao40CorrelateAccessCodeTag;
import ru.r2cloud.jradio.crc.Crc16Arc;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Smog1 extends Ao40BeaconSource<Smog1Beacon> {

	public Smog1(ByteInput input) {
		super(new Ao40CorrelateAccessCodeTag(input, 8));
	}

	@Override
	protected Smog1Beacon parseAo40Beacon(byte[] raw) throws UncorrectableException, IOException {
		Smog1Beacon result = new Smog1Beacon();
		if (Crc16Arc.calculate(raw) != 0) {
			throw new UncorrectableException("crc mismatch");
		}
		result.readExternal(raw);
		return result;
	}

}
