package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class MmError {

	private boolean InternalError;
	private boolean WatchdogRebootError;
	private boolean MMMSerialError;
	private boolean GenError;
	private boolean RTEMSError;
	private boolean StandBy;
	private boolean MMM5VOvercurrent;
	private boolean MMMDCDCOutOfTemperatureRange;

	public MmError(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		InternalError = ((raw >> 7) & 0x1) > 0;
		WatchdogRebootError = ((raw >> 6) & 0x1) > 0;
		MMMSerialError = ((raw >> 5) & 0x1) > 0;
		GenError = ((raw >> 4) & 0x1) > 0;
		RTEMSError = ((raw >> 3) & 0x1) > 0;
		StandBy = ((raw >> 2) & 0x1) > 0;
		MMM5VOvercurrent = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		MMMDCDCOutOfTemperatureRange = ((raw >> 6) & 0x1) > 0;
	}

	public boolean isInternalError() {
		return InternalError;
	}

	public void setInternalError(boolean internalError) {
		InternalError = internalError;
	}

	public boolean isWatchdogRebootError() {
		return WatchdogRebootError;
	}

	public void setWatchdogRebootError(boolean watchdogRebootError) {
		WatchdogRebootError = watchdogRebootError;
	}

	public boolean isMMMSerialError() {
		return MMMSerialError;
	}

	public void setMMMSerialError(boolean mMMSerialError) {
		MMMSerialError = mMMSerialError;
	}

	public boolean isGenError() {
		return GenError;
	}

	public void setGenError(boolean genError) {
		GenError = genError;
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

	public boolean isMMM5VOvercurrent() {
		return MMM5VOvercurrent;
	}

	public void setMMM5VOvercurrent(boolean mMM5VOvercurrent) {
		MMM5VOvercurrent = mMM5VOvercurrent;
	}

	public boolean isMMMDCDCOutOfTemperatureRange() {
		return MMMDCDCOutOfTemperatureRange;
	}

	public void setMMMDCDCOutOfTemperatureRange(boolean mMMDCDCOutOfTemperatureRange) {
		MMMDCDCOutOfTemperatureRange = mMMDCDCOutOfTemperatureRange;
	}

}
