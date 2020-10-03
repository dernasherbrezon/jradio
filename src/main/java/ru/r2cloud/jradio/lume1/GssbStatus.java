package ru.r2cloud.jradio.lume1;

import java.io.DataInputStream;
import java.io.IOException;

public class GssbStatus {

	private int rebootCount;
	private int currentState;
	private int antennaState;
	private int attemptsTotal;
	
	public GssbStatus() {
		//do nothing
	}

	public GssbStatus(DataInputStream bis) throws IOException {
		rebootCount = bis.readUnsignedByte();
		currentState = bis.readUnsignedByte();
		antennaState = bis.readUnsignedByte();
		attemptsTotal = bis.readUnsignedShort();
	}

	public int getRebootCount() {
		return rebootCount;
	}

	public void setRebootCount(int rebootCount) {
		this.rebootCount = rebootCount;
	}

	public int getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int currentState) {
		this.currentState = currentState;
	}

	public int getAntennaState() {
		return antennaState;
	}

	public void setAntennaState(int antennaState) {
		this.antennaState = antennaState;
	}

	public int getAttemptsTotal() {
		return attemptsTotal;
	}

	public void setAttemptsTotal(int attemptsTotal) {
		this.attemptsTotal = attemptsTotal;
	}
	
}
