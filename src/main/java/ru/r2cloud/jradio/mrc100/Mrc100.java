package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.crc.Crc16CcittFalse;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.fec.ra.RaDecoder;

public class Mrc100 extends BeaconSource<Mrc100Beacon> {

	private final int raSize;
	private final byte[] temp;

	public Mrc100(ByteInput input, int raSize, int messageSize) {
		super(new CorrelateSyncword(input, 4, "11100011000111001001110110101110", messageSize * 8));
		this.raSize = raSize;
		this.temp = new byte[raSize];
	}

	@Override
	protected Mrc100Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		RaDecoder decoder = new RaDecoder(raSize);
		byte[] data = decoder.decode(raw);
		temp[0] = data[0];
		System.arraycopy(data, 3, temp, 1, temp.length - 3);
		temp[temp.length - 1] = data[1];
		temp[temp.length - 2] = data[2];
		if (Crc16CcittFalse.calculate(temp, 0, temp.length) != 0) {
			System.out.println("fail");
			throw new UncorrectableException("crc mismatch");
		}
		if ((data[0] & 0xFF) == 0xc5) {
			System.out.println("sync");
			// skip sync packets
			return null;
		}
		Mrc100Beacon result = new Mrc100Beacon();
		result.readExternal(data);
		return result;
	}

}
