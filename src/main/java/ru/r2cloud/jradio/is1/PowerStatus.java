package ru.r2cloud.jradio.is1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PowerStatus {

	private EclipseState eclipseState;
	private boolean sd1;
	private boolean sd0;
	private boolean htr;
	private boolean sband;
	private boolean adcs;
	private boolean cip;
	private boolean daxss;

	public PowerStatus() {
		// do nothing
	}

	public PowerStatus(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		eclipseState = EclipseState.valueOfCode((raw >> 7) & 0x1);
		sd1 = ((raw >> 6) & 0x1) > 0;
		sd0 = ((raw >> 5) & 0x1) > 0;
		htr = ((raw >> 4) & 0x1) > 0;
		sband = ((raw >> 3) & 0x1) > 0;
		adcs = ((raw >> 2) & 0x1) > 0;
		cip = ((raw >> 1) & 0x1) > 0;
		daxss = (raw & 0x1) > 0;
	}

	public EclipseState getEclipseState() {
		return eclipseState;
	}

	public void setEclipseState(EclipseState eclipseState) {
		this.eclipseState = eclipseState;
	}

	public boolean isSd1() {
		return sd1;
	}

	public void setSd1(boolean sd1) {
		this.sd1 = sd1;
	}

	public boolean isSd0() {
		return sd0;
	}

	public void setSd0(boolean sd0) {
		this.sd0 = sd0;
	}

	public boolean isHtr() {
		return htr;
	}

	public void setHtr(boolean htr) {
		this.htr = htr;
	}

	public boolean isSband() {
		return sband;
	}

	public void setSband(boolean sband) {
		this.sband = sband;
	}

	public boolean isAdcs() {
		return adcs;
	}

	public void setAdcs(boolean adcs) {
		this.adcs = adcs;
	}

	public boolean isCip() {
		return cip;
	}

	public void setCip(boolean cip) {
		this.cip = cip;
	}

	public boolean isDaxss() {
		return daxss;
	}

	public void setDaxss(boolean daxss) {
		this.daxss = daxss;
	}

}
