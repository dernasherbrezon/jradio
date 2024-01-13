package ru.r2cloud.jradio.bdsat;

public class Psu {

	private int resetCounter;
	private long uptime;
	private long totalUptime;
	private float batteryLevel;
	private float tempSys;
	private float tempBat;
	private float curIn;
	private float curOut;
	private int chStat;
	private SystemState sysState;
	private int gndWdt;

	public Psu() {
		// do nothing
	}

	public Psu(String[] parts) {
		int i = 1;
		resetCounter = Integer.parseInt(parts[i++]);
		uptime = Long.parseLong(parts[i++]);
		totalUptime = Long.parseLong(parts[i++]);
		batteryLevel = Integer.parseInt(parts[i++]) * 0.001f;
		tempSys = Integer.parseInt(parts[i++]) * 0.01f;
		tempBat = Integer.parseInt(parts[i++]) * 0.01f;
		curIn = Integer.parseInt(parts[i++]) * 0.001f;
		curOut = Integer.parseInt(parts[i++]) * 0.001f;
		chStat = Integer.parseInt(parts[i++], 16);
		sysState = SystemState.valueOfCode(parts[i++]);
		gndWdt = Integer.parseInt(parts[i++]);
	}

	public int getResetCounter() {
		return resetCounter;
	}

	public void setResetCounter(int resetCounter) {
		this.resetCounter = resetCounter;
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

	public float getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(float batteryLevel) {
		this.batteryLevel = batteryLevel;
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

	public int getChStat() {
		return chStat;
	}

	public void setChStat(int chStat) {
		this.chStat = chStat;
	}

	public SystemState getSysState() {
		return sysState;
	}

	public void setSysState(SystemState sysState) {
		this.sysState = sysState;
	}

	public int getGndWdt() {
		return gndWdt;
	}

	public void setGndWdt(int gndWdt) {
		this.gndWdt = gndWdt;
	}

}
