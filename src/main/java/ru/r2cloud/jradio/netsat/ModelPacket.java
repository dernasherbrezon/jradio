package ru.r2cloud.jradio.netsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ModelPacket {

	private TelemetryPayload telemetry;
	private int packetType;
	private int uid;
	private int type;
	private long time;
	private byte[] unknownPayload;

	public ModelPacket() {
		// do nothing
	}

	public ModelPacket(LittleEndianDataInputStream ldis) throws IOException {
		packetType = ldis.readUnsignedByte();
		uid = ldis.readUnsignedShort();
		type = ldis.readUnsignedByte();
		int length = ldis.readUnsignedByte();
		time = ldis.readUnsigned6Bytes();
		if (type == 0xAF && length == 0x2A) {
			telemetry = new TelemetryPayload(ldis);
		} else {
			unknownPayload = new byte[length];
			ldis.readFully(unknownPayload);
		}
	}

	public TelemetryPayload getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(TelemetryPayload telemetry) {
		this.telemetry = telemetry;
	}

	public int getPacketType() {
		return packetType;
	}

	public void setPacketType(int packetType) {
		this.packetType = packetType;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
