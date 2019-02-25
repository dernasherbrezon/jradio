package ru.r2cloud.jradio.suomi100;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Beacon0Com {

	private long timestamp;
	private short[] temp;
	private short rssi;
	private short rferr;
	private short rssiBackground;

	public Beacon0Com(DataInputStream dis) throws IOException {
		timestamp = StreamUtils.readUnsignedInt(dis);
		temp = new short[2];
		temp[0] = dis.readShort();
		temp[1] = dis.readShort();
		rssi = dis.readShort();
		rferr = dis.readShort();
		rssiBackground = dis.readShort();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public short[] getTemp() {
		return temp;
	}

	public void setTemp(short[] temp) {
		this.temp = temp;
	}

	public short getRssi() {
		return rssi;
	}

	public void setRssi(short rssi) {
		this.rssi = rssi;
	}

	public short getRferr() {
		return rferr;
	}

	public void setRferr(short rferr) {
		this.rferr = rferr;
	}

	public short getRssiBackground() {
		return rssiBackground;
	}

	public void setRssiBackground(short rssiBackground) {
		this.rssiBackground = rssiBackground;
	}
	
}
