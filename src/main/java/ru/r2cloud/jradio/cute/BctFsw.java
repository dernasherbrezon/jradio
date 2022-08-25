package ru.r2cloud.jradio.cute;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.cute.fsw.FswAnalogs;
import ru.r2cloud.jradio.cute.fsw.FswAttCmd;
import ru.r2cloud.jradio.cute.fsw.FswAttCtrl;
import ru.r2cloud.jradio.cute.fsw.FswAttDet;
import ru.r2cloud.jradio.cute.fsw.FswCal;
import ru.r2cloud.jradio.cute.fsw.FswClockSync;
import ru.r2cloud.jradio.cute.fsw.FswCommandTlm;
import ru.r2cloud.jradio.cute.fsw.FswCss;
import ru.r2cloud.jradio.cute.fsw.FswEventCheck;
import ru.r2cloud.jradio.cute.fsw.FswGeneral;
import ru.r2cloud.jradio.cute.fsw.FswGps;
import ru.r2cloud.jradio.cute.fsw.FswImu;
import ru.r2cloud.jradio.cute.fsw.FswMag;
import ru.r2cloud.jradio.cute.fsw.FswMomentum;
import ru.r2cloud.jradio.cute.fsw.FswPayload;
import ru.r2cloud.jradio.cute.fsw.FswPower;
import ru.r2cloud.jradio.cute.fsw.FswRadio;
import ru.r2cloud.jradio.cute.fsw.FswRefs;
import ru.r2cloud.jradio.cute.fsw.FswRwDrive;
import ru.r2cloud.jradio.cute.fsw.FswTables;
import ru.r2cloud.jradio.cute.fsw.FswTime;
import ru.r2cloud.jradio.cute.fsw.FswTlmProc;
import ru.r2cloud.jradio.cute.fsw.FswTracker;
import ru.r2cloud.jradio.cute.fsw.FswTrackerCtrl;
import ru.r2cloud.jradio.cute.soh.SohL0;

public class BctFsw {

	private SohL0 l0;
	private FswCommandTlm commandTlm;
	private FswGeneral general;
	private FswTime time;
	private FswRefs refs;
	private FswAttDet attDet;
	private FswAttCmd attCmd;
	private FswRwDrive rwDrive;
	private FswTracker tracker;
	private FswAttCtrl attCtrl;
	private FswMomentum momentum;
	private FswCss css;
	private FswMag mag;
	private FswImu imu;
	private FswClockSync clockSync;
	private FswPayload payload;
	private FswTlmProc tlmProc;
	private FswAnalogs analogs;
	private FswTables tables;
	private FswTracker tracker2;
	private FswGps gps;
	private FswEventCheck eventCheck;
	private FswRadio radio;
	private FswCal cal;
	private FswTrackerCtrl trackerCtrl;
	private FswPower power;

	public BctFsw() {
		// do nothing
	}

	public BctFsw(DataInputStream dis) throws IOException {
		l0 = new SohL0(dis);
		commandTlm = new FswCommandTlm(dis);
		general = new FswGeneral(dis);
		time = new FswTime(dis);
		refs = new FswRefs(dis);
		attDet = new FswAttDet(dis);
		attCmd = new FswAttCmd(dis);
		rwDrive = new FswRwDrive(dis);
		tracker = new FswTracker(dis);
		attCtrl = new FswAttCtrl(dis);
		momentum = new FswMomentum(dis);
		css = new FswCss(dis);
		mag = new FswMag(dis);
		imu = new FswImu(dis);
		clockSync = new FswClockSync(dis);
		payload = new FswPayload(dis);
		tlmProc = new FswTlmProc(dis);
		analogs = new FswAnalogs(dis);
		tables = new FswTables(dis);
		tracker2 = new FswTracker(dis);
		gps = new FswGps(dis);
		eventCheck = new FswEventCheck(dis);
		radio = new FswRadio(dis);
		cal = new FswCal(dis);
		trackerCtrl = new FswTrackerCtrl(dis);
		power = new FswPower(dis);
	}

	public SohL0 getL0() {
		return l0;
	}

	public void setL0(SohL0 l0) {
		this.l0 = l0;
	}

	public FswCommandTlm getCommandTlm() {
		return commandTlm;
	}

	public void setCommandTlm(FswCommandTlm commandTlm) {
		this.commandTlm = commandTlm;
	}

	public FswGeneral getGeneral() {
		return general;
	}

	public void setGeneral(FswGeneral general) {
		this.general = general;
	}

	public FswTime getTime() {
		return time;
	}

	public void setTime(FswTime time) {
		this.time = time;
	}

	public FswRefs getRefs() {
		return refs;
	}

	public void setRefs(FswRefs refs) {
		this.refs = refs;
	}

	public FswAttDet getAttDet() {
		return attDet;
	}

	public void setAttDet(FswAttDet attDet) {
		this.attDet = attDet;
	}

	public FswAttCmd getAttCmd() {
		return attCmd;
	}

	public void setAttCmd(FswAttCmd attCmd) {
		this.attCmd = attCmd;
	}

	public FswRwDrive getRwDrive() {
		return rwDrive;
	}

	public void setRwDrive(FswRwDrive rwDrive) {
		this.rwDrive = rwDrive;
	}

	public FswTracker getTracker() {
		return tracker;
	}

	public void setTracker(FswTracker tracker) {
		this.tracker = tracker;
	}

	public FswAttCtrl getAttCtrl() {
		return attCtrl;
	}

	public void setAttCtrl(FswAttCtrl attCtrl) {
		this.attCtrl = attCtrl;
	}

	public FswMomentum getMomentum() {
		return momentum;
	}

	public void setMomentum(FswMomentum momentum) {
		this.momentum = momentum;
	}

	public FswCss getCss() {
		return css;
	}

	public void setCss(FswCss css) {
		this.css = css;
	}

	public FswMag getMag() {
		return mag;
	}

	public void setMag(FswMag mag) {
		this.mag = mag;
	}

	public FswImu getImu() {
		return imu;
	}

	public void setImu(FswImu imu) {
		this.imu = imu;
	}

	public FswClockSync getClockSync() {
		return clockSync;
	}

	public void setClockSync(FswClockSync clockSync) {
		this.clockSync = clockSync;
	}

	public FswPayload getPayload() {
		return payload;
	}

	public void setPayload(FswPayload payload) {
		this.payload = payload;
	}

	public FswTlmProc getTlmProc() {
		return tlmProc;
	}

	public void setTlmProc(FswTlmProc tlmProc) {
		this.tlmProc = tlmProc;
	}

	public FswAnalogs getAnalogs() {
		return analogs;
	}

	public void setAnalogs(FswAnalogs analogs) {
		this.analogs = analogs;
	}

	public FswTables getTables() {
		return tables;
	}

	public void setTables(FswTables tables) {
		this.tables = tables;
	}

	public FswTracker getTracker2() {
		return tracker2;
	}

	public void setTracker2(FswTracker tracker2) {
		this.tracker2 = tracker2;
	}

	public FswGps getGps() {
		return gps;
	}

	public void setGps(FswGps gps) {
		this.gps = gps;
	}

	public FswEventCheck getEventCheck() {
		return eventCheck;
	}

	public void setEventCheck(FswEventCheck eventCheck) {
		this.eventCheck = eventCheck;
	}

	public FswRadio getRadio() {
		return radio;
	}

	public void setRadio(FswRadio radio) {
		this.radio = radio;
	}

	public FswCal getCal() {
		return cal;
	}

	public void setCal(FswCal cal) {
		this.cal = cal;
	}

	public FswTrackerCtrl getTrackerCtrl() {
		return trackerCtrl;
	}

	public void setTrackerCtrl(FswTrackerCtrl trackerCtrl) {
		this.trackerCtrl = trackerCtrl;
	}

	public FswPower getPower() {
		return power;
	}

	public void setPower(FswPower power) {
		this.power = power;
	}

}
