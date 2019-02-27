package ru.r2cloud.jradio.rhw;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class UHFStatistics {

	private CanStatistics canStatistics;
	private long bootCount;
	private int lastBootReason;
	private boolean memoryViolationResetHasOccured;
	private short internalTemp;
	private long currentCspPacketNumber;
	private long allowedRelayPacketCount;
	private long rxCspFrameCount;
	private long rxRelayFrameCount;
	private long txCspFrameCount;
	private long rxFifoErrorCount;
	private long txFifoErrorCount;
	
	public UHFStatistics() {
		//do nothing
	}

	public UHFStatistics(LittleEndianDataInputStream dis) throws IOException {
		canStatistics = new CanStatistics(dis);
		bootCount = dis.readUnsignedInt();
		lastBootReason = dis.readUnsignedShort();
		memoryViolationResetHasOccured = dis.readUnsignedByte() > 0;
		internalTemp = dis.readShort();
		currentCspPacketNumber = dis.readUnsignedInt();
		allowedRelayPacketCount = dis.readUnsignedShort();
		rxCspFrameCount = dis.readUnsignedInt();
		rxRelayFrameCount = dis.readUnsignedInt();
		txCspFrameCount = dis.readUnsignedInt();
		rxFifoErrorCount = dis.readUnsignedInt();
		txFifoErrorCount = dis.readUnsignedInt();
	}

	public CanStatistics getCanStatistics() {
		return canStatistics;
	}

	public void setCanStatistics(CanStatistics canStatistics) {
		this.canStatistics = canStatistics;
	}

	public long getBootCount() {
		return bootCount;
	}

	public void setBootCount(long bootCount) {
		this.bootCount = bootCount;
	}

	public int getLastBootReason() {
		return lastBootReason;
	}

	public void setLastBootReason(int lastBootReason) {
		this.lastBootReason = lastBootReason;
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

	public long getRxCspFrameCount() {
		return rxCspFrameCount;
	}

	public void setRxCspFrameCount(long rxCspFrameCount) {
		this.rxCspFrameCount = rxCspFrameCount;
	}

	public long getRxRelayFrameCount() {
		return rxRelayFrameCount;
	}

	public void setRxRelayFrameCount(long rxRelayFrameCount) {
		this.rxRelayFrameCount = rxRelayFrameCount;
	}

	public long getTxCspFrameCount() {
		return txCspFrameCount;
	}

	public void setTxCspFrameCount(long txCspFrameCount) {
		this.txCspFrameCount = txCspFrameCount;
	}

	public long getRxFifoErrorCount() {
		return rxFifoErrorCount;
	}

	public void setRxFifoErrorCount(long rxFifoErrorCount) {
		this.rxFifoErrorCount = rxFifoErrorCount;
	}

	public long getTxFifoErrorCount() {
		return txFifoErrorCount;
	}

	public void setTxFifoErrorCount(long txFifoErrorCount) {
		this.txFifoErrorCount = txFifoErrorCount;
	}

	public long getCurrentCspPacketNumber() {
		return currentCspPacketNumber;
	}

	public void setCurrentCspPacketNumber(long currentCspPacketNumber) {
		this.currentCspPacketNumber = currentCspPacketNumber;
	}

	public long getAllowedRelayPacketCount() {
		return allowedRelayPacketCount;
	}

	public void setAllowedRelayPacketCount(long allowedRelayPacketCount) {
		this.allowedRelayPacketCount = allowedRelayPacketCount;
	}

}
