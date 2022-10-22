package ru.r2cloud.jradio.ccsds;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.PhaseAmbiguityResolver;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.fec.ViterbiSoft;
import ru.r2cloud.jradio.fec.ccsds.Randomize;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class CcsdsBeaconSource<T extends Beacon> extends BeaconSource<T> {

	private static final Logger LOG = LoggerFactory.getLogger(CcsdsBeaconSource.class);

	private final PhaseAmbiguityResolver phaseAmbiguityResolver;
	private final ViterbiSoft viterbiSoft;
	private final Class<T> clazz;

	public CcsdsBeaconSource(ByteInput input, Class<T> clazz) {
		if (!input.getContext().getSoftBits()) {
			throw new IllegalArgumentException("expected soft bits");
		}
		int totalBits = (255 + 1) * 2 * 8;
		// viterbi encoded 0x1acffc1d
		this.phaseAmbiguityResolver = new PhaseAmbiguityResolver(0x56081C971AA73D3EL);
		this.viterbiSoft = new ViterbiSoft((byte) 0x4f, (byte) 0x6d, true, totalBits);
		this.input = new CorrelateSyncword(input, 16, phaseAmbiguityResolver.getSynchronizationMarkers(), totalBits);
		this.clazz = clazz;
	}

	public byte[] decode(byte[] raw) throws UncorrectableException {
		long accessCode = input.getContext().getCurrentMarker().getAccessCode();
		phaseAmbiguityResolver.rotateSoft(raw, accessCode);
		byte[] data = viterbiSoft.decode(raw);
		Randomize.shuffle(data);
		return ReedSolomon.CCSDS.decodeDualBasis(data);
	}

	@Override
	protected T parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		raw = decode(raw);
		if (raw == null) {
			return null;
		}
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
