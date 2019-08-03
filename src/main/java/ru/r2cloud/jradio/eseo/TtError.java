package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class TtError {

	private boolean pllFailedToLockInTheTransmitter;
	private boolean chargePumpCurrentExceededLimitsInTX;
	private boolean pllFailedToLockInTheReceiver;
	private boolean chargePumpCurrentExceededLimitsInRX;
	private boolean rssiLowerThanTheSensitivityThreshold;
	private boolean pktToGSExceedMaxLimit;
	private boolean maximumAllowedFrequencyDeviationExceeded;
	private boolean wdtError;
	private boolean rtemsError;
	private boolean temperatureOfTheRFSectionExceededLimits;
	private boolean temperatureOfTheDCDCSectionExceededLimits;
	private boolean standBy;
	private boolean currentOfTheHPAIsInsufficient;
	private boolean currentOfTheHPAExceededLimit;
	private boolean currentOfTheLNARTXExceededLimit;

	public TtError(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		pllFailedToLockInTheTransmitter = ((raw >> 7) & 0x1) > 0;
		chargePumpCurrentExceededLimitsInTX = ((raw >> 6) & 0x1) > 0;
		pllFailedToLockInTheReceiver = ((raw >> 5) & 0x1) > 0;
		chargePumpCurrentExceededLimitsInRX = ((raw >> 4) & 0x1) > 0;
		rssiLowerThanTheSensitivityThreshold = ((raw >> 3) & 0x1) > 0;
		pktToGSExceedMaxLimit = ((raw >> 2) & 0x1) > 0;
		maximumAllowedFrequencyDeviationExceeded = ((raw >> 1) & 0x1) > 0;
		wdtError = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		rtemsError = ((raw >> 7) & 0x1) > 0;
		temperatureOfTheRFSectionExceededLimits = ((raw >> 6) & 0x1) > 0;
		temperatureOfTheDCDCSectionExceededLimits = ((raw >> 5) & 0x1) > 0;
		standBy = ((raw >> 4) & 0x1) > 0;
		currentOfTheHPAIsInsufficient = ((raw >> 3) & 0x1) > 0;
		currentOfTheHPAExceededLimit = ((raw >> 2) & 0x1) > 0;
		currentOfTheLNARTXExceededLimit = ((raw >> 1) & 0x1) > 0;
	}

	public boolean isPllFailedToLockInTheTransmitter() {
		return pllFailedToLockInTheTransmitter;
	}

	public void setPllFailedToLockInTheTransmitter(boolean pllFailedToLockInTheTransmitter) {
		this.pllFailedToLockInTheTransmitter = pllFailedToLockInTheTransmitter;
	}

	public boolean isChargePumpCurrentExceededLimitsInTX() {
		return chargePumpCurrentExceededLimitsInTX;
	}

	public void setChargePumpCurrentExceededLimitsInTX(boolean chargePumpCurrentExceededLimitsInTX) {
		this.chargePumpCurrentExceededLimitsInTX = chargePumpCurrentExceededLimitsInTX;
	}

	public boolean isPllFailedToLockInTheReceiver() {
		return pllFailedToLockInTheReceiver;
	}

	public void setPllFailedToLockInTheReceiver(boolean pllFailedToLockInTheReceiver) {
		this.pllFailedToLockInTheReceiver = pllFailedToLockInTheReceiver;
	}

	public boolean isChargePumpCurrentExceededLimitsInRX() {
		return chargePumpCurrentExceededLimitsInRX;
	}

	public void setChargePumpCurrentExceededLimitsInRX(boolean chargePumpCurrentExceededLimitsInRX) {
		this.chargePumpCurrentExceededLimitsInRX = chargePumpCurrentExceededLimitsInRX;
	}

	public boolean isRssiLowerThanTheSensitivityThreshold() {
		return rssiLowerThanTheSensitivityThreshold;
	}

	public void setRssiLowerThanTheSensitivityThreshold(boolean rssiLowerThanTheSensitivityThreshold) {
		this.rssiLowerThanTheSensitivityThreshold = rssiLowerThanTheSensitivityThreshold;
	}

	public boolean isPktToGSExceedMaxLimit() {
		return pktToGSExceedMaxLimit;
	}

	public void setPktToGSExceedMaxLimit(boolean pktToGSExceedMaxLimit) {
		this.pktToGSExceedMaxLimit = pktToGSExceedMaxLimit;
	}

	public boolean isMaximumAllowedFrequencyDeviationExceeded() {
		return maximumAllowedFrequencyDeviationExceeded;
	}

	public void setMaximumAllowedFrequencyDeviationExceeded(boolean maximumAllowedFrequencyDeviationExceeded) {
		this.maximumAllowedFrequencyDeviationExceeded = maximumAllowedFrequencyDeviationExceeded;
	}

	public boolean isWdtError() {
		return wdtError;
	}

	public void setWdtError(boolean wdtError) {
		this.wdtError = wdtError;
	}

	public boolean isRtemsError() {
		return rtemsError;
	}

	public void setRtemsError(boolean rtemsError) {
		this.rtemsError = rtemsError;
	}

	public boolean isTemperatureOfTheRFSectionExceededLimits() {
		return temperatureOfTheRFSectionExceededLimits;
	}

	public void setTemperatureOfTheRFSectionExceededLimits(boolean temperatureOfTheRFSectionExceededLimits) {
		this.temperatureOfTheRFSectionExceededLimits = temperatureOfTheRFSectionExceededLimits;
	}

	public boolean isTemperatureOfTheDCDCSectionExceededLimits() {
		return temperatureOfTheDCDCSectionExceededLimits;
	}

	public void setTemperatureOfTheDCDCSectionExceededLimits(boolean temperatureOfTheDCDCSectionExceededLimits) {
		this.temperatureOfTheDCDCSectionExceededLimits = temperatureOfTheDCDCSectionExceededLimits;
	}

	public boolean isStandBy() {
		return standBy;
	}

	public void setStandBy(boolean standBy) {
		this.standBy = standBy;
	}

	public boolean isCurrentOfTheHPAIsInsufficient() {
		return currentOfTheHPAIsInsufficient;
	}

	public void setCurrentOfTheHPAIsInsufficient(boolean currentOfTheHPAIsInsufficient) {
		this.currentOfTheHPAIsInsufficient = currentOfTheHPAIsInsufficient;
	}

	public boolean isCurrentOfTheHPAExceededLimit() {
		return currentOfTheHPAExceededLimit;
	}

	public void setCurrentOfTheHPAExceededLimit(boolean currentOfTheHPAExceededLimit) {
		this.currentOfTheHPAExceededLimit = currentOfTheHPAExceededLimit;
	}

	public boolean isCurrentOfTheLNARTXExceededLimit() {
		return currentOfTheLNARTXExceededLimit;
	}

	public void setCurrentOfTheLNARTXExceededLimit(boolean currentOfTheLNARTXExceededLimit) {
		this.currentOfTheLNARTXExceededLimit = currentOfTheLNARTXExceededLimit;
	}

}
