package ru.r2cloud.jradio.dora;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Payload1 {

	private int sipmAdcValue;
	private int currentPayloadSchedule;
	private long timestamp;
	private int mode;
	private long availableStorage;
	private long availableMemory;
	private long cpuLoad;
	private long rpiUptime;
	private long rebootCount;
	private long numberOfReceivedCommands;
	private int lastReceivedCommand;
	private long errorCount;
	private int filterBankPowerCh1;
	private int filterBankPowerCh2;

	public Payload1() {
		// do nothing
	}

	public Payload1(DataInputStream dis) throws IOException {
		sipmAdcValue = dis.readUnsignedShort();
		currentPayloadSchedule = dis.readUnsignedShort();
		timestamp = dis.readLong() & 0xFFFFFFFFL;
		mode = dis.readUnsignedByte();
		availableStorage = StreamUtils.readUnsignedInt(dis);
		availableMemory = StreamUtils.readUnsignedInt(dis);
		cpuLoad = StreamUtils.readUnsignedInt(dis);
		rpiUptime = StreamUtils.readUnsignedInt(dis);
		rebootCount = StreamUtils.readUnsignedInt(dis);
		numberOfReceivedCommands = StreamUtils.readUnsignedInt(dis);
		lastReceivedCommand = dis.readUnsignedByte();
		errorCount = StreamUtils.readUnsignedInt(dis);
		filterBankPowerCh1 = dis.readUnsignedByte();
		filterBankPowerCh2 = dis.readUnsignedByte();
	}

	public int getSipmAdcValue() {
		return sipmAdcValue;
	}

	public void setSipmAdcValue(int sipmAdcValue) {
		this.sipmAdcValue = sipmAdcValue;
	}

	public int getCurrentPayloadSchedule() {
		return currentPayloadSchedule;
	}

	public void setCurrentPayloadSchedule(int currentPayloadSchedule) {
		this.currentPayloadSchedule = currentPayloadSchedule;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public long getAvailableStorage() {
		return availableStorage;
	}

	public void setAvailableStorage(long availableStorage) {
		this.availableStorage = availableStorage;
	}

	public long getAvailableMemory() {
		return availableMemory;
	}

	public void setAvailableMemory(long availableMemory) {
		this.availableMemory = availableMemory;
	}

	public long getCpuLoad() {
		return cpuLoad;
	}

	public void setCpuLoad(long cpuLoad) {
		this.cpuLoad = cpuLoad;
	}

	public long getRpiUptime() {
		return rpiUptime;
	}

	public void setRpiUptime(long rpiUptime) {
		this.rpiUptime = rpiUptime;
	}

	public long getRebootCount() {
		return rebootCount;
	}

	public void setRebootCount(long rebootCount) {
		this.rebootCount = rebootCount;
	}

	public long getNumberOfReceivedCommands() {
		return numberOfReceivedCommands;
	}

	public void setNumberOfReceivedCommands(long numberOfReceivedCommands) {
		this.numberOfReceivedCommands = numberOfReceivedCommands;
	}

	public int getLastReceivedCommand() {
		return lastReceivedCommand;
	}

	public void setLastReceivedCommand(int lastReceivedCommand) {
		this.lastReceivedCommand = lastReceivedCommand;
	}

	public long getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(long errorCount) {
		this.errorCount = errorCount;
	}

	public int getFilterBankPowerCh1() {
		return filterBankPowerCh1;
	}

	public void setFilterBankPowerCh1(int filterBankPowerCh1) {
		this.filterBankPowerCh1 = filterBankPowerCh1;
	}

	public int getFilterBankPowerCh2() {
		return filterBankPowerCh2;
	}

	public void setFilterBankPowerCh2(int filterBankPowerCh2) {
		this.filterBankPowerCh2 = filterBankPowerCh2;
	}

}
