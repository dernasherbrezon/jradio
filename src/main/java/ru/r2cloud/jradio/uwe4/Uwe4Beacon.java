package ru.r2cloud.jradio.uwe4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.netsat.CompassHeader;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Uwe4Beacon extends Ax25Beacon {

	private CompassHeader compassHeader;
	private Telemetry telemetry;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		compassHeader = new CompassHeader(ldis);
		if (compassHeader.getApi() != 14) {
			unknownPayload = new byte[compassHeader.getPayloadSize()];
			ldis.readFully(unknownPayload);
			return;
		}
		telemetry = new Telemetry(ldis);
	}

	public CompassHeader getCompassHeader() {
		return compassHeader;
	}

	public void setCompassHeader(CompassHeader compassHeader) {
		this.compassHeader = compassHeader;
	}

	public Telemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(Telemetry telemetry) {
		this.telemetry = telemetry;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
