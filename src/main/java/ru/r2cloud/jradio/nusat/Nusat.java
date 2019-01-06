package ru.r2cloud.jradio.nusat;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.crc.Crc8;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.fec.rs.bch.ReedSolomon;

public class Nusat extends BeaconSource<NusatBeacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Nusat.class);

	private ReedSolomon rs = new ReedSolomon(4);

	public Nusat(TaggedStreamToPdu input) {
		super(input);
	}

	@Override
	protected NusatBeacon parseBeacon(byte[] raw) {
		try {
			byte[] data = rs.decode(raw);
			int length = data[0] & 0xFF;
			//length field is not used anywhere. just log it
			if (LOG.isDebugEnabled()) {
				LOG.debug("the length is: {}", length);
			}
			int crc8 = data[1] & 0xFF;
			byte[] packet = Arrays.copyOfRange(data, 2, data.length);
			Randomize.shuffle(packet);
			if (Crc8.calculate(packet) != crc8) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("crc mismatch");
				}
				return null;
			}
			NusatBeacon beacon = new NusatBeacon();
			beacon.readExternal(packet);
			Float beginSample = (Float) input.getContext().getCurrent().get(CorrelateAccessCodeTag.SOURCE_SAMPLE);
			if (beginSample != null) {
				beacon.setBeginSample(beginSample.longValue());
			}
			return beacon;
		} catch (UncorrectableException e) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("unable to decode reed solomon: " + e.getMessage());
			}
			return null;
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		}
	}

}
