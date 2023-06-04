package ru.r2cloud.jradio.sanosat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SanosatBeacon extends Beacon {

	private String callsign;
	private int type;
	private SanosatTelemetry telemetry;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(new DataInputStream(new ByteArrayInputStream(data)));
		dis.skipBytes(4);
		byte[] callsignBytes = new byte[7];
		dis.readFully(callsignBytes);
		callsign = new String(callsignBytes, StandardCharsets.US_ASCII).trim();
		type = dis.readUnsignedShort();
		if (type == 0x0001) {
			telemetry = new SanosatTelemetry(dis);
		} else {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public SanosatTelemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(SanosatTelemetry telemetry) {
		this.telemetry = telemetry;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
