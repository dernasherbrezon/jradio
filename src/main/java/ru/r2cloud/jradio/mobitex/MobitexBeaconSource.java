package ru.r2cloud.jradio.mobitex;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.InvertBits;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class MobitexBeaconSource<T extends Beacon> extends BeaconSource<T> {

	private static final Logger LOG = LoggerFactory.getLogger(MobitexBeaconSource.class);

	private final Class<T> clazz;

	public MobitexBeaconSource(ByteInput input, Class<T> clazz) {
		this(input, clazz, MobitexBeacon.MAX_SIZE);
	}

	public MobitexBeaconSource(ByteInput input, Class<T> clazz, int maxLengthBytes) {
		super(new CorrelateSyncword(new InvertBits(input), 4, "0101011101100101", maxLengthBytes * 8));
		if (!input.getContext().getSoftBits()) {
			throw new IllegalArgumentException("expected soft bits");
		}
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
		result.readExternal(UnpackedToPacked.packSoft(raw, 0, raw.length / 8));
		return result;
	}
}
