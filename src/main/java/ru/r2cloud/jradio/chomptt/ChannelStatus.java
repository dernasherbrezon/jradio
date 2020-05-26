package ru.r2cloud.jradio.chomptt;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ChannelStatus {

	private boolean channelNumber;
	private boolean unclearedError;
	private boolean tdcOn;
	private boolean tdcOvercurrent;
	private boolean tdcGood;
	private boolean hvOn;
	private boolean hvOvercurrent;
	private boolean hvGood;
	private boolean tecOn;
	private boolean tecOvercurrent;
	private boolean tecGood;
	private boolean tecLocked;
	private boolean clockBite;
	private boolean dllNotLk;

	private float temperature;
	private float csacTemperature;
	private float current;

	public ChannelStatus() {
		// do nothing
	}

	public ChannelStatus(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		channelNumber = (raw & 0b1) > 0;
		unclearedError = ((raw >> 1) & 0b1) > 0;
		tdcOn = ((raw >> 2) & 0b1) > 0;
		tdcOvercurrent = ((raw >> 3) & 0b1) > 0;
		tdcGood = ((raw >> 4) & 0b1) > 0;
		hvOn = ((raw >> 5) & 0b1) > 0;
		hvOvercurrent = ((raw >> 6) & 0b1) > 0;
		hvGood = ((raw >> 7) & 0b1) > 0;

		raw = dis.readUnsignedByte();
		tecOn = (raw & 0b1) > 0;
		tecOvercurrent = ((raw >> 1) & 0b1) > 0;
		tecGood = ((raw >> 2) & 0b1) > 0;
		tecLocked = ((raw >> 3) & 0b1) > 0;
		clockBite = ((raw >> 4) & 0b1) > 0;
		dllNotLk = ((raw >> 5) & 0b1) > 0;

		temperature = PayloadTelemetry.convertTemperature(dis.readUnsignedShort());
		csacTemperature = dis.readShort() * 0.01f;
		current = convertCurrent(dis.readUnsignedShort()) / 0.1f;
	}

	public boolean isChannelNumber() {
		return channelNumber;
	}
	
	public void setChannelNumber(boolean channelNumber) {
		this.channelNumber = channelNumber;
	}

	public boolean isUnclearedError() {
		return unclearedError;
	}

	public void setUnclearedError(boolean unclearedError) {
		this.unclearedError = unclearedError;
	}

	public boolean isTdcOn() {
		return tdcOn;
	}

	public void setTdcOn(boolean tdcOn) {
		this.tdcOn = tdcOn;
	}

	public boolean isTdcOvercurrent() {
		return tdcOvercurrent;
	}

	public void setTdcOvercurrent(boolean tdcOvercurrent) {
		this.tdcOvercurrent = tdcOvercurrent;
	}

	public boolean isTdcGood() {
		return tdcGood;
	}

	public void setTdcGood(boolean tdcGood) {
		this.tdcGood = tdcGood;
	}

	public boolean isHvOn() {
		return hvOn;
	}

	public void setHvOn(boolean hvOn) {
		this.hvOn = hvOn;
	}

	public boolean isHvOvercurrent() {
		return hvOvercurrent;
	}

	public void setHvOvercurrent(boolean hvOvercurrent) {
		this.hvOvercurrent = hvOvercurrent;
	}

	public boolean isHvGood() {
		return hvGood;
	}

	public void setHvGood(boolean hvGood) {
		this.hvGood = hvGood;
	}

	public boolean isTecOn() {
		return tecOn;
	}

	public void setTecOn(boolean tecOn) {
		this.tecOn = tecOn;
	}

	public boolean isTecOvercurrent() {
		return tecOvercurrent;
	}

	public void setTecOvercurrent(boolean tecOvercurrent) {
		this.tecOvercurrent = tecOvercurrent;
	}

	public boolean isTecGood() {
		return tecGood;
	}

	public void setTecGood(boolean tecGood) {
		this.tecGood = tecGood;
	}

	public boolean isTecLocked() {
		return tecLocked;
	}

	public void setTecLocked(boolean tecLocked) {
		this.tecLocked = tecLocked;
	}

	public boolean isClockBite() {
		return clockBite;
	}

	public void setClockBite(boolean clockBite) {
		this.clockBite = clockBite;
	}

	public boolean isDllNotLk() {
		return dllNotLk;
	}

	public void setDllNotLk(boolean dllNotLk) {
		this.dllNotLk = dllNotLk;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getCsacTemperature() {
		return csacTemperature;
	}

	public void setCsacTemperature(float csacTemperature) {
		this.csacTemperature = csacTemperature;
	}

	public float getCurrent() {
		return current;
	}

	public void setCurrent(float current) {
		this.current = current;
	}

	private static float convertCurrent(int value) {
		value = value & 0x7FFF;
		if ((value & 0x4000) > 0) {
			return -((~value & 0x3FFF) + 1) * 7.04f * 19.075f / 1000000;
		} else {
			return (value & 0x3FFF) * 7.04f * 19.075f / 1000000;
		}
	}

}
