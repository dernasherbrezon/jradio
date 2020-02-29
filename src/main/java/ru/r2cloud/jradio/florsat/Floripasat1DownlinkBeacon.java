package ru.r2cloud.jradio.florsat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Floripasat1DownlinkBeacon extends Beacon {

	private String sourceCallsign;
	private Telemetry telemetry;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		int type = dis.readUnsignedByte();
		byte[] sourceCallsignData = new byte[7];
		dis.readFully(sourceCallsignData);
		sourceCallsign = new String(sourceCallsignData, StandardCharsets.ISO_8859_1);
		switch (type) {
		case 16:
			telemetry = new Telemetry(dis);
			break;
		default:
			if (dis.available() > 0) {
				unknownPayload = new byte[dis.available()];
				dis.readFully(unknownPayload);
			}
		}
	}

	public String getSourceCallsign() {
		return sourceCallsign;
	}

	public void setSourceCallsign(String sourceCallsign) {
		this.sourceCallsign = sourceCallsign;
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
