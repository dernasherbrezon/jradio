package ru.r2cloud.jradio.crocube;

public class Psu {

	private long resetCount;
	private long uptime; // seconds
	private long totalUptime; // seconds
	private float batteryVoltage;
	private float tempSys;
	private float tempBat;
	private float curIn;
	private float curOut;
	private long channelStatuses;
	private SysState sysState;
	private long gndWdt; // hours

	public Psu() {
		// do nothing
	}

	public Psu(String[] parts) {
		resetCount = Long.valueOf(parts[1]);
		uptime = Long.valueOf(parts[2]);
		totalUptime = Long.valueOf(parts[3]);
		batteryVoltage = Long.valueOf(parts[4]) * 0.001f;
		tempSys = Long.valueOf(parts[5]) * 0.01f;
		tempBat = Long.valueOf(parts[6]) * 0.01f;
		curIn = Long.valueOf(parts[7]) * 0.001f;
		curOut = Long.valueOf(parts[8]) * 0.001f;
		channelStatuses = Long.valueOf(parts[9], 16);
		sysState = SysState.valueOfCode(Integer.valueOf(parts[10]));
		gndWdt = Long.valueOf(parts[11]);
	}

	public long getResetCount() {
		return resetCount;
	}

	public void setResetCount(long resetCount) {
		this.resetCount = resetCount;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public long getTotalUptime() {
		return totalUptime;
	}

	public void setTotalUptime(long totalUptime) {
		this.totalUptime = totalUptime;
	}

	public float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public float getTempSys() {
		return tempSys;
	}

	public void setTempSys(float tempSys) {
		this.tempSys = tempSys;
	}

	public float getTempBat() {
		return tempBat;
	}

	public void setTempBat(float tempBat) {
		this.tempBat = tempBat;
	}

	public float getCurIn() {
		return curIn;
	}

	public void setCurIn(float curIn) {
		this.curIn = curIn;
	}

	public float getCurOut() {
		return curOut;
	}

	public void setCurOut(float curOut) {
		this.curOut = curOut;
	}

	public long getChannelStatuses() {
		return channelStatuses;
	}

	public void setChannelStatuses(long channelStatuses) {
		this.channelStatuses = channelStatuses;
	}

	public SysState getSysState() {
		return sysState;
	}

	public void setSysState(SysState sysState) {
		this.sysState = sysState;
	}

	public long getGndWdt() {
		return gndWdt;
	}

	public void setGndWdt(long gndWdt) {
		this.gndWdt = gndWdt;
	}

}
