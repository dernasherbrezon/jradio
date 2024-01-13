package ru.r2cloud.jradio.bdsat;

public class Obc {

	private int resetCount;
	private long uptime;
	private long uptimeTotal;
	private float batteryLevel;
	private float tempMcu;
	private float tempBoard;
	private float tempZn;
	private float tempXp;
	private float tempYp;
	private float tempYn;
	private float tempXn;
	private int freeMemory;

	public Obc() {
		// do nothing
	}

	public Obc(String[] parts) {
		int i = 1;
		resetCount = Integer.valueOf(parts[i++]);
		uptime = Long.valueOf(parts[i++]);
		uptimeTotal = Long.valueOf(parts[i++]);
		batteryLevel = Integer.valueOf(parts[i++]) * 0.001f;
		tempMcu = Integer.valueOf(parts[i++]) * 0.01f;
		tempBoard = Integer.valueOf(parts[i++]) * 0.01f;
		tempZn = Integer.valueOf(parts[i++]) * 0.01f;
		tempXp = Integer.valueOf(parts[i++]) * 0.01f;
		tempYp = Integer.valueOf(parts[i++]) * 0.01f;
		tempYn = Integer.valueOf(parts[i++]) * 0.01f;
		tempXn = Integer.valueOf(parts[i++]) * 0.01f;
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

	public float getTempBoard() {
		return tempBoard;
	}

	public void setTempBoard(float tempBoard) {
		this.tempBoard = tempBoard;
	}

	public float getTempZn() {
		return tempZn;
	}

	public void setTempZn(float tempZn) {
		this.tempZn = tempZn;
	}

	public float getTempXp() {
		return tempXp;
	}

	public void setTempXp(float tempXp) {
		this.tempXp = tempXp;
	}

	public float getTempYp() {
		return tempYp;
	}

	public void setTempYp(float tempYp) {
		this.tempYp = tempYp;
	}

	public float getTempYn() {
		return tempYn;
	}

	public void setTempYn(float tempYn) {
		this.tempYn = tempYn;
	}

	public float getTempXn() {
		return tempXn;
	}

	public void setTempXn(float tempXn) {
		this.tempXn = tempXn;
	}

	public int getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(int freeMemory) {
		this.freeMemory = freeMemory;
	}

}
