package ru.r2cloud.jradio.polyitan1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PolyItan1Beacon extends Beacon {

	private Header header;
	private String beacon0;
	private Beacon1 beacon1;
	private Beacon2 beacon2;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		int type = dis.readUnsignedByte();
		switch (type) {
		case 0:
			byte[] strBytes = new byte[30];
			dis.readFully(strBytes);
			beacon0 = new String(strBytes, StandardCharsets.US_ASCII).trim();
			break;
		case 1:
			beacon1 = new Beacon1(new LittleEndianDataInputStream(dis));
			break;
		case 2:
			beacon2 = new Beacon2(new LittleEndianDataInputStream(dis));
			break;
		default:
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
			break;
		}
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public String getBeacon0() {
		return beacon0;
	}

	public void setBeacon0(String beacon0) {
		this.beacon0 = beacon0;
	}

	public Beacon1 getBeacon1() {
		return beacon1;
	}

	public void setBeacon1(Beacon1 beacon1) {
		this.beacon1 = beacon1;
	}

	public Beacon2 getBeacon2() {
		return beacon2;
	}

	public void setBeacon2(Beacon2 beacon2) {
		this.beacon2 = beacon2;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
