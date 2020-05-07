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
	private byte[] ackPayload;
	private byte[] imagePayload;
	private byte[] unknownPayload;
	private RamParams ramParams;
	private RamParams flashParams;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		ax25Header = new Header(dis);
		cspHeader = new ru.r2cloud.jradio.csp.Header(dis);
		if (dis.available() == 137) {
			telemetry = new Telemetry(dis);
		} else if (dis.available() == 232) {
			imagePayload = new byte[dis.available()];
			dis.readFully(imagePayload);
		} else if (dis.available() == 2) {
			ackPayload = new byte[dis.available()];
			dis.readFully(ackPayload);
		} else if (dis.available() == 44) {
			ramParams = new RamParams(dis);
			flashParams = new RamParams(dis);
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

	public byte[] getAckPayload() {
		return ackPayload;
	}

	public void setAckPayload(byte[] ackPayload) {
		this.ackPayload = ackPayload;
	}

	public byte[] getImagePayload() {
		return imagePayload;
	}

	public void setImagePayload(byte[] imagePayload) {
		this.imagePayload = imagePayload;
	}

	public RamParams getRamParams() {
		return ramParams;
	}

	public void setRamParams(RamParams ramParams) {
		this.ramParams = ramParams;
	}

	public RamParams getFlashParams() {
		return flashParams;
	}

	public void setFlashParams(RamParams flashParams) {
		this.flashParams = flashParams;
	}

}
