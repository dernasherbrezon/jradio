package ru.r2cloud.jradio.is1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class AliveFlags {

	private boolean cltState;
	private boolean daxss;
	private boolean cip;
	private boolean adcs;
	private boolean sband;
	private boolean uhf;
	private boolean sd1;
	private boolean sd0;

	public AliveFlags() {
		// do nothing
	}

	public AliveFlags(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		cltState = ((raw >> 7) & 0x1) > 0;
		daxss = ((raw >> 6) & 0x1) > 0;
		cip = ((raw >> 5) & 0x1) > 0;
		adcs = ((raw >> 4) & 0x1) > 0;
		sband = ((raw >> 3) & 0x1) > 0;
		uhf = ((raw >> 2) & 0x1) > 0;
		sd1 = ((raw >> 1) & 0x1) > 0;
		sd0 = (raw & 0x1) > 0;
	}

	public boolean isCltState() {
		return cltState;
	}

	public void setCltState(boolean cltState) {
		this.cltState = cltState;
	}

	public boolean isDaxss() {
		return daxss;
	}

	public void setDaxss(boolean daxss) {
		this.daxss = daxss;
	}

	public boolean isCip() {
		return cip;
	}

	public void setCip(boolean cip) {
		this.cip = cip;
	}

	public boolean isAdcs() {
		return adcs;
	}

	public void setAdcs(boolean adcs) {
		this.adcs = adcs;
	}

	public boolean isSband() {
		return sband;
	}

	public void setSband(boolean sband) {
		this.sband = sband;
	}

	public boolean isUhf() {
		return uhf;
	}

	public void setUhf(boolean uhf) {
		this.uhf = uhf;
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

}
