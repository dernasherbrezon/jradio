package ru.r2cloud.jradio.ctim;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ccsds.PacketPrimaryHeader;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.BitInputStream;

public class CtimBeacon extends Beacon {

	private Header header;
	private PacketPrimaryHeader primary;
	private SecondaryHeader secondary;
	private Telemetry telemetry;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		BitInputStream bis = new BitInputStream(dis);
		primary = new PacketPrimaryHeader(bis);
		if (primary.isSecondaryHeader()) {
			secondary = new SecondaryHeader(dis);
		}
		if (primary.getApplicationProcessId() == 0x01) {
			telemetry = new Telemetry(dis);
		} else {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public PacketPrimaryHeader getPrimary() {
		return primary;
	}

	public void setPrimary(PacketPrimaryHeader primary) {
		this.primary = primary;
	}

	public SecondaryHeader getSecondary() {
		return secondary;
	}

	public void setSecondary(SecondaryHeader secondary) {
		this.secondary = secondary;
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
