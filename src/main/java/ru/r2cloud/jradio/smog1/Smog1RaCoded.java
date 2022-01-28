package ru.r2cloud.jradio.smog1;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.crc.Crc16Arc;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.fec.ra.RaDecoder;

public class Smog1RaCoded extends BeaconSource<Smog1Beacon> {

	private final int raSize;

	public Smog1RaCoded(ByteInput input, int raSize, int messageSize) {
		super(new CorrelateSyncword(input, 6, "001011011101010001100011110001010011010110011001", messageSize * 8));
		this.raSize = raSize;
	}

	@Override
	protected Smog1Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		RaDecoder decoder = new RaDecoder(raSize);
		byte[] decoded = decoder.decode(raw);
		if (Crc16Arc.calculate(decoded) != 0) {
			throw new UncorrectableException("crc mismatch");
		}
		Smog1Beacon result = new Smog1Beacon();
		result.readExternal(decoded);
		return result;
	}

}
