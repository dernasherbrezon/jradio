package ru.r2cloud.jradio.geoscan;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Geoscan2Obc {

	private boolean flyweel;
	private boolean coil;
	private boolean ins;
	private boolean cam;
	private boolean xPlus;
	private boolean xMinus;
	private boolean yPlus;
	private boolean yMinus;

	private byte txPlus;
	private byte txMinus;
	private byte tyPlus;
	private byte tyMinus;
	private int gnssNum;
	private int cameraFiles;

	public Geoscan2Obc() {
		// do nothing
	}

	public Geoscan2Obc(LittleEndianDataInputStream dis) throws IOException {
		dis.skipBytes(2);
		int raw = dis.readUnsignedByte();
		flyweel = (((raw >> 7) & 0x01) > 0);
		coil = (((raw >> 6) & 0x01) > 0);
		ins = (((raw >> 5) & 0x01) > 0);
		cam = (((raw >> 4) & 0x01) > 0);
		xPlus = (((raw >> 3) & 0x01) > 0);
		xMinus = (((raw >> 2) & 0x01) > 0);
		yPlus = (((raw >> 1) & 0x01) > 0);
		yMinus = ((raw & 0x01) > 0);

		txPlus = dis.readByte();
		txMinus = dis.readByte();
		tyPlus = dis.readByte();
		tyMinus = dis.readByte();
		gnssNum = dis.readUnsignedByte();
		dis.skipBytes(2);
		cameraFiles = dis.readUnsignedByte();
		dis.skipBytes(5);
	}

	public boolean isFlyweel() {
		return flyweel;
	}

	public void setFlyweel(boolean flyweel) {
		this.flyweel = flyweel;
	}

	public boolean isCoil() {
		return coil;
	}

	public void setCoil(boolean coil) {
		this.coil = coil;
	}

	public boolean isIns() {
		return ins;
	}

	public void setIns(boolean ins) {
		this.ins = ins;
	}

	public boolean isCam() {
		return cam;
	}

	public void setCam(boolean cam) {
		this.cam = cam;
	}

	public boolean isxPlus() {
		return xPlus;
	}

	public void setxPlus(boolean xPlus) {
		this.xPlus = xPlus;
	}

	public boolean isxMinus() {
		return xMinus;
	}

	public void setxMinus(boolean xMinus) {
		this.xMinus = xMinus;
	}

	public boolean isyPlus() {
		return yPlus;
	}

	public void setyPlus(boolean yPlus) {
		this.yPlus = yPlus;
	}

	public boolean isyMinus() {
		return yMinus;
	}

	public void setyMinus(boolean yMinus) {
		this.yMinus = yMinus;
	}

	public byte getTxPlus() {
		return txPlus;
	}

	public void setTxPlus(byte txPlus) {
		this.txPlus = txPlus;
	}

	public byte getTxMinus() {
		return txMinus;
	}

	public void setTxMinus(byte txMinus) {
		this.txMinus = txMinus;
	}

	public byte getTyPlus() {
		return tyPlus;
	}

	public void setTyPlus(byte tyPlus) {
		this.tyPlus = tyPlus;
	}

	public byte getTyMinus() {
		return tyMinus;
	}

	public void setTyMinus(byte tyMinus) {
		this.tyMinus = tyMinus;
	}

	public int getGnssNum() {
		return gnssNum;
	}

	public void setGnssNum(int gnssNum) {
		this.gnssNum = gnssNum;
	}

	public int getCameraFiles() {
		return cameraFiles;
	}

	public void setCameraFiles(int cameraFiles) {
		this.cameraFiles = cameraFiles;
	}

}
