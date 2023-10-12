package ru.r2cloud.jradio.sharjahsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SystemInfo {

	private boolean battery;
	private boolean eps;
	private boolean adcs;
	private boolean uvModem;
	private boolean sband;
	private boolean ixrd;
	private boolean cam2mp;
	private boolean cam5mp;
	private boolean ifBoard;
	private boolean rtc;
	private boolean beacon;
	private boolean antennas;
	private OpMode mode;

	private int restartCount;
	private int lastResetCause;
	private long systemUptime;
	private long systemTime;

	public SystemInfo() {
		// do nothing
	}

	public SystemInfo(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		battery = ((raw >> 7) & 0x1) > 0;
		eps = ((raw >> 6) & 0x1) > 0;
		adcs = ((raw >> 5) & 0x1) > 0;
		uvModem = ((raw >> 4) & 0x1) > 0;
		sband = ((raw >> 3) & 0x1) > 0;
		ixrd = ((raw >> 2) & 0x1) > 0;
		cam2mp = ((raw >> 1) & 0x1) > 0;
		cam5mp = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		ifBoard = ((raw >> 7) & 0x1) > 0;
		rtc = ((raw >> 6) & 0x1) > 0;
		beacon = ((raw >> 5) & 0x1) > 0;
		antennas = ((raw >> 4) & 0x1) > 0;
		mode = OpMode.valueOfCode(raw & 0b1111);

		restartCount = dis.readUnsignedShort();
		lastResetCause = dis.readUnsignedByte();
		systemUptime = dis.readUnsignedInt();
		systemTime = dis.readUnsignedInt();
	}

	public boolean isBattery() {
		return battery;
	}

	public void setBattery(boolean battery) {
		this.battery = battery;
	}

	public boolean isEps() {
		return eps;
	}

	public void setEps(boolean eps) {
		this.eps = eps;
	}

	public boolean isAdcs() {
		return adcs;
	}

	public void setAdcs(boolean adcs) {
		this.adcs = adcs;
	}

	public boolean isUvModem() {
		return uvModem;
	}

	public void setUvModem(boolean uvModem) {
		this.uvModem = uvModem;
	}

	public boolean isSband() {
		return sband;
	}

	public void setSband(boolean sband) {
		this.sband = sband;
	}

	public boolean isIxrd() {
		return ixrd;
	}

	public void setIxrd(boolean ixrd) {
		this.ixrd = ixrd;
	}

	public boolean isCam2mp() {
		return cam2mp;
	}

	public void setCam2mp(boolean cam2mp) {
		this.cam2mp = cam2mp;
	}

	public boolean isCam5mp() {
		return cam5mp;
	}

	public void setCam5mp(boolean cam5mp) {
		this.cam5mp = cam5mp;
	}

	public boolean isIfBoard() {
		return ifBoard;
	}

	public void setIfBoard(boolean ifBoard) {
		this.ifBoard = ifBoard;
	}

	public boolean isRtc() {
		return rtc;
	}

	public void setRtc(boolean rtc) {
		this.rtc = rtc;
	}

	public boolean isBeacon() {
		return beacon;
	}

	public void setBeacon(boolean beacon) {
		this.beacon = beacon;
	}

	public boolean isAntennas() {
		return antennas;
	}

	public void setAntennas(boolean antennas) {
		this.antennas = antennas;
	}

	public OpMode getMode() {
		return mode;
	}

	public void setMode(OpMode mode) {
		this.mode = mode;
	}

	public int getRestartCount() {
		return restartCount;
	}

	public void setRestartCount(int restartCount) {
		this.restartCount = restartCount;
	}

	public int getLastResetCause() {
		return lastResetCause;
	}

	public void setLastResetCause(int lastResetCause) {
		this.lastResetCause = lastResetCause;
	}

	public long getSystemUptime() {
		return systemUptime;
	}

	public void setSystemUptime(long systemUptime) {
		this.systemUptime = systemUptime;
	}

	public long getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(long systemTime) {
		this.systemTime = systemTime;
	}

}
