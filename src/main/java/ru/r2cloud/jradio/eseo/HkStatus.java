package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class HkStatus {

	// HK request is active
	private boolean pmm;
	private boolean pmr;
	private boolean tmm;
	private boolean tmr;
	private boolean ssm;
	private boolean ssr;
	private boolean ese;
	private boolean mwr;
	private boolean mwm;
	private boolean mps;
	private boolean mmm;
	private boolean mmr;
	private boolean mtm;
	private boolean mtr;
	private boolean tri;
	private boolean lmp;
	private boolean pcam;
	private boolean ams;
	private boolean stx;
	private boolean gps;

	private boolean scam;
	private boolean automaticRotation;
	private boolean general;
	private boolean power;
	private boolean obd;
	private boolean aocs;
	private boolean fdirtmtc;
	private boolean payload;
	private boolean disabled;

	public HkStatus() {
		// do nothing
	}

	public HkStatus(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();

		pmm = ((raw >> 5) & 0x1) > 0;
		pmr = ((raw >> 4) & 0x1) > 0;
		tmm = ((raw >> 3) & 0x1) > 0;
		tmr = ((raw >> 2) & 0x1) > 0;
		ssm = ((raw >> 1) & 0x1) > 0;
		ssr = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		ese = ((raw >> 7) & 0x1) > 0;
		mwr = ((raw >> 6) & 0x1) > 0;
		mwm = ((raw >> 5) & 0x1) > 0;
		mps = ((raw >> 4) & 0x1) > 0;
		mmm = ((raw >> 3) & 0x1) > 0;
		mmr = ((raw >> 2) & 0x1) > 0;
		mtm = ((raw >> 1) & 0x1) > 0;
		mtr = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		tri = ((raw >> 7) & 0x1) > 0;
		lmp = ((raw >> 6) & 0x1) > 0;
		pcam = ((raw >> 5) & 0x1) > 0;
		ams = ((raw >> 4) & 0x1) > 0;
		stx = ((raw >> 3) & 0x1) > 0;
		gps = ((raw >> 2) & 0x1) > 0;

		scam = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		automaticRotation = ((raw >> 7) & 0x1) > 0;
		general = ((raw >> 6) & 0x1) > 0;
		power = ((raw >> 5) & 0x1) > 0;
		obd = ((raw >> 4) & 0x1) > 0;
		aocs = ((raw >> 3) & 0x1) > 0;
		fdirtmtc = ((raw >> 2) & 0x1) > 0;
		payload = ((raw >> 1) & 0x1) > 0;
		disabled = (raw & 0x1) > 0;
	}

	public boolean isPmm() {
		return pmm;
	}

	public void setPmm(boolean pmm) {
		this.pmm = pmm;
	}

	public boolean isPmr() {
		return pmr;
	}

	public void setPmr(boolean pmr) {
		this.pmr = pmr;
	}

	public boolean isTmm() {
		return tmm;
	}

	public void setTmm(boolean tmm) {
		this.tmm = tmm;
	}

	public boolean isTmr() {
		return tmr;
	}

	public void setTmr(boolean tmr) {
		this.tmr = tmr;
	}

	public boolean isSsm() {
		return ssm;
	}

	public void setSsm(boolean ssm) {
		this.ssm = ssm;
	}

	public boolean isSsr() {
		return ssr;
	}

	public void setSsr(boolean ssr) {
		this.ssr = ssr;
	}

	public boolean isEse() {
		return ese;
	}

	public void setEse(boolean ese) {
		this.ese = ese;
	}

	public boolean isMwr() {
		return mwr;
	}

	public void setMwr(boolean mwr) {
		this.mwr = mwr;
	}

	public boolean isMwm() {
		return mwm;
	}

	public void setMwm(boolean mwm) {
		this.mwm = mwm;
	}

	public boolean isMps() {
		return mps;
	}

	public void setMps(boolean mps) {
		this.mps = mps;
	}

	public boolean isMmm() {
		return mmm;
	}

	public void setMmm(boolean mmm) {
		this.mmm = mmm;
	}

	public boolean isMmr() {
		return mmr;
	}

	public void setMmr(boolean mmr) {
		this.mmr = mmr;
	}

	public boolean isMtm() {
		return mtm;
	}

	public void setMtm(boolean mtm) {
		this.mtm = mtm;
	}

	public boolean isMtr() {
		return mtr;
	}

	public void setMtr(boolean mtr) {
		this.mtr = mtr;
	}

	public boolean isTri() {
		return tri;
	}

	public void setTri(boolean tri) {
		this.tri = tri;
	}

	public boolean isLmp() {
		return lmp;
	}

	public void setLmp(boolean lmp) {
		this.lmp = lmp;
	}

	public boolean isPcam() {
		return pcam;
	}

	public void setPcam(boolean pcam) {
		this.pcam = pcam;
	}

	public boolean isAms() {
		return ams;
	}

	public void setAms(boolean ams) {
		this.ams = ams;
	}

	public boolean isStx() {
		return stx;
	}

	public void setStx(boolean stx) {
		this.stx = stx;
	}

	public boolean isGps() {
		return gps;
	}

	public void setGps(boolean gps) {
		this.gps = gps;
	}

	public boolean isScam() {
		return scam;
	}

	public void setScam(boolean scam) {
		this.scam = scam;
	}

	public boolean isAutomaticRotation() {
		return automaticRotation;
	}

	public void setAutomaticRotation(boolean automaticRotation) {
		this.automaticRotation = automaticRotation;
	}

	public boolean isGeneral() {
		return general;
	}

	public void setGeneral(boolean general) {
		this.general = general;
	}

	public boolean isPower() {
		return power;
	}

	public void setPower(boolean power) {
		this.power = power;
	}

	public boolean isObd() {
		return obd;
	}

	public void setObd(boolean obd) {
		this.obd = obd;
	}

	public boolean isAocs() {
		return aocs;
	}

	public void setAocs(boolean aocs) {
		this.aocs = aocs;
	}

	public boolean isFdirtmtc() {
		return fdirtmtc;
	}

	public void setFdirtmtc(boolean fdirtmtc) {
		this.fdirtmtc = fdirtmtc;
	}

	public boolean isPayload() {
		return payload;
	}

	public void setPayload(boolean payload) {
		this.payload = payload;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

}
