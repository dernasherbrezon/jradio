package ru.r2cloud.jradio.geoscan;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.AdditiveScrambler;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.crc.Crc16Cc11xx;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Geoscan<T extends Beacon> extends BeaconSource<T> {

	private static final Logger LOG = LoggerFactory.getLogger(Geoscan.class);

	private final AdditiveScrambler scrambler;
	private final Class<T> clazz;

	public Geoscan(ByteInput demod, Class<T> clazz) {
		this(demod, clazz, 66);
	}

	public Geoscan(ByteInput demod, Class<T> clazz, int beaconSizeBytes) {
		super(new CorrelateSyncword(new SoftToHard(demod), 4, "10010011000010110101000111011110", beaconSizeBytes * 8));
		scrambler = new AdditiveScrambler(0x21, 0x1ff, 8, 8);
		this.clazz = clazz;
	}

	@Override
	protected T parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		raw = UnpackedToPacked.pack(raw);
		scrambler.shuffle(raw);
		if (Crc16Cc11xx.calculate(raw, 0, raw.length) != 0) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc mismatch");
			}
			return null;
		}
		// cutoff crc
		byte[] payload = new byte[raw.length - 2];
		System.arraycopy(raw, 0, payload, 0, payload.length);
		T result;
		try {
			result = clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			LOG.error("unable to init beacon", e);
			return null;
		}
		result.readExternal(payload);
		return result;
	}

}
