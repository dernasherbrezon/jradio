package ru.r2cloud.jradio.cute;

import java.io.DataInputStream;
import java.io.IOException;

public class SohAttCtrl {

	private float positionError1;
	private float positionError2;
	private float positionError3;
	private int timeIntoSearch;
	private int waitTimer;
	private float sunPointAngleError;
	private SunPointState sunPointState;

	public SohAttCtrl() {
		// do nothing
	}

	public SohAttCtrl(DataInputStream dis) throws IOException {
		positionError1 = dis.readInt() * 2e-09f;
		positionError2 = dis.readInt() * 2e-09f;
		positionError3 = dis.readInt() * 2e-09f;
		timeIntoSearch = dis.readUnsignedShort();
		waitTimer = dis.readUnsignedShort();
		sunPointAngleError = dis.readUnsignedShort() * 0.003f;
		sunPointState = SunPointState.valueOfCode(dis.readUnsignedByte());
	}

	public float getPositionError1() {
		return positionError1;
	}

	public void setPositionError1(float positionError1) {
		this.positionError1 = positionError1;
	}

	public float getPositionError2() {
		return positionError2;
	}

	public void setPositionError2(float positionError2) {
		this.positionError2 = positionError2;
	}

	public float getPositionError3() {
		return positionError3;
	}

	public void setPositionError3(float positionError3) {
		this.positionError3 = positionError3;
	}

	public int getTimeIntoSearch() {
		return timeIntoSearch;
	}

	public void setTimeIntoSearch(int timeIntoSearch) {
		this.timeIntoSearch = timeIntoSearch;
	}

	public int getWaitTimer() {
		return waitTimer;
	}

	public void setWaitTimer(int waitTimer) {
		this.waitTimer = waitTimer;
	}

	public float getSunPointAngleError() {
		return sunPointAngleError;
	}

	public void setSunPointAngleError(float sunPointAngleError) {
		this.sunPointAngleError = sunPointAngleError;
	}

	public SunPointState getSunPointState() {
		return sunPointState;
	}

	public void setSunPointState(SunPointState sunPointState) {
		this.sunPointState = sunPointState;
	}

}
