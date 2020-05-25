package ru.r2cloud.jradio.painani1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Painani1Beacon extends Beacon {

	private Header header;
	private Telemetry telemetry;
	private ShortTelemetry shortTelemetry;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		if (data.length == 30) {
			shortTelemetry = new ShortTelemetry(dis);
		} else if (data.length == 72) {
			int id = dis.readUnsignedShort();
			if (id != 0x3cc3) {
				throw new UncorrectableException("invalid id");
			}
			telemetry = new Telemetry(dis);
		} else {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public ShortTelemetry getShortTelemetry() {
		return shortTelemetry;
	}

	public void setShortTelemetry(ShortTelemetry shortTelemetry) {
		this.shortTelemetry = shortTelemetry;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
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
