package ru.r2cloud.jradio.roseycub;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.StreamUtils;

public class PeriodicMessage {

	private String message;
	private long time;
	private int batteryVoltage;
	private short batteryCurrent;
	private short temperature;
	private int mode;
	private int epsBootCounter;
	private int commandCounter;

	public PeriodicMessage() {
		// do nothing
	}

	public PeriodicMessage(DataInputStream dis) throws IOException {
		byte[] messagebytes = new byte[30];
		dis.readFully(messagebytes);
		message = new String(messagebytes, StandardCharsets.ISO_8859_1).trim();
		time = StreamUtils.readUnsignedInt(dis);
		batteryVoltage = dis.readUnsignedShort();
		batteryCurrent = dis.readShort();
		temperature = dis.readShort();
		mode = dis.readUnsignedByte();
		epsBootCounter = dis.readUnsignedShort();
		commandCounter = dis.readUnsignedShort();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public short getBatteryCurrent() {
		return batteryCurrent;
	}

	public void setBatteryCurrent(short batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
	}

	public short getTemperature() {
		return temperature;
	}

	public void setTemperature(short temperature) {
		this.temperature = temperature;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getEpsBootCounter() {
		return epsBootCounter;
	}

	public void setEpsBootCounter(int epsBootCounter) {
		this.epsBootCounter = epsBootCounter;
	}

	public int getCommandCounter() {
		return commandCounter;
	}

	public void setCommandCounter(int commandCounter) {
		this.commandCounter = commandCounter;
	}

}
