package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.fec.ViterbiSoft;
import ru.r2cloud.jradio.fec.ccsds.Randomize;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.Deinterleave;

public class SmogPShort extends BeaconSource<SmogPBeacon> {

	private static final int SKIP = 80;
	private static final int RS_LENGTH = 128 + 32;
	private static final int ROWS = 51;
	private static final int COLS = 52;

	private final ViterbiSoft viterbi;

	public SmogPShort(ByteInput input) {
		super(new SmogPShortCorrelate(input, 8));
		this.viterbi = new ViterbiSoft((byte) 0x4f, (byte) 0x6d, true, ((RS_LENGTH + 1) * 8) * 2);
	}

	@Override
	protected SmogPBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		SmogPBeacon result = new SmogPBeacon();
		result.readExternal(fecDecode(raw));
		return result;
	}

	private byte[] fecDecode(byte[] data) throws UncorrectableException {
		byte[] symbols = Deinterleave.deinterleaveBitsUnpacked(data, 0, COLS, ROWS); // 2652
		byte[] skipped = new byte[((RS_LENGTH + 1) * 8) * 2]; // 2592
		System.arraycopy(symbols, SKIP, skipped, 0, symbols.length - SKIP);
		byte[] decoded = viterbi.decode(skipped);
		Randomize.shuffle(decoded);
		return ReedSolomon.decode(decoded);
	}

}
