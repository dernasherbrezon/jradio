package ru.r2cloud.jradio.rhw;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class EpsStatistics {

	private long bootCount;
	private int periodicBootCount;
	private int[] bootReasons;
	private int lastBootReason;
	private long totalUptimeSeconds;
	private long uptimeSeconds;
	private boolean memoryViolationResetHasOccured;
	private short internalTemp;

	public EpsStatistics(LittleEndianDataInputStream dis) throws IOException {
		bootCount = dis.readUnsignedInt();
		periodicBootCount = dis.readUnsignedShort();
		bootReasons = new int[12];
		for (int i = 0; i < bootReasons.length; i++) {
			bootReasons[i] = dis.readUnsignedByte();
		}
		lastBootReason = dis.readUnsignedByte();
		totalUptimeSeconds = dis.readUnsignedInt();
		uptimeSeconds = dis.readUnsignedInt();
		memoryViolationResetHasOccured = dis.readUnsignedByte() > 0;
		internalTemp = dis.readShort();
	}

	public long getBootCount() {
		return bootCount;
	}

	public void setBootCount(long bootCount) {
		this.bootCount = bootCount;
	}

	public int getPeriodicBootCount() {
		return periodicBootCount;
	}

	public void setPeriodicBootCount(int periodicBootCount) {
		this.periodicBootCount = periodicBootCount;
	}

	public int[] getBootReasons() {
		return bootReasons;
	}

	public void setBootReasons(int[] bootReasons) {
		this.bootReasons = bootReasons;
	}

	public int getLastBootReason() {
		return lastBootReason;
	}

	public void setLastBootReason(int lastBootReason) {
		this.lastBootReason = lastBootReason;
	}

	public long getTotalUptimeSeconds() {
		return totalUptimeSeconds;
	}

	public void setTotalUptimeSeconds(long totalUptimeSeconds) {
		this.totalUptimeSeconds = totalUptimeSeconds;
	}

	public long getUptimeSeconds() {
		return uptimeSeconds;
	}

	public void setUptimeSeconds(long uptimeSeconds) {
		this.uptimeSeconds = uptimeSeconds;
	}

	public boolean isMemoryViolationResetHasOccured() {
		return memoryViolationResetHasOccured;
	}

	public void setMemoryViolationResetHasOccured(boolean memoryViolationResetHasOccured) {
		this.memoryViolationResetHasOccured = memoryViolationResetHasOccured;
	}

	public short getInternalTemp() {
		return internalTemp;
	}

	public void setInternalTemp(short internalTemp) {
		this.internalTemp = internalTemp;
	}

}
