package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class BusOverride {

	private Address commandId;
	private int size;
	private Address messageSource;
	private Service service;
	private Address tlmSource;
	private ObcTelemetryFull obcTelemetry;
	private EpsTelemetryFull epsTelemetry;
	private AdbTelemetryFull adbTelemetry;
	private CommsTelemetry commsTelemetry;

	public BusOverride() {
		// do nothing
	}

	public BusOverride(DataInputStream dis) throws IOException {
		commandId = Address.valueOfCode(dis.readUnsignedByte());
		size = dis.readUnsignedByte();
		messageSource = Address.valueOfCode(dis.readUnsignedByte());
		service = Service.valueOfCode(dis.readUnsignedByte());
		tlmSource = Address.valueOfCode(dis.readUnsignedByte());
		switch (tlmSource) {
		case OBC:
			obcTelemetry = new ObcTelemetryFull(dis);
			break;
		case EPS:
			epsTelemetry = new EpsTelemetryFull(dis);
			break;
		case ADB:
			adbTelemetry = new AdbTelemetryFull(dis);
			break;
		case COMMS:
			commsTelemetry = new CommsTelemetry(dis);
			break;
		default:
			break;
		}
	}

	public Address getCommandId() {
		return commandId;
	}

	public void setCommandId(Address commandId) {
		this.commandId = commandId;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Address getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(Address messageSource) {
		this.messageSource = messageSource;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Address getTlmSource() {
		return tlmSource;
	}

	public void setTlmSource(Address tlmSource) {
		this.tlmSource = tlmSource;
	}

	public ObcTelemetryFull getObcTelemetry() {
		return obcTelemetry;
	}

	public void setObcTelemetry(ObcTelemetryFull obcTelemetry) {
		this.obcTelemetry = obcTelemetry;
	}

	public EpsTelemetryFull getEpsTelemetry() {
		return epsTelemetry;
	}

	public void setEpsTelemetry(EpsTelemetryFull epsTelemetry) {
		this.epsTelemetry = epsTelemetry;
	}

	public AdbTelemetryFull getAdbTelemetry() {
		return adbTelemetry;
	}

	public void setAdbTelemetry(AdbTelemetryFull adbTelemetry) {
		this.adbTelemetry = adbTelemetry;
	}

	public CommsTelemetry getCommsTelemetry() {
		return commsTelemetry;
	}

	public void setCommsTelemetry(CommsTelemetry commsTelemetry) {
		this.commsTelemetry = commsTelemetry;
	}

}
