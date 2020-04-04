package ru.r2cloud.jradio.delfic3;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class DeployStatus {

	private boolean spYp;
	private boolean spYm;
	private boolean mabXm;
	private boolean mabYm;
	private boolean mabXp;
	private boolean mabYp;

	public DeployStatus() {
		// do nothing
	}

	public DeployStatus(LsbBitInputStream bis) throws IOException {
		spYp = bis.readBit();
		spYm = bis.readBit();
		mabXm = bis.readBit();
		mabYm = bis.readBit();
		mabXp = bis.readBit();
		mabYp = bis.readBit();
	}

	public boolean isSpYp() {
		return spYp;
	}

	public void setSpYp(boolean spYp) {
		this.spYp = spYp;
	}

	public boolean isSpYm() {
		return spYm;
	}

	public void setSpYm(boolean spYm) {
		this.spYm = spYm;
	}

	public boolean isMabXm() {
		return mabXm;
	}

	public void setMabXm(boolean mabXm) {
		this.mabXm = mabXm;
	}

	public boolean isMabYm() {
		return mabYm;
	}

	public void setMabYm(boolean mabYm) {
		this.mabYm = mabYm;
	}

	public boolean isMabXp() {
		return mabXp;
	}

	public void setMabXp(boolean mabXp) {
		this.mabXp = mabXp;
	}

	public boolean isMabYp() {
		return mabYp;
	}

	public void setMabYp(boolean mabYp) {
		this.mabYp = mabYp;
	}

}
