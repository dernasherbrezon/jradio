package ru.r2cloud.jradio.bsusat1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class RfCommand {

	private int commandId;
	private int commandPriority;
	private int commandTime;
	private byte[] parameters;

	public RfCommand() {
		// do nothing
	}

	public RfCommand(LittleEndianDataInputStream dis) throws IOException {
		commandId = dis.readUnsignedByte();
		commandPriority = dis.readUnsignedByte();
		commandTime = dis.readUnsignedShort();
		parameters = new byte[17];
		dis.readFully(parameters);
	}

	public int getCommandId() {
		return commandId;
	}

	public void setCommandId(int commandId) {
		this.commandId = commandId;
	}

	public int getCommandPriority() {
		return commandPriority;
	}

	public void setCommandPriority(int commandPriority) {
		this.commandPriority = commandPriority;
	}

	public int getCommandTime() {
		return commandTime;
	}

	public void setCommandTime(int commandTime) {
		this.commandTime = commandTime;
	}

	public byte[] getParameters() {
		return parameters;
	}

	public void setParameters(byte[] parameters) {
		this.parameters = parameters;
	}

}
