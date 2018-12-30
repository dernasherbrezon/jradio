package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

public class TtError {

	private boolean PLLFailedToLockInTheTransmitter;
	private boolean ChargePumpCurrentExceededLimitsInTX;
	private boolean PLLFailedToLockInTheReceiver;
	private boolean ChargePumpCurrentExceededLimitsInRX;
	private boolean RSSILowerThanTheSensitivityThreshold;
	private boolean PKTToGSExceedMaxLimit;
	private boolean MaximumAllowedFrequencyDeviationExceeded;
	private boolean WDTError;
	private boolean RTEMSError;
	private boolean TemperatureOfTheRFSectionExceededLimits;
	private boolean TemperatureOfTheDCDCSectionExceededLimits;
	private boolean StandBy;
	private boolean CurrentOfTheHPAIsInsufficient;
	private boolean CurrentOfTheHPAExceededLimit;
	private boolean CurrentOfTheLNARTXExceededLimit;

	public TtError(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		PLLFailedToLockInTheTransmitter = ((raw >> 7) & 0x1) > 0;
		ChargePumpCurrentExceededLimitsInTX = ((raw >> 6) & 0x1) > 0;
		PLLFailedToLockInTheReceiver = ((raw >> 5) & 0x1) > 0;
		ChargePumpCurrentExceededLimitsInRX = ((raw >> 4) & 0x1) > 0;
		RSSILowerThanTheSensitivityThreshold = ((raw >> 3) & 0x1) > 0;
		PKTToGSExceedMaxLimit = ((raw >> 2) & 0x1) > 0;
		MaximumAllowedFrequencyDeviationExceeded = ((raw >> 1) & 0x1) > 0;
		WDTError = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		RTEMSError = ((raw >> 7) & 0x1) > 0;
		TemperatureOfTheRFSectionExceededLimits = ((raw >> 6) & 0x1) > 0;
		TemperatureOfTheDCDCSectionExceededLimits = ((raw >> 5) & 0x1) > 0;
		StandBy = ((raw >> 4) & 0x1) > 0;
		CurrentOfTheHPAIsInsufficient = ((raw >> 3) & 0x1) > 0;
		CurrentOfTheHPAExceededLimit = ((raw >> 2) & 0x1) > 0;
		CurrentOfTheLNARTXExceededLimit = ((raw >> 1) & 0x1) > 0;
	}

	public boolean isPLLFailedToLockInTheTransmitter() {
		return PLLFailedToLockInTheTransmitter;
	}

	public void setPLLFailedToLockInTheTransmitter(boolean pLLFailedToLockInTheTransmitter) {
		PLLFailedToLockInTheTransmitter = pLLFailedToLockInTheTransmitter;
	}

	public boolean isChargePumpCurrentExceededLimitsInTX() {
		return ChargePumpCurrentExceededLimitsInTX;
	}

	public void setChargePumpCurrentExceededLimitsInTX(boolean chargePumpCurrentExceededLimitsInTX) {
		ChargePumpCurrentExceededLimitsInTX = chargePumpCurrentExceededLimitsInTX;
	}

	public boolean isPLLFailedToLockInTheReceiver() {
		return PLLFailedToLockInTheReceiver;
	}

	public void setPLLFailedToLockInTheReceiver(boolean pLLFailedToLockInTheReceiver) {
		PLLFailedToLockInTheReceiver = pLLFailedToLockInTheReceiver;
	}

	public boolean isChargePumpCurrentExceededLimitsInRX() {
		return ChargePumpCurrentExceededLimitsInRX;
	}

	public void setChargePumpCurrentExceededLimitsInRX(boolean chargePumpCurrentExceededLimitsInRX) {
		ChargePumpCurrentExceededLimitsInRX = chargePumpCurrentExceededLimitsInRX;
	}

	public boolean isRSSILowerThanTheSensitivityThreshold() {
		return RSSILowerThanTheSensitivityThreshold;
	}

	public void setRSSILowerThanTheSensitivityThreshold(boolean rSSILowerThanTheSensitivityThreshold) {
		RSSILowerThanTheSensitivityThreshold = rSSILowerThanTheSensitivityThreshold;
	}

	public boolean isPKTToGSExceedMaxLimit() {
		return PKTToGSExceedMaxLimit;
	}

	public void setPKTToGSExceedMaxLimit(boolean pKTToGSExceedMaxLimit) {
		PKTToGSExceedMaxLimit = pKTToGSExceedMaxLimit;
	}

	public boolean isMaximumAllowedFrequencyDeviationExceeded() {
		return MaximumAllowedFrequencyDeviationExceeded;
	}

	public void setMaximumAllowedFrequencyDeviationExceeded(boolean maximumAllowedFrequencyDeviationExceeded) {
		MaximumAllowedFrequencyDeviationExceeded = maximumAllowedFrequencyDeviationExceeded;
	}

	public boolean isWDTError() {
		return WDTError;
	}

	public void setWDTError(boolean wDTError) {
		WDTError = wDTError;
	}

	public boolean isRTEMSError() {
		return RTEMSError;
	}

	public void setRTEMSError(boolean rTEMSError) {
		RTEMSError = rTEMSError;
	}

	public boolean isTemperatureOfTheRFSectionExceededLimits() {
		return TemperatureOfTheRFSectionExceededLimits;
	}

	public void setTemperatureOfTheRFSectionExceededLimits(boolean temperatureOfTheRFSectionExceededLimits) {
		TemperatureOfTheRFSectionExceededLimits = temperatureOfTheRFSectionExceededLimits;
	}

	public boolean isTemperatureOfTheDCDCSectionExceededLimits() {
		return TemperatureOfTheDCDCSectionExceededLimits;
	}

	public void setTemperatureOfTheDCDCSectionExceededLimits(boolean temperatureOfTheDCDCSectionExceededLimits) {
		TemperatureOfTheDCDCSectionExceededLimits = temperatureOfTheDCDCSectionExceededLimits;
	}

	public boolean isStandBy() {
		return StandBy;
	}

	public void setStandBy(boolean standBy) {
		StandBy = standBy;
	}

	public boolean isCurrentOfTheHPAIsInsufficient() {
		return CurrentOfTheHPAIsInsufficient;
	}

	public void setCurrentOfTheHPAIsInsufficient(boolean currentOfTheHPAIsInsufficient) {
		CurrentOfTheHPAIsInsufficient = currentOfTheHPAIsInsufficient;
	}

	public boolean isCurrentOfTheHPAExceededLimit() {
		return CurrentOfTheHPAExceededLimit;
	}

	public void setCurrentOfTheHPAExceededLimit(boolean currentOfTheHPAExceededLimit) {
		CurrentOfTheHPAExceededLimit = currentOfTheHPAExceededLimit;
	}

	public boolean isCurrentOfTheLNARTXExceededLimit() {
		return CurrentOfTheLNARTXExceededLimit;
	}

	public void setCurrentOfTheLNARTXExceededLimit(boolean currentOfTheLNARTXExceededLimit) {
		CurrentOfTheLNARTXExceededLimit = currentOfTheLNARTXExceededLimit;
	}
	
	
}
