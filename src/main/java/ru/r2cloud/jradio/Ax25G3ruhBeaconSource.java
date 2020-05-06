package ru.r2cloud.jradio;

import java.io.IOException;

import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Ax25G3ruhBeaconSource<T extends Beacon> extends BeaconSource<T> {

	private final Class<T> clazz;

	public Ax25G3ruhBeaconSource(ByteInput input, Class<T> clazz) {
		super(new HdlcReceiver(new Descrambler(new NrziDecode(new SoftToHard(input)), 0x21, 0, 16), 10000));
		this.clazz = clazz;
	}

	@Override
	protected T parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		try {
			T result = clazz.newInstance();
			result.readExternal(raw);
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
