package ru.r2cloud.jradio.geoscan;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Geoscan2Comm {

	private int uvbus;
	private byte rssiLast;
	private byte rssiMin;
	private int txNum;
	private int qsoNum;

	public Geoscan2Comm() {
		// do nothing
	}

	public Geoscan2Comm(LittleEndianDataInputStream dis) throws IOException {
		dis.skipBytes(1);
		uvbus = dis.readUnsignedShort();
		dis.skipBytes(2);
		rssiLast = dis.readByte();
		rssiMin = dis.readByte();
		dis.skipBytes(2);
		txNum = dis.readUnsignedByte();
		dis.skipBytes(3);
		qsoNum = dis.readUnsignedByte();
		dis.skipBytes(2);
	}

	public int getUvbus() {
		return uvbus;
	}

	public void setUvbus(int uvbus) {
		this.uvbus = uvbus;
	}

	public byte getRssiLast() {
		return rssiLast;
	}

	public void setRssiLast(byte rssiLast) {
		this.rssiLast = rssiLast;
	}

	public byte getRssiMin() {
		return rssiMin;
	}

	public void setRssiMin(byte rssiMin) {
		this.rssiMin = rssiMin;
	}

	public int getTxNum() {
		return txNum;
	}

	public void setTxNum(int txNum) {
		this.txNum = txNum;
	}

	public int getQsoNum() {
		return qsoNum;
	}

	public void setQsoNum(int qsoNum) {
		this.qsoNum = qsoNum;
	}

}
