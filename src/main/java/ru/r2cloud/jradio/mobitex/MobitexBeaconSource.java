package ru.r2cloud.jradio.mobitex;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.InvertBits;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class MobitexBeaconSource<T extends Beacon> extends BeaconSource<T> {

	private static final Logger LOG = LoggerFactory.getLogger(MobitexBeaconSource.class);

	private final Class<T> clazz;

	public MobitexBeaconSource(ByteInput input, Class<T> clazz) {
		super(new CorrelateSyncword(new InvertBits(new SoftToHard(input)), 4, "0101011101100101", MobitexBeacon.MAX_SIZE * 8));
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
		result.readExternal(UnpackedToPacked.pack(raw));
		return result;
	}
}
