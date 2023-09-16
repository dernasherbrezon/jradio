package ru.r2cloud.jradio.suomi100;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.csp.Header;

public class Suomi100Beacon extends Beacon {

	private Header header;
	private int beaconType;
	private Beacon0 beacon0;
	private Beacon1 beacon1;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		beaconType = dis.readUnsignedByte();
		if (beaconType == 0) {
			beacon0 = new Beacon0(dis);
		} else if (beaconType == 1) {
			beacon1 = new Beacon1(dis);
		} else {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}
	
	public byte[] getUnknownPayload() {
		return unknownPayload;
	}
	
	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public int getBeaconType() {
		return beaconType;
	}

	public void setBeaconType(int beaconType) {
		this.beaconType = beaconType;
	}

	public Beacon0 getBeacon0() {
		return beacon0;
	}

	public void setBeacon0(Beacon0 beacon0) {
		this.beacon0 = beacon0;
	}

	public Beacon1 getBeacon1() {
		return beacon1;
	}

	public void setBeacon1(Beacon1 beacon1) {
		this.beacon1 = beacon1;
	}

}
