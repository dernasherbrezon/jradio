package ru.r2cloud.jradio.cute;

import java.io.DataInputStream;
import java.io.IOException;

public class BctSoh {

	private SohL0 sohl0;
	private SohCommandTlm sohCommandTlm;
	private SohGeneral sohGeneral;
	private SohTime sohTime;
	private SohRefs sohRefs;
	private SohAttDet sohAttDet;
	private SohAttCmd sohAttCmd;
	private SohRwDrive sohRwDrive;
	private SohTracker sohTracker;
	private SohAttCtrl sohAttCtrl;
	private SohMomentum sohMomentum;
	private SohCss sohCss;
	private SohMag sohMag;
	private SohImu sohImu;
	private SohClockSync sohClockSync;
	private SohAnalogs sohAnalogs;
	private SohTracker sohTracker2;
	private SohGps sohGps;
	private SohEventCheck sohEventCheck;
	private SohRadio sohRadio;
	private SohTrackerCtrl sohTrackerCtrl;

	public BctSoh() {
		// do nothing
	}

	public BctSoh(DataInputStream dis) throws IOException {
		sohl0 = new SohL0(dis);
		sohCommandTlm = new SohCommandTlm(dis);
		sohGeneral = new SohGeneral(dis);
		sohTime = new SohTime(dis);
		sohRefs = new SohRefs(dis);
		sohAttDet = new SohAttDet(dis);
		sohAttCmd = new SohAttCmd(dis);
		sohRwDrive = new SohRwDrive(dis);
		sohTracker = new SohTracker(dis);
		sohAttCtrl = new SohAttCtrl(dis);
		sohMomentum = new SohMomentum(dis);
		sohCss = new SohCss(dis);
		sohMag = new SohMag(dis);
		sohImu = new SohImu(dis);
		sohClockSync = new SohClockSync(dis);
		sohAnalogs = new SohAnalogs(dis);
		sohTracker2 = new SohTracker(dis);
		sohGps = new SohGps(dis);
		sohEventCheck = new SohEventCheck(dis);
		sohRadio = new SohRadio(dis);
		sohTrackerCtrl = new SohTrackerCtrl(dis);
	}

	public SohL0 getSohl0() {
		return sohl0;
	}

	public void setSohl0(SohL0 sohl0) {
		this.sohl0 = sohl0;
	}

	public SohCommandTlm getSohCommandTlm() {
		return sohCommandTlm;
	}

	public void setSohCommandTlm(SohCommandTlm sohCommandTlm) {
		this.sohCommandTlm = sohCommandTlm;
	}

	public SohGeneral getSohGeneral() {
		return sohGeneral;
	}

	public void setSohGeneral(SohGeneral sohGeneral) {
		this.sohGeneral = sohGeneral;
	}

	public SohTime getSohTime() {
		return sohTime;
	}

	public void setSohTime(SohTime sohTime) {
		this.sohTime = sohTime;
	}

	public SohRefs getSohRefs() {
		return sohRefs;
	}

	public void setSohRefs(SohRefs sohRefs) {
		this.sohRefs = sohRefs;
	}

	public SohAttDet getSohAttDet() {
		return sohAttDet;
	}

	public void setSohAttDet(SohAttDet sohAttDet) {
		this.sohAttDet = sohAttDet;
	}

	public SohAttCmd getSohAttCmd() {
		return sohAttCmd;
	}

	public void setSohAttCmd(SohAttCmd sohAttCmd) {
		this.sohAttCmd = sohAttCmd;
	}

	public SohRwDrive getSohRwDrive() {
		return sohRwDrive;
	}

	public void setSohRwDrive(SohRwDrive sohRwDrive) {
		this.sohRwDrive = sohRwDrive;
	}

	public SohTracker getSohTracker() {
		return sohTracker;
	}

	public void setSohTracker(SohTracker sohTracker) {
		this.sohTracker = sohTracker;
	}

	public SohAttCtrl getSohAttCtrl() {
		return sohAttCtrl;
	}

	public void setSohAttCtrl(SohAttCtrl sohAttCtrl) {
		this.sohAttCtrl = sohAttCtrl;
	}

	public SohMomentum getSohMomentum() {
		return sohMomentum;
	}

	public void setSohMomentum(SohMomentum sohMomentum) {
		this.sohMomentum = sohMomentum;
	}

	public SohCss getSohCss() {
		return sohCss;
	}

	public void setSohCss(SohCss sohCss) {
		this.sohCss = sohCss;
	}

	public SohMag getSohMag() {
		return sohMag;
	}

	public void setSohMag(SohMag sohMag) {
		this.sohMag = sohMag;
	}

	public SohImu getSohImu() {
		return sohImu;
	}

	public void setSohImu(SohImu sohImu) {
		this.sohImu = sohImu;
	}

	public SohClockSync getSohClockSync() {
		return sohClockSync;
	}

	public void setSohClockSync(SohClockSync sohClockSync) {
		this.sohClockSync = sohClockSync;
	}

	public SohAnalogs getSohAnalogs() {
		return sohAnalogs;
	}

	public void setSohAnalogs(SohAnalogs sohAnalogs) {
		this.sohAnalogs = sohAnalogs;
	}

	public SohTracker getSohTracker2() {
		return sohTracker2;
	}
	
	public void setSohTracker2(SohTracker sohTracker2) {
		this.sohTracker2 = sohTracker2;
	}

	public SohGps getSohGps() {
		return sohGps;
	}

	public void setSohGps(SohGps sohGps) {
		this.sohGps = sohGps;
	}

	public SohEventCheck getSohEventCheck() {
		return sohEventCheck;
	}

	public void setSohEventCheck(SohEventCheck sohEventCheck) {
		this.sohEventCheck = sohEventCheck;
	}

	public SohRadio getSohRadio() {
		return sohRadio;
	}

	public void setSohRadio(SohRadio sohRadio) {
		this.sohRadio = sohRadio;
	}

	public SohTrackerCtrl getSohTrackerCtrl() {
		return sohTrackerCtrl;
	}

	public void setSohTrackerCtrl(SohTrackerCtrl sohTrackerCtrl) {
		this.sohTrackerCtrl = sohTrackerCtrl;
	}

}
