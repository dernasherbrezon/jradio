package ru.r2cloud.jradio.at03;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.crc.Crc16Arc;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.fec.rs.bch.ReedSolomon;

public class At03 extends BeaconSource<At03Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(At03.class);

	private final ReedSolomon rs = new ReedSolomon(16);

	public At03(MessageInput input) {
		super(input);
		if (!input.getContext().getSoftBits()) {
			throw new IllegalArgumentException("expected soft bits");
		}
	}

	@Override
	protected At03Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		raw = UnpackedToPacked.packSoft(raw, 0, raw.length / 8);
		byte[] data = rs.decode(raw);
		if (data.length != 48) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("unexpected packet length: {}", data.length);
			}
			return null;
		}
		int crc = Crc16Arc.calculate(data);
		if (crc != 0) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc mismatch");
			}
			return null;
		}
		At03Beacon beacon = new At03Beacon();
		beacon.readExternal(Arrays.copyOfRange(data, 0, 46));
		return beacon;
	}

}
