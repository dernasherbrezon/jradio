package ru.r2cloud.jradio.ao40;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ViterbiSoft;
import ru.r2cloud.jradio.fec.ccsds.Randomize;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public abstract class Ao40BeaconSource<T> extends BeaconSource<T> {

	private static final Logger LOG = LoggerFactory.getLogger(Ao40BeaconSource.class);
	private static final int RS_LENGTH = 128 + 32;
	private static final int ROWS = 80;
	private static final int COLS = 65;
	
	private final ViterbiSoft viterbi;

	public Ao40BeaconSource(MessageInput input) {
		super(input);
		this.viterbi = new ViterbiSoft((byte) 0x4f, (byte) 0x6d, true, 5200);
	}

	@Override
	protected T parseBeacon(byte[] raw) {
		int col;
		int row;
		int coltop;
		int rowstart;
		byte[] symbols = new byte[((RS_LENGTH + RS_LENGTH) * 8 + 6) * 2 + 65 + 3];
		coltop = 0;
		for (col = 1; col < ROWS; col++) { /* Skip first column as it's the sync vector */
			rowstart = 0;
			for (row = 0; row < COLS; row++) {
				symbols[coltop + row] = raw[rowstart + col];
				rowstart += ROWS;
			}
			coltop += COLS;
		}
		byte[] data = viterbi.decode(symbols);
		Randomize.shuffle(data);
		byte[] packet = new byte[256];
		try {
			for (int i = 0; i < 2; i++) {
				byte[] part = new byte[RS_LENGTH];
				//de-interleave RS blocks
				for (int j = 0; j < RS_LENGTH; j++) {
					part[j] = data[2 * j + i];
				}
				part = ReedSolomon.decode(part);
				//interleave RS blocks back
				for (int j = 0; j < part.length; j++) {
					packet[2 * j + i] = part[j];
				}
			}
			return parseAo40Beacon(packet);
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		} catch (UncorrectableException e) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("unable to decode reed solomon: {}", e.getMessage());
			}
			return null;
		}
	}

	protected abstract T parseAo40Beacon(byte[] raw) throws UncorrectableException, IOException;

}
