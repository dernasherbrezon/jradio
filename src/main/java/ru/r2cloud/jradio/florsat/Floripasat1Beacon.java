package ru.r2cloud.jradio.florsat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Floripasat1Beacon extends Beacon {

	private String sourceCallsign;
	private OBDHData obdhData;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		int type = dis.readUnsignedByte();
		byte[] sourceCallsignData = new byte[7];
		dis.readFully(sourceCallsignData);
		sourceCallsign = new String(sourceCallsignData, StandardCharsets.ISO_8859_1);
		switch (type) {
		case 0:
			obdhData = new OBDHData(dis);
			break;
			// TODO more types
		default:
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public String getSourceCallsign() {
		return sourceCallsign;
	}

	public void setSourceCallsign(String sourceCallsign) {
		this.sourceCallsign = sourceCallsign;
	}

	public OBDHData getObdhData() {
		return obdhData;
	}

	public void setObdhData(OBDHData obdhData) {
		this.obdhData = obdhData;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
