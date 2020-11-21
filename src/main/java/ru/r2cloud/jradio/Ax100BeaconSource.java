package ru.r2cloud.jradio;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.gomx1.AX100Decoder;

public class Ax100BeaconSource<T extends Beacon> extends BeaconSource<T> {

	private static final Logger LOG = LoggerFactory.getLogger(Ax25G3ruhBeaconSource.class);

	private final Class<T> clazz;

	public Ax100BeaconSource(ByteInput input, int beaconSizeBytes, Class<T> clazz) {
		this(input, beaconSizeBytes, "10010011000010110101000111011110", clazz, false, true, true);
	}
	
	public Ax100BeaconSource(ByteInput input, int beaconSizeBytes, Class<T> clazz, boolean forceViterbi, boolean forceScrambler, boolean forceReedSolomon) {
		this(input, beaconSizeBytes, "10010011000010110101000111011110", clazz, forceViterbi, forceScrambler, forceReedSolomon);
	}
	
	public Ax100BeaconSource(ByteInput input, int beaconSizeBytes, String accessCode, Class<T> clazz, boolean forceViterbi, boolean forceScrambler, boolean forceReedSolomon) {
		super(new AX100Decoder(new TaggedStreamToPdu(new FixedLengthTagger(new CorrelateAccessCodeTag(input, 4, accessCode, true), (beaconSizeBytes + 3) * 8)), forceViterbi, forceScrambler, forceReedSolomon));
		this.clazz = clazz;
	}

	@Override
	protected T parseBeacon(byte[] raw) throws UncorrectableException, IOException {
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
		result.readExternal(raw);
		return result;
	}
}
