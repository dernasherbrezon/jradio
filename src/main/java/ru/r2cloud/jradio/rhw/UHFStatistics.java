package ru.r2cloud.jradio.rhw;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class UHFStatistics {

	private CanStatistics canStatistics;
	private long bootCount;
	private int lastBootReason;
	private boolean memoryViolationResetHasOccured;
	private short internalTemp;
	private long current_csp_packet_number;
	private long allowed_relay_packet_count;
	private long rxCspFrameCount;
	private long rxRelayFrameCount;
	private long txCspFrameCount;
	private long rxFifoErrorCount;
	private long txFifoErrorCount;

	public UHFStatistics(LittleEndianDataInputStream dis) throws IOException {
		canStatistics = new CanStatistics(dis);
		bootCount = dis.readUnsignedInt();
		lastBootReason = dis.readUnsignedShort();
		memoryViolationResetHasOccured = dis.readUnsignedByte() > 0;
		internalTemp = dis.readShort();
		current_csp_packet_number = dis.readUnsignedInt();
		allowed_relay_packet_count = dis.readUnsignedInt();
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

	public long getCurrent_csp_packet_number() {
		return current_csp_packet_number;
	}

	public void setCurrent_csp_packet_number(long current_csp_packet_number) {
		this.current_csp_packet_number = current_csp_packet_number;
	}

	public long getAllowed_relay_packet_count() {
		return allowed_relay_packet_count;
	}

	public void setAllowed_relay_packet_count(long allowed_relay_packet_count) {
		this.allowed_relay_packet_count = allowed_relay_packet_count;
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

}
