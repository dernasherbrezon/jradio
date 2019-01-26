package ru.r2cloud.jradio.astrocasat;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.fec.rs.bch.ReedSolomon;
import ru.r2cloud.jradio.util.MathUtils;

public class Astrocast extends BeaconSource<AstrocastBeacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Astrocast.class);
	private static final ReedSolomon rs = new ReedSolomon(32);

	public Astrocast(MessageInput input) {
		super(input);
	}

	@Override
	protected AstrocastBeacon parseBeacon(byte[] raw) {
		for (int i = 0; i < raw.length; i++) {
			raw[i] = (byte) MathUtils.reverseBitsInByte(raw[i] & 0xFF);
		}
		try {
			byte[] data = rs.decode(raw);
			byte[] frame = extractAx25Frame(data);
			if (frame == null) {
				return null;
			}
			frame = Arrays.copyOfRange(frame, 0, frame.length - 2);
			AstrocastBeacon result = new AstrocastBeacon();
			result.readExternal(frame);
			Float beginSample = (Float) input.getContext().getCurrent().get(CorrelateAccessCodeTag.SOURCE_SAMPLE);
			if (beginSample != null) {
				result.setBeginSample(beginSample.longValue());
			}
			return result;
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

	private static byte[] extractAx25Frame(byte[] data) {
		if ((data[0] & 0xFF) != 0x7e) {
			return null;
		}
		int endIndex = -1;
		for (int i = 1; i < data.length; i++) {
			if ((data[i] & 0xFF) == 0x7E) {
				endIndex = i;
				break;
			}
		}
		if (endIndex == -1) {
			return null;
		}
		return Arrays.copyOfRange(data, 1, endIndex);
	}

}
