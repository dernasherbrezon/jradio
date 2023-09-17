package ru.r2cloud.jradio.geoscan;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class GeoscanAdc {

	private long adcTimestamp;
	private long sunSensorPosX;
	private long sunSensorNegX;
	private long sunSensorPosY;
	private long sunSensorNegY;
	private long sunSensorNegZ;
	private byte magSensorPosX;
	private byte magSensorNegX;
	private byte magSensorPosY;
	private byte magSensorNegY;
	private byte magSensorPosZ;
	private byte magSensorNegZ;
	private byte temperaturePosX;
	private byte temperatureNegX;
	private byte temperaturePosY;
	private byte temperatureNegY;
	private byte temperaturePosZ;
	private byte temperatureNegZ;
	private byte temperatureCell1;
	private byte temperatureCell2;
	private long status;

	public GeoscanAdc() {
		// do nothing
	}

	public GeoscanAdc(LittleEndianDataInputStream dis) throws IOException {
		adcTimestamp = dis.readUnsignedInt();
		sunSensorPosX = dis.readUnsignedInt();
		sunSensorNegX = dis.readUnsignedInt();
		sunSensorPosY = dis.readUnsignedInt();
		sunSensorNegY = dis.readUnsignedInt();
		sunSensorNegZ = dis.readUnsignedInt();
		magSensorPosX = dis.readByte();
		magSensorNegX = dis.readByte();
		magSensorPosY = dis.readByte();
		magSensorNegY = dis.readByte();
		magSensorPosZ = dis.readByte();
		magSensorNegZ = dis.readByte();
		temperaturePosX = dis.readByte();
		temperatureNegX = dis.readByte();
		temperaturePosY = dis.readByte();
		temperatureNegY = dis.readByte();
		temperaturePosZ = dis.readByte();
		temperatureNegZ = dis.readByte();
		temperatureCell1 = dis.readByte();
		temperatureCell2 = dis.readByte();
		status = dis.readUnsignedInt();
	}

	public long getAdcTimestamp() {
		return adcTimestamp;
	}

	public void setAdcTimestamp(long adcTimestamp) {
		this.adcTimestamp = adcTimestamp;
	}

	public long getSunSensorPosX() {
		return sunSensorPosX;
	}

	public void setSunSensorPosX(long sunSensorPosX) {
		this.sunSensorPosX = sunSensorPosX;
	}

	public long getSunSensorNegX() {
		return sunSensorNegX;
	}

	public void setSunSensorNegX(long sunSensorNegX) {
		this.sunSensorNegX = sunSensorNegX;
	}

	public long getSunSensorPosY() {
		return sunSensorPosY;
	}

	public void setSunSensorPosY(long sunSensorPosY) {
		this.sunSensorPosY = sunSensorPosY;
	}

	public long getSunSensorNegY() {
		return sunSensorNegY;
	}

	public void setSunSensorNegY(long sunSensorNegY) {
		this.sunSensorNegY = sunSensorNegY;
	}

	public long getSunSensorNegZ() {
		return sunSensorNegZ;
	}

	public void setSunSensorNegZ(long sunSensorNegZ) {
		this.sunSensorNegZ = sunSensorNegZ;
	}

	public byte getMagSensorPosX() {
		return magSensorPosX;
	}

	public void setMagSensorPosX(byte magSensorPosX) {
		this.magSensorPosX = magSensorPosX;
	}

	public byte getMagSensorNegX() {
		return magSensorNegX;
	}

	public void setMagSensorNegX(byte magSensorNegX) {
		this.magSensorNegX = magSensorNegX;
	}

	public byte getMagSensorPosY() {
		return magSensorPosY;
	}

	public void setMagSensorPosY(byte magSensorPosY) {
		this.magSensorPosY = magSensorPosY;
	}

	public byte getMagSensorNegY() {
		return magSensorNegY;
	}

	public void setMagSensorNegY(byte magSensorNegY) {
		this.magSensorNegY = magSensorNegY;
	}

	public byte getMagSensorPosZ() {
		return magSensorPosZ;
	}

	public void setMagSensorPosZ(byte magSensorPosZ) {
		this.magSensorPosZ = magSensorPosZ;
	}

	public byte getMagSensorNegZ() {
		return magSensorNegZ;
	}

	public void setMagSensorNegZ(byte magSensorNegZ) {
		this.magSensorNegZ = magSensorNegZ;
	}

	public byte getTemperaturePosX() {
		return temperaturePosX;
	}

	public void setTemperaturePosX(byte temperaturePosX) {
		this.temperaturePosX = temperaturePosX;
	}

	public byte getTemperatureNegX() {
		return temperatureNegX;
	}

	public void setTemperatureNegX(byte temperatureNegX) {
		this.temperatureNegX = temperatureNegX;
	}

	public byte getTemperaturePosY() {
		return temperaturePosY;
	}

	public void setTemperaturePosY(byte temperaturePosY) {
		this.temperaturePosY = temperaturePosY;
	}

	public byte getTemperatureNegY() {
		return temperatureNegY;
	}

	public void setTemperatureNegY(byte temperatureNegY) {
		this.temperatureNegY = temperatureNegY;
	}

	public byte getTemperaturePosZ() {
		return temperaturePosZ;
	}

	public void setTemperaturePosZ(byte temperaturePosZ) {
		this.temperaturePosZ = temperaturePosZ;
	}

	public byte getTemperatureNegZ() {
		return temperatureNegZ;
	}

	public void setTemperatureNegZ(byte temperatureNegZ) {
		this.temperatureNegZ = temperatureNegZ;
	}

	public byte getTemperatureCell1() {
		return temperatureCell1;
	}

	public void setTemperatureCell1(byte temperatureCell1) {
		this.temperatureCell1 = temperatureCell1;
	}

	public byte getTemperatureCell2() {
		return temperatureCell2;
	}

	public void setTemperatureCell2(byte temperatureCell2) {
		this.temperatureCell2 = temperatureCell2;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

}
