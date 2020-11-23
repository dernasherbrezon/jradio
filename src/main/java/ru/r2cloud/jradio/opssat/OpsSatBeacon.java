package ru.r2cloud.jradio.opssat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class OpsSatBeacon extends Beacon {

	private ru.r2cloud.jradio.csp.Header cspHeader;
	private byte[] unknownPayload;
	private Telemetry telemetry;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		cspHeader = new ru.r2cloud.jradio.csp.Header(dis);
		// source = 5 is the telemetry
		// according to the spec, opssat can sometimes send non-telemetry packets on UHF
		if (cspHeader.getSource() != 5) {
			// 4 is for crc-32
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
			return;
		}

		telemetry = new Telemetry(dis);
	}

	public ru.r2cloud.jradio.csp.Header getCspHeader() {
		return cspHeader;
	}

	public void setCspHeader(ru.r2cloud.jradio.csp.Header cspHeader) {
		this.cspHeader = cspHeader;
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
