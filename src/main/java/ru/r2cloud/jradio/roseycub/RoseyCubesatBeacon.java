package ru.r2cloud.jradio.roseycub;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class RoseyCubesatBeacon extends Ax25Beacon {

	private int payloadSize;
	private int payloadTo;
	private int packetId;
	private PeriodicMessage message;
	private ImageChunk imageChunk;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		payloadSize = dis.readUnsignedByte();
		payloadTo = dis.readUnsignedByte();
		packetId = dis.readUnsignedShort();
		if (packetId == 0xFFFF) {
			message = new PeriodicMessage(dis);
		} else if (packetId == 0xA40C) {
			imageChunk = new ImageChunk(dis);
		} else {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public ImageChunk getImageChunk() {
		return imageChunk;
	}

	public void setImageChunk(ImageChunk imageChunk) {
		this.imageChunk = imageChunk;
	}

	public int getPayloadSize() {
		return payloadSize;
	}

	public void setPayloadSize(int payloadSize) {
		this.payloadSize = payloadSize;
	}

	public int getPayloadTo() {
		return payloadTo;
	}

	public void setPayloadTo(int payloadTo) {
		this.payloadTo = payloadTo;
	}

	public int getPacketId() {
		return packetId;
	}

	public void setPacketId(int packetId) {
		this.packetId = packetId;
	}

	public PeriodicMessage getMessage() {
		return message;
	}

	public void setMessage(PeriodicMessage message) {
		this.message = message;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
