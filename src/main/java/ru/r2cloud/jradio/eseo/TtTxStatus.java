package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class TtTxStatus {

	private boolean lockPin;
	private boolean dclkPin;
	private boolean lockPin2;
	private boolean carrierSense;
	private boolean continuousPLLLockIndicator;
	private boolean instantaneousPLLLockIndicator;
	private boolean pllFailedLockAtPowerUp;
	private boolean calibrationComplete;

	public TtTxStatus() {
		// do nothing
	}

	public TtTxStatus(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		lockPin = ((raw >> 7) & 0x1) > 0;
		dclkPin = ((raw >> 6) & 0x1) > 0;
		lockPin2 = ((raw >> 5) & 0x1) > 0;
		carrierSense = ((raw >> 4) & 0x1) > 0;
		continuousPLLLockIndicator = ((raw >> 3) & 0x1) > 0;
		instantaneousPLLLockIndicator = ((raw >> 2) & 0x1) > 0;
		pllFailedLockAtPowerUp = ((raw >> 1) & 0x1) > 0;
		calibrationComplete = (raw & 0x1) > 0;
	}

	public boolean isLockPin() {
		return lockPin;
	}

	public void setLockPin(boolean lockPin) {
		this.lockPin = lockPin;
	}

	public boolean isDclkPin() {
		return dclkPin;
	}

	public void setDclkPin(boolean dclkPin) {
		this.dclkPin = dclkPin;
	}

	public boolean isLockPin2() {
		return lockPin2;
	}

	public void setLockPin2(boolean lockPin2) {
		this.lockPin2 = lockPin2;
	}

	public boolean isCarrierSense() {
		return carrierSense;
	}

	public void setCarrierSense(boolean carrierSense) {
		this.carrierSense = carrierSense;
	}

	public boolean isContinuousPLLLockIndicator() {
		return continuousPLLLockIndicator;
	}

	public void setContinuousPLLLockIndicator(boolean continuousPLLLockIndicator) {
		this.continuousPLLLockIndicator = continuousPLLLockIndicator;
	}

	public boolean isInstantaneousPLLLockIndicator() {
		return instantaneousPLLLockIndicator;
	}

	public void setInstantaneousPLLLockIndicator(boolean instantaneousPLLLockIndicator) {
		this.instantaneousPLLLockIndicator = instantaneousPLLLockIndicator;
	}

	public boolean isPllFailedLockAtPowerUp() {
		return pllFailedLockAtPowerUp;
	}

	public void setPllFailedLockAtPowerUp(boolean pllFailedLockAtPowerUp) {
		this.pllFailedLockAtPowerUp = pllFailedLockAtPowerUp;
	}

	public boolean isCalibrationComplete() {
		return calibrationComplete;
	}

	public void setCalibrationComplete(boolean calibrationComplete) {
		this.calibrationComplete = calibrationComplete;
	}

}
