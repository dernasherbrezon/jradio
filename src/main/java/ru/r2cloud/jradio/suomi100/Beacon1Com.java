package ru.r2cloud.jradio.suomi100;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Beacon1Com {

	private long timestamp;
	private int txDuty;
	private long totalTxCount;
	private long totalRxCount;
	private long totalTxBytes;
	private long totalRxBytes;
	private int bootCount;
	private long bootCause;
	private long txBytes;
	private long rxBytes;
	private int config;
	private long txCount;
	private long rxCount;

	public Beacon1Com(DataInputStream dis) throws IOException {
		timestamp = StreamUtils.readUnsignedInt(dis);
		txDuty = dis.readUnsignedByte();
		totalTxCount = StreamUtils.readUnsignedInt(dis);
		totalRxCount = StreamUtils.readUnsignedInt(dis);
		totalTxBytes = StreamUtils.readUnsignedInt(dis);
		totalRxBytes = StreamUtils.readUnsignedInt(dis);
		bootCount = dis.readUnsignedShort();
		bootCause = StreamUtils.readUnsignedInt(dis);
		txBytes = StreamUtils.readUnsignedInt(dis);
		rxBytes = StreamUtils.readUnsignedInt(dis);
		config = dis.readUnsignedByte();
		txCount = StreamUtils.readUnsignedInt(dis);
		rxCount = StreamUtils.readUnsignedInt(dis);
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getTxDuty() {
		return txDuty;
	}

	public void setTxDuty(int txDuty) {
		this.txDuty = txDuty;
	}

	public long getTotalTxCount() {
		return totalTxCount;
	}

	public void setTotalTxCount(long totalTxCount) {
		this.totalTxCount = totalTxCount;
	}

	public long getTotalRxCount() {
		return totalRxCount;
	}

	public void setTotalRxCount(long totalRxCount) {
		this.totalRxCount = totalRxCount;
	}

	public long getTotalTxBytes() {
		return totalTxBytes;
	}

	public void setTotalTxBytes(long totalTxBytes) {
		this.totalTxBytes = totalTxBytes;
	}

	public long getTotalRxBytes() {
		return totalRxBytes;
	}

	public void setTotalRxBytes(long totalRxBytes) {
		this.totalRxBytes = totalRxBytes;
	}

	public int getBootCount() {
		return bootCount;
	}

	public void setBootCount(int bootCount) {
		this.bootCount = bootCount;
	}

	public long getBootCause() {
		return bootCause;
	}

	public void setBootCause(long bootCause) {
		this.bootCause = bootCause;
	}

	public long getTxBytes() {
		return txBytes;
	}

	public void setTxBytes(long txBytes) {
		this.txBytes = txBytes;
	}

	public long getRxBytes() {
		return rxBytes;
	}

	public void setRxBytes(long rxBytes) {
		this.rxBytes = rxBytes;
	}

	public int getConfig() {
		return config;
	}

	public void setConfig(int config) {
		this.config = config;
	}

	public long getTxCount() {
		return txCount;
	}

	public void setTxCount(long txCount) {
		this.txCount = txCount;
	}

	public long getRxCount() {
		return rxCount;
	}

	public void setRxCount(long rxCount) {
		this.rxCount = rxCount;
	}

}
