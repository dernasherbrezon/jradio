package ru.r2cloud.jradio.sstk1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry {

	private long obcTimestamp;
	private float epsCellCurrent;
	private float epsSystemCurrent;
	private float epsCellVoltageHalf;
	private float epsCellVoltageFull;
	private float epsIntegralCellCurrent;
	private float epsIntegralSystemCurrent;
	private byte adcTemperaturePosX;
	private byte adcTemperatureNegX;
	private byte adcTemperaturePosY;
	private byte adcTemperatureNegY;
	private byte adcTemperaturePosZ;
	private byte adcTemperatureNegZ;
	private byte adcTemperatureCell1;
	private byte adcTemperatureCell2;
	private int attitudeControl;
	private float obcCpuLoad;
	private int obcBootCount;
	private int commBootCount;
	private int commRssi;
	private int commReceivedPackets;
	private int commSentPackets;

	public Telemetry() {
		// do nothing
	}

	public Telemetry(LittleEndianDataInputStream dis) throws IOException {
		obcTimestamp = dis.readUnsignedInt();
		epsCellCurrent = dis.readUnsignedShort() * 0.0000766f;
		epsSystemCurrent = dis.readUnsignedShort() * 0.00003076f;
		epsCellVoltageHalf = dis.readUnsignedShort() * 0.00006875f;
		epsCellVoltageFull = dis.readUnsignedShort() * 0.0001375f;
		epsIntegralCellCurrent = dis.readUnsignedInt() * 0.00003076f;
		epsIntegralSystemCurrent = dis.readUnsignedInt() * 0.0000766f;
		adcTemperaturePosX = dis.readByte();
		adcTemperatureNegX = dis.readByte();
		adcTemperaturePosY = dis.readByte();
		adcTemperatureNegY = dis.readByte();
		adcTemperaturePosZ = dis.readByte();
		adcTemperatureNegZ = dis.readByte();
		adcTemperatureCell1 = dis.readByte();
		adcTemperatureCell2 = dis.readByte();
		attitudeControl = dis.readUnsignedByte();
		obcCpuLoad = dis.readUnsignedByte() * 0.390625f;
		obcBootCount = dis.readUnsignedShort() - 7476;
		commBootCount = dis.readUnsignedShort() - 1505;
		commRssi = -99 + dis.readByte();
		commReceivedPackets = dis.readUnsignedShort();
		commSentPackets = dis.readUnsignedShort();
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

	public float getEpsIntegralCellCurrent() {
		return epsIntegralCellCurrent;
	}

	public void setEpsIntegralCellCurrent(float epsIntegralCellCurrent) {
		this.epsIntegralCellCurrent = epsIntegralCellCurrent;
	}

	public float getEpsIntegralSystemCurrent() {
		return epsIntegralSystemCurrent;
	}

	public void setEpsIntegralSystemCurrent(float epsIntegralSystemCurrent) {
		this.epsIntegralSystemCurrent = epsIntegralSystemCurrent;
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

	public int getAttitudeControl() {
		return attitudeControl;
	}

	public void setAttitudeControl(int attitudeControl) {
		this.attitudeControl = attitudeControl;
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

	public int getCommRssi() {
		return commRssi;
	}

	public void setCommRssi(int commRssi) {
		this.commRssi = commRssi;
	}

	public int getCommReceivedPackets() {
		return commReceivedPackets;
	}

	public void setCommReceivedPackets(int commReceivedPackets) {
		this.commReceivedPackets = commReceivedPackets;
	}

	public int getCommSentPackets() {
		return commSentPackets;
	}

	public void setCommSentPackets(int commSentPackets) {
		this.commSentPackets = commSentPackets;
	}

}
