package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.cute.CommandStatus;
import ru.r2cloud.jradio.util.StreamUtils;

public class FswCommandTlm {

	private CommandStatus commandStatus;
	private CommandStatus commandRejectStatus;
	private int realtimeCmdAcceptCounter;
	private int realtimeCmdRejectCounter;
	private int storedCmdAcceptCounter;
	private int storedCmdRejectCounter;
	private int[] lastAccCmd;
	private int[] lastRejCmd;
	private int healthStatusInterval;
	private int navTlmInterval;
	private boolean storedTimedCmdsEnabled;
	private boolean macrosEnabled;
	private int lastStCmdQueued;
	private int lastStCmdExpired;
	private int lastAutonomousMacro;
	private long storedTimedCmdsQueued;
	private long storedTimedCmdsExpired;
	private long macroCmdsQueued;
	private long macroCmdsExpired;
	private int[] macrosExecutingPack;
	private long packageExecTime;
	private long lastRtCmdRunCount;

	public FswCommandTlm() {
		// do nothing
	}

	public FswCommandTlm(DataInputStream dis) throws IOException {
		commandStatus = CommandStatus.valueOfCode(dis.readUnsignedByte());
		commandRejectStatus = CommandStatus.valueOfCode(dis.readUnsignedByte());
		realtimeCmdAcceptCounter = dis.readUnsignedByte();
		realtimeCmdRejectCounter = dis.readUnsignedByte();
		storedCmdAcceptCounter = dis.readUnsignedByte();
		storedCmdRejectCounter = dis.readUnsignedByte();
		lastAccCmd = StreamUtils.readUnsignedByteArray(dis, 8);
		lastRejCmd = StreamUtils.readUnsignedByteArray(dis, 8);
		healthStatusInterval = dis.readUnsignedShort();
		navTlmInterval = dis.readUnsignedShort();
		storedTimedCmdsEnabled = dis.readBoolean();
		macrosEnabled = dis.readBoolean();
		lastStCmdQueued = dis.readUnsignedShort();
		lastStCmdExpired = dis.readUnsignedShort();
		lastAutonomousMacro = dis.readUnsignedShort();
		storedTimedCmdsQueued = StreamUtils.readUnsignedInt(dis);
		storedTimedCmdsExpired = StreamUtils.readUnsignedInt(dis);
		macroCmdsQueued = StreamUtils.readUnsignedInt(dis);
		macroCmdsExpired = StreamUtils.readUnsignedInt(dis);
		macrosExecutingPack = StreamUtils.readUnsignedByteArray(dis, 12);
		packageExecTime = StreamUtils.readUnsignedInt(dis);
		lastRtCmdRunCount = StreamUtils.readUnsignedInt(dis);
	}

	public CommandStatus getCommandStatus() {
		return commandStatus;
	}

	public void setCommandStatus(CommandStatus commandStatus) {
		this.commandStatus = commandStatus;
	}

	public CommandStatus getCommandRejectStatus() {
		return commandRejectStatus;
	}

	public void setCommandRejectStatus(CommandStatus commandRejectStatus) {
		this.commandRejectStatus = commandRejectStatus;
	}

	public int getRealtimeCmdAcceptCounter() {
		return realtimeCmdAcceptCounter;
	}

	public void setRealtimeCmdAcceptCounter(int realtimeCmdAcceptCounter) {
		this.realtimeCmdAcceptCounter = realtimeCmdAcceptCounter;
	}

	public int getRealtimeCmdRejectCounter() {
		return realtimeCmdRejectCounter;
	}

	public void setRealtimeCmdRejectCounter(int realtimeCmdRejectCounter) {
		this.realtimeCmdRejectCounter = realtimeCmdRejectCounter;
	}

	public int getStoredCmdAcceptCounter() {
		return storedCmdAcceptCounter;
	}

	public void setStoredCmdAcceptCounter(int storedCmdAcceptCounter) {
		this.storedCmdAcceptCounter = storedCmdAcceptCounter;
	}

	public int getStoredCmdRejectCounter() {
		return storedCmdRejectCounter;
	}

	public void setStoredCmdRejectCounter(int storedCmdRejectCounter) {
		this.storedCmdRejectCounter = storedCmdRejectCounter;
	}

	public int[] getLastAccCmd() {
		return lastAccCmd;
	}

	public void setLastAccCmd(int[] lastAccCmd) {
		this.lastAccCmd = lastAccCmd;
	}

	public int[] getLastRejCmd() {
		return lastRejCmd;
	}

	public void setLastRejCmd(int[] lastRejCmd) {
		this.lastRejCmd = lastRejCmd;
	}

	public int getHealthStatusInterval() {
		return healthStatusInterval;
	}

	public void setHealthStatusInterval(int healthStatusInterval) {
		this.healthStatusInterval = healthStatusInterval;
	}

	public int getNavTlmInterval() {
		return navTlmInterval;
	}

	public void setNavTlmInterval(int navTlmInterval) {
		this.navTlmInterval = navTlmInterval;
	}

	public boolean isStoredTimedCmdsEnabled() {
		return storedTimedCmdsEnabled;
	}

	public void setStoredTimedCmdsEnabled(boolean storedTimedCmdsEnabled) {
		this.storedTimedCmdsEnabled = storedTimedCmdsEnabled;
	}

	public boolean isMacrosEnabled() {
		return macrosEnabled;
	}

	public void setMacrosEnabled(boolean macrosEnabled) {
		this.macrosEnabled = macrosEnabled;
	}

	public int getLastStCmdQueued() {
		return lastStCmdQueued;
	}

	public void setLastStCmdQueued(int lastStCmdQueued) {
		this.lastStCmdQueued = lastStCmdQueued;
	}

	public int getLastStCmdExpired() {
		return lastStCmdExpired;
	}

	public void setLastStCmdExpired(int lastStCmdExpired) {
		this.lastStCmdExpired = lastStCmdExpired;
	}

	public int getLastAutonomousMacro() {
		return lastAutonomousMacro;
	}

	public void setLastAutonomousMacro(int lastAutonomousMacro) {
		this.lastAutonomousMacro = lastAutonomousMacro;
	}

	public long getStoredTimedCmdsQueued() {
		return storedTimedCmdsQueued;
	}

	public void setStoredTimedCmdsQueued(long storedTimedCmdsQueued) {
		this.storedTimedCmdsQueued = storedTimedCmdsQueued;
	}

	public long getStoredTimedCmdsExpired() {
		return storedTimedCmdsExpired;
	}

	public void setStoredTimedCmdsExpired(long storedTimedCmdsExpired) {
		this.storedTimedCmdsExpired = storedTimedCmdsExpired;
	}

	public long getMacroCmdsQueued() {
		return macroCmdsQueued;
	}

	public void setMacroCmdsQueued(long macroCmdsQueued) {
		this.macroCmdsQueued = macroCmdsQueued;
	}

	public long getMacroCmdsExpired() {
		return macroCmdsExpired;
	}

	public void setMacroCmdsExpired(long macroCmdsExpired) {
		this.macroCmdsExpired = macroCmdsExpired;
	}

	public int[] getMacrosExecutingPack() {
		return macrosExecutingPack;
	}

	public void setMacrosExecutingPack(int[] macrosExecutingPack) {
		this.macrosExecutingPack = macrosExecutingPack;
	}

	public long getPackageExecTime() {
		return packageExecTime;
	}

	public void setPackageExecTime(long packageExecTime) {
		this.packageExecTime = packageExecTime;
	}

	public long getLastRtCmdRunCount() {
		return lastRtCmdRunCount;
	}

	public void setLastRtCmdRunCount(long lastRtCmdRunCount) {
		this.lastRtCmdRunCount = lastRtCmdRunCount;
	}

}
