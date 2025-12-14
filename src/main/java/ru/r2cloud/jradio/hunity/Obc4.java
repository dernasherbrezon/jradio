package ru.r2cloud.jradio.hunity;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Obc4 {

	private long timestamp;
	private Bat[] bat;

	private SampleTime expTemperatureTime;
	private float expTemperature;

	private SampleTime wingXmYmTemperatureTime;
	private float wingXmYmTemperature;
	private SampleTime wingXpYmTemperatureTime;
	private float wingXpYmTemperature;

	private SampleTime sideXmTemperatureTime;
	private float sideXmTemperature;
	private SampleTime sideXpTemperatureTime;
	private float sideXpTemperature;
	private SampleTime sideYmTemperatureTime;
	private float sideYmTemperature;
	private SampleTime sideYpTemperatureTime;
	private float sideYpTemperature;
	private SampleTime sideZmTemperatureTime;
	private float sideZmTemperature;

	public Obc4() {
		// do nothing
	}

	public Obc4(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		bat = new Bat[2];
		for (int i = 0; i < bat.length; i++) {
			bat[i] = new Bat(dis);
		}
		for (int i = 0; i < bat.length; i++) {
			bat[i].setGyroXTime(SampleTime.valueOfByte(dis.readUnsignedByte()));
			bat[i].setGyroX(dis.readFloat());
			bat[i].setGyroYTime(SampleTime.valueOfByte(dis.readUnsignedByte()));
			bat[i].setGyroY(dis.readFloat());
			bat[i].setGyroZTime(SampleTime.valueOfByte(dis.readUnsignedByte()));
			bat[i].setGyroZ(dis.readFloat());
		}

		for (int i = 0; i < bat.length; i++) {
			bat[i].setMagnetoXTime(SampleTime.valueOfByte(dis.readUnsignedByte()));
			bat[i].setMagnetoX(dis.readInt());
			bat[i].setMagnetoYTime(SampleTime.valueOfByte(dis.readUnsignedByte()));
			bat[i].setMagnetoY(dis.readInt());
			bat[i].setMagnetoZTime(SampleTime.valueOfByte(dis.readUnsignedByte()));
			bat[i].setMagnetoZ(dis.readInt());
		}

		int i16 = dis.readShort();
		expTemperatureTime = SampleTime.valueOf3Bit(i16);
		expTemperature = (i16 >> 3) / 10.0f;

		i16 = dis.readShort();
		wingXmYmTemperatureTime = SampleTime.valueOf3Bit(i16);
		wingXmYmTemperature = (i16 >> 3) / 10.0f;
		i16 = dis.readShort();
		wingXpYmTemperatureTime = SampleTime.valueOf3Bit(i16);
		wingXpYmTemperature = (i16 >> 3) / 10.0f;

		i16 = dis.readShort();
		sideXmTemperatureTime = SampleTime.valueOf3Bit(i16);
		sideXmTemperature = (i16 >> 3) / 10.0f;
		i16 = dis.readShort();
		sideXpTemperatureTime = SampleTime.valueOf3Bit(i16);
		sideXpTemperature = (i16 >> 3) / 10.0f;
		i16 = dis.readShort();
		sideYmTemperatureTime = SampleTime.valueOf3Bit(i16);
		sideYmTemperature = (i16 >> 3) / 10.0f;
		i16 = dis.readShort();
		sideYpTemperatureTime = SampleTime.valueOf3Bit(i16);
		sideYpTemperature = (i16 >> 3) / 10.0f;
		i16 = dis.readShort();
		sideZmTemperatureTime = SampleTime.valueOf3Bit(i16);
		sideZmTemperature = (i16 >> 3) / 10.0f;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Bat[] getBat() {
		return bat;
	}

	public void setBat(Bat[] bat) {
		this.bat = bat;
	}

	public SampleTime getExpTemperatureTime() {
		return expTemperatureTime;
	}

	public void setExpTemperatureTime(SampleTime expTemperatureTime) {
		this.expTemperatureTime = expTemperatureTime;
	}

	public float getExpTemperature() {
		return expTemperature;
	}

	public void setExpTemperature(float expTemperature) {
		this.expTemperature = expTemperature;
	}

	public SampleTime getWingXmYmTemperatureTime() {
		return wingXmYmTemperatureTime;
	}

	public void setWingXmYmTemperatureTime(SampleTime wingXmYmTemperatureTime) {
		this.wingXmYmTemperatureTime = wingXmYmTemperatureTime;
	}

	public float getWingXmYmTemperature() {
		return wingXmYmTemperature;
	}

	public void setWingXmYmTemperature(float wingXmYmTemperature) {
		this.wingXmYmTemperature = wingXmYmTemperature;
	}

	public SampleTime getWingXpYmTemperatureTime() {
		return wingXpYmTemperatureTime;
	}

	public void setWingXpYmTemperatureTime(SampleTime wingXpYmTemperatureTime) {
		this.wingXpYmTemperatureTime = wingXpYmTemperatureTime;
	}

	public float getWingXpYmTemperature() {
		return wingXpYmTemperature;
	}

	public void setWingXpYmTemperature(float wingXpYmTemperature) {
		this.wingXpYmTemperature = wingXpYmTemperature;
	}

	public SampleTime getSideXmTemperatureTime() {
		return sideXmTemperatureTime;
	}

	public void setSideXmTemperatureTime(SampleTime sideXmTemperatureTime) {
		this.sideXmTemperatureTime = sideXmTemperatureTime;
	}

	public float getSideXmTemperature() {
		return sideXmTemperature;
	}

	public void setSideXmTemperature(float sideXmTemperature) {
		this.sideXmTemperature = sideXmTemperature;
	}

	public SampleTime getSideXpTemperatureTime() {
		return sideXpTemperatureTime;
	}

	public void setSideXpTemperatureTime(SampleTime sideXpTemperatureTime) {
		this.sideXpTemperatureTime = sideXpTemperatureTime;
	}

	public float getSideXpTemperature() {
		return sideXpTemperature;
	}

	public void setSideXpTemperature(float sideXpTemperature) {
		this.sideXpTemperature = sideXpTemperature;
	}

	public SampleTime getSideYmTemperatureTime() {
		return sideYmTemperatureTime;
	}

	public void setSideYmTemperatureTime(SampleTime sideYmTemperatureTime) {
		this.sideYmTemperatureTime = sideYmTemperatureTime;
	}

	public float getSideYmTemperature() {
		return sideYmTemperature;
	}

	public void setSideYmTemperature(float sideYmTemperature) {
		this.sideYmTemperature = sideYmTemperature;
	}

	public SampleTime getSideYpTemperatureTime() {
		return sideYpTemperatureTime;
	}

	public void setSideYpTemperatureTime(SampleTime sideYpTemperatureTime) {
		this.sideYpTemperatureTime = sideYpTemperatureTime;
	}

	public float getSideYpTemperature() {
		return sideYpTemperature;
	}

	public void setSideYpTemperature(float sideYpTemperature) {
		this.sideYpTemperature = sideYpTemperature;
	}

	public SampleTime getSideZmTemperatureTime() {
		return sideZmTemperatureTime;
	}

	public void setSideZmTemperatureTime(SampleTime sideZmTemperatureTime) {
		this.sideZmTemperatureTime = sideZmTemperatureTime;
	}

	public float getSideZmTemperature() {
		return sideZmTemperature;
	}

	public void setSideZmTemperature(float sideZmTemperature) {
		this.sideZmTemperature = sideZmTemperature;
	}

}
