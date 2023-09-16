package ru.r2cloud.jradio.sputnix;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SputnixTelemetry {

	private int type;
	private int size;

	private float voltageSb1;
	private float voltageSb2;
	private float voltageSb3;
	private int amperageSb1;
	private int amperageSb2;
	private int amperageSb3;
	private int amperageBattery;
	private int amperageChannel1;
	private int amperageChannel2;
	private int amperageChannel3;
	private int amperageChannel4;
	private short temperatureBattery1;
	private short temperatureBattery2;
	private short temperatureBattery3;
	private short temperatureBattery4;
	private int flag;
	private float voltageBattery;
	private long telemetryCounter;
	private long telemetryTime;
	private int bkResetCounter;
	private int bkFlag;
	private int temperatureVhfAmp;
	private int temperatureVhfReceiver;
	private byte rssiRx;
	private byte rssiIdle;
	private byte pf;
	private byte pb;
	private int vhfCounter;
	private int vhfFlag;
	private long vhfTime;
	private long uptime;
	private int amperageVhf;
	private float voltageVhf;

	public SputnixTelemetry() {
		// do nothing
	}

	public SputnixTelemetry(LittleEndianDataInputStream dis) throws IOException {
		type = dis.readUnsignedShort();
		size = dis.readShort();
		dis.skipBytes(4);
		voltageSb1 = dis.readUnsignedShort() / 1000.0f;
		voltageSb2 = dis.readUnsignedShort() / 1000.0f;
		voltageSb3 = dis.readUnsignedShort() / 1000.0f;
		amperageSb1 = dis.readUnsignedShort();
		amperageSb2 = dis.readUnsignedShort();
		amperageSb3 = dis.readUnsignedShort();
		amperageBattery = dis.readUnsignedShort();
		amperageChannel1 = dis.readUnsignedShort();
		amperageChannel2 = dis.readUnsignedShort();
		amperageChannel3 = dis.readUnsignedShort();
		amperageChannel4 = dis.readUnsignedShort();
		temperatureBattery1 = dis.readShort();
		temperatureBattery2 = dis.readShort();
		temperatureBattery3 = dis.readShort();
		temperatureBattery4 = dis.readShort();
		flag = dis.readInt();
		voltageBattery = dis.readUnsignedShort() / 1000.0f;
		telemetryCounter = dis.readUnsignedInt();
		telemetryTime = dis.readUnsignedInt();
		bkResetCounter = dis.readUnsignedByte();
		bkFlag = dis.readUnsignedByte();
		temperatureVhfAmp = dis.readUnsignedByte();
		temperatureVhfReceiver = dis.readUnsignedByte();
		rssiRx = dis.readByte();
		rssiIdle = dis.readByte();
		pf = dis.readByte();
		pb = dis.readByte();
		vhfCounter = dis.readUnsignedByte();
		vhfFlag = dis.readUnsignedByte();
		vhfTime = dis.readUnsignedInt();
		uptime = dis.readUnsignedInt();
		amperageVhf = dis.readUnsignedShort();
		voltageVhf = dis.readUnsignedShort() / 1000.0f;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public float getVoltageSb1() {
		return voltageSb1;
	}

	public void setVoltageSb1(float voltageSb1) {
		this.voltageSb1 = voltageSb1;
	}

	public float getVoltageSb2() {
		return voltageSb2;
	}

	public void setVoltageSb2(float voltageSb2) {
		this.voltageSb2 = voltageSb2;
	}

	public float getVoltageSb3() {
		return voltageSb3;
	}

	public void setVoltageSb3(float voltageSb3) {
		this.voltageSb3 = voltageSb3;
	}

	public int getAmperageSb1() {
		return amperageSb1;
	}

	public void setAmperageSb1(int amperageSb1) {
		this.amperageSb1 = amperageSb1;
	}

	public int getAmperageSb2() {
		return amperageSb2;
	}

	public void setAmperageSb2(int amperageSb2) {
		this.amperageSb2 = amperageSb2;
	}

	public int getAmperageSb3() {
		return amperageSb3;
	}

	public void setAmperageSb3(int amperageSb3) {
		this.amperageSb3 = amperageSb3;
	}

	public int getAmperageBattery() {
		return amperageBattery;
	}

	public void setAmperageBattery(int amperageBattery) {
		this.amperageBattery = amperageBattery;
	}

	public int getAmperageChannel1() {
		return amperageChannel1;
	}

	public void setAmperageChannel1(int amperageChannel1) {
		this.amperageChannel1 = amperageChannel1;
	}

	public int getAmperageChannel2() {
		return amperageChannel2;
	}

	public void setAmperageChannel2(int amperageChannel2) {
		this.amperageChannel2 = amperageChannel2;
	}

	public int getAmperageChannel3() {
		return amperageChannel3;
	}

	public void setAmperageChannel3(int amperageChannel3) {
		this.amperageChannel3 = amperageChannel3;
	}

	public int getAmperageChannel4() {
		return amperageChannel4;
	}

	public void setAmperageChannel4(int amperageChannel4) {
		this.amperageChannel4 = amperageChannel4;
	}

	public short getTemperatureBattery1() {
		return temperatureBattery1;
	}

	public void setTemperatureBattery1(short temperatureBattery1) {
		this.temperatureBattery1 = temperatureBattery1;
	}

	public short getTemperatureBattery2() {
		return temperatureBattery2;
	}

	public void setTemperatureBattery2(short temperatureBattery2) {
		this.temperatureBattery2 = temperatureBattery2;
	}

	public short getTemperatureBattery3() {
		return temperatureBattery3;
	}

	public void setTemperatureBattery3(short temperatureBattery3) {
		this.temperatureBattery3 = temperatureBattery3;
	}

	public short getTemperatureBattery4() {
		return temperatureBattery4;
	}

	public void setTemperatureBattery4(short temperatureBattery4) {
		this.temperatureBattery4 = temperatureBattery4;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public float getVoltageBattery() {
		return voltageBattery;
	}

	public void setVoltageBattery(float voltageBattery) {
		this.voltageBattery = voltageBattery;
	}

	public long getTelemetryCounter() {
		return telemetryCounter;
	}

	public void setTelemetryCounter(long telemetryCounter) {
		this.telemetryCounter = telemetryCounter;
	}

	public long getTelemetryTime() {
		return telemetryTime;
	}

	public void setTelemetryTime(long telemetryTime) {
		this.telemetryTime = telemetryTime;
	}

	public int getBkResetCounter() {
		return bkResetCounter;
	}

	public void setBkResetCounter(int bkResetCounter) {
		this.bkResetCounter = bkResetCounter;
	}

	public int getBkFlag() {
		return bkFlag;
	}

	public void setBkFlag(int bkFlag) {
		this.bkFlag = bkFlag;
	}

	public int getTemperatureVhfAmp() {
		return temperatureVhfAmp;
	}

	public void setTemperatureVhfAmp(int temperatureVhfAmp) {
		this.temperatureVhfAmp = temperatureVhfAmp;
	}

	public int getTemperatureVhfReceiver() {
		return temperatureVhfReceiver;
	}

	public void setTemperatureVhfReceiver(int temperatureVhfReceiver) {
		this.temperatureVhfReceiver = temperatureVhfReceiver;
	}

	public byte getRssiRx() {
		return rssiRx;
	}

	public void setRssiRx(byte rssiRx) {
		this.rssiRx = rssiRx;
	}

	public byte getRssiIdle() {
		return rssiIdle;
	}

	public void setRssiIdle(byte rssiIdle) {
		this.rssiIdle = rssiIdle;
	}

	public byte getPf() {
		return pf;
	}

	public void setPf(byte pf) {
		this.pf = pf;
	}

	public byte getPb() {
		return pb;
	}

	public void setPb(byte pb) {
		this.pb = pb;
	}

	public int getVhfCounter() {
		return vhfCounter;
	}

	public void setVhfCounter(int vhfCounter) {
		this.vhfCounter = vhfCounter;
	}

	public int getVhfFlag() {
		return vhfFlag;
	}

	public void setVhfFlag(int vhfFlag) {
		this.vhfFlag = vhfFlag;
	}

	public long getVhfTime() {
		return vhfTime;
	}

	public void setVhfTime(long vhfTime) {
		this.vhfTime = vhfTime;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public int getAmperageVhf() {
		return amperageVhf;
	}

	public void setAmperageVhf(int amperageVhf) {
		this.amperageVhf = amperageVhf;
	}

	public float getVoltageVhf() {
		return voltageVhf;
	}

	public void setVoltageVhf(float voltageVhf) {
		this.voltageVhf = voltageVhf;
	}

}
