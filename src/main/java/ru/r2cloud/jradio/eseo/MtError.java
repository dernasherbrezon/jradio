package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class MtError {

	private boolean MTMInternalError;
	private boolean WatchdogRebootError;
	private boolean MTMCommunicationError;
	private boolean MTMGenError;
	private boolean MTMxCoilError;
	private boolean MTMyCoilError;
	private boolean MTMzCoilError;
	private boolean RTEMSError;
	private boolean StandBy;
	private boolean MTMDCDC12OutOfTemperatureRange;
	private boolean MTMDCDC5OutOfTemperatureRange;
	private boolean MTM12VOvercurrent;
	private boolean MTM5VOvercurrent;

	public MtError(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		MTMInternalError = ((raw >> 7) & 0x1) > 0;
		WatchdogRebootError = ((raw >> 6) & 0x1) > 0;
		MTMCommunicationError = ((raw >> 5) & 0x1) > 0;
		MTMGenError = ((raw >> 4) & 0x1) > 0;
		MTMxCoilError = ((raw >> 3) & 0x1) > 0;
		MTMyCoilError = ((raw >> 2) & 0x1) > 0;
		MTMzCoilError = ((raw >> 1) & 0x1) > 0;
		RTEMSError = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		StandBy = ((raw >> 7) & 0x1) > 0;
		MTMDCDC12OutOfTemperatureRange = ((raw >> 6) & 0x1) > 0;
		MTMDCDC5OutOfTemperatureRange = ((raw >> 5) & 0x1) > 0;
		MTM12VOvercurrent = ((raw >> 4) & 0x1) > 0;
		MTM5VOvercurrent = ((raw >> 3) & 0x1) > 0;
	}

	public boolean isMTMInternalError() {
		return MTMInternalError;
	}

	public void setMTMInternalError(boolean mTMInternalError) {
		MTMInternalError = mTMInternalError;
	}

	public boolean isWatchdogRebootError() {
		return WatchdogRebootError;
	}

	public void setWatchdogRebootError(boolean watchdogRebootError) {
		WatchdogRebootError = watchdogRebootError;
	}

	public boolean isMTMCommunicationError() {
		return MTMCommunicationError;
	}

	public void setMTMCommunicationError(boolean mTMCommunicationError) {
		MTMCommunicationError = mTMCommunicationError;
	}

	public boolean isMTMGenError() {
		return MTMGenError;
	}

	public void setMTMGenError(boolean mTMGenError) {
		MTMGenError = mTMGenError;
	}

	public boolean isMTMxCoilError() {
		return MTMxCoilError;
	}

	public void setMTMxCoilError(boolean mTMxCoilError) {
		MTMxCoilError = mTMxCoilError;
	}

	public boolean isMTMyCoilError() {
		return MTMyCoilError;
	}

	public void setMTMyCoilError(boolean mTMyCoilError) {
		MTMyCoilError = mTMyCoilError;
	}

	public boolean isMTMzCoilError() {
		return MTMzCoilError;
	}

	public void setMTMzCoilError(boolean mTMzCoilError) {
		MTMzCoilError = mTMzCoilError;
	}

	public boolean isRTEMSError() {
		return RTEMSError;
	}

	public void setRTEMSError(boolean rTEMSError) {
		RTEMSError = rTEMSError;
	}

	public boolean isStandBy() {
		return StandBy;
	}

	public void setStandBy(boolean standBy) {
		StandBy = standBy;
	}

	public boolean isMTMDCDC12OutOfTemperatureRange() {
		return MTMDCDC12OutOfTemperatureRange;
	}

	public void setMTMDCDC12OutOfTemperatureRange(boolean mTMDCDC12OutOfTemperatureRange) {
		MTMDCDC12OutOfTemperatureRange = mTMDCDC12OutOfTemperatureRange;
	}

	public boolean isMTMDCDC5OutOfTemperatureRange() {
		return MTMDCDC5OutOfTemperatureRange;
	}

	public void setMTMDCDC5OutOfTemperatureRange(boolean mTMDCDC5OutOfTemperatureRange) {
		MTMDCDC5OutOfTemperatureRange = mTMDCDC5OutOfTemperatureRange;
	}

	public boolean isMTM12VOvercurrent() {
		return MTM12VOvercurrent;
	}

	public void setMTM12VOvercurrent(boolean mTM12VOvercurrent) {
		MTM12VOvercurrent = mTM12VOvercurrent;
	}

	public boolean isMTM5VOvercurrent() {
		return MTM5VOvercurrent;
	}

	public void setMTM5VOvercurrent(boolean mTM5VOvercurrent) {
		MTM5VOvercurrent = mTM5VOvercurrent;
	}

}
