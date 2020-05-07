package ru.r2cloud.jradio.quetzal1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Quetzal1Beacon extends Beacon {

	private Header ax25Header;
	private ru.r2cloud.jradio.csp.Header cspHeader;
	private Telemetry telemetry;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		ax25Header = new Header(dis);
		cspHeader = new ru.r2cloud.jradio.csp.Header(dis);
		if (dis.available() == 137) {
			telemetry = new Telemetry(dis);
		} else {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public Header getAx25Header() {
		return ax25Header;
	}

	public void setAx25Header(Header ax25Header) {
		this.ax25Header = ax25Header;
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
