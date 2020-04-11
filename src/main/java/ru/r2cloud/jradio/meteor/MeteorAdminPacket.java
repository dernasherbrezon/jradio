package ru.r2cloud.jradio.meteor;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeteorAdminPacket {

	private static final Logger LOG = LoggerFactory.getLogger(MeteorAdminPacket.class);

	private int hour;
	private int minute;
	private int second;

	private int lineScanLatency;
	private int msuMrCode;

	private DigitalTelemetry digitalTelemetry;
	private AnalogTelemetry analogTelemetry;

	private byte[] calibrationDetails;

	public MeteorAdminPacket() {
		// do nothing
	}

	public MeteorAdminPacket(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		dis.skipBytes(8);
		hour = dis.readUnsignedByte();
		minute = dis.readUnsignedByte();
		second = dis.readUnsignedByte();
		lineScanLatency = dis.readUnsignedByte();
		msuMrCode = dis.readUnsignedByte();
		int telemetryType = dis.readUnsignedByte();
		if (telemetryType == 0) {
			digitalTelemetry = new DigitalTelemetry(dis);
		} else if (telemetryType == 0b00001111) {
			analogTelemetry = new AnalogTelemetry(dis);
		} else {
			LOG.info("unknown telemetry type: {}", telemetryType);
			dis.skipBytes(16);
		}
		dis.skipBytes(5);
		calibrationDetails = new byte[15];
		dis.readFully(calibrationDetails);
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getLineScanLatency() {
		return lineScanLatency;
	}

	public void setLineScanLatency(int lineScanLatency) {
		this.lineScanLatency = lineScanLatency;
	}

	public int getMsuMrCode() {
		return msuMrCode;
	}

	public void setMsuMrCode(int msuMrCode) {
		this.msuMrCode = msuMrCode;
	}

	public DigitalTelemetry getDigitalTelemetry() {
		return digitalTelemetry;
	}

	public void setDigitalTelemetry(DigitalTelemetry digitalTelemetry) {
		this.digitalTelemetry = digitalTelemetry;
	}

	public AnalogTelemetry getAnalogTelemetry() {
		return analogTelemetry;
	}

	public void setAnalogTelemetry(AnalogTelemetry analogTelemetry) {
		this.analogTelemetry = analogTelemetry;
	}

	public byte[] getCalibrationDetails() {
		return calibrationDetails;
	}

	public void setCalibrationDetails(byte[] calibrationDetails) {
		this.calibrationDetails = calibrationDetails;
	}

}
