package ru.r2cloud.jradio.lasarsat;

public class Nav {

	private long week;
	private long time;
	private long posX;
	private long posY;
	private long posZ;
	private long velX;
	private long velY;
	private long velZ;
	private long sats;
	private long dop;
	private long antCur;
	private long volt;
	private long maxSnr;

	public Nav() {
		// do nothing
	}

	public Nav(String[] parts) {
		int i = 1;
		week = Long.valueOf(parts[i++]);
		time = Long.valueOf(parts[i++]);
		posX = Long.valueOf(parts[i++]);
		posY = Long.valueOf(parts[i++]);
		posZ = Long.valueOf(parts[i++]);
		velX = Long.valueOf(parts[i++]);
		velY = Long.valueOf(parts[i++]);
		velZ = Long.valueOf(parts[i++]);
		sats = Long.valueOf(parts[i++]);
		dop = Long.valueOf(parts[i++]);
		antCur = Long.valueOf(parts[i++]);
		volt = Long.valueOf(parts[i++]);
		maxSnr = Long.valueOf(parts[i++]);
	}

	public long getWeek() {
		return week;
	}

	public void setWeek(long week) {
		this.week = week;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getPosX() {
		return posX;
	}

	public void setPosX(long posX) {
		this.posX = posX;
	}

	public long getPosY() {
		return posY;
	}

	public void setPosY(long posY) {
		this.posY = posY;
	}

	public long getPosZ() {
		return posZ;
	}

	public void setPosZ(long posZ) {
		this.posZ = posZ;
	}

	public long getVelX() {
		return velX;
	}

	public void setVelX(long velX) {
		this.velX = velX;
	}

	public long getVelY() {
		return velY;
	}

	public void setVelY(long velY) {
		this.velY = velY;
	}

	public long getVelZ() {
		return velZ;
	}

	public void setVelZ(long velZ) {
		this.velZ = velZ;
	}

	public long getSats() {
		return sats;
	}

	public void setSats(long sats) {
		this.sats = sats;
	}

	public long getDop() {
		return dop;
	}

	public void setDop(long dop) {
		this.dop = dop;
	}

	public long getAntCur() {
		return antCur;
	}

	public void setAntCur(long antCur) {
		this.antCur = antCur;
	}

	public long getVolt() {
		return volt;
	}

	public void setVolt(long volt) {
		this.volt = volt;
	}

	public long getMaxSnr() {
		return maxSnr;
	}

	public void setMaxSnr(long maxSnr) {
		this.maxSnr = maxSnr;
	}

}
