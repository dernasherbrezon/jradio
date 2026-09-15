package ru.r2cloud.jradio.ccsds;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.PhaseAmbiguityResolver;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.fec.Viterbi;
import ru.r2cloud.jradio.fec.ViterbiSoft;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class CcsdsBeaconSource<T extends Beacon> extends BeaconSource<T> {

	private static final Logger LOG = LoggerFactory.getLogger(CcsdsBeaconSource.class);

	private static final Set<Coding> SUPPORTED_CODING = new HashSet<>();
	private static final Set<ScramblerType> SUPPORTED_SCRAMBLERS = new HashSet<>();

	static {
		SUPPORTED_CODING.add(Coding.CONCANTENATED_RS255_239);
		SUPPORTED_CODING.add(Coding.CONVOLUTIONAL);
		SUPPORTED_CODING.add(Coding.UNCODED);

		SUPPORTED_SCRAMBLERS.add(ScramblerType.NONE);
		SUPPORTED_SCRAMBLERS.add(ScramblerType.CCITT);
		SUPPORTED_SCRAMBLERS.add(ScramblerType.IESS);
	}

	private final PhaseAmbiguityResolver phaseAmbiguityResolver;
	private final ViterbiSoft viterbiSoft;
	private final Class<T> clazz;
	private final CcsdsFraming framing;

	public CcsdsBeaconSource(ByteInput input, Class<T> clazz) {
		this(input, null, clazz);
	}

	public CcsdsBeaconSource(ByteInput input, CcsdsFraming framing, Class<T> clazz) {
		if (!input.getContext().getSoftBits()) {
			throw new IllegalArgumentException("expected soft bits");
		}
		if (framing != null) {
			this.framing = framing;
		} else {
			CcsdsFraming defaultFrame = new CcsdsFraming();
			defaultFrame.setFrameLength(255);
			defaultFrame.setScrambler(ScramblerType.CCITT);
			defaultFrame.setCoding(Coding.CONCANTENATED_RS255_239);
			defaultFrame.setSyncwordThreshold(14);
			defaultFrame.setSyncword("1ACFFC1D");
			this.framing = defaultFrame;
		}
		if (!SUPPORTED_CODING.contains(this.framing.getCoding())) {
			throw new IllegalArgumentException("unsupported coding: " + this.framing.getCoding());
		}
		if (!SUPPORTED_SCRAMBLERS.contains(this.framing.getScrambler())) {
			throw new IllegalArgumentException("unsupported scrambler: " + this.framing.getScrambler());
		}
		if (this.framing.getFrameLength() == 0) {
			throw new IllegalArgumentException("frameLength cannot be empty");
		}
		if (this.framing.getSyncword() == null || this.framing.getSyncword().trim().length() == 0) {
			// this is CCSDS beacon source. Assume default CCSDS syncword
			this.framing.setSyncword("1ACFFC1D");
		}
		int totalBits = this.framing.getFrameLength() * 8;
		if (isViterbiEnabled(this.framing.getCoding())) {
			totalBits += 1 * 8; // TAIL
			totalBits *= 2;
			this.viterbiSoft = new ViterbiSoft((byte) 0x4f, (byte) 0x6d, true, totalBits);
		} else {
			this.viterbiSoft = null;
		}
		int syncwordLengthBits = this.framing.getSyncword().length() * 4;
		long syncword = Long.valueOf(this.framing.getSyncword(), 16);
		if (this.viterbiSoft != null) {
			byte[] encodedSyncword = Viterbi.encode(longToBytes(syncword, syncwordLengthBits), (byte) 0x4f, (byte) 0x6d, true);
			syncwordLengthBits *= 2;
			this.phaseAmbiguityResolver = new PhaseAmbiguityResolver(bytesToLong(encodedSyncword, 0, syncwordLengthBits / 8), syncwordLengthBits);
		} else {
			this.phaseAmbiguityResolver = new PhaseAmbiguityResolver(syncword, syncwordLengthBits);
		}
		if (this.framing.getSyncwordThreshold() > syncwordLengthBits) {
			throw new IllegalArgumentException("syncword threshold " + this.framing.getSyncwordThreshold() + " cannot be more than the actual syncword: " + syncwordLengthBits);
		}
		this.input = new CorrelateSyncword(input, this.framing.getSyncwordThreshold(), phaseAmbiguityResolver.getSynchronizationMarkers(), totalBits);
		this.clazz = clazz;
	}

	public byte[] decode(byte[] raw) throws UncorrectableException {
		long accessCode = input.getContext().getCurrentMarker().getAccessCode();
		phaseAmbiguityResolver.rotateSoft(raw, accessCode);
		byte[] data;
		if (viterbiSoft != null) {
			data = viterbiSoft.decode(raw);
		} else {
			data = UnpackedToPacked.packSoft(raw, 0, raw.length / 8);
		}
		if (framing.getScrambler().equals(ScramblerType.CCITT)) {
			CcittScrambler.shuffle(data);
		} else if (framing.getScrambler().equals(ScramblerType.IESS)) {
			IessScrambler.shuffle(data);
		}
		if (framing.getCoding().equals(Coding.CONCANTENATED_RS255_239)) {
			return ReedSolomon.CCSDS.decodeDualBasis(data);
		}
		return data;
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

	private static boolean isViterbiEnabled(Coding coding) {
		return coding.equals(Coding.CONVOLUTIONAL) || coding.equals(Coding.CONCANTENATED_RS204_188) || coding.equals(Coding.CONCANTENATED_RS255_239);
	}

	static long bytesToLong(byte[] data, int offset, int length) {
		if (length > 8 || (offset + length) > data.length) {
			throw new IllegalArgumentException("invalid length: " + length);
		}
		long result = 0L;
		for (int i = offset; i < (offset + length); i++) {
			result = result << 8;
			result |= (data[i] & 0xFF);
		}
		return result;
	}

	static byte[] longToBytes(long data, int totalBits) {
		// do not support partial bytes
		byte[] result = new byte[totalBits / 8];
		for (int i = 0; i < result.length; i++) {
			result[i] = (byte) ((data >> (totalBits - (i + 1) * 8)) & 0xFF);
		}
		return result;
	}
}
