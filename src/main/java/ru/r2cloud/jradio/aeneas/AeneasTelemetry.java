package ru.r2cloud.jradio.aeneas;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class AeneasTelemetry {

	private int type;
	private AeneasTimestamp time;
	private int reboots;
	private int rebootCause;

	private boolean deviceIsBusy;
	private boolean deviceIsWriteEnabled;
	private boolean allSectorsSoftwareProtected;
	private boolean wpIsDeasserted;
	private boolean lastOpErrorDetected;
	private boolean sectorProtectionRegistersLocked;

	private long tlmPointer;
	private long plyPointer;
	private long miscWritePointer;
	
	public AeneasTelemetry() {
		//do nothing
	}

	public AeneasTelemetry(DataInputStream dis) throws IOException {
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		type = ldis.readUnsignedByte();
		ldis.skipBytes(2);
		time = new AeneasTimestamp(ldis);
		reboots = ldis.readUnsignedShort();
		rebootCause = ldis.readUnsignedShort();

		int raw = ldis.readUnsignedByte();
		deviceIsBusy = (raw & 0x1) > 0;
		deviceIsWriteEnabled = ((raw >> 1) & 0x1) > 0;
		allSectorsSoftwareProtected = (((raw >> 2) & 0b11) == 0b11);
		wpIsDeasserted = ((raw >> 4) & 0x1) > 0;
		lastOpErrorDetected = ((raw >> 5) & 0x1) > 0;
		sectorProtectionRegistersLocked = ((raw >> 7) & 0x1) > 0;

		ldis.skipBytes(1);
		// no RadioStatusReport
		ldis.skipBytes(1);

		tlmPointer = ldis.readUnsignedInt();
		plyPointer = ldis.readUnsignedInt();
		miscWritePointer = ldis.readUnsignedInt();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public AeneasTimestamp getTime() {
		return time;
	}

	public void setTime(AeneasTimestamp time) {
		this.time = time;
	}

	public int getReboots() {
		return reboots;
	}

	public void setReboots(int reboots) {
		this.reboots = reboots;
	}

	public int getRebootCause() {
		return rebootCause;
	}

	public void setRebootCause(int rebootCause) {
		this.rebootCause = rebootCause;
	}

	public boolean isDeviceIsBusy() {
		return deviceIsBusy;
	}

	public void setDeviceIsBusy(boolean deviceIsBusy) {
		this.deviceIsBusy = deviceIsBusy;
	}

	public boolean isDeviceIsWriteEnabled() {
		return deviceIsWriteEnabled;
	}

	public void setDeviceIsWriteEnabled(boolean deviceIsWriteEnabled) {
		this.deviceIsWriteEnabled = deviceIsWriteEnabled;
	}

	public boolean isAllSectorsSoftwareProtected() {
		return allSectorsSoftwareProtected;
	}

	public void setAllSectorsSoftwareProtected(boolean allSectorsSoftwareProtected) {
		this.allSectorsSoftwareProtected = allSectorsSoftwareProtected;
	}

	public boolean isWpIsDeasserted() {
		return wpIsDeasserted;
	}

	public void setWpIsDeasserted(boolean wpIsDeasserted) {
		this.wpIsDeasserted = wpIsDeasserted;
	}

	public boolean isLastOpErrorDetected() {
		return lastOpErrorDetected;
	}

	public void setLastOpErrorDetected(boolean lastOpErrorDetected) {
		this.lastOpErrorDetected = lastOpErrorDetected;
	}

	public boolean isSectorProtectionRegistersLocked() {
		return sectorProtectionRegistersLocked;
	}

	public void setSectorProtectionRegistersLocked(boolean sectorProtectionRegistersLocked) {
		this.sectorProtectionRegistersLocked = sectorProtectionRegistersLocked;
	}

	public long getTlmPointer() {
		return tlmPointer;
	}

	public void setTlmPointer(long tlmPointer) {
		this.tlmPointer = tlmPointer;
	}

	public long getPlyPointer() {
		return plyPointer;
	}

	public void setPlyPointer(long plyPointer) {
		this.plyPointer = plyPointer;
	}

	public long getMiscWritePointer() {
		return miscWritePointer;
	}

	public void setMiscWritePointer(long miscWritePointer) {
		this.miscWritePointer = miscWritePointer;
	}

}
