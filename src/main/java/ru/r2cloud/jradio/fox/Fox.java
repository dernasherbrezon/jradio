package ru.r2cloud.jradio.fox;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Fox<T extends Beacon> extends BeaconSource<T> {

	private static final Logger LOG = LoggerFactory.getLogger(Fox.class);

	public static final int SLOW_FRAME_SIZE = 96;
	private final Class<T> clazz;
	private final byte[] buffer = new byte[SLOW_FRAME_SIZE];

	public Fox(MessageInput input, Class<T> clazz) {
		super(input);
		this.clazz = clazz;
	}

	@Override
	protected T parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		byte[] decoded = decode10b(buffer, raw);
		byte[] message = ReedSolomon.CCSDS.decodeData(decoded);
		T result;
		try {
			result = clazz.newInstance();
		} catch (InstantiationException e) {
			LOG.error("unable to init beacon", e);
			return null;
		} catch (IllegalAccessException e) {
			LOG.error("unable to read beacon", e);
			return null;
		}
		result.readExternal(message);
		return result;
	}

	static byte[] decode10b(byte[] buffer, byte[] raw) throws UncorrectableException {
		for (int i = 0; i < buffer.length; i++) {
			int cur = 0;
			for (int j = 0; j < 10; j++) {
				cur += ((raw[i * 10 + j] << (10 - j - 1)));
			}
			buffer[i] = Code8b10b.decode(cur);
		}
		return buffer;
	}

}
