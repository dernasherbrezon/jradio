package ru.r2cloud.jradio.lume1;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class GssbStatus {

	private int rebootCount;
	private int currentState;
	private int antennaState;
	private int attemptsTotal;

	public GssbStatus(BitInputStream bis) throws IOException {
		rebootCount = bis.readUnsignedInt(8);
		currentState = bis.readUnsignedInt(8);
		antennaState = bis.readUnsignedInt(8);
		attemptsTotal = bis.readUnsignedInt(16);
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
