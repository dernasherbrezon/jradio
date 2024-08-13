package ru.r2cloud.jradio.eirsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Wbc {

	private boolean wbcenabled0;
	private int controllerexecutioncount0;

	public Wbc() {
		// do nothing
	}

	public Wbc(BitInputStream dis) throws IOException {
		wbcenabled0 = dis.readBoolean();
		controllerexecutioncount0 = dis.readUnsignedByte();
	}

	public boolean isWbcenabled0() {
		return wbcenabled0;
	}

	public void setWbcenabled0(boolean wbcenabled0) {
		this.wbcenabled0 = wbcenabled0;
	}

	public int getControllerexecutioncount0() {
		return controllerexecutioncount0;
	}

	public void setControllerexecutioncount0(int controllerexecutioncount0) {
		this.controllerexecutioncount0 = controllerexecutioncount0;
	}

}
