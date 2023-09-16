package ru.r2cloud.jradio.sputnix;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.usp.UspBeacon;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SputnixBeacon extends UspBeacon {

	private SputnixTelemetry telemetry;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		telemetry = new SputnixTelemetry(ldis);
	}

	public SputnixTelemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(SputnixTelemetry telemetry) {
		this.telemetry = telemetry;
	}
}
