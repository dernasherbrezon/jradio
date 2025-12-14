package ru.r2cloud.jradio.hunity;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Obc1 {

	private long timestamp;
	private int telemetryVersion;
	private long uptime;
	private long lastFileId;
	private float interPacketGap;
	private float cycleEndSleep;

	private SampleTime rttcTemperatureTime;
	private float rttcTemperature;

	private SampleTime obcVoltageTime;
	private int obcVoltage;

	private SampleTime obcTemperatureTime;
	private float obcTemperature;

	private Pcu[] pcu;
	private Sdc[] sdc;
	private Com[] com;

	private SampleTime configTime;
	private long config;

	private SampleTime preferredComTime;
	private int preferredCom;

	public Obc1() {
		// do nothing
	}

	public Obc1(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		telemetryVersion = dis.readUnsignedByte();
		uptime = dis.readUnsignedInt();
		lastFileId = dis.readUnsignedInt();
		interPacketGap = dis.readUnsignedInt() / 1.0e6f;
		cycleEndSleep = dis.readUnsignedInt() / 1.0e6f;

		short i16 = dis.readShort();
		rttcTemperatureTime = SampleTime.valueOf3Bit(i16);
		rttcTemperature = (i16 >> 3) / 10.0f;

		int u16 = dis.readUnsignedShort();
		obcVoltageTime = SampleTime.valueOf3Bit(u16);
		obcVoltage = (u16 >> 3);

		i16 = dis.readShort();
		obcTemperatureTime = SampleTime.valueOf3Bit(i16);
		obcTemperature = (i16 >> 3) / 10.0f;

		pcu = new Pcu[2];
		for (int i = 0; i < pcu.length; i++) {
			pcu[i] = new Pcu(dis);
		}
		for (int i = 0; i < pcu.length; i++) {
			u16 = dis.readUnsignedShort();
			pcu[i].setUnRegBusVoltageTime(SampleTime.valueOf3Bit(u16));
			pcu[i].setUnRegBusVoltage(u16 >> 3);
		}
		for (int i = 0; i < pcu.length; i++) {
			u16 = dis.readUnsignedShort();
			pcu[i].setRegBusVoltageTime(SampleTime.valueOf3Bit(u16));
			pcu[i].setRegBusVoltage(u16 >> 3);
		}

		sdc = new Sdc[2];
		for (int i = 0; i < sdc.length; i++) {
			sdc[i] = new Sdc(dis);
		}

		com = new Com[2];
		for (int i = 0; i < com.length; i++) {
			com[i] = new Com(dis);
		}

		configTime = SampleTime.valueOfByte(dis.readUnsignedByte());
		config = dis.readUnsignedInt();

		preferredComTime = SampleTime.valueOfByte(dis.readUnsignedByte());
		preferredCom = dis.readByte();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getTelemetryVersion() {
		return telemetryVersion;
	}

	public void setTelemetryVersion(int telemetryVersion) {
		this.telemetryVersion = telemetryVersion;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public long getLastFileId() {
		return lastFileId;
	}

	public void setLastFileId(long lastFileId) {
		this.lastFileId = lastFileId;
	}

	public float getInterPacketGap() {
		return interPacketGap;
	}

	public void setInterPacketGap(float interPacketGap) {
		this.interPacketGap = interPacketGap;
	}

	public float getCycleEndSleep() {
		return cycleEndSleep;
	}

	public void setCycleEndSleep(float cycleEndSleep) {
		this.cycleEndSleep = cycleEndSleep;
	}

	public SampleTime getRttcTemperatureTime() {
		return rttcTemperatureTime;
	}

	public void setRttcTemperatureTime(SampleTime rttcTemperatureTime) {
		this.rttcTemperatureTime = rttcTemperatureTime;
	}

	public float getRttcTemperature() {
		return rttcTemperature;
	}

	public void setRttcTemperature(float rttcTemperature) {
		this.rttcTemperature = rttcTemperature;
	}

	public SampleTime getObcVoltageTime() {
		return obcVoltageTime;
	}

	public void setObcVoltageTime(SampleTime obcVoltageTime) {
		this.obcVoltageTime = obcVoltageTime;
	}

	public int getObcVoltage() {
		return obcVoltage;
	}

	public void setObcVoltage(int obcVoltage) {
		this.obcVoltage = obcVoltage;
	}

	public SampleTime getObcTemperatureTime() {
		return obcTemperatureTime;
	}

	public void setObcTemperatureTime(SampleTime obcTemperatureTime) {
		this.obcTemperatureTime = obcTemperatureTime;
	}

	public float getObcTemperature() {
		return obcTemperature;
	}

	public void setObcTemperature(float obcTemperature) {
		this.obcTemperature = obcTemperature;
	}

	public Pcu[] getPcu() {
		return pcu;
	}

	public void setPcu(Pcu[] pcu) {
		this.pcu = pcu;
	}

	public Sdc[] getSdc() {
		return sdc;
	}

	public void setSdc(Sdc[] sdc) {
		this.sdc = sdc;
	}

	public Com[] getCom() {
		return com;
	}

	public void setCom(Com[] com) {
		this.com = com;
	}

	public SampleTime getConfigTime() {
		return configTime;
	}

	public void setConfigTime(SampleTime configTime) {
		this.configTime = configTime;
	}

	public long getConfig() {
		return config;
	}

	public void setConfig(long config) {
		this.config = config;
	}

	public SampleTime getPreferredComTime() {
		return preferredComTime;
	}

	public void setPreferredComTime(SampleTime preferredComTime) {
		this.preferredComTime = preferredComTime;
	}

	public int getPreferredCom() {
		return preferredCom;
	}

	public void setPreferredCom(int preferredCom) {
		this.preferredCom = preferredCom;
	}

}
