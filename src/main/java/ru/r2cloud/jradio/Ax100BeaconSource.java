package ru.r2cloud.jradio;

import java.io.IOException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.gomx1.AX100Decoder;

public class Ax100BeaconSource<T extends Beacon> extends BeaconSource<T> {

	private static final String DEFAULT_SYNCWORD = "10010011000010110101000111011110";

	private static final Logger LOG = LoggerFactory.getLogger(Ax100BeaconSource.class);

	private final Class<T> clazz;

	public Ax100BeaconSource(ByteInput input, Class<T> clazz) {
		this(input, 512, DEFAULT_SYNCWORD, clazz, false, true, true);
	}

	public Ax100BeaconSource(ByteInput input, int beaconSizeBytes, Class<T> clazz) {
		this(input, beaconSizeBytes, DEFAULT_SYNCWORD, clazz, false, true, true);
	}

	public Ax100BeaconSource(ByteInput input, int beaconSizeBytes, Class<T> clazz, boolean forceViterbi, boolean forceScrambler, boolean forceReedSolomon) {
		this(input, beaconSizeBytes, DEFAULT_SYNCWORD, clazz, forceViterbi, forceScrambler, forceReedSolomon);
	}

	public Ax100BeaconSource(ByteInput input, int beaconSizeBytes, String syncword, Class<T> clazz, boolean forceViterbi, boolean forceScrambler, boolean forceReedSolomon) {
		super(new AX100Decoder(new CorrelateSyncword(input, 6, Collections.singleton(syncword), (beaconSizeBytes + 3) * 8), forceViterbi, forceScrambler, forceReedSolomon));
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
