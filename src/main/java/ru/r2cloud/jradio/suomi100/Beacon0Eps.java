package ru.r2cloud.jradio.suomi100;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Beacon0Eps {

	private long timestamp;
	private int[] pvVolt;
	private int battVolt;
	private int[] outputCurrent;
	private int[] pvCurrent;
	private int battInCurrent;
	private int battOutCurrent;
	private short[] temperature;
	private int battMode;
	
	public Beacon0Eps() {
		//do nothing
	}

	public Beacon0Eps(DataInputStream dis) throws IOException {
		timestamp = StreamUtils.readUnsignedInt(dis);
		pvVolt = readUint16(3, dis);
		battVolt = dis.readUnsignedShort();
		outputCurrent = readUint16(7, dis);
		pvCurrent = readUint16(3, dis);
		battInCurrent = dis.readUnsignedShort();
		battOutCurrent = dis.readUnsignedShort();
		temperature = new short[6];
		for (int i = 0; i < temperature.length; i++) {
			temperature[i] = dis.readShort();
		}
		battMode = dis.readUnsignedByte();
	}

	private static int[] readUint16(int size, DataInputStream dis) throws IOException {
		int[] result = new int[size];
		for (int i = 0; i < result.length; i++) {
			result[i] = dis.readUnsignedShort();
		}
		return result;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int[] getPvVolt() {
		return pvVolt;
	}

	public void setPvVolt(int[] pvVolt) {
		this.pvVolt = pvVolt;
	}

	public int getBattVolt() {
		return battVolt;
	}

	public void setBattVolt(int battVolt) {
		this.battVolt = battVolt;
	}

	public int[] getOutputCurrent() {
		return outputCurrent;
	}

	public void setOutputCurrent(int[] outputCurrent) {
		this.outputCurrent = outputCurrent;
	}

	public int[] getPvCurrent() {
		return pvCurrent;
	}

	public void setPvCurrent(int[] pvCurrent) {
		this.pvCurrent = pvCurrent;
	}

	public int getBattInCurrent() {
		return battInCurrent;
	}

	public void setBattInCurrent(int battInCurrent) {
		this.battInCurrent = battInCurrent;
	}

	public int getBattOutCurrent() {
		return battOutCurrent;
	}

	public void setBattOutCurrent(int battOutCurrent) {
		this.battOutCurrent = battOutCurrent;
	}

	public short[] getTemperature() {
		return temperature;
	}

	public void setTemperature(short[] temperature) {
		this.temperature = temperature;
	}

	public int getBattMode() {
		return battMode;
	}

	public void setBattMode(int battMode) {
		this.battMode = battMode;
	}

}
