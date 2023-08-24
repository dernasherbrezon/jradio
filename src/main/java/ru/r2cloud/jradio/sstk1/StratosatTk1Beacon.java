package ru.r2cloud.jradio.sstk1;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class StratosatTk1Beacon extends Ax25Beacon {

	private Telemetry telemetry;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		telemetry = new Telemetry(new LittleEndianDataInputStream(dis));
	}

	public Telemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(Telemetry telemetry) {
		this.telemetry = telemetry;
	}

}
