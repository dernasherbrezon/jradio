package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.crc.Crc16CcittFalse;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.fec.ra.RaDecoder;

public class Mrc100<T extends ru.r2cloud.jradio.Beacon> extends BeaconSource<T> {

	private static final Logger LOG = LoggerFactory.getLogger(Mrc100.class);
	private final int raSize;
	private final byte[] temp;
	private final Class<T> clazz;

	public Mrc100(ByteInput input, int raSize, int messageSize, Class<T> clazz) {
		super(new CorrelateSyncword(input, 4, "11100011000111001001110110101110", messageSize * 8));
		this.raSize = raSize;
		this.temp = new byte[raSize];
		this.clazz = clazz;
	}

	@Override
	protected T parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		RaDecoder decoder = new RaDecoder(raSize);
		byte[] data = decoder.decode(raw);
		temp[0] = data[0];
		System.arraycopy(data, 3, temp, 1, temp.length - 3);
		temp[temp.length - 1] = data[1];
		temp[temp.length - 2] = data[2];
		if (Crc16CcittFalse.calculate(temp, 0, temp.length) != 0) {
			throw new UncorrectableException("crc mismatch");
		}
		if ((data[0] & 0xFF) == 0xc5) {
			// skip sync packets
			return null;
		}
		T result;
		try {
			result = clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			LOG.error("unable to init beacon", e);
			return null;
		}
		result.readExternal(data);
		return result;
	}

}
