package ru.r2cloud.jradio.randev;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class RandevBeacon extends Ax25Beacon {

	private Telemetry telemetry;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		try {
			telemetry = new Telemetry(new LittleEndianDataInputStream(dis));
		} catch (Exception e) {
			// no way to tell which are telemetry or other types of messages
			// assume EOFException means different type of message
			readBeacon(dis);
		}
	}

	public Telemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(Telemetry telemetry) {
		this.telemetry = telemetry;
	}
}
