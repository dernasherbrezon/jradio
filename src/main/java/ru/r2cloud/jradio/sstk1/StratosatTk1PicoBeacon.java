package ru.r2cloud.jradio.sstk1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.RawBeacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class StratosatTk1PicoBeacon extends RawBeacon {

	private StratosatTk1PicoTelemetry telemetry;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		try {
			telemetry = new StratosatTk1PicoTelemetry(new DataInputStream(new ByteArrayInputStream(data)));
		} catch (Exception e) {
			// can receive non-telemetry data
			super.readBeacon(data);
		}
	}

	public StratosatTk1PicoTelemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(StratosatTk1PicoTelemetry telemetry) {
		this.telemetry = telemetry;
	}

}
