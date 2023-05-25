package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.cute.CommandStatus;

public class SohCommandTlm {

	private CommandStatus commandStatus;
	private int realtimeCmdAcceptCounter;
	private int realtimeCmdRejectCounter;
	private int storedCmdAcceptCounter;
	private int storedCmdRejectCounter;

	public SohCommandTlm() {
		// do nothing
	}

	public SohCommandTlm(DataInputStream dis) throws IOException {
		commandStatus = CommandStatus.valueOfCode(dis.readUnsignedByte());
		realtimeCmdAcceptCounter = dis.readUnsignedByte();
		realtimeCmdRejectCounter = dis.readUnsignedByte();
		storedCmdAcceptCounter = dis.readUnsignedByte();
		storedCmdRejectCounter = dis.readUnsignedByte();
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

}
