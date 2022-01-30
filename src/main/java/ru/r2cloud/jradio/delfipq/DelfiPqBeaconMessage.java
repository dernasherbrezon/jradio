package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class DelfiPqBeaconMessage {

	private Address destination;
	private int size;
	private Address beaconSource;
	private Service service;
	private MessageType messageType;
	private MessageOutcome messageOutcome;
	private Address tlmSource;

	private ObcTelemetry obcTelemetry;
	private EpsTelemetry epsTelemetry;
	private AdbTelemetry adbTelemetry;
	private CommsTelemetry commsTelemetry;

	public DelfiPqBeaconMessage() {
		// do nothing
	}

	public DelfiPqBeaconMessage(DataInputStream dis) throws IOException {
		destination = Address.valueOfCode(dis.readUnsignedByte());
		size = dis.readUnsignedByte();
		beaconSource = Address.valueOfCode(dis.readUnsignedByte());
		service = Service.valueOfCode(dis.readUnsignedByte());
		messageType = MessageType.valueOfCode(dis.readUnsignedByte());
		messageOutcome = MessageOutcome.valueOfCode(dis.readUnsignedByte());
		tlmSource = Address.valueOfCode(dis.readUnsignedByte());
		switch (tlmSource) {
		case OBC:
			obcTelemetry = new ObcTelemetry(dis);
			break;
		case EPS:
			epsTelemetry = new EpsTelemetry(dis);
			break;
		case ADB:
			adbTelemetry = new AdbTelemetry(dis);
			break;
		case COMMS:
			commsTelemetry = new CommsTelemetry(dis);
			break;
		default:
			break;
		}
	}

	public Address getDestination() {
		return destination;
	}

	public void setDestination(Address destination) {
		this.destination = destination;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Address getBeaconSource() {
		return beaconSource;
	}

	public void setBeaconSource(Address beaconSource) {
		this.beaconSource = beaconSource;
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

	public MessageOutcome getMessageOutcome() {
		return messageOutcome;
	}

	public void setMessageOutcome(MessageOutcome messageOutcome) {
		this.messageOutcome = messageOutcome;
	}

	public Address getTlmSource() {
		return tlmSource;
	}

	public void setTlmSource(Address tlmSource) {
		this.tlmSource = tlmSource;
	}

	public ObcTelemetry getObcTelemetry() {
		return obcTelemetry;
	}

	public void setObcTelemetry(ObcTelemetry obcTelemetry) {
		this.obcTelemetry = obcTelemetry;
	}

	public EpsTelemetry getEpsTelemetry() {
		return epsTelemetry;
	}

	public void setEpsTelemetry(EpsTelemetry epsTelemetry) {
		this.epsTelemetry = epsTelemetry;
	}

	public AdbTelemetry getAdbTelemetry() {
		return adbTelemetry;
	}

	public void setAdbTelemetry(AdbTelemetry adbTelemetry) {
		this.adbTelemetry = adbTelemetry;
	}

	public CommsTelemetry getCommsTelemetry() {
		return commsTelemetry;
	}

	public void setCommsTelemetry(CommsTelemetry commsTelemetry) {
		this.commsTelemetry = commsTelemetry;
	}

}
