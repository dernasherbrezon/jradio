package ru.r2cloud.jradio;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Ax25G3ruhBeaconSource<T extends Beacon> extends BeaconSource<T> {

	private static final Logger LOG = LoggerFactory.getLogger(Ax25G3ruhBeaconSource.class);

	private final Class<T> clazz;

	public Ax25G3ruhBeaconSource(ByteInput input, Class<T> clazz) {
		super(new HdlcReceiver(new Descrambler(new NrziDecode(new SoftToHard(input)), 0x21, 0, 16), 10000, Header.LENGTH_BYTES, true));
		this.clazz = clazz;
	}

	@Override
	protected T parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		T result;
		try {
			result = clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			LOG.error("unable to init beacon", e);
			return null;
		}
		result.readExternal(raw);
		return result;
	}

}
