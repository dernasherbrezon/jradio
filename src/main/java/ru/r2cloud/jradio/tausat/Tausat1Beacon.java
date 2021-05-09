package ru.r2cloud.jradio.tausat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.StreamUtils;

public class Tausat1Beacon extends Ax25Beacon {

	private int commandType;
	private int commandSubType;
	private int length;
	private long satelliteTime;

	private Tausat1Telemetry telemetry;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		commandType = dis.readUnsignedByte();
		commandSubType = dis.readUnsignedByte();
		length = dis.readUnsignedShort();
		satelliteTime = StreamUtils.readUnsignedInt(dis);
		if (commandType == 3 && commandSubType == 25) {
			telemetry = new Tausat1Telemetry(dis);
		} else {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public int getCommandType() {
		return commandType;
	}

	public void setCommandType(int commandType) {
		this.commandType = commandType;
	}

	public int getCommandSubType() {
		return commandSubType;
	}

	public void setCommandSubType(int commandSubType) {
		this.commandSubType = commandSubType;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public long getSatelliteTime() {
		return satelliteTime;
	}

	public void setSatelliteTime(long satelliteTime) {
		this.satelliteTime = satelliteTime;
	}

	public Tausat1Telemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(Tausat1Telemetry telemetry) {
		this.telemetry = telemetry;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
