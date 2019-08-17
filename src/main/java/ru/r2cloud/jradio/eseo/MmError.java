package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class MmError {

	private boolean internalError;
	private boolean watchdogRebootError;
	private boolean mmmSerialError;
	private boolean genError;
	private boolean rtemsError;
	private boolean standBy;
	private boolean mmm5VOvercurrent;
	private boolean mmmDCDCOutOfTemperatureRange;

	public MmError() {
		// do nothing
	}

	public MmError(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		internalError = ((raw >> 7) & 0x1) > 0;
		watchdogRebootError = ((raw >> 6) & 0x1) > 0;
		mmmSerialError = ((raw >> 5) & 0x1) > 0;
		genError = ((raw >> 4) & 0x1) > 0;
		rtemsError = ((raw >> 3) & 0x1) > 0;
		standBy = ((raw >> 2) & 0x1) > 0;
		mmm5VOvercurrent = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		mmmDCDCOutOfTemperatureRange = ((raw >> 6) & 0x1) > 0;
	}

	public boolean isInternalError() {
		return internalError;
	}

	public void setInternalError(boolean internalError) {
		this.internalError = internalError;
	}

	public boolean isWatchdogRebootError() {
		return watchdogRebootError;
	}

	public void setWatchdogRebootError(boolean watchdogRebootError) {
		this.watchdogRebootError = watchdogRebootError;
	}

	public boolean isMmmSerialError() {
		return mmmSerialError;
	}

	public void setMmmSerialError(boolean mmmSerialError) {
		this.mmmSerialError = mmmSerialError;
	}

	public boolean isGenError() {
		return genError;
	}

	public void setGenError(boolean genError) {
		this.genError = genError;
	}

	public boolean isRtemsError() {
		return rtemsError;
	}

	public void setRtemsError(boolean rtemsError) {
		this.rtemsError = rtemsError;
	}

	public boolean isStandBy() {
		return standBy;
	}

	public void setStandBy(boolean standBy) {
		this.standBy = standBy;
	}

	public boolean isMmm5VOvercurrent() {
		return mmm5VOvercurrent;
	}

	public void setMmm5VOvercurrent(boolean mmm5vOvercurrent) {
		mmm5VOvercurrent = mmm5vOvercurrent;
	}

	public boolean isMmmDCDCOutOfTemperatureRange() {
		return mmmDCDCOutOfTemperatureRange;
	}

	public void setMmmDCDCOutOfTemperatureRange(boolean mmmDCDCOutOfTemperatureRange) {
		this.mmmDCDCOutOfTemperatureRange = mmmDCDCOutOfTemperatureRange;
	}

}
