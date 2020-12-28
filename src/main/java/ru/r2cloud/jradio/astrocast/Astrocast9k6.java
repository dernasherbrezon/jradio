package ru.r2cloud.jradio.astrocast;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.ccsds.Scrambler;
import ru.r2cloud.jradio.crc.Crc16Ibm3740;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Astrocast9k6 extends BeaconSource<Astrocast9k6Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Astrocast9k6.class);

	public Astrocast9k6(MessageInput input) {
		super(input);
	}

	@Override
	protected Astrocast9k6Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		raw = UnpackedToPacked.pack(raw);
		Scrambler.shuffle(raw);
		byte[] frame = ReedSolomon.CCSDS.decodeDualBasis(raw, 5);
		int crc = ((frame[frame.length - 2] & 0xFF) << 8) | (frame[frame.length - 1] & 0xFF);
		if (Crc16Ibm3740.calculate(frame, 0, frame.length - 2) != crc) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc mismatch");
			}
			return null;
		}
		Astrocast9k6Beacon result = new Astrocast9k6Beacon();
		result.readExternal(frame);
		return result;
	}

}
