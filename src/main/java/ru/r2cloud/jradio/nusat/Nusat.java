package ru.r2cloud.jradio.nusat;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.crc.Crc8;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.fec.rs.bch.ReedSolomon;

public class Nusat extends BeaconSource<NusatBeacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Nusat.class);

	private ReedSolomon rs = new ReedSolomon(4);

	public Nusat(MessageInput input) {
		super(input);
		if (!input.getContext().getSoftBits()) {
			throw new IllegalArgumentException("expected soft bits");
		}
	}

	@Override
	protected NusatBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		raw = UnpackedToPacked.packSoft(raw, 0, raw.length / 8);
		byte[] data = rs.decode(raw);
		int length = data[0] & 0xFF;
		// length field is not used anywhere. just log it
		if (LOG.isDebugEnabled()) {
			LOG.debug("the length is: {}", length);
		}
		int crc8 = data[1] & 0xFF;
		Randomize.shuffle(data, 2, data.length - 2);
		if (Crc8.calculate(data, 2, data.length - 2) != crc8) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc mismatch");
			}
			return null;
		}
		byte[] packet = Arrays.copyOfRange(data, 2, data.length);
		NusatBeacon beacon = new NusatBeacon();
		beacon.readExternal(packet);
		return beacon;
	}

}
