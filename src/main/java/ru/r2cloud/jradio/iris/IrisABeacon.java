package ru.r2cloud.jradio.iris;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.ccsds.PrimaryHeader;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.BitInputStream;
import ru.r2cloud.jradio.util.StreamUtils;

public class IrisABeacon extends Ax25Beacon {

	private SecondaryHeader secondaryHeader;
	private PrimaryHeader primaryHeader;

	private int tmPacketPusVersionNumber;
	private int serviceTypeId;
	private int messageSubtypeId;
	private long time;

	private Telemetry telemetry;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		secondaryHeader = new SecondaryHeader(dis);
		primaryHeader = new PrimaryHeader(new BitInputStream(dis));

		tmPacketPusVersionNumber = dis.readUnsignedByte();
		serviceTypeId = dis.readUnsignedByte();
		messageSubtypeId = dis.readUnsignedByte();
		time = StreamUtils.readUnsignedInt(dis);
		dis.skipBytes(1);
		if (dis.available() >= 21) {
			telemetry = new Telemetry(dis);
		} else {
			super.readBeacon(dis);
		}
	}

	public SecondaryHeader getSecondaryHeader() {
		return secondaryHeader;
	}

	public void setSecondaryHeader(SecondaryHeader secondaryHeader) {
		this.secondaryHeader = secondaryHeader;
	}

	public PrimaryHeader getPrimaryHeader() {
		return primaryHeader;
	}

	public void setPrimaryHeader(PrimaryHeader primaryHeader) {
		this.primaryHeader = primaryHeader;
	}

	public int getTmPacketPusVersionNumber() {
		return tmPacketPusVersionNumber;
	}

	public void setTmPacketPusVersionNumber(int tmPacketPusVersionNumber) {
		this.tmPacketPusVersionNumber = tmPacketPusVersionNumber;
	}

	public int getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(int serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public int getMessageSubtypeId() {
		return messageSubtypeId;
	}

	public void setMessageSubtypeId(int messageSubtypeId) {
		this.messageSubtypeId = messageSubtypeId;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Telemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(Telemetry telemetry) {
		this.telemetry = telemetry;
	}

}
