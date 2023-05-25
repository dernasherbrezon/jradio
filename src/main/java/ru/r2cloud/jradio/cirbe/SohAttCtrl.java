package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

public class SohAttCtrl {

	private float eigenError;
	private float sunPointAngleError;
	private boolean sunSourceFailover;
	private boolean sunAvoidFlag;
	private boolean onSunFlag;
	private boolean momentumTooHigh;
	private boolean attCtrlActive;

	public SohAttCtrl() {
		// do nothing
	}

	public SohAttCtrl(DataInputStream dis) throws IOException {
		eigenError = dis.readInt() / 659999963.0400021f;
		sunPointAngleError = dis.readShort() / 333.33335555555703f;
		int raw = dis.readUnsignedByte();
		sunSourceFailover = ((raw >> 5) & 0x1) > 0;
		sunAvoidFlag = ((raw >> 4) & 0x1) > 0;
		onSunFlag = ((raw >> 2) & 0x1) > 0;
		momentumTooHigh = ((raw >> 1) & 0x1) > 0;
		attCtrlActive = (raw & 0x1) > 0;
	}

	public float getEigenError() {
		return eigenError;
	}

	public void setEigenError(float eigenError) {
		this.eigenError = eigenError;
	}

	public float getSunPointAngleError() {
		return sunPointAngleError;
	}

	public void setSunPointAngleError(float sunPointAngleError) {
		this.sunPointAngleError = sunPointAngleError;
	}

	public boolean isSunSourceFailover() {
		return sunSourceFailover;
	}

	public void setSunSourceFailover(boolean sunSourceFailover) {
		this.sunSourceFailover = sunSourceFailover;
	}

	public boolean isSunAvoidFlag() {
		return sunAvoidFlag;
	}

	public void setSunAvoidFlag(boolean sunAvoidFlag) {
		this.sunAvoidFlag = sunAvoidFlag;
	}

	public boolean isOnSunFlag() {
		return onSunFlag;
	}

	public void setOnSunFlag(boolean onSunFlag) {
		this.onSunFlag = onSunFlag;
	}

	public boolean isMomentumTooHigh() {
		return momentumTooHigh;
	}

	public void setMomentumTooHigh(boolean momentumTooHigh) {
		this.momentumTooHigh = momentumTooHigh;
	}

	public boolean isAttCtrlActive() {
		return attCtrlActive;
	}

	public void setAttCtrlActive(boolean attCtrlActive) {
		this.attCtrlActive = attCtrlActive;
	}

}
