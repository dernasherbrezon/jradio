package ru.r2cloud.jradio.picsat;

import java.io.DataInputStream;
import java.io.IOException;

public class AuxHeader {

	private int hkTick;
	private int binning;
	private MainMode mainMode;
	private int acqMode;
	private boolean diodeFlag;
	private boolean interruptFlag;
	private boolean piezoFlag;
	private boolean hvFlag;
	private boolean sensorsFlag;
	private boolean tecFlag;
	private boolean hkFlag;
	private int procFreq;
	private int tecSetpoint;

	public AuxHeader() {
		// do nothing
	}

	public AuxHeader(DataInputStream dis) throws IOException {
		hkTick = dis.readUnsignedShort();
		binning = dis.readUnsignedByte();
		mainMode = MainMode.valueOfCode(dis.readUnsignedByte());
		acqMode = dis.readUnsignedByte();
		int raw = dis.readUnsignedByte();
		diodeFlag = ((raw >> 7) & 0x1) > 0;
		interruptFlag = ((raw >> 6) & 0x1) > 0;
		piezoFlag = ((raw >> 5) & 0x1) > 0;
		hvFlag = ((raw >> 4) & 0x1) > 0;
		sensorsFlag = ((raw >> 3) & 0x1) > 0;
		tecFlag = ((raw >> 2) & 0x1) > 0;
		hkFlag = ((raw >> 1) & 0x1) > 0;
		procFreq = dis.readUnsignedByte();
		tecSetpoint = dis.readUnsignedShort();
	}

	public int getHkTick() {
		return hkTick;
	}

	public void setHkTick(int hkTick) {
		this.hkTick = hkTick;
	}

	public int getBinning() {
		return binning;
	}

	public void setBinning(int binning) {
		this.binning = binning;
	}

	public MainMode getMainMode() {
		return mainMode;
	}

	public void setMainMode(MainMode mainMode) {
		this.mainMode = mainMode;
	}

	public int getAcqMode() {
		return acqMode;
	}

	public void setAcqMode(int acqMode) {
		this.acqMode = acqMode;
	}

	public boolean isDiodeFlag() {
		return diodeFlag;
	}

	public void setDiodeFlag(boolean diodeFlag) {
		this.diodeFlag = diodeFlag;
	}

	public boolean isInterruptFlag() {
		return interruptFlag;
	}

	public void setInterruptFlag(boolean interruptFlag) {
		this.interruptFlag = interruptFlag;
	}

	public boolean isPiezoFlag() {
		return piezoFlag;
	}

	public void setPiezoFlag(boolean piezoFlag) {
		this.piezoFlag = piezoFlag;
	}

	public boolean isHvFlag() {
		return hvFlag;
	}

	public void setHvFlag(boolean hvFlag) {
		this.hvFlag = hvFlag;
	}

	public boolean isSensorsFlag() {
		return sensorsFlag;
	}

	public void setSensorsFlag(boolean sensorsFlag) {
		this.sensorsFlag = sensorsFlag;
	}

	public boolean isTecFlag() {
		return tecFlag;
	}

	public void setTecFlag(boolean tecFlag) {
		this.tecFlag = tecFlag;
	}

	public boolean isHkFlag() {
		return hkFlag;
	}

	public void setHkFlag(boolean hkFlag) {
		this.hkFlag = hkFlag;
	}

	public int getProcFreq() {
		return procFreq;
	}

	public void setProcFreq(int procFreq) {
		this.procFreq = procFreq;
	}

	public int getTecSetpoint() {
		return tecSetpoint;
	}

	public void setTecSetpoint(int tecSetpoint) {
		this.tecSetpoint = tecSetpoint;
	}

}
