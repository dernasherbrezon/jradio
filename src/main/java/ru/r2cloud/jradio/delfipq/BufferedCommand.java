package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class BufferedCommand {

	private Address commandId;
	private Address destination;
	private int size;
	private Address source;
	private Service service;
	private MessageType request;

	private ObcTelemetryFull obcTelemetry;
	private EpsTelemetryFull epsTelemetry;
	private AdbTelemetryFull adbTelemetry;
	private CommsTelemetryFull commsTelemetry;

	public BufferedCommand() {
		// do nothing
	}

	public BufferedCommand(DataInputStream dis) throws IOException {
		commandId = Address.valueOfCode(dis.readUnsignedByte());
		destination = Address.valueOfCode(dis.readUnsignedByte());
		size = dis.readUnsignedByte();
		source = Address.valueOfCode(dis.readUnsignedByte());
		service = Service.valueOfCode(dis.readUnsignedByte());
		request = MessageType.valueOfCode(dis.readUnsignedByte());
		// not really source here:
		// https://github.com/DelfiSpace/gr-satellites/blob/034e6aa24d968a5f02517e7cb20734e7e3d7e9f2/python/telemetry/delfipq.py#L305
		// typo?
		switch (source) {
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
			commsTelemetry = new CommsTelemetryFull(dis);
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

	public Address getSource() {
		return source;
	}

	public void setSource(Address source) {
		this.source = source;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public MessageType getRequest() {
		return request;
	}

	public void setRequest(MessageType request) {
		this.request = request;
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

	public CommsTelemetryFull getCommsTelemetry() {
		return commsTelemetry;
	}

	public void setCommsTelemetry(CommsTelemetryFull commsTelemetry) {
		this.commsTelemetry = commsTelemetry;
	}

}
