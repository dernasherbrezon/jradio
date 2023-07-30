package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry3 {

	private long timestamp;
	private LswCv[] lsw;
	private long pcu1LswStatus;
	private long pcu2LswStatus;
	private BooleanValue debStatus;
	private BooleanValue gyrStatus;
	private BooleanValue sz2Status;
	private BooleanValue sz1Status;
	private UshortCvtValue[] mpptInputCurrent;
	private UshortCvtValue[] mpptInputVoltage;
	private UshortCvtValue[] mpptOutputCurrent;
	private UshortCvtValue[] mpptOutputVoltage;
	private FloatValue[] solarTemperature;
	private LightSensor[] accuLightSensors;
	private UshortValue[] accuInfraSensor;
	private long lastFileId;

	public Telemetry3() {
		// do nothing
	}

	public Telemetry3(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		lsw = new LswCv[4];
		lsw[0] = new LswCv("UNIDEB", dis);
		lsw[1] = new LswCv("UNIGYR", dis);
		lsw[2] = new LswCv("UNISZ2", dis);
		lsw[3] = new LswCv("UNISZ1", dis);
		pcu1LswStatus = dis.readUnsignedInt();
		pcu2LswStatus = dis.readUnsignedInt();
		debStatus = new BooleanValue(dis);
		gyrStatus = new BooleanValue(dis);
		sz2Status = new BooleanValue(dis);
		sz1Status = new BooleanValue(dis);
		mpptInputCurrent = readUShortArray(dis, 4);
		mpptInputVoltage = readUShortArray(dis, 4);
		mpptOutputCurrent = readUShortArray(dis, 4);
		mpptOutputVoltage = readUShortArray(dis, 4);
		solarTemperature = new FloatValue[6];
		for (int i = 0; i < solarTemperature.length; i++) {
			ShortCvtValue cur = new ShortCvtValue(dis);
			solarTemperature[i] = new FloatValue();
			solarTemperature[i].setTimeAgo(cur.getTimeAgo());
			solarTemperature[i].setValue(cur.getValue() / 10.0f);
		}
		accuLightSensors = new LightSensor[6];
		for (int i = 0; i < accuLightSensors.length; i++) {
			accuLightSensors[i] = new LightSensor(dis);
		}
		accuInfraSensor = new UshortValue[6];
		for (int i = 0; i < accuInfraSensor.length; i++) {
			accuInfraSensor[i] = new UshortValue(dis);
		}
		lastFileId = dis.readUnsignedInt();
	}

	private static UshortCvtValue[] readUShortArray(LittleEndianDataInputStream dis, int length) throws IOException {
		UshortCvtValue[] result = new UshortCvtValue[length];
		for (int i = 0; i < result.length; i++) {
			result[i] = new UshortCvtValue(dis);
		}
		return result;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public LswCv[] getLsw() {
		return lsw;
	}

	public void setLsw(LswCv[] lsw) {
		this.lsw = lsw;
	}

	public long getPcu1LswStatus() {
		return pcu1LswStatus;
	}

	public void setPcu1LswStatus(long pcu1LswStatus) {
		this.pcu1LswStatus = pcu1LswStatus;
	}

	public long getPcu2LswStatus() {
		return pcu2LswStatus;
	}

	public void setPcu2LswStatus(long pcu2LswStatus) {
		this.pcu2LswStatus = pcu2LswStatus;
	}

	public BooleanValue getDebStatus() {
		return debStatus;
	}

	public void setDebStatus(BooleanValue debStatus) {
		this.debStatus = debStatus;
	}

	public BooleanValue getGyrStatus() {
		return gyrStatus;
	}

	public void setGyrStatus(BooleanValue gyrStatus) {
		this.gyrStatus = gyrStatus;
	}

	public BooleanValue getSz2Status() {
		return sz2Status;
	}

	public void setSz2Status(BooleanValue sz2Status) {
		this.sz2Status = sz2Status;
	}

	public BooleanValue getSz1Status() {
		return sz1Status;
	}

	public void setSz1Status(BooleanValue sz1Status) {
		this.sz1Status = sz1Status;
	}

	public UshortCvtValue[] getMpptInputCurrent() {
		return mpptInputCurrent;
	}

	public void setMpptInputCurrent(UshortCvtValue[] mpptInputCurrent) {
		this.mpptInputCurrent = mpptInputCurrent;
	}

	public UshortCvtValue[] getMpptInputVoltage() {
		return mpptInputVoltage;
	}

	public void setMpptInputVoltage(UshortCvtValue[] mpptInputVoltage) {
		this.mpptInputVoltage = mpptInputVoltage;
	}

	public UshortCvtValue[] getMpptOutputCurrent() {
		return mpptOutputCurrent;
	}

	public void setMpptOutputCurrent(UshortCvtValue[] mpptOutputCurrent) {
		this.mpptOutputCurrent = mpptOutputCurrent;
	}

	public UshortCvtValue[] getMpptOutputVoltage() {
		return mpptOutputVoltage;
	}

	public void setMpptOutputVoltage(UshortCvtValue[] mpptOutputVoltage) {
		this.mpptOutputVoltage = mpptOutputVoltage;
	}

	public FloatValue[] getSolarTemperature() {
		return solarTemperature;
	}

	public void setSolarTemperature(FloatValue[] solarTemperature) {
		this.solarTemperature = solarTemperature;
	}

	public LightSensor[] getAccuLightSensors() {
		return accuLightSensors;
	}

	public void setAccuLightSensors(LightSensor[] accuLightSensors) {
		this.accuLightSensors = accuLightSensors;
	}

	public UshortValue[] getAccuInfraSensor() {
		return accuInfraSensor;
	}

	public void setAccuInfraSensor(UshortValue[] accuInfraSensor) {
		this.accuInfraSensor = accuInfraSensor;
	}

	public long getLastFileId() {
		return lastFileId;
	}

	public void setLastFileId(long lastFileId) {
		this.lastFileId = lastFileId;
	}

}
