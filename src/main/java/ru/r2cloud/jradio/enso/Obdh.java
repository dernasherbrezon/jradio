package ru.r2cloud.jradio.enso;

import java.io.IOException;

import ru.r2cloud.jradio.celesta.ObdhMode;
import ru.r2cloud.jradio.celesta.SatelliteMode;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Obdh {

	private long timestamp;
	private float temperature;
	private SatelliteMode satelliteMode;
	private ObdhMode obdhMode;
	private long bytesToTransmit;
	private int numberOfResets;
	private int numberOfErrors;

	public Obdh() {
		// do nothing
	}

	public Obdh(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		int tempRaw = dis.readUnsignedShort();
		if (tempRaw <= 4095) {
			temperature = tempRaw * 0.0625f;
		} else {
			temperature = (tempRaw - 8192) * 0.0625f;
		}
		satelliteMode = SatelliteMode.valueOfCode(dis.readUnsignedByte());
		obdhMode = ObdhMode.valueOfCode(dis.readUnsignedByte());
		bytesToTransmit = dis.readUnsignedInt();
		numberOfResets = dis.readUnsignedShort();
		numberOfErrors = dis.readUnsignedShort();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public SatelliteMode getSatelliteMode() {
		return satelliteMode;
	}

	public void setSatelliteMode(SatelliteMode satelliteMode) {
		this.satelliteMode = satelliteMode;
	}

	public ObdhMode getObdhMode() {
		return obdhMode;
	}

	public void setObdhMode(ObdhMode obdhMode) {
		this.obdhMode = obdhMode;
	}

	public long getBytesToTransmit() {
		return bytesToTransmit;
	}

	public void setBytesToTransmit(long bytesToTransmit) {
		this.bytesToTransmit = bytesToTransmit;
	}

	public int getNumberOfResets() {
		return numberOfResets;
	}

	public void setNumberOfResets(int numberOfResets) {
		this.numberOfResets = numberOfResets;
	}

	public int getNumberOfErrors() {
		return numberOfErrors;
	}

	public void setNumberOfErrors(int numberOfErrors) {
		this.numberOfErrors = numberOfErrors;
	}

}
