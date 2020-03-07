package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class AckInfo {

	private short serial;
	private float rssi;

	public AckInfo() {
		// do nothing
	}

	public AckInfo(LittleEndianDataInputStream dis) throws IOException {
		serial = dis.readShort();
		rssi = (dis.readUnsignedByte() - 2.0f * 131.0f) / 2.0f;
	}

	public short getSerial() {
		return serial;
	}

	public void setSerial(short serial) {
		this.serial = serial;
	}

	public float getRssi() {
		return rssi;
	}

	public void setRssi(float rssi) {
		this.rssi = rssi;
	}

}
