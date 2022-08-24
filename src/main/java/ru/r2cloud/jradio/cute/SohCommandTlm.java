package ru.r2cloud.jradio.cute;

import java.io.DataInputStream;
import java.io.IOException;

public class SohCommandTlm {

	private CommandStatus commandStatus;
	private int realtimeCmdAcceptCounter;
	private int realtimeCmdRejectCounter;
	private int storedCmdAcceptCounter;
	private int storedCmdRejectCounter;
	private int macrosExecutingPack1;
	private int macrosExecutingPack2;

	public SohCommandTlm() {
		// do nothing
	}

	public SohCommandTlm(DataInputStream dis) throws IOException {
		commandStatus = CommandStatus.valueOfCode(dis.readUnsignedByte());
		realtimeCmdAcceptCounter = dis.readUnsignedByte();
		realtimeCmdRejectCounter = dis.readUnsignedByte();
		storedCmdAcceptCounter = dis.readUnsignedByte();
		storedCmdRejectCounter = dis.readUnsignedByte();
		macrosExecutingPack1 = dis.readUnsignedByte();
		macrosExecutingPack2 = dis.readUnsignedByte();
	}

	public CommandStatus getCommandStatus() {
		return commandStatus;
	}

	public void setCommandStatus(CommandStatus commandStatus) {
		this.commandStatus = commandStatus;
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

	public int getMacrosExecutingPack1() {
		return macrosExecutingPack1;
	}

	public void setMacrosExecutingPack1(int macrosExecutingPack1) {
		this.macrosExecutingPack1 = macrosExecutingPack1;
	}

	public int getMacrosExecutingPack2() {
		return macrosExecutingPack2;
	}

	public void setMacrosExecutingPack2(int macrosExecutingPack2) {
		this.macrosExecutingPack2 = macrosExecutingPack2;
	}

}
