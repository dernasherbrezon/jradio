package ru.r2cloud.jradio.ls2;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;
import ru.r2cloud.jradio.util.StreamUtils;

public class SysmgrData {

	private int type;
	private float daughterATemperature;
	private float daughterBTemperature;
	private float threeVPlTemperature;
	private float rfAmpTemperature;

	private float xMinusTemperature;
	private float xPlusTemperature;
	private float yMinusTemperature;
	private float yPlusTemperature;
	private float zMinusTemperature;
	private float zPlusTemperature;

	private float atmelCurrent;
	private float atmelVoltage;
	private float threeVCurrent;
	private float threeVVoltage;
	private float threeVPayloadCurrent;
	private float threeVPayloadVoltage;
	private float fiveVPayloadCurrent;
	private float fiveVPayloadVoltage;
	private float daughterACurrent;
	private float daughterAVoltage;
	private float daughterBCurrent;
	private float daughterBVoltage;
	private float xMinusInternalCurrent;
	private float xMinusInternalVoltage;
	private float xMinusExternalCurrent;
	private float xMinusExternalVoltage;
	private float xPlusInternalCurrent;
	private float xPlusInternalVoltage;
	private float xPlusExternalCurrent;
	private float xPlusExternalVoltage;
	private float yMinusInternalCurrent;
	private float yMinusInternalVoltage;
	private float yMinusExternalCurrent;
	private float yMinusExternalVoltage;
	private float yPlusInternalCurrent;
	private float yPlusInternalVoltage;
	private float yPlusExternalCurrent;
	private float yPlusExternalVoltage;
	private float zMinusExternalCurrent;
	private float zMinusExternalVoltage;

	private long userCpuTime;
	private long sysCpuTime;
	private long idleCpuTime;
	private long processes;
	private long memFree;
	private long buffers;
	private long cached;
	private long dataFree;
	private long nandErasures;

	private int beaconCnt;
	private long time;
	private long boottime;
	private int longDurationCounter;

	public SysmgrData() {
		// do nothing
	}

	public SysmgrData(LittleEndianDataInputStream dis) throws IOException {
		type = dis.readUnsignedByte();
		daughterATemperature = dis.readUnsignedByte() * 0.5f - 75;
		daughterBTemperature = dis.readUnsignedByte() * 0.5f - 75;
		threeVPlTemperature = dis.readUnsignedByte() * 0.5f - 75;
		rfAmpTemperature = dis.readUnsignedByte() * 0.5f - 75;

		xMinusTemperature = dis.readUnsignedByte() * 0.5f - 75;
		xPlusTemperature = dis.readUnsignedByte() * 0.5f - 75;
		yMinusTemperature = dis.readUnsignedByte() * 0.5f - 75;
		yPlusTemperature = dis.readUnsignedByte() * 0.5f - 75;
		zMinusTemperature = dis.readUnsignedByte() * 0.5f - 75;
		zPlusTemperature = dis.readUnsignedByte() * 0.5f - 75;

		atmelCurrent = dis.readUnsignedByte() / 2048f;
		atmelVoltage = dis.readUnsignedByte() / 32f;
		threeVCurrent = dis.readUnsignedByte() / 2048f;
		threeVVoltage = dis.readUnsignedByte() / 32f;
		threeVPayloadCurrent = dis.readUnsignedByte() / 128f;
		threeVPayloadVoltage = dis.readUnsignedByte() / 32f;
		fiveVPayloadCurrent = dis.readUnsignedByte() / 128f;
		fiveVPayloadVoltage = dis.readUnsignedByte() / 32f;
		daughterACurrent = dis.readUnsignedByte() / 128f;
		daughterAVoltage = dis.readUnsignedByte() / 32f;
		daughterBCurrent = dis.readUnsignedByte() / 128f;
		daughterBVoltage = dis.readUnsignedByte() / 32f;

		xMinusInternalCurrent = dis.readByte() / 64f;
		xMinusInternalVoltage = dis.readUnsignedByte() / 32f;
		xMinusExternalCurrent = dis.readByte() / 64f;
		xMinusExternalVoltage = dis.readUnsignedByte() / 32f;
		xPlusInternalCurrent = dis.readByte() / 64f;
		xPlusInternalVoltage = dis.readUnsignedByte() / 32f;
		xPlusExternalCurrent = dis.readByte() / 64f;
		xPlusExternalVoltage = dis.readUnsignedByte() / 32f;

		yMinusInternalCurrent = dis.readByte() / 64f;
		yMinusInternalVoltage = dis.readUnsignedByte() / 32f;
		yMinusExternalCurrent = dis.readByte() / 64f;
		yMinusExternalVoltage = dis.readUnsignedByte() / 32f;
		yPlusInternalCurrent = dis.readByte() / 64f;
		yPlusInternalVoltage = dis.readUnsignedByte() / 32f;
		yPlusExternalCurrent = dis.readByte() / 64f;
		yPlusExternalVoltage = dis.readUnsignedByte() / 32f;

		zMinusExternalCurrent = dis.readByte() / 64f;
		zMinusExternalVoltage = dis.readUnsignedByte() / 32f;

		userCpuTime = StreamUtils.readUnsignedInt(dis.getBigEndianDataInputStream());
		sysCpuTime = StreamUtils.readUnsignedInt(dis.getBigEndianDataInputStream());
		idleCpuTime = StreamUtils.readUnsignedInt(dis.getBigEndianDataInputStream());
		processes = StreamUtils.readUnsignedInt(dis.getBigEndianDataInputStream());
		memFree = StreamUtils.readUnsignedInt(dis.getBigEndianDataInputStream());
		buffers = StreamUtils.readUnsignedInt(dis.getBigEndianDataInputStream());
		cached = StreamUtils.readUnsignedInt(dis.getBigEndianDataInputStream());
		dataFree = StreamUtils.readUnsignedInt(dis.getBigEndianDataInputStream());
		nandErasures = StreamUtils.readUnsignedInt(dis.getBigEndianDataInputStream());

		beaconCnt = dis.getBigEndianDataInputStream().readUnsignedShort();
		time = StreamUtils.readUnsignedInt(dis.getBigEndianDataInputStream());
		boottime = StreamUtils.readUnsignedInt(dis.getBigEndianDataInputStream());
		longDurationCounter = dis.getBigEndianDataInputStream().readUnsignedShort();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public float getDaughterATemperature() {
		return daughterATemperature;
	}

	public void setDaughterATemperature(float daughterATemperature) {
		this.daughterATemperature = daughterATemperature;
	}

	public float getDaughterBTemperature() {
		return daughterBTemperature;
	}

	public void setDaughterBTemperature(float daughterBTemperature) {
		this.daughterBTemperature = daughterBTemperature;
	}

	public float getThreeVPlTemperature() {
		return threeVPlTemperature;
	}

	public void setThreeVPlTemperature(float threeVPlTemperature) {
		this.threeVPlTemperature = threeVPlTemperature;
	}

	public float getRfAmpTemperature() {
		return rfAmpTemperature;
	}

	public void setRfAmpTemperature(float rfAmpTemperature) {
		this.rfAmpTemperature = rfAmpTemperature;
	}

	public float getXMinusTemperature() {
		return xMinusTemperature;
	}

	public void setXMinusTemperature(float xMinusTemperature) {
		this.xMinusTemperature = xMinusTemperature;
	}

	public float getXPlusTemperature() {
		return xPlusTemperature;
	}

	public void setXPlusTemperature(float xPlusTemperature) {
		this.xPlusTemperature = xPlusTemperature;
	}

	public float getYMinusTemperature() {
		return yMinusTemperature;
	}

	public void setYMinusTemperature(float yMinusTemperature) {
		this.yMinusTemperature = yMinusTemperature;
	}

	public float getYPlusTemperature() {
		return yPlusTemperature;
	}

	public void setYPlusTemperature(float yPlusTemperature) {
		this.yPlusTemperature = yPlusTemperature;
	}

	public float getZMinusTemperature() {
		return zMinusTemperature;
	}

	public void setZMinusTemperature(float zMinusTemperature) {
		this.zMinusTemperature = zMinusTemperature;
	}

	public float getZPlusTemperature() {
		return zPlusTemperature;
	}

	public void setZPlusTemperature(float zPlusTemperature) {
		this.zPlusTemperature = zPlusTemperature;
	}

	public float getAtmelCurrent() {
		return atmelCurrent;
	}

	public void setAtmelCurrent(float atmelCurrent) {
		this.atmelCurrent = atmelCurrent;
	}

	public float getAtmelVoltage() {
		return atmelVoltage;
	}

	public void setAtmelVoltage(float atmelVoltage) {
		this.atmelVoltage = atmelVoltage;
	}

	public float getThreeVCurrent() {
		return threeVCurrent;
	}

	public void setThreeVCurrent(float threeVCurrent) {
		this.threeVCurrent = threeVCurrent;
	}

	public float getThreeVVoltage() {
		return threeVVoltage;
	}

	public void setThreeVVoltage(float threeVVoltage) {
		this.threeVVoltage = threeVVoltage;
	}

	public float getThreeVPayloadCurrent() {
		return threeVPayloadCurrent;
	}

	public void setThreeVPayloadCurrent(float threeVPayloadCurrent) {
		this.threeVPayloadCurrent = threeVPayloadCurrent;
	}

	public float getThreeVPayloadVoltage() {
		return threeVPayloadVoltage;
	}

	public void setThreeVPayloadVoltage(float threeVPayloadVoltage) {
		this.threeVPayloadVoltage = threeVPayloadVoltage;
	}

	public float getFiveVPayloadCurrent() {
		return fiveVPayloadCurrent;
	}

	public void setFiveVPayloadCurrent(float fiveVPayloadCurrent) {
		this.fiveVPayloadCurrent = fiveVPayloadCurrent;
	}

	public float getFiveVPayloadVoltage() {
		return fiveVPayloadVoltage;
	}

	public void setFiveVPayloadVoltage(float fiveVPayloadVoltage) {
		this.fiveVPayloadVoltage = fiveVPayloadVoltage;
	}

	public float getDaughterACurrent() {
		return daughterACurrent;
	}

	public void setDaughterACurrent(float daughterACurrent) {
		this.daughterACurrent = daughterACurrent;
	}

	public float getDaughterAVoltage() {
		return daughterAVoltage;
	}

	public void setDaughterAVoltage(float daughterAVoltage) {
		this.daughterAVoltage = daughterAVoltage;
	}

	public float getDaughterBCurrent() {
		return daughterBCurrent;
	}

	public void setDaughterBCurrent(float daughterBCurrent) {
		this.daughterBCurrent = daughterBCurrent;
	}

	public float getDaughterBVoltage() {
		return daughterBVoltage;
	}

	public void setDaughterBVoltage(float daughterBVoltage) {
		this.daughterBVoltage = daughterBVoltage;
	}

	public float getXMinusInternalCurrent() {
		return xMinusInternalCurrent;
	}

	public void setXMinusInternalCurrent(float xMinusInternalCurrent) {
		this.xMinusInternalCurrent = xMinusInternalCurrent;
	}

	public float getXMinusInternalVoltage() {
		return xMinusInternalVoltage;
	}

	public void setXMinusInternalVoltage(float xMinusInternalVoltage) {
		this.xMinusInternalVoltage = xMinusInternalVoltage;
	}

	public float getXMinusExternalCurrent() {
		return xMinusExternalCurrent;
	}

	public void setXMinusExternalCurrent(float xMinusExternalCurrent) {
		this.xMinusExternalCurrent = xMinusExternalCurrent;
	}

	public float getXMinusExternalVoltage() {
		return xMinusExternalVoltage;
	}

	public void setXMinusExternalVoltage(float xMinusExternalVoltage) {
		this.xMinusExternalVoltage = xMinusExternalVoltage;
	}

	public float getXPlusInternalCurrent() {
		return xPlusInternalCurrent;
	}

	public void setXPlusInternalCurrent(float xPlusInternalCurrent) {
		this.xPlusInternalCurrent = xPlusInternalCurrent;
	}

	public float getXPlusInternalVoltage() {
		return xPlusInternalVoltage;
	}

	public void setXPlusInternalVoltage(float xPlusInternalVoltage) {
		this.xPlusInternalVoltage = xPlusInternalVoltage;
	}

	public float getXPlusExternalCurrent() {
		return xPlusExternalCurrent;
	}

	public void setXPlusExternalCurrent(float xPlusExternalCurrent) {
		this.xPlusExternalCurrent = xPlusExternalCurrent;
	}

	public float getXPlusExternalVoltage() {
		return xPlusExternalVoltage;
	}

	public void setXPlusExternalVoltage(float xPlusExternalVoltage) {
		this.xPlusExternalVoltage = xPlusExternalVoltage;
	}

	public float getYMinusInternalCurrent() {
		return yMinusInternalCurrent;
	}

	public void setYMinusInternalCurrent(float yMinusInternalCurrent) {
		this.yMinusInternalCurrent = yMinusInternalCurrent;
	}

	public float getYMinusInternalVoltage() {
		return yMinusInternalVoltage;
	}

	public void setYMinusInternalVoltage(float yMinusInternalVoltage) {
		this.yMinusInternalVoltage = yMinusInternalVoltage;
	}

	public float getYMinusExternalCurrent() {
		return yMinusExternalCurrent;
	}

	public void setYMinusExternalCurrent(float yMinusExternalCurrent) {
		this.yMinusExternalCurrent = yMinusExternalCurrent;
	}

	public float getYMinusExternalVoltage() {
		return yMinusExternalVoltage;
	}

	public void setYMinusExternalVoltage(float yMinusExternalVoltage) {
		this.yMinusExternalVoltage = yMinusExternalVoltage;
	}

	public float getYPlusInternalCurrent() {
		return yPlusInternalCurrent;
	}

	public void setYPlusInternalCurrent(float yPlusInternalCurrent) {
		this.yPlusInternalCurrent = yPlusInternalCurrent;
	}

	public float getYPlusInternalVoltage() {
		return yPlusInternalVoltage;
	}

	public void setYPlusInternalVoltage(float yPlusInternalVoltage) {
		this.yPlusInternalVoltage = yPlusInternalVoltage;
	}

	public float getYPlusExternalCurrent() {
		return yPlusExternalCurrent;
	}

	public void setYPlusExternalCurrent(float yPlusExternalCurrent) {
		this.yPlusExternalCurrent = yPlusExternalCurrent;
	}

	public float getYPlusExternalVoltage() {
		return yPlusExternalVoltage;
	}

	public void setYPlusExternalVoltage(float yPlusExternalVoltage) {
		this.yPlusExternalVoltage = yPlusExternalVoltage;
	}

	public float getZMinusExternalCurrent() {
		return zMinusExternalCurrent;
	}

	public void setZMinusExternalCurrent(float zMinusExternalCurrent) {
		this.zMinusExternalCurrent = zMinusExternalCurrent;
	}

	public float getZMinusExternalVoltage() {
		return zMinusExternalVoltage;
	}

	public void setZMinusExternalVoltage(float zMinusExternalVoltage) {
		this.zMinusExternalVoltage = zMinusExternalVoltage;
	}

	public long getUserCpuTime() {
		return userCpuTime;
	}

	public void setUserCpuTime(long userCpuTime) {
		this.userCpuTime = userCpuTime;
	}

	public long getSysCpuTime() {
		return sysCpuTime;
	}

	public void setSysCpuTime(long sysCpuTime) {
		this.sysCpuTime = sysCpuTime;
	}

	public long getIdleCpuTime() {
		return idleCpuTime;
	}

	public void setIdleCpuTime(long idleCpuTime) {
		this.idleCpuTime = idleCpuTime;
	}

	public long getProcesses() {
		return processes;
	}

	public void setProcesses(long processes) {
		this.processes = processes;
	}

	public long getMemFree() {
		return memFree;
	}

	public void setMemFree(long memFree) {
		this.memFree = memFree;
	}

	public long getBuffers() {
		return buffers;
	}

	public void setBuffers(long buffers) {
		this.buffers = buffers;
	}

	public long getCached() {
		return cached;
	}

	public void setCached(long cached) {
		this.cached = cached;
	}

	public long getDataFree() {
		return dataFree;
	}

	public void setDataFree(long dataFree) {
		this.dataFree = dataFree;
	}

	public long getNandErasures() {
		return nandErasures;
	}

	public void setNandErasures(long nandErasures) {
		this.nandErasures = nandErasures;
	}

	public int getBeaconCnt() {
		return beaconCnt;
	}

	public void setBeaconCnt(int beaconCnt) {
		this.beaconCnt = beaconCnt;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getBoottime() {
		return boottime;
	}

	public void setBoottime(long boottime) {
		this.boottime = boottime;
	}

	public int getLongDurationCounter() {
		return longDurationCounter;
	}

	public void setLongDurationCounter(int longDurationCounter) {
		this.longDurationCounter = longDurationCounter;
	}

}
