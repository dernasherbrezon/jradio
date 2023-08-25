package ru.r2cloud.jradio.is7;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry {

	private int size;
	private long commandeReceived;
	private long commandeWithError;
	private long frameNotProcessed;
	private long digipeaterMesssageProcessed;
	private long experiemntalCommand;
	private long experiemntalCommandError;
	private long mailboxCommandeReceived;
	private long mailboxErrorCommandeReceived;
	private long payloadCommandeReceived;
	private long payloadCommandWithError;
	private long timestamp1;
	private long timestamp2;
	private int lastResetCause;
	private int currentState;

	public Telemetry() {
		// do nothing
	}

	public Telemetry(LittleEndianDataInputStream dis) throws IOException {
		size = dis.readUnsignedShort();
		commandeReceived = dis.readUnsignedInt();
		commandeWithError = dis.readUnsignedInt();
		frameNotProcessed = dis.readUnsignedInt();
		digipeaterMesssageProcessed = dis.readUnsignedInt();
		experiemntalCommand = dis.readUnsignedInt();
		experiemntalCommandError = dis.readUnsignedInt();
		mailboxCommandeReceived = dis.readUnsignedInt();
		mailboxErrorCommandeReceived = dis.readUnsignedInt();
		payloadCommandeReceived = dis.readUnsignedInt();
		payloadCommandWithError = dis.readUnsignedInt();
		timestamp1 = dis.readUnsignedInt();
		timestamp2 = dis.readUnsignedInt();
		lastResetCause = dis.readUnsignedShort();
		currentState = dis.readUnsignedShort();
		dis.skipBytes(4);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getCommandeReceived() {
		return commandeReceived;
	}

	public void setCommandeReceived(long commandeReceived) {
		this.commandeReceived = commandeReceived;
	}

	public long getCommandeWithError() {
		return commandeWithError;
	}

	public void setCommandeWithError(long commandeWithError) {
		this.commandeWithError = commandeWithError;
	}

	public long getFrameNotProcessed() {
		return frameNotProcessed;
	}

	public void setFrameNotProcessed(long frameNotProcessed) {
		this.frameNotProcessed = frameNotProcessed;
	}

	public long getDigipeaterMesssageProcessed() {
		return digipeaterMesssageProcessed;
	}

	public void setDigipeaterMesssageProcessed(long digipeaterMesssageProcessed) {
		this.digipeaterMesssageProcessed = digipeaterMesssageProcessed;
	}

	public long getExperiemntalCommand() {
		return experiemntalCommand;
	}

	public void setExperiemntalCommand(long experiemntalCommand) {
		this.experiemntalCommand = experiemntalCommand;
	}

	public long getExperiemntalCommandError() {
		return experiemntalCommandError;
	}

	public void setExperiemntalCommandError(long experiemntalCommandError) {
		this.experiemntalCommandError = experiemntalCommandError;
	}

	public long getMailboxCommandeReceived() {
		return mailboxCommandeReceived;
	}

	public void setMailboxCommandeReceived(long mailboxCommandeReceived) {
		this.mailboxCommandeReceived = mailboxCommandeReceived;
	}

	public long getMailboxErrorCommandeReceived() {
		return mailboxErrorCommandeReceived;
	}

	public void setMailboxErrorCommandeReceived(long mailboxErrorCommandeReceived) {
		this.mailboxErrorCommandeReceived = mailboxErrorCommandeReceived;
	}

	public long getPayloadCommandeReceived() {
		return payloadCommandeReceived;
	}

	public void setPayloadCommandeReceived(long payloadCommandeReceived) {
		this.payloadCommandeReceived = payloadCommandeReceived;
	}

	public long getPayloadCommandWithError() {
		return payloadCommandWithError;
	}

	public void setPayloadCommandWithError(long payloadCommandWithError) {
		this.payloadCommandWithError = payloadCommandWithError;
	}

	public long getTimestamp1() {
		return timestamp1;
	}

	public void setTimestamp1(long timestamp1) {
		this.timestamp1 = timestamp1;
	}

	public long getTimestamp2() {
		return timestamp2;
	}

	public void setTimestamp2(long timestamp2) {
		this.timestamp2 = timestamp2;
	}

	public int getLastResetCause() {
		return lastResetCause;
	}

	public void setLastResetCause(int lastResetCause) {
		this.lastResetCause = lastResetCause;
	}

	public int getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int currentState) {
		this.currentState = currentState;
	}

}
