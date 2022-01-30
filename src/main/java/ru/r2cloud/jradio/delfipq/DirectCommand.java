package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class DirectCommand {

	private Service service;
	private MessageType messageType;
	private PingMessage pingMessage;
	private CommsTelemetryFull telemetry;

	public DirectCommand() {
		// do nothing
	}

	public DirectCommand(DataInputStream dis) throws IOException {
		service = Service.valueOfCode(dis.readUnsignedByte());
		messageType = MessageType.valueOfCode(dis.readUnsignedByte());
		switch (service) {
		case PING:
			pingMessage = new PingMessage();
			break;
		case TELEMETRY:
			telemetry = new CommsTelemetryFull(dis);
			break;
		default:
			break;
		}
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public PingMessage getPingMessage() {
		return pingMessage;
	}

	public void setPingMessage(PingMessage pingMessage) {
		this.pingMessage = pingMessage;
	}

	public CommsTelemetryFull getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(CommsTelemetryFull telemetry) {
		this.telemetry = telemetry;
	}

}
