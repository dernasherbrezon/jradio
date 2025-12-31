package ru.r2cloud.jradio.lucky7;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.AdditiveScrambler;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.crc.Crc16Cc11xx;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Lucky7 extends BeaconSource<Lucky7Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Lucky7.class);

	private final AdditiveScrambler pn9 = new AdditiveScrambler(0x21, 0x1e1, 8, 1);

	public Lucky7(MessageInput input) {
		super(input);
		if (!input.getContext().getSoftBits()) {
			throw new IllegalArgumentException("expected soft bits");
		}
	}

	@Override
	protected Lucky7Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		SoftToHard.convertToHard(raw);
		pn9.shuffle(raw);
		byte[] packed = UnpackedToPacked.packLittleEndian(raw, 0, raw.length / 8);
		if (Crc16Cc11xx.calculate(packed) != 0) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc mismatch");
			}
			return null;
		}
		Lucky7Beacon result = new Lucky7Beacon();
		result.readExternal(packed);
		return result;
	}

}
