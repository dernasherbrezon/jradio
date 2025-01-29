package ru.r2cloud.jradio.crocube;

public class Obc {

	private long bootCount;
	private long uptime;// seconds
	private long totalUptime;// seconds
	private float batteryLevel;
	private float tempMcu;
	private long freemem;

	public Obc() {
		// do nothing
	}

	public Obc(String[] parts) {
		bootCount = Long.valueOf(parts[1]);
		uptime = Long.valueOf(parts[2]);
		totalUptime = Long.valueOf(parts[3]);
		batteryLevel = Long.valueOf(parts[4]) * 0.001f;
		tempMcu = Long.valueOf(parts[5]) * 0.01f;
		freemem = Long.valueOf(parts[6]);
	}

	public long getBootCount() {
		return bootCount;
	}

	public void setBootCount(long bootCount) {
		this.bootCount = bootCount;
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

	public float getTempMcu() {
		return tempMcu;
	}

	public void setTempMcu(float tempMcu) {
		this.tempMcu = tempMcu;
	}

	public long getFreemem() {
		return freemem;
	}

	public void setFreemem(long freemem) {
		this.freemem = freemem;
	}

}
