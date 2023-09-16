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
		try {
			telemetry = new SputnixTelemetry(ldis);
		} catch (Exception e) {
			super.readBeacon(dis);
		}
	}

	public SputnixTelemetry getTelemetry() {
		return telemetry;
	}
	
	public void setTelemetry(SputnixTelemetry telemetry) {
		this.telemetry = telemetry;
	}
}
