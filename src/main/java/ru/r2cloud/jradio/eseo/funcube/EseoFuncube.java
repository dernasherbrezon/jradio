package ru.r2cloud.jradio.eseo.funcube;

import java.io.IOException;

import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.ao40.Ao40BeaconSource;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class EseoFuncube extends Ao40BeaconSource<EseoFuncubeBeacon> {

	public EseoFuncube(MessageInput input) {
		super(input);
	}

	@Override
	protected EseoFuncubeBeacon parseAo40Beacon(byte[] raw) throws UncorrectableException, IOException {
		EseoFuncubeBeacon result = new EseoFuncubeBeacon();
		result.readExternal(raw);
		return result;
	}

}
