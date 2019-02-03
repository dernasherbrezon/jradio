package ru.r2cloud.jradio.eseo.funcube;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.ao40.Ao40BeaconSource;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;

public class EseoFuncube extends Ao40BeaconSource<EseoFuncubeBeacon> {

	private static final Logger LOG = LoggerFactory.getLogger(EseoFuncube.class);

	public EseoFuncube(MessageInput input) {
		super(input);
	}

	@Override
	protected EseoFuncubeBeacon parseAo40Beacon(byte[] raw) {
		EseoFuncubeBeacon result = new EseoFuncubeBeacon();
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
