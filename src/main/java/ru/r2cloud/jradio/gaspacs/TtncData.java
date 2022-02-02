package ru.r2cloud.jradio.gaspacs;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class TtncData {

	private long timestamp;
	private int missionMode;
	private int rebootCount;
	private float boomboxUv;
	private float spxPlusTemp1;
	private float spzPlusTemp2;
	private float raspberryPiTemp;
	private float epsMcuTemp;
	private float cell1BatteryTemp;
	private float cell2BatteryTemp;
	private float batteryVoltage;
	private float batteryCurrent;
	private float bcrVoltage;
	private float bcrCurrent;
	private float eps3v3Current;
	private float eps5vCurrent;
	private float spxVoltage;
	private float spxPlusCurrent;
	private float spxMinusCurrent;
	private float spyVoltage;
	private float spyPlusCurrent;
	private float spyMinusCurrent;
	private float spzVoltage;
	private float spzPlusCurrent;

	public TtncData() {
		// do nothing
	}

	public TtncData(DataInputStream dis) throws IOException {
		timestamp = StreamUtils.readUnsignedInt(dis);
		missionMode = dis.readUnsignedByte();
		rebootCount = dis.readUnsignedShort();
		boomboxUv = dis.readFloat();
		spxPlusTemp1 = dis.readFloat();
		spzPlusTemp2 = dis.readFloat();
		raspberryPiTemp = dis.readFloat();
		epsMcuTemp = dis.readFloat();
		cell1BatteryTemp = dis.readFloat();
		cell2BatteryTemp = dis.readFloat();
		batteryVoltage = dis.readFloat();
		batteryCurrent = dis.readFloat();
		bcrVoltage = dis.readFloat();
		bcrCurrent = dis.readFloat();
		eps3v3Current = dis.readFloat();
		eps5vCurrent = dis.readFloat();
		spxVoltage = dis.readFloat();
		spxPlusCurrent = dis.readFloat();
		spxMinusCurrent = dis.readFloat();
		spyVoltage = dis.readFloat();
		spyPlusCurrent = dis.readFloat();
		spyMinusCurrent = dis.readFloat();
		spzVoltage = dis.readFloat();
		spzPlusCurrent = dis.readFloat();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getMissionMode() {
		return missionMode;
	}

	public void setMissionMode(int missionMode) {
		this.missionMode = missionMode;
	}

	public int getRebootCount() {
		return rebootCount;
	}

	public void setRebootCount(int rebootCount) {
		this.rebootCount = rebootCount;
	}

	public float getBoomboxUv() {
		return boomboxUv;
	}

	public void setBoomboxUv(float boomboxUv) {
		this.boomboxUv = boomboxUv;
	}

	public float getSpxPlusTemp1() {
		return spxPlusTemp1;
	}

	public void setSpxPlusTemp1(float spxPlusTemp1) {
		this.spxPlusTemp1 = spxPlusTemp1;
	}

	public float getSpzPlusTemp2() {
		return spzPlusTemp2;
	}

	public void setSpzPlusTemp2(float spzPlusTemp2) {
		this.spzPlusTemp2 = spzPlusTemp2;
	}

	public float getRaspberryPiTemp() {
		return raspberryPiTemp;
	}

	public void setRaspberryPiTemp(float raspberryPiTemp) {
		this.raspberryPiTemp = raspberryPiTemp;
	}

	public float getEpsMcuTemp() {
		return epsMcuTemp;
	}

	public void setEpsMcuTemp(float epsMcuTemp) {
		this.epsMcuTemp = epsMcuTemp;
	}

	public float getCell1BatteryTemp() {
		return cell1BatteryTemp;
	}

	public void setCell1BatteryTemp(float cell1BatteryTemp) {
		this.cell1BatteryTemp = cell1BatteryTemp;
	}

	public float getCell2BatteryTemp() {
		return cell2BatteryTemp;
	}

	public void setCell2BatteryTemp(float cell2BatteryTemp) {
		this.cell2BatteryTemp = cell2BatteryTemp;
	}

	public float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public float getBatteryCurrent() {
		return batteryCurrent;
	}

	public void setBatteryCurrent(float batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
	}

	public float getBcrVoltage() {
		return bcrVoltage;
	}

	public void setBcrVoltage(float bcrVoltage) {
		this.bcrVoltage = bcrVoltage;
	}

	public float getBcrCurrent() {
		return bcrCurrent;
	}

	public void setBcrCurrent(float bcrCurrent) {
		this.bcrCurrent = bcrCurrent;
	}

	public float getEps3v3Current() {
		return eps3v3Current;
	}

	public void setEps3v3Current(float eps3v3Current) {
		this.eps3v3Current = eps3v3Current;
	}

	public float getEps5vCurrent() {
		return eps5vCurrent;
	}

	public void setEps5vCurrent(float eps5vCurrent) {
		this.eps5vCurrent = eps5vCurrent;
	}

	public float getSpxVoltage() {
		return spxVoltage;
	}

	public void setSpxVoltage(float spxVoltage) {
		this.spxVoltage = spxVoltage;
	}

	public float getSpxPlusCurrent() {
		return spxPlusCurrent;
	}

	public void setSpxPlusCurrent(float spxPlusCurrent) {
		this.spxPlusCurrent = spxPlusCurrent;
	}

	public float getSpxMinusCurrent() {
		return spxMinusCurrent;
	}

	public void setSpxMinusCurrent(float spxMinusCurrent) {
		this.spxMinusCurrent = spxMinusCurrent;
	}

	public float getSpyVoltage() {
		return spyVoltage;
	}

	public void setSpyVoltage(float spyVoltage) {
		this.spyVoltage = spyVoltage;
	}

	public float getSpyPlusCurrent() {
		return spyPlusCurrent;
	}

	public void setSpyPlusCurrent(float spyPlusCurrent) {
		this.spyPlusCurrent = spyPlusCurrent;
	}

	public float getSpyMinusCurrent() {
		return spyMinusCurrent;
	}

	public void setSpyMinusCurrent(float spyMinusCurrent) {
		this.spyMinusCurrent = spyMinusCurrent;
	}

	public float getSpzVoltage() {
		return spzVoltage;
	}

	public void setSpzVoltage(float spzVoltage) {
		this.spzVoltage = spzVoltage;
	}

	public float getSpzPlusCurrent() {
		return spzPlusCurrent;
	}

	public void setSpzPlusCurrent(float spzPlusCurrent) {
		this.spzPlusCurrent = spzPlusCurrent;
	}

}
