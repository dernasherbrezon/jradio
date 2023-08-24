package ru.r2cloud.jradio.celesta;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;
import ru.r2cloud.jradio.util.StreamUtils;

public class Obdh {

	private long timestamp;
	private int temperature;
	private SatelliteMode satelliteMode;
	private ObdhMode obdhMode;
	private long bytesToTransmit;
	private int numberOfResets;
	private int numberOfErrors;

	public Obdh() {
		// do nothing
	}

	public Obdh(DataInputStream dis) throws IOException {
		// some fields are little ending, some not. weird
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		timestamp = StreamUtils.readUnsignedInt(dis);
		temperature = ldis.readUnsignedShort();
		satelliteMode = SatelliteMode.valueOfCode(dis.readUnsignedByte());
		obdhMode = ObdhMode.valueOfCode(dis.readUnsignedByte());
		bytesToTransmit = ldis.readUnsignedInt();
		numberOfResets = ldis.readUnsignedShort();
		numberOfErrors = ldis.readUnsignedShort();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
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
