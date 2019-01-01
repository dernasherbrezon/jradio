package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class TtTxStatus {

	private boolean LOCKPin;
	private boolean DCLKPin;
	private boolean LOCKPin2;
	private boolean CarrierSense;
	private boolean ContinuousPLLLockIndicator;
	private boolean InstantaneousPLLLockIndicator;
	private boolean PLLFailedLockAtPowerUp;
	private boolean CalibrationComplete;

	public TtTxStatus(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		LOCKPin = ((raw >> 7) & 0x1) > 0;
		DCLKPin = ((raw >> 6) & 0x1) > 0;
		LOCKPin2 = ((raw >> 5) & 0x1) > 0;
		CarrierSense = ((raw >> 4) & 0x1) > 0;
		ContinuousPLLLockIndicator = ((raw >> 3) & 0x1) > 0;
		InstantaneousPLLLockIndicator = ((raw >> 2) & 0x1) > 0;
		PLLFailedLockAtPowerUp = ((raw >> 1) & 0x1) > 0;
		CalibrationComplete = ((raw >> 0) & 0x1) > 0;
	}

	public boolean isLOCKPin() {
		return LOCKPin;
	}

	public void setLOCKPin(boolean lOCKPin) {
		LOCKPin = lOCKPin;
	}

	public boolean isDCLKPin() {
		return DCLKPin;
	}

	public void setDCLKPin(boolean dCLKPin) {
		DCLKPin = dCLKPin;
	}

	public boolean isLOCKPin2() {
		return LOCKPin2;
	}

	public void setLOCKPin2(boolean lOCKPin2) {
		LOCKPin2 = lOCKPin2;
	}

	public boolean isCarrierSense() {
		return CarrierSense;
	}

	public void setCarrierSense(boolean carrierSense) {
		CarrierSense = carrierSense;
	}

	public boolean isContinuousPLLLockIndicator() {
		return ContinuousPLLLockIndicator;
	}

	public void setContinuousPLLLockIndicator(boolean continuousPLLLockIndicator) {
		ContinuousPLLLockIndicator = continuousPLLLockIndicator;
	}

	public boolean isInstantaneousPLLLockIndicator() {
		return InstantaneousPLLLockIndicator;
	}

	public void setInstantaneousPLLLockIndicator(boolean instantaneousPLLLockIndicator) {
		InstantaneousPLLLockIndicator = instantaneousPLLLockIndicator;
	}

	public boolean isPLLFailedLockAtPowerUp() {
		return PLLFailedLockAtPowerUp;
	}

	public void setPLLFailedLockAtPowerUp(boolean pLLFailedLockAtPowerUp) {
		PLLFailedLockAtPowerUp = pLLFailedLockAtPowerUp;
	}

	public boolean isCalibrationComplete() {
		return CalibrationComplete;
	}

	public void setCalibrationComplete(boolean calibrationComplete) {
		CalibrationComplete = calibrationComplete;
	}

}
