package ru.r2cloud.jradio.nexus;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class FieldIntensityData {

	private long satelliteTime;
	private float[] fiData;

	public FieldIntensityData() {
		// do nothing
	}

	public FieldIntensityData(DataInputStream dis) throws IOException {
		satelliteTime = (long) (LittleEndianDataInputStream.readUnsignedInt(dis) * 0.5 * 1000);
		fiData = new float[dis.available() / 2];
		for (int i = 0; i < fiData.length; i++) {
			float vin = dis.readUnsignedShort() * 2.048f / 1024;
			fiData[i] = 121.89f * vin * vin * vin - 574.69f * vin * vin + 953.85f * vin - 607.93f;
		}
	}

	public long getSatelliteTime() {
		return satelliteTime;
	}

	public void setSatelliteTime(long satelliteTime) {
		this.satelliteTime = satelliteTime;
	}

	public float[] getFiData() {
		return fiData;
	}

	public void setFiData(float[] fiData) {
		this.fiData = fiData;
	}
	
}
