package ru.r2cloud.jradio.snuglite;

import java.io.DataInputStream;
import java.io.IOException;

public class SimpleBeacon {

	private int firmwareVersion;
	private int timeYear;
	private int timeMonth;
	private int timeDay;
	private int timeHour;
	private int timeMinute;
	private int timeSecond;
	private Positioning positioning;;
	private int positionX;
	private int positionY;
	private int positionZ;
	private int velocityX;
	private int velocityY;
	private int velocityZ;
	private BatteryMode batteryMode;
	private int batteryVoltage;

	public SimpleBeacon() {
		// do nothing
	}

	public SimpleBeacon(DataInputStream dis) throws IOException {
		firmwareVersion = dis.readUnsignedByte();
		timeYear = dis.readUnsignedByte();
		timeMonth = dis.readUnsignedByte();
		timeDay = dis.readUnsignedByte();
		timeHour = dis.readUnsignedByte();
		timeMinute = dis.readUnsignedByte();
		timeSecond = dis.readUnsignedByte();
		positioning = Positioning.valufOfCode(dis.readUnsignedByte());
		positionX = dis.readInt();
		positionY = dis.readInt();
		positionZ = dis.readInt();
		velocityX = dis.readInt();
		velocityY = dis.readInt();
		velocityZ = dis.readInt();
		batteryMode = BatteryMode.valufOfCode(dis.readUnsignedByte());
		batteryVoltage = dis.readUnsignedShort();
	}

	public int getFirmwareVersion() {
		return firmwareVersion;
	}

	public void setFirmwareVersion(int firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}

	public int getTimeYear() {
		return timeYear;
	}

	public void setTimeYear(int timeYear) {
		this.timeYear = timeYear;
	}

	public int getTimeMonth() {
		return timeMonth;
	}

	public void setTimeMonth(int timeMonth) {
		this.timeMonth = timeMonth;
	}

	public int getTimeDay() {
		return timeDay;
	}

	public void setTimeDay(int timeDay) {
		this.timeDay = timeDay;
	}

	public int getTimeHour() {
		return timeHour;
	}

	public void setTimeHour(int timeHour) {
		this.timeHour = timeHour;
	}

	public int getTimeMinute() {
		return timeMinute;
	}

	public void setTimeMinute(int timeMinute) {
		this.timeMinute = timeMinute;
	}

	public int getTimeSecond() {
		return timeSecond;
	}

	public void setTimeSecond(int timeSecond) {
		this.timeSecond = timeSecond;
	}

	public Positioning getPositioning() {
		return positioning;
	}

	public void setPositioning(Positioning positioning) {
		this.positioning = positioning;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getPositionZ() {
		return positionZ;
	}

	public void setPositionZ(int positionZ) {
		this.positionZ = positionZ;
	}

	public int getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

	public int getVelocityZ() {
		return velocityZ;
	}

	public void setVelocityZ(int velocityZ) {
		this.velocityZ = velocityZ;
	}

	public BatteryMode getBatteryMode() {
		return batteryMode;
	}

	public void setBatteryMode(BatteryMode batteryMode) {
		this.batteryMode = batteryMode;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

}
