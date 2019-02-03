package ru.r2cloud.jradio.nayif1;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.ao40.Ao40BeaconSource;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;

public class Nayif1 extends Ao40BeaconSource<Nayif1Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Nayif1.class);

	public Nayif1(MessageInput input) {
		super(input);
	}

	@Override
	protected Nayif1Beacon parseAo40Beacon(byte[] raw) {
		Nayif1Beacon result = new Nayif1Beacon();
		try {
			result.readExternal(raw);
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		}
		Float beginSample = (Float) input.getContext().getCurrent().get(CorrelateAccessCodeTag.SOURCE_SAMPLE);
		if (beginSample != null) {
			result.setBeginSample(beginSample.longValue());
		}
		return result;
	}

}
