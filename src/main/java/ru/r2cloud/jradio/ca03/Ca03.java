package ru.r2cloud.jradio.ca03;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Ca03 extends BeaconSource<Ca03Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Ca03.class);

	public Ca03(TaggedStreamToPdu input) {
		super(input);
	}

	@Override
	protected Ca03Beacon parseBeacon(byte[] raw) {
		int length = raw[0] & 0xFF;
		try {
			byte[] data = Arrays.copyOfRange(raw, 1, length + 1);
			data = ReedSolomon.decode(data);
			Ca03Beacon current = new Ca03Beacon();
			current.readExternal(data);
			return current;
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
