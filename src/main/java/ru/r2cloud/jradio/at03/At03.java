package ru.r2cloud.jradio.at03;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.crc.Crc16Arc;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.fec.rs.bch.ReedSolomon;

public class At03 extends BeaconSource<At03Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(At03.class);

	private final ReedSolomon rs = new ReedSolomon(16);

	public At03(TaggedStreamToPdu input) {
		super(input);
	}

	@Override
	protected At03Beacon parseBeacon(byte[] raw) {
		try {
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
					LOG.debug("checksum mismtach: {}", crc);
				}
				return null;
			}
			At03Beacon beacon = new At03Beacon();
			beacon.readExternal(Arrays.copyOfRange(data, 0, 46));
			return beacon;
		} catch (UncorrectableException e) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("unable to decode reed solomon: {}", e.getMessage());
			}
			return null;
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		}
	}

}
