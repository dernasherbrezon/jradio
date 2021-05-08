package ru.r2cloud.jradio.bugsat;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class BugsatBeacon extends Ax25Beacon {

	private String message;
	private byte[] unknownPayload;
	private BugsatTelemetry telemetry;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		int id0 = dis.readUnsignedByte();
		if (id0 == 0x3A) {
			byte[] data = new byte[dis.available()];
			dis.readFully(data);
			message = new String(data, StandardCharsets.US_ASCII).trim();
			return;
		}
		int code = id0;
		if (id0 == 0xFF) {
			code = (code << 8) + dis.readUnsignedByte();
			code = (code << 8) + dis.readUnsignedByte();
		}

		if (code != 0xFFFFF0) {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
			return;
		}
		telemetry = new BugsatTelemetry(dis);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

	public BugsatTelemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(BugsatTelemetry telemetry) {
		this.telemetry = telemetry;
	}

}
