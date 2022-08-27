package ru.r2cloud.jradio.ctim;

import java.io.DataInputStream;
import java.io.IOException;

public class PayloadState {

	private boolean powerCycleReq;
	private boolean powerOffReq;
	private boolean statMsgState;
	private boolean timeMsgState;
	private AdcsAlive aliveState;

	public PayloadState() {
		// do nothing
	}

	public PayloadState(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		powerCycleReq = ((raw >> 5) & 0x1) > 0;
		powerOffReq = ((raw >> 4) & 0x1) > 0;
		statMsgState = ((raw >> 3) & 0x1) > 0;
		timeMsgState = ((raw >> 2) & 0x1) > 0;
		aliveState = AdcsAlive.valueOfCode(raw & 0b11);
	}

	public boolean isPowerCycleReq() {
		return powerCycleReq;
	}

	public void setPowerCycleReq(boolean powerCycleReq) {
		this.powerCycleReq = powerCycleReq;
	}

	public boolean isPowerOffReq() {
		return powerOffReq;
	}

	public void setPowerOffReq(boolean powerOffReq) {
		this.powerOffReq = powerOffReq;
	}

	public boolean isStatMsgState() {
		return statMsgState;
	}

	public void setStatMsgState(boolean statMsgState) {
		this.statMsgState = statMsgState;
	}

	public boolean isTimeMsgState() {
		return timeMsgState;
	}

	public void setTimeMsgState(boolean timeMsgState) {
		this.timeMsgState = timeMsgState;
	}

	public AdcsAlive getAliveState() {
		return aliveState;
	}

	public void setAliveState(AdcsAlive aliveState) {
		this.aliveState = aliveState;
	}

}
