package ru.r2cloud.jradio.sstk1;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.AdditiveScrambler;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.crc.Crc16Cc11xx;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class StratosatTk1 extends BeaconSource<StratosatTk1Beacon> {
	
	private static final Logger LOG = LoggerFactory.getLogger(StratosatTk1.class);
	private final AdditiveScrambler scrambler;

	public StratosatTk1(ByteInput demod) {
		super(new CorrelateSyncword(new SoftToHard(demod), 4, "10010011000010110101000111011110", 66 * 8));
		scrambler = new AdditiveScrambler(0x21, 0x1ff, 8, 8);
	}

	@Override
	protected StratosatTk1Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		raw = UnpackedToPacked.pack(raw);
		scrambler.shuffle(raw);
		if (Crc16Cc11xx.calculate(raw, 0, raw.length) != 0) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc mismatch");
			}
			return null;
		}
		StratosatTk1Beacon result = new StratosatTk1Beacon();
		result.readExternal(raw);
		return result;
	}

}
