package ru.r2cloud.jradio.lasarsat;

public class Dos {

	private long mode;
	private long gyrX;
	private long gyrY;
	private long gyrZ;
	private long magX;
	private long magY;
	private long magZ;
	private long plasma;
	private long phd;
	private long dozi;
	private long gyrT;
	private long magT;
	private long lppa;
	private long busCur;
	private long busVol;
	private long uptime;

	public Dos() {
		// do nothing
	}

	public Dos(String[] parts) {
		int i = 1;
		mode = Long.valueOf(parts[i++]);
		gyrX = Long.valueOf(parts[i++]);
		gyrY = Long.valueOf(parts[i++]);
		gyrZ = Long.valueOf(parts[i++]);
		magX = Long.valueOf(parts[i++]);
		magY = Long.valueOf(parts[i++]);
		magZ = Long.valueOf(parts[i++]);
		plasma = Long.valueOf(parts[i++]);
		phd = Long.valueOf(parts[i++]);
		dozi = Long.valueOf(parts[i++]);
		gyrT = Long.valueOf(parts[i++]);
		magT = Long.valueOf(parts[i++]);
		lppa = Long.valueOf(parts[i++]);
		busCur = Long.valueOf(parts[i++]);
		busVol = Long.valueOf(parts[i++]);
		uptime = Long.valueOf(parts[i++]);
	}

	public long getMode() {
		return mode;
	}

	public void setMode(long mode) {
		this.mode = mode;
	}

	public long getGyrX() {
		return gyrX;
	}

	public void setGyrX(long gyrX) {
		this.gyrX = gyrX;
	}

	public long getGyrY() {
		return gyrY;
	}

	public void setGyrY(long gyrY) {
		this.gyrY = gyrY;
	}

	public long getGyrZ() {
		return gyrZ;
	}

	public void setGyrZ(long gyrZ) {
		this.gyrZ = gyrZ;
	}

	public long getMagX() {
		return magX;
	}

	public void setMagX(long magX) {
		this.magX = magX;
	}

	public long getMagY() {
		return magY;
	}

	public void setMagY(long magY) {
		this.magY = magY;
	}

	public long getMagZ() {
		return magZ;
	}

	public void setMagZ(long magZ) {
		this.magZ = magZ;
	}

	public long getPlasma() {
		return plasma;
	}

	public void setPlasma(long plasma) {
		this.plasma = plasma;
	}

	public long getPhd() {
		return phd;
	}

	public void setPhd(long phd) {
		this.phd = phd;
	}

	public long getDozi() {
		return dozi;
	}

	public void setDozi(long dozi) {
		this.dozi = dozi;
	}

	public long getGyrT() {
		return gyrT;
	}

	public void setGyrT(long gyrT) {
		this.gyrT = gyrT;
	}

	public long getMagT() {
		return magT;
	}

	public void setMagT(long magT) {
		this.magT = magT;
	}

	public long getLppa() {
		return lppa;
	}

	public void setLppa(long lppa) {
		this.lppa = lppa;
	}

	public long getBusCur() {
		return busCur;
	}

	public void setBusCur(long busCur) {
		this.busCur = busCur;
	}

	public long getBusVol() {
		return busVol;
	}

	public void setBusVol(long busVol) {
		this.busVol = busVol;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

}
