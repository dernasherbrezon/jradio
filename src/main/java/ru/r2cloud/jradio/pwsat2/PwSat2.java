package ru.r2cloud.jradio.pwsat2;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;

public class PwSat2 extends BeaconSource<PwSat2Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(PwSat2.class);

	public PwSat2(MessageInput input) {
		super(input);
	}

	@Override
	protected PwSat2Beacon parseBeacon(byte[] raw) {
		try {
			PwSat2Beacon result = new PwSat2Beacon();
			result.readExternal(raw);
			//FIXME
//			Float beginSample = (Float) input.getContext().getCurrent().get(CorrelateAccessCodeTag.SOURCE_SAMPLE);
//			if (beginSample != null) {
//				result.setBeginSample(beginSample.longValue());
//			}
			return result;
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		}
	}

}
