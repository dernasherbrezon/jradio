package ru.r2cloud.jradio.siriussat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ShortBeacon {

	private int solarPanel1Voltage;
	private int solarPanel2Voltage;
	private int solarPanel3Voltage;
	private int solarPanel1Current;
	private int solarPanel2Current;
	private int solarPanel3Current;

	private short batterCurrent;
	private int channel1Current;
	private int channel2Current;
	private int channel3Current;
	private int channel4Current;

	private short batteryTemperature1;
	private short batteryTemperature2;
	private short batteryTemperature3;
	private short batteryTemperature4;

	private boolean criticalBatteryVoltage;
	private boolean minimalBatteryVoltage;
	private boolean heater2ManualControl;
	private boolean heater1ManualControl;
	private boolean heater2On;
	private boolean heater1On;
	private boolean batteryOvertemperature;
	private boolean batteryUndertemperature;
	private boolean channel4On;
	private boolean channel3On;
	private boolean channel2On;
	private boolean channel1On;
	private boolean channel4CurrentLimit;
	private boolean channel3CurrentLimit;
	private boolean channel2CurrentLimit;
	private boolean channel1CurrentLimit;
	private boolean chargingVoltage;

	private short batteryVoltage;
	private long pssTelemetryNumber;
	private int pssLastTelemetryTimestamp;
	private int pssResetCounter;
	private int psFlags;
	private byte uhfAmpTemperature;
	private byte uhfTemperature;
	private byte rxRssi;
	private byte idleRssi;
	private byte forwardWavePower;
	private byte reflectedWavePower;
	private int uhfResetCounter;
	private int uhfFlags;
	private int uhfTelemetryTimestamp;
	private long uptimeSeconds;
	private int uhfConsumptionCurrent;
	private short uhfVoltage;

	public ShortBeacon() {
		// do nothing
	}

	public ShortBeacon(LittleEndianDataInputStream dis) throws IOException {
		solarPanel1Voltage = dis.readUnsignedShort();
		solarPanel2Voltage = dis.readUnsignedShort();
		solarPanel3Voltage = dis.readUnsignedShort();
		solarPanel1Current = dis.readUnsignedShort();
		solarPanel2Current = dis.readUnsignedShort();
		solarPanel3Current = dis.readUnsignedShort();

		batterCurrent = dis.readShort();
		channel1Current = dis.readUnsignedShort();
		channel2Current = dis.readUnsignedShort();
		channel3Current = dis.readUnsignedShort();
		channel4Current = dis.readUnsignedShort();

		batteryTemperature1 = dis.readShort();
		batteryTemperature2 = dis.readShort();
		batteryTemperature3 = dis.readShort();
		batteryTemperature4 = dis.readShort();

		int raw = dis.readUnsignedByte();
		criticalBatteryVoltage = ((raw >> 7) & 0x1) > 0;
		minimalBatteryVoltage = ((raw >> 6) & 0x1) > 0;
		heater2ManualControl = ((raw >> 5) & 0x1) > 0;
		heater1ManualControl = ((raw >> 4) & 0x1) > 0;
		heater2On = ((raw >> 3) & 0x1) > 0;
		heater1On = ((raw >> 2) & 0x1) > 0;
		batteryOvertemperature = ((raw >> 1) & 0x1) > 0;
		batteryUndertemperature = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		channel4On = ((raw >> 7) & 0x1) > 0;
		channel3On = ((raw >> 6) & 0x1) > 0;
		channel2On = ((raw >> 5) & 0x1) > 0;
		channel1On = ((raw >> 4) & 0x1) > 0;
		channel4CurrentLimit = ((raw >> 3) & 0x1) > 0;
		channel3CurrentLimit = ((raw >> 2) & 0x1) > 0;
		channel2CurrentLimit = ((raw >> 1) & 0x1) > 0;
		channel1CurrentLimit = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		chargingVoltage = (raw & 0x1) > 0;
		dis.skipBytes(1);

		batteryVoltage = dis.readShort();
		pssTelemetryNumber = dis.readUnsignedInt();
		pssLastTelemetryTimestamp = dis.readInt();
		pssResetCounter = dis.readUnsignedByte();
		psFlags = dis.readUnsignedByte();
		uhfAmpTemperature = dis.readByte();
		uhfTemperature = dis.readByte();
		rxRssi = dis.readByte();
		idleRssi = dis.readByte();
		forwardWavePower = dis.readByte();
		reflectedWavePower = dis.readByte();
		uhfResetCounter = dis.readUnsignedByte();
		uhfFlags = dis.readUnsignedByte();
		uhfTelemetryTimestamp = dis.readInt();
		uptimeSeconds = dis.readUnsignedInt();
		uhfConsumptionCurrent = dis.readUnsignedShort();
		uhfVoltage = dis.readShort();
	}

	public int getSolarPanel1Voltage() {
		return solarPanel1Voltage;
	}

	public void setSolarPanel1Voltage(int solarPanel1Voltage) {
		this.solarPanel1Voltage = solarPanel1Voltage;
	}

	public int getSolarPanel2Voltage() {
		return solarPanel2Voltage;
	}

	public void setSolarPanel2Voltage(int solarPanel2Voltage) {
		this.solarPanel2Voltage = solarPanel2Voltage;
	}

	public int getSolarPanel3Voltage() {
		return solarPanel3Voltage;
	}

	public void setSolarPanel3Voltage(int solarPanel3Voltage) {
		this.solarPanel3Voltage = solarPanel3Voltage;
	}

	public int getSolarPanel1Current() {
		return solarPanel1Current;
	}

	public void setSolarPanel1Current(int solarPanel1Current) {
		this.solarPanel1Current = solarPanel1Current;
	}

	public int getSolarPanel2Current() {
		return solarPanel2Current;
	}

	public void setSolarPanel2Current(int solarPanel2Current) {
		this.solarPanel2Current = solarPanel2Current;
	}

	public int getSolarPanel3Current() {
		return solarPanel3Current;
	}

	public void setSolarPanel3Current(int solarPanel3Current) {
		this.solarPanel3Current = solarPanel3Current;
	}

	public short getBatterCurrent() {
		return batterCurrent;
	}

	public void setBatterCurrent(short batterCurrent) {
		this.batterCurrent = batterCurrent;
	}

	public int getChannel1Current() {
		return channel1Current;
	}

	public void setChannel1Current(int channel1Current) {
		this.channel1Current = channel1Current;
	}

	public int getChannel2Current() {
		return channel2Current;
	}

	public void setChannel2Current(int channel2Current) {
		this.channel2Current = channel2Current;
	}

	public int getChannel3Current() {
		return channel3Current;
	}

	public void setChannel3Current(int channel3Current) {
		this.channel3Current = channel3Current;
	}

	public int getChannel4Current() {
		return channel4Current;
	}

	public void setChannel4Current(int channel4Current) {
		this.channel4Current = channel4Current;
	}

	public short getBatteryTemperature1() {
		return batteryTemperature1;
	}

	public void setBatteryTemperature1(short batteryTemperature1) {
		this.batteryTemperature1 = batteryTemperature1;
	}

	public short getBatteryTemperature2() {
		return batteryTemperature2;
	}

	public void setBatteryTemperature2(short batteryTemperature2) {
		this.batteryTemperature2 = batteryTemperature2;
	}

	public short getBatteryTemperature3() {
		return batteryTemperature3;
	}

	public void setBatteryTemperature3(short batteryTemperature3) {
		this.batteryTemperature3 = batteryTemperature3;
	}

	public short getBatteryTemperature4() {
		return batteryTemperature4;
	}

	public void setBatteryTemperature4(short batteryTemperature4) {
		this.batteryTemperature4 = batteryTemperature4;
	}

	public boolean isCriticalBatteryVoltage() {
		return criticalBatteryVoltage;
	}

	public void setCriticalBatteryVoltage(boolean criticalBatteryVoltage) {
		this.criticalBatteryVoltage = criticalBatteryVoltage;
	}

	public boolean isMinimalBatteryVoltage() {
		return minimalBatteryVoltage;
	}

	public void setMinimalBatteryVoltage(boolean minimalBatteryVoltage) {
		this.minimalBatteryVoltage = minimalBatteryVoltage;
	}

	public boolean isHeater2ManualControl() {
		return heater2ManualControl;
	}

	public void setHeater2ManualControl(boolean heater2ManualControl) {
		this.heater2ManualControl = heater2ManualControl;
	}

	public boolean isHeater1ManualControl() {
		return heater1ManualControl;
	}

	public void setHeater1ManualControl(boolean heater1ManualControl) {
		this.heater1ManualControl = heater1ManualControl;
	}

	public boolean isHeater2On() {
		return heater2On;
	}

	public void setHeater2On(boolean heater2On) {
		this.heater2On = heater2On;
	}

	public boolean isHeater1On() {
		return heater1On;
	}

	public void setHeater1On(boolean heater1On) {
		this.heater1On = heater1On;
	}

	public boolean isBatteryOvertemperature() {
		return batteryOvertemperature;
	}

	public void setBatteryOvertemperature(boolean batteryOvertemperature) {
		this.batteryOvertemperature = batteryOvertemperature;
	}

	public boolean isBatteryUndertemperature() {
		return batteryUndertemperature;
	}

	public void setBatteryUndertemperature(boolean batteryUndertemperature) {
		this.batteryUndertemperature = batteryUndertemperature;
	}

	public boolean isChannel4On() {
		return channel4On;
	}

	public void setChannel4On(boolean channel4On) {
		this.channel4On = channel4On;
	}

	public boolean isChannel3On() {
		return channel3On;
	}

	public void setChannel3On(boolean channel3On) {
		this.channel3On = channel3On;
	}

	public boolean isChannel2On() {
		return channel2On;
	}

	public void setChannel2On(boolean channel2On) {
		this.channel2On = channel2On;
	}

	public boolean isChannel1On() {
		return channel1On;
	}

	public void setChannel1On(boolean channel1On) {
		this.channel1On = channel1On;
	}

	public boolean isChannel4CurrentLimit() {
		return channel4CurrentLimit;
	}

	public void setChannel4CurrentLimit(boolean channel4CurrentLimit) {
		this.channel4CurrentLimit = channel4CurrentLimit;
	}

	public boolean isChannel3CurrentLimit() {
		return channel3CurrentLimit;
	}

	public void setChannel3CurrentLimit(boolean channel3CurrentLimit) {
		this.channel3CurrentLimit = channel3CurrentLimit;
	}

	public boolean isChannel2CurrentLimit() {
		return channel2CurrentLimit;
	}

	public void setChannel2CurrentLimit(boolean channel2CurrentLimit) {
		this.channel2CurrentLimit = channel2CurrentLimit;
	}

	public boolean isChannel1CurrentLimit() {
		return channel1CurrentLimit;
	}

	public void setChannel1CurrentLimit(boolean channel1CurrentLimit) {
		this.channel1CurrentLimit = channel1CurrentLimit;
	}

	public boolean isChargingVoltage() {
		return chargingVoltage;
	}

	public void setChargingVoltage(boolean chargingVoltage) {
		this.chargingVoltage = chargingVoltage;
	}

	public short getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(short batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public long getPssTelemetryNumber() {
		return pssTelemetryNumber;
	}

	public void setPssTelemetryNumber(long pssTelemetryNumber) {
		this.pssTelemetryNumber = pssTelemetryNumber;
	}

	public int getPssLastTelemetryTimestamp() {
		return pssLastTelemetryTimestamp;
	}

	public void setPssLastTelemetryTimestamp(int pssLastTelemetryTimestamp) {
		this.pssLastTelemetryTimestamp = pssLastTelemetryTimestamp;
	}

	public int getPssResetCounter() {
		return pssResetCounter;
	}

	public void setPssResetCounter(int pssResetCounter) {
		this.pssResetCounter = pssResetCounter;
	}

	public int getPsFlags() {
		return psFlags;
	}

	public void setPsFlags(int psFlags) {
		this.psFlags = psFlags;
	}

	public byte getUhfAmpTemperature() {
		return uhfAmpTemperature;
	}

	public void setUhfAmpTemperature(byte uhfAmpTemperature) {
		this.uhfAmpTemperature = uhfAmpTemperature;
	}

	public byte getUhfTemperature() {
		return uhfTemperature;
	}

	public void setUhfTemperature(byte uhfTemperature) {
		this.uhfTemperature = uhfTemperature;
	}

	public byte getRxRssi() {
		return rxRssi;
	}

	public void setRxRssi(byte rxRssi) {
		this.rxRssi = rxRssi;
	}

	public byte getIdleRssi() {
		return idleRssi;
	}

	public void setIdleRssi(byte idleRssi) {
		this.idleRssi = idleRssi;
	}

	public byte getForwardWavePower() {
		return forwardWavePower;
	}

	public void setForwardWavePower(byte forwardWavePower) {
		this.forwardWavePower = forwardWavePower;
	}

	public byte getReflectedWavePower() {
		return reflectedWavePower;
	}

	public void setReflectedWavePower(byte reflectedWavePower) {
		this.reflectedWavePower = reflectedWavePower;
	}

	public int getUhfResetCounter() {
		return uhfResetCounter;
	}

	public void setUhfResetCounter(int uhfResetCounter) {
		this.uhfResetCounter = uhfResetCounter;
	}

	public int getUhfFlags() {
		return uhfFlags;
	}

	public void setUhfFlags(int uhfFlags) {
		this.uhfFlags = uhfFlags;
	}

	public int getUhfTelemetryTimestamp() {
		return uhfTelemetryTimestamp;
	}

	public void setUhfTelemetryTimestamp(int uhfTelemetryTimestamp) {
		this.uhfTelemetryTimestamp = uhfTelemetryTimestamp;
	}

	public long getUptimeSeconds() {
		return uptimeSeconds;
	}

	public void setUptimeSeconds(long uptimeSeconds) {
		this.uptimeSeconds = uptimeSeconds;
	}

	public int getUhfConsumptionCurrent() {
		return uhfConsumptionCurrent;
	}

	public void setUhfConsumptionCurrent(int uhfConsumptionCurrent) {
		this.uhfConsumptionCurrent = uhfConsumptionCurrent;
	}

	public short getUhfVoltage() {
		return uhfVoltage;
	}

	public void setUhfVoltage(short uhfVoltage) {
		this.uhfVoltage = uhfVoltage;
	}

}
