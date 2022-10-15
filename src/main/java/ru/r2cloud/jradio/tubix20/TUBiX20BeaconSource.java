package ru.r2cloud.jradio.tubix20;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.InvertBits;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.mobitex.MobitexBeaconSource;

public class TUBiX20BeaconSource<T extends TUBiX20Beacon> extends BeaconSource<T> {

	private static final Logger LOG = LoggerFactory.getLogger(MobitexBeaconSource.class);

	private final Class<T> clazz;

	public TUBiX20BeaconSource(ByteInput input, Class<T> clazz) {
		super(new CorrelateSyncword(new InvertBits(new SoftToHard(input)), 6, "111011110000111011110000", TUBiX20Beacon.MAX_SIZE * 8));
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
