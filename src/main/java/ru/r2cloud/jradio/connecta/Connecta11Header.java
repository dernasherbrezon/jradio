package ru.r2cloud.jradio.connecta;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Connecta11Header {

	private long beaconPreamble;
	private int beaconPreamble1;
	private int beaconId;

	public Connecta11Header() {
		// do nothing
	}

	public Connecta11Header(LittleEndianDataInputStream dis) throws IOException {
		beaconPreamble = dis.readUnsignedInt();
		beaconPreamble1 = dis.readUnsignedByte();
		beaconId = dis.readUnsignedByte();
		dis.skipBytes(10);
	}

	public long getBeaconPreamble() {
		return beaconPreamble;
	}

	public void setBeaconPreamble(long beaconPreamble) {
		this.beaconPreamble = beaconPreamble;
	}

	public int getBeaconPreamble1() {
		return beaconPreamble1;
	}

	public void setBeaconPreamble1(int beaconPreamble1) {
		this.beaconPreamble1 = beaconPreamble1;
	}

	public int getBeaconId() {
		return beaconId;
	}

	public void setBeaconId(int beaconId) {
		this.beaconId = beaconId;
	}

}
