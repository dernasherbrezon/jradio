package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.cute.soh.SohEventCheck;
import ru.r2cloud.jradio.cute.soh.SohRwDrive;
import ru.r2cloud.jradio.cute.soh.SohTracker;

public class BctSoh {

	private SohL0 l0;
	private SohCommandTlm commandTlm;
	private SohGeneral general;
	private SohTime time;
	private SohRefs refs;
	private SohAttDet attDet;
	private SohAttCmd attCmd;
	private SohRwDrive rwDrive;
	private SohTracker tracker;
	private SohAttCtrl attCtrl;
	private SohMomentum momentum;
	private SohCss css;
	private SohMag mag;
	private SohImu imu;
	private SohClockSync clockSync;
	private SohAnalogs analogs;
	private SohGps gps;
	private SohEventCheck eventCheck;
	private SohRadio radio;
	private SohTrackerCtrl trackerCtrl;

	public BctSoh() {
		// do nothing
	}

	public BctSoh(DataInputStream dis) throws IOException {
		l0 = new SohL0(dis);
		commandTlm = new SohCommandTlm(dis);
		general = new SohGeneral(dis);
		time = new SohTime(dis);
		refs = new SohRefs(dis);
		attDet = new SohAttDet(dis);
		attCmd = new SohAttCmd(dis);
		rwDrive = new SohRwDrive(dis);
		tracker = new SohTracker(dis);
		attCtrl = new SohAttCtrl(dis);
		momentum = new SohMomentum(dis);
		css = new SohCss(dis);
		mag = new SohMag(dis);
		imu = new SohImu(dis);
		clockSync = new SohClockSync(dis);
		analogs = new SohAnalogs(dis);
		gps = new SohGps(dis);
		eventCheck = new SohEventCheck(dis);
		radio = new SohRadio(dis);
		trackerCtrl = new SohTrackerCtrl(dis);
	}

	public SohL0 getL0() {
		return l0;
	}

	public void setL0(SohL0 l0) {
		this.l0 = l0;
	}

	public SohCommandTlm getCommandTlm() {
		return commandTlm;
	}

	public void setCommandTlm(SohCommandTlm commandTlm) {
		this.commandTlm = commandTlm;
	}

	public SohGeneral getGeneral() {
		return general;
	}

	public void setGeneral(SohGeneral general) {
		this.general = general;
	}

	public SohTime getTime() {
		return time;
	}

	public void setTime(SohTime time) {
		this.time = time;
	}

	public SohRefs getRefs() {
		return refs;
	}

	public void setRefs(SohRefs refs) {
		this.refs = refs;
	}

	public SohAttDet getAttDet() {
		return attDet;
	}

	public void setAttDet(SohAttDet attDet) {
		this.attDet = attDet;
	}

	public SohAttCmd getAttCmd() {
		return attCmd;
	}

	public void setAttCmd(SohAttCmd attCmd) {
		this.attCmd = attCmd;
	}

	public SohRwDrive getRwDrive() {
		return rwDrive;
	}

	public void setRwDrive(SohRwDrive rwDrive) {
		this.rwDrive = rwDrive;
	}

	public SohTracker getTracker() {
		return tracker;
	}

	public void setTracker(SohTracker tracker) {
		this.tracker = tracker;
	}

	public SohAttCtrl getAttCtrl() {
		return attCtrl;
	}

	public void setAttCtrl(SohAttCtrl attCtrl) {
		this.attCtrl = attCtrl;
	}

	public SohMomentum getMomentum() {
		return momentum;
	}

	public void setMomentum(SohMomentum momentum) {
		this.momentum = momentum;
	}

	public SohCss getCss() {
		return css;
	}

	public void setCss(SohCss css) {
		this.css = css;
	}

	public SohMag getMag() {
		return mag;
	}

	public void setMag(SohMag mag) {
		this.mag = mag;
	}

	public SohImu getImu() {
		return imu;
	}

	public void setImu(SohImu imu) {
		this.imu = imu;
	}

	public SohClockSync getClockSync() {
		return clockSync;
	}

	public void setClockSync(SohClockSync clockSync) {
		this.clockSync = clockSync;
	}

	public SohAnalogs getAnalogs() {
		return analogs;
	}

	public void setAnalogs(SohAnalogs analogs) {
		this.analogs = analogs;
	}

	public SohGps getGps() {
		return gps;
	}

	public void setGps(SohGps gps) {
		this.gps = gps;
	}

	public SohEventCheck getEventCheck() {
		return eventCheck;
	}

	public void setEventCheck(SohEventCheck eventCheck) {
		this.eventCheck = eventCheck;
	}

	public SohRadio getRadio() {
		return radio;
	}

	public void setRadio(SohRadio radio) {
		this.radio = radio;
	}

	public SohTrackerCtrl getTrackerCtrl() {
		return trackerCtrl;
	}

	public void setTrackerCtrl(SohTrackerCtrl trackerCtrl) {
		this.trackerCtrl = trackerCtrl;
	}

}
