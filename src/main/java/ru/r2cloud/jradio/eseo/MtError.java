package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class MtError {

	private boolean mtmInternalError;
	private boolean watchdogRebootError;
	private boolean mtmCommunicationError;
	private boolean mtmGenError;
	private boolean mtmxCoilError;
	private boolean mtmyCoilError;
	private boolean mtmzCoilError;
	private boolean rteMSError;
	private boolean standBy;
	private boolean mtmDCDC12OutOfTemperatureRange;
	private boolean mtmDCDC5OutOfTemperatureRange;
	private boolean mtm12VOvercurrent;
	private boolean mtm5VOvercurrent;

	public MtError(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		mtmInternalError = ((raw >> 7) & 0x1) > 0;
		watchdogRebootError = ((raw >> 6) & 0x1) > 0;
		mtmCommunicationError = ((raw >> 5) & 0x1) > 0;
		mtmGenError = ((raw >> 4) & 0x1) > 0;
		mtmxCoilError = ((raw >> 3) & 0x1) > 0;
		mtmyCoilError = ((raw >> 2) & 0x1) > 0;
		mtmzCoilError = ((raw >> 1) & 0x1) > 0;
		rteMSError = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		standBy = ((raw >> 7) & 0x1) > 0;
		mtmDCDC12OutOfTemperatureRange = ((raw >> 6) & 0x1) > 0;
		mtmDCDC5OutOfTemperatureRange = ((raw >> 5) & 0x1) > 0;
		mtm12VOvercurrent = ((raw >> 4) & 0x1) > 0;
		mtm5VOvercurrent = ((raw >> 3) & 0x1) > 0;
	}

	public boolean isMtmInternalError() {
		return mtmInternalError;
	}

	public void setMtmInternalError(boolean mtmInternalError) {
		this.mtmInternalError = mtmInternalError;
	}

	public boolean isWatchdogRebootError() {
		return watchdogRebootError;
	}

	public void setWatchdogRebootError(boolean watchdogRebootError) {
		this.watchdogRebootError = watchdogRebootError;
	}

	public boolean isMtmCommunicationError() {
		return mtmCommunicationError;
	}

	public void setMtmCommunicationError(boolean mtmCommunicationError) {
		this.mtmCommunicationError = mtmCommunicationError;
	}

	public boolean isMtmGenError() {
		return mtmGenError;
	}

	public void setMtmGenError(boolean mtmGenError) {
		this.mtmGenError = mtmGenError;
	}

	public boolean isMtmxCoilError() {
		return mtmxCoilError;
	}

	public void setMtmxCoilError(boolean mtmxCoilError) {
		this.mtmxCoilError = mtmxCoilError;
	}

	public boolean isMtmyCoilError() {
		return mtmyCoilError;
	}

	public void setMtmyCoilError(boolean mtmyCoilError) {
		this.mtmyCoilError = mtmyCoilError;
	}

	public boolean isMtmzCoilError() {
		return mtmzCoilError;
	}

	public void setMtmzCoilError(boolean mtmzCoilError) {
		this.mtmzCoilError = mtmzCoilError;
	}

	public boolean isRteMSError() {
		return rteMSError;
	}

	public void setRteMSError(boolean rteMSError) {
		this.rteMSError = rteMSError;
	}

	public boolean isStandBy() {
		return standBy;
	}

	public void setStandBy(boolean standBy) {
		this.standBy = standBy;
	}

	public boolean isMtmDCDC12OutOfTemperatureRange() {
		return mtmDCDC12OutOfTemperatureRange;
	}

	public void setMtmDCDC12OutOfTemperatureRange(boolean mtmDCDC12OutOfTemperatureRange) {
		this.mtmDCDC12OutOfTemperatureRange = mtmDCDC12OutOfTemperatureRange;
	}

	public boolean isMtmDCDC5OutOfTemperatureRange() {
		return mtmDCDC5OutOfTemperatureRange;
	}

	public void setMtmDCDC5OutOfTemperatureRange(boolean mtmDCDC5OutOfTemperatureRange) {
		this.mtmDCDC5OutOfTemperatureRange = mtmDCDC5OutOfTemperatureRange;
	}

	public boolean isMtm12VOvercurrent() {
		return mtm12VOvercurrent;
	}

	public void setMtm12VOvercurrent(boolean mtm12vOvercurrent) {
		mtm12VOvercurrent = mtm12vOvercurrent;
	}

	public boolean isMtm5VOvercurrent() {
		return mtm5VOvercurrent;
	}

	public void setMtm5VOvercurrent(boolean mtm5vOvercurrent) {
		mtm5VOvercurrent = mtm5vOvercurrent;
	}

}
