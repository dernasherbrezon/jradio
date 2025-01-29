package ru.r2cloud.jradio.lasarsat;

public class Obc {

	private int resetCount;
	private long uptime;
	private long uptimeTotal;
	private float tempMcu;
	private int freeMemory;

	public Obc() {
		// do nothing
	}

	public Obc(String[] parts) {
		int i = 1;
		resetCount = Integer.valueOf(parts[i++]);
		uptime = Long.valueOf(parts[i++]);
		uptimeTotal = Long.valueOf(parts[i++]);
		tempMcu = Integer.valueOf(parts[i++]) * 0.01f;
		freeMemory = Integer.valueOf(parts[i++]);
	}

	public int getResetCount() {
		return resetCount;
	}

	public void setResetCount(int resetCount) {
		this.resetCount = resetCount;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public long getUptimeTotal() {
		return uptimeTotal;
	}

	public void setUptimeTotal(long uptimeTotal) {
		this.uptimeTotal = uptimeTotal;
	}

	public float getTempMcu() {
		return tempMcu;
	}

	public void setTempMcu(float tempMcu) {
		this.tempMcu = tempMcu;
	}

	public int getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(int freeMemory) {
		this.freeMemory = freeMemory;
	}

}
