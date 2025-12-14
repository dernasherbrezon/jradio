package ru.r2cloud.jradio.hunity;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Obc5 {

	private long timestamp;

	private SampleTime stxTemperatureTime;
	private float stxTemperature;
	private SampleTime gpsLattitudeTime;
	private float gpsLattitude;
	private SampleTime gpsLongitudeTime;
	private float gpsLongitude;
	private SampleTime gpsCourseTime;
	private int gpsCourse;
	private SampleTime gpsSpeedTime;
	private long gpsSpeed;
	private SampleTime gpsSatellitesTime;
	private int gpsSatellites;
	private SampleTime gpsValidTime;
	private int gpsValid;
	private SampleTime dosimeterTemperatureTime;
	private float dosimeterTemperature;
	private SampleTime dosimeter1Time;
	private long dosimeter1;
	private SampleTime dosimeter2Time;
	private long dosimeter2;

	public Obc5() {
		// do nothing
	}

	public Obc5(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();

		int i16 = dis.readShort();
		stxTemperatureTime = SampleTime.valueOf3Bit(i16);
		stxTemperature = (i16 >> 3) / 10.0f;

		gpsLattitudeTime = SampleTime.valueOfByte(dis.readUnsignedByte());
		gpsLattitude = dis.readInt() / 10000.0f;
		gpsLongitudeTime = SampleTime.valueOfByte(dis.readUnsignedByte());
		gpsLongitude = dis.readInt() / 10000.0f;
		gpsCourseTime = SampleTime.valueOfByte(dis.readUnsignedByte());
		gpsCourse = dis.readUnsignedShort();
		gpsSpeedTime = SampleTime.valueOfByte(dis.readUnsignedByte());
		gpsSpeed = dis.readUnsignedInt();
		gpsSatellitesTime = SampleTime.valueOfByte(dis.readUnsignedByte());
		gpsSatellites = dis.readUnsignedByte();
		gpsValidTime = SampleTime.valueOfByte(dis.readUnsignedByte());
		gpsValid = dis.readUnsignedByte();

		i16 = dis.readShort();
		dosimeterTemperatureTime = SampleTime.valueOf3Bit(i16);
		dosimeterTemperature = (i16 >> 3) / 10.0f;
		dosimeter1Time = SampleTime.valueOfByte(dis.readUnsignedByte());
		dosimeter1 = dis.readUnsignedInt();
		dosimeter2Time = SampleTime.valueOfByte(dis.readUnsignedByte());
		dosimeter2 = dis.readUnsignedInt();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public SampleTime getStxTemperatureTime() {
		return stxTemperatureTime;
	}

	public void setStxTemperatureTime(SampleTime stxTemperatureTime) {
		this.stxTemperatureTime = stxTemperatureTime;
	}

	public float getStxTemperature() {
		return stxTemperature;
	}

	public void setStxTemperature(float stxTemperature) {
		this.stxTemperature = stxTemperature;
	}

	public SampleTime getGpsLattitudeTime() {
		return gpsLattitudeTime;
	}

	public void setGpsLattitudeTime(SampleTime gpsLattitudeTime) {
		this.gpsLattitudeTime = gpsLattitudeTime;
	}

	public float getGpsLattitude() {
		return gpsLattitude;
	}

	public void setGpsLattitude(float gpsLattitude) {
		this.gpsLattitude = gpsLattitude;
	}

	public SampleTime getGpsLongitudeTime() {
		return gpsLongitudeTime;
	}

	public void setGpsLongitudeTime(SampleTime gpsLongitudeTime) {
		this.gpsLongitudeTime = gpsLongitudeTime;
	}

	public float getGpsLongitude() {
		return gpsLongitude;
	}

	public void setGpsLongitude(float gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	public SampleTime getGpsCourseTime() {
		return gpsCourseTime;
	}

	public void setGpsCourseTime(SampleTime gpsCourseTime) {
		this.gpsCourseTime = gpsCourseTime;
	}

	public int getGpsCourse() {
		return gpsCourse;
	}

	public void setGpsCourse(int gpsCourse) {
		this.gpsCourse = gpsCourse;
	}

	public SampleTime getGpsSpeedTime() {
		return gpsSpeedTime;
	}

	public void setGpsSpeedTime(SampleTime gpsSpeedTime) {
		this.gpsSpeedTime = gpsSpeedTime;
	}

	public long getGpsSpeed() {
		return gpsSpeed;
	}

	public void setGpsSpeed(long gpsSpeed) {
		this.gpsSpeed = gpsSpeed;
	}

	public SampleTime getGpsSatellitesTime() {
		return gpsSatellitesTime;
	}

	public void setGpsSatellitesTime(SampleTime gpsSatellitesTime) {
		this.gpsSatellitesTime = gpsSatellitesTime;
	}

	public int getGpsSatellites() {
		return gpsSatellites;
	}

	public void setGpsSatellites(int gpsSatellites) {
		this.gpsSatellites = gpsSatellites;
	}

	public SampleTime getGpsValidTime() {
		return gpsValidTime;
	}

	public void setGpsValidTime(SampleTime gpsValidTime) {
		this.gpsValidTime = gpsValidTime;
	}

	public int getGpsValid() {
		return gpsValid;
	}

	public void setGpsValid(int gpsValid) {
		this.gpsValid = gpsValid;
	}

	public SampleTime getDosimeterTemperatureTime() {
		return dosimeterTemperatureTime;
	}

	public void setDosimeterTemperatureTime(SampleTime dosimeterTemperatureTime) {
		this.dosimeterTemperatureTime = dosimeterTemperatureTime;
	}

	public float getDosimeterTemperature() {
		return dosimeterTemperature;
	}

	public void setDosimeterTemperature(float dosimeterTemperature) {
		this.dosimeterTemperature = dosimeterTemperature;
	}

	public SampleTime getDosimeter1Time() {
		return dosimeter1Time;
	}

	public void setDosimeter1Time(SampleTime dosimeter1Time) {
		this.dosimeter1Time = dosimeter1Time;
	}

	public long getDosimeter1() {
		return dosimeter1;
	}

	public void setDosimeter1(long dosimeter1) {
		this.dosimeter1 = dosimeter1;
	}

	public SampleTime getDosimeter2Time() {
		return dosimeter2Time;
	}

	public void setDosimeter2Time(SampleTime dosimeter2Time) {
		this.dosimeter2Time = dosimeter2Time;
	}

	public long getDosimeter2() {
		return dosimeter2;
	}

	public void setDosimeter2(long dosimeter2) {
		this.dosimeter2 = dosimeter2;
	}

}
