package ru.r2cloud.jradio.chomptt;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PayloadTelemetry {

	private boolean unclearedError;
	private boolean unclearedChannel1Error;
	private boolean unclearedChannel2Error;
	private boolean channel1On;
	private boolean channel1Overcurrent;
	private boolean channel1Good;
	private boolean channel2On;
	private boolean channel2Overcurrent;
	private boolean channel2Good;
	private boolean laserOn;
	private boolean laserOvercurrent;
	private boolean laserGood;
	private boolean laser1Enabled;
	private boolean laser2Enabled;
	private boolean laser3Enabled;
	private boolean laser4Enabled;

	private float supTemperature1;
	private float supTemperature2;
	private float vbatVoltage;

	private ChannelStatus channel1;
	private ChannelStatus channel2;

	private int counterTime;
	private long overflowTime;

	public PayloadTelemetry() {
		// do nothing
	}

	public PayloadTelemetry(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		unclearedError = (raw & 0x1) > 0;
		unclearedChannel1Error = ((raw >> 1) & 0x1) > 0;
		unclearedChannel2Error = ((raw >> 2) & 0x1) > 0;
		channel1On = ((raw >> 3) & 0x1) > 0;
		channel1Overcurrent = ((raw >> 4) & 0x1) > 0;
		channel1Good = ((raw >> 5) & 0x1) > 0;
		channel2On = ((raw >> 6) & 0x1) > 0;
		channel2Overcurrent = ((raw >> 7) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		channel2Good = (raw & 0x1) > 0;
		laserOn = ((raw >> 1) & 0x1) > 0;
		laserOvercurrent = ((raw >> 2) & 0x1) > 0;
		laserGood = ((raw >> 3) & 0x1) > 0;
		laser1Enabled = ((raw >> 4) & 0x1) > 0;
		laser2Enabled = ((raw >> 5) & 0x1) > 0;
		laser3Enabled = ((raw >> 6) & 0x1) > 0;
		laser4Enabled = ((raw >> 7) & 0x1) > 0;

		supTemperature1 = convertTemperature(dis.readUnsignedShort());
		supTemperature2 = convertTemperature(dis.readUnsignedShort());
		vbatVoltage = convertVolt(dis.readUnsignedShort()) * 3.5f;

		channel1 = new ChannelStatus(dis);
		channel2 = new ChannelStatus(dis);

		counterTime = dis.readUnsignedShort();
		overflowTime = dis.readUnsignedInt();
	}

	public boolean isUnclearedError() {
		return unclearedError;
	}

	public void setUnclearedError(boolean unclearedError) {
		this.unclearedError = unclearedError;
	}

	public boolean isUnclearedChannel1Error() {
		return unclearedChannel1Error;
	}

	public void setUnclearedChannel1Error(boolean unclearedChannel1Error) {
		this.unclearedChannel1Error = unclearedChannel1Error;
	}

	public boolean isUnclearedChannel2Error() {
		return unclearedChannel2Error;
	}

	public void setUnclearedChannel2Error(boolean unclearedChannel2Error) {
		this.unclearedChannel2Error = unclearedChannel2Error;
	}

	public boolean isChannel1On() {
		return channel1On;
	}

	public void setChannel1On(boolean channel1On) {
		this.channel1On = channel1On;
	}

	public boolean isChannel1Overcurrent() {
		return channel1Overcurrent;
	}

	public void setChannel1Overcurrent(boolean channel1Overcurrent) {
		this.channel1Overcurrent = channel1Overcurrent;
	}

	public boolean isChannel1Good() {
		return channel1Good;
	}

	public void setChannel1Good(boolean channel1Good) {
		this.channel1Good = channel1Good;
	}

	public boolean isChannel2On() {
		return channel2On;
	}

	public void setChannel2On(boolean channel2On) {
		this.channel2On = channel2On;
	}

	public boolean isChannel2Overcurrent() {
		return channel2Overcurrent;
	}

	public void setChannel2Overcurrent(boolean channel2Overcurrent) {
		this.channel2Overcurrent = channel2Overcurrent;
	}

	public boolean isChannel2Good() {
		return channel2Good;
	}

	public void setChannel2Good(boolean channel2Good) {
		this.channel2Good = channel2Good;
	}

	public boolean isLaserOn() {
		return laserOn;
	}

	public void setLaserOn(boolean laserOn) {
		this.laserOn = laserOn;
	}

	public boolean isLaserOvercurrent() {
		return laserOvercurrent;
	}

	public void setLaserOvercurrent(boolean laserOvercurrent) {
		this.laserOvercurrent = laserOvercurrent;
	}

	public boolean isLaserGood() {
		return laserGood;
	}

	public void setLaserGood(boolean laserGood) {
		this.laserGood = laserGood;
	}

	public boolean isLaser1Enabled() {
		return laser1Enabled;
	}

	public void setLaser1Enabled(boolean laser1Enabled) {
		this.laser1Enabled = laser1Enabled;
	}

	public boolean isLaser2Enabled() {
		return laser2Enabled;
	}

	public void setLaser2Enabled(boolean laser2Enabled) {
		this.laser2Enabled = laser2Enabled;
	}

	public boolean isLaser3Enabled() {
		return laser3Enabled;
	}

	public void setLaser3Enabled(boolean laser3Enabled) {
		this.laser3Enabled = laser3Enabled;
	}

	public boolean isLaser4Enabled() {
		return laser4Enabled;
	}

	public void setLaser4Enabled(boolean laser4Enabled) {
		this.laser4Enabled = laser4Enabled;
	}

	public float getSupTemperature1() {
		return supTemperature1;
	}

	public void setSupTemperature1(float supTemperature1) {
		this.supTemperature1 = supTemperature1;
	}

	public float getSupTemperature2() {
		return supTemperature2;
	}

	public void setSupTemperature2(float supTemperature2) {
		this.supTemperature2 = supTemperature2;
	}

	public float getVbatVoltage() {
		return vbatVoltage;
	}

	public void setVbatVoltage(float vbatVoltage) {
		this.vbatVoltage = vbatVoltage;
	}

	public ChannelStatus getChannel1() {
		return channel1;
	}

	public void setChannel1(ChannelStatus channel1) {
		this.channel1 = channel1;
	}

	public ChannelStatus getChannel2() {
		return channel2;
	}

	public void setChannel2(ChannelStatus channel2) {
		this.channel2 = channel2;
	}

	public int getCounterTime() {
		return counterTime;
	}

	public void setCounterTime(int counterTime) {
		this.counterTime = counterTime;
	}

	public long getOverflowTime() {
		return overflowTime;
	}

	public void setOverflowTime(long overflowTime) {
		this.overflowTime = overflowTime;
	}

	private static float convertVolt(int value) {
		value = value & 0x7FFF;
		if ((value & 0x4000) > 0) {
			return -((~value & 0x3FFF) + 1) * 305.18f / 1000000;
		} else {
			return (value & 0x3FFF) * 305.18f / 1000000;
		}
	}

	static float convertTemperature(int value) {
		value = value & 0x0FFF;
		if ((value & 0x0800) > 0) {
			return -((~value & 0x0FFF) + 1) * 0.0625f;
		} else {
			return value * 0.0625f;
		}
	}

}
