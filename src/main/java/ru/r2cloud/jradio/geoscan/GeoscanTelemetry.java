package ru.r2cloud.jradio.geoscan;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class GeoscanTelemetry {

	private long obcTimestamp;
	private float epsCellCurrent;
	private float epsSystemCurrent;
	private float epsCellVoltageHalf;
	private float epsCellVoltageFull;
	private byte adcTemperaturePosX;
	private byte adcTemperatureNegX;
	private byte adcTemperaturePosY;
	private byte adcTemperatureNegY;
	private byte adcTemperaturePosZ;
	private byte adcTemperatureNegZ;
	private byte adcTemperatureCell1;
	private byte adcTemperatureCell2;
	private float obcCpuLoad;
	private int obcBootCount;
	private int commBootCount;
	private byte commRssi;

	public GeoscanTelemetry() {
		// do nothing
	}

	public GeoscanTelemetry(LittleEndianDataInputStream dis) throws IOException {
		obcTimestamp = dis.readUnsignedInt();
		epsCellCurrent = 0.0000766f * dis.readUnsignedShort();
		epsSystemCurrent = 0.00003076f * dis.readUnsignedShort();
		epsCellVoltageHalf = 0.00006928f * dis.readUnsignedShort();
		epsCellVoltageFull = 0.00013856f * dis.readUnsignedShort();
		adcTemperaturePosX = dis.readByte();
		adcTemperatureNegX = dis.readByte();
		adcTemperaturePosY = dis.readByte();
		adcTemperatureNegY = dis.readByte();
		adcTemperaturePosZ = dis.readByte();
		adcTemperatureNegZ = dis.readByte();
		adcTemperatureCell1 = dis.readByte();
		adcTemperatureCell2 = dis.readByte();
		obcCpuLoad = 0.390625f * dis.readUnsignedByte();
		obcBootCount = dis.readUnsignedShort() - 7476;
		commBootCount = dis.readUnsignedShort() - 1505;
		commRssi = (byte) (-99 + dis.readByte());
	}

	public long getObcTimestamp() {
		return obcTimestamp;
	}

	public void setObcTimestamp(long obcTimestamp) {
		this.obcTimestamp = obcTimestamp;
	}

	public float getEpsCellCurrent() {
		return epsCellCurrent;
	}

	public void setEpsCellCurrent(float epsCellCurrent) {
		this.epsCellCurrent = epsCellCurrent;
	}

	public float getEpsSystemCurrent() {
		return epsSystemCurrent;
	}

	public void setEpsSystemCurrent(float epsSystemCurrent) {
		this.epsSystemCurrent = epsSystemCurrent;
	}

	public float getEpsCellVoltageHalf() {
		return epsCellVoltageHalf;
	}

	public void setEpsCellVoltageHalf(float epsCellVoltageHalf) {
		this.epsCellVoltageHalf = epsCellVoltageHalf;
	}

	public float getEpsCellVoltageFull() {
		return epsCellVoltageFull;
	}

	public void setEpsCellVoltageFull(float epsCellVoltageFull) {
		this.epsCellVoltageFull = epsCellVoltageFull;
	}

	public byte getAdcTemperaturePosX() {
		return adcTemperaturePosX;
	}

	public void setAdcTemperaturePosX(byte adcTemperaturePosX) {
		this.adcTemperaturePosX = adcTemperaturePosX;
	}

	public byte getAdcTemperatureNegX() {
		return adcTemperatureNegX;
	}

	public void setAdcTemperatureNegX(byte adcTemperatureNegX) {
		this.adcTemperatureNegX = adcTemperatureNegX;
	}

	public byte getAdcTemperaturePosY() {
		return adcTemperaturePosY;
	}

	public void setAdcTemperaturePosY(byte adcTemperaturePosY) {
		this.adcTemperaturePosY = adcTemperaturePosY;
	}

	public byte getAdcTemperatureNegY() {
		return adcTemperatureNegY;
	}

	public void setAdcTemperatureNegY(byte adcTemperatureNegY) {
		this.adcTemperatureNegY = adcTemperatureNegY;
	}

	public byte getAdcTemperaturePosZ() {
		return adcTemperaturePosZ;
	}

	public void setAdcTemperaturePosZ(byte adcTemperaturePosZ) {
		this.adcTemperaturePosZ = adcTemperaturePosZ;
	}

	public byte getAdcTemperatureNegZ() {
		return adcTemperatureNegZ;
	}

	public void setAdcTemperatureNegZ(byte adcTemperatureNegZ) {
		this.adcTemperatureNegZ = adcTemperatureNegZ;
	}

	public byte getAdcTemperatureCell1() {
		return adcTemperatureCell1;
	}

	public void setAdcTemperatureCell1(byte adcTemperatureCell1) {
		this.adcTemperatureCell1 = adcTemperatureCell1;
	}

	public byte getAdcTemperatureCell2() {
		return adcTemperatureCell2;
	}

	public void setAdcTemperatureCell2(byte adcTemperatureCell2) {
		this.adcTemperatureCell2 = adcTemperatureCell2;
	}

	public float getObcCpuLoad() {
		return obcCpuLoad;
	}

	public void setObcCpuLoad(float obcCpuLoad) {
		this.obcCpuLoad = obcCpuLoad;
	}

	public int getObcBootCount() {
		return obcBootCount;
	}

	public void setObcBootCount(int obcBootCount) {
		this.obcBootCount = obcBootCount;
	}

	public int getCommBootCount() {
		return commBootCount;
	}

	public void setCommBootCount(int commBootCount) {
		this.commBootCount = commBootCount;
	}

	public byte getCommRssi() {
		return commRssi;
	}

	public void setCommRssi(byte commRssi) {
		this.commRssi = commRssi;
	}

}
