package ru.r2cloud.jradio;

import java.io.IOException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.HdlcReceiver;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Ax25G3ruhBeaconSource<T extends Beacon> extends BeaconSource<T> {

	private static final Logger LOG = LoggerFactory.getLogger(Ax25G3ruhBeaconSource.class);
	// flag is 01111110. last bit is always discarded
	private static final int FLAG_LENGTH = 7;
	private static final int MAXLENGTHBYTES = 512;
	private static final int FCS_LENGTH = 2;

	private final Class<T> clazz;
	private final Integer seed;
	private final byte[] assistedHeader;
	private final boolean checksum;

	public Ax25G3ruhBeaconSource(ByteInput input, Class<T> clazz) {
		this(input, clazz, true);
	}

	public Ax25G3ruhBeaconSource(ByteInput input, Class<T> clazz, boolean checksum) {
		this(input, clazz, checksum, null);
	}

	public Ax25G3ruhBeaconSource(ByteInput input, Class<T> clazz, boolean checksum, byte[] assistedHeader) {
		super(new HdlcReceiver(new Descrambler(new NrziDecode(new SoftToHard(input)), 0x21, 0, 16), MAXLENGTHBYTES, Header.LENGTH_BYTES, checksum, false, assistedHeader));
		this.clazz = clazz;
		this.seed = null;
		this.checksum = checksum;
		this.assistedHeader = assistedHeader;
	}

	public Ax25G3ruhBeaconSource(ByteInput input, Class<T> clazz, boolean checksum, byte[] assistedHeader, String syncword, int seed) {
		super(new CorrelateSyncword(new SoftToHard(input), 3, Collections.singleton(syncword), ((MAXLENGTHBYTES + FCS_LENGTH) * 8) + FLAG_LENGTH));
		this.clazz = clazz;
		this.seed = seed;
		this.checksum = checksum;
		this.assistedHeader = assistedHeader;
	}

	@Override
	protected T parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		if (seed == null) {
			return parseBeaconFromFinalBytes(raw);
		}
		try (HdlcReceiver receiver = new HdlcReceiver(new Descrambler(new NrziDecode(new ArrayByteInput(raw)), 0x21, seed, 16), MAXLENGTHBYTES, Header.LENGTH_BYTES, checksum, true, assistedHeader)) {
			return parseBeaconFromFinalBytes(receiver.readBytes());
		}
	}

	private T parseBeaconFromFinalBytes(byte[] raw) throws UncorrectableException, IOException {
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
