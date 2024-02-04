package ru.r2cloud.jradio.suchai2;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class TelemetryStatus {

	private long index;
	private long timestamp;
	private OpMode obcOpmode;
	private long rtcDateTime;
	private long obcLastReset;
	private long obcHrsAlive;
	private long obcHrsWoReset;
	private long obcResetCounter;
	private long obcExecutedCmds;
	private long obcFailedCmds;
	private long comCountTm;
	private long comCountTc;
	private long comLastTc;
	private long fplLast;
	private long fplQueue;
	private long adsTleEpoch;
	private long epsVbatt;
	private long epsCurSun;
	private long epsCurSys;
	private long obcTemp1;
	private long epsTempBat0;
	private long drpMachAction;
	private long drpMachState;
	private long drpMachPayloads;
	private long drpMachStep;

	public TelemetryStatus() {
		// do nothing
	}

	public TelemetryStatus(DataInputStream dis) throws IOException {
		index = StreamUtils.readUnsignedInt(dis);
		timestamp = StreamUtils.readUnsignedInt(dis);
		obcOpmode = OpMode.valueOfCode((int) StreamUtils.readUnsignedInt(dis));
		rtcDateTime = StreamUtils.readUnsignedInt(dis);
		obcLastReset = StreamUtils.readUnsignedInt(dis);
		obcHrsAlive = StreamUtils.readUnsignedInt(dis);
		obcHrsWoReset = StreamUtils.readUnsignedInt(dis);
		obcResetCounter = StreamUtils.readUnsignedInt(dis);
		obcExecutedCmds = StreamUtils.readUnsignedInt(dis);
		obcFailedCmds = StreamUtils.readUnsignedInt(dis);
		comCountTm = StreamUtils.readUnsignedInt(dis);
		comCountTc = StreamUtils.readUnsignedInt(dis);
		comLastTc = StreamUtils.readUnsignedInt(dis);
		fplLast = StreamUtils.readUnsignedInt(dis);
		fplQueue = StreamUtils.readUnsignedInt(dis);
		adsTleEpoch = StreamUtils.readUnsignedInt(dis);
		epsVbatt = StreamUtils.readUnsignedInt(dis);
		epsCurSun = StreamUtils.readUnsignedInt(dis);
		epsCurSys = StreamUtils.readUnsignedInt(dis);
		obcTemp1 = StreamUtils.readUnsignedInt(dis);
		epsTempBat0 = StreamUtils.readUnsignedInt(dis);
		drpMachAction = StreamUtils.readUnsignedInt(dis);
		drpMachState = StreamUtils.readUnsignedInt(dis);
		drpMachPayloads = StreamUtils.readUnsignedInt(dis);
		drpMachStep = StreamUtils.readUnsignedInt(dis);
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public OpMode getObcOpmode() {
		return obcOpmode;
	}

	public void setObcOpmode(OpMode obcOpmode) {
		this.obcOpmode = obcOpmode;
	}

	public long getRtcDateTime() {
		return rtcDateTime;
	}

	public void setRtcDateTime(long rtcDateTime) {
		this.rtcDateTime = rtcDateTime;
	}

	public long getObcLastReset() {
		return obcLastReset;
	}

	public void setObcLastReset(long obcLastReset) {
		this.obcLastReset = obcLastReset;
	}

	public long getObcHrsAlive() {
		return obcHrsAlive;
	}

	public void setObcHrsAlive(long obcHrsAlive) {
		this.obcHrsAlive = obcHrsAlive;
	}

	public long getObcHrsWoReset() {
		return obcHrsWoReset;
	}

	public void setObcHrsWoReset(long obcHrsWoReset) {
		this.obcHrsWoReset = obcHrsWoReset;
	}

	public long getObcResetCounter() {
		return obcResetCounter;
	}

	public void setObcResetCounter(long obcResetCounter) {
		this.obcResetCounter = obcResetCounter;
	}

	public long getObcExecutedCmds() {
		return obcExecutedCmds;
	}

	public void setObcExecutedCmds(long obcExecutedCmds) {
		this.obcExecutedCmds = obcExecutedCmds;
	}

	public long getObcFailedCmds() {
		return obcFailedCmds;
	}

	public void setObcFailedCmds(long obcFailedCmds) {
		this.obcFailedCmds = obcFailedCmds;
	}

	public long getComCountTm() {
		return comCountTm;
	}

	public void setComCountTm(long comCountTm) {
		this.comCountTm = comCountTm;
	}

	public long getComCountTc() {
		return comCountTc;
	}

	public void setComCountTc(long comCountTc) {
		this.comCountTc = comCountTc;
	}

	public long getComLastTc() {
		return comLastTc;
	}

	public void setComLastTc(long comLastTc) {
		this.comLastTc = comLastTc;
	}

	public long getFplLast() {
		return fplLast;
	}

	public void setFplLast(long fplLast) {
		this.fplLast = fplLast;
	}

	public long getFplQueue() {
		return fplQueue;
	}

	public void setFplQueue(long fplQueue) {
		this.fplQueue = fplQueue;
	}

	public long getAdsTleEpoch() {
		return adsTleEpoch;
	}

	public void setAdsTleEpoch(long adsTleEpoch) {
		this.adsTleEpoch = adsTleEpoch;
	}

	public long getEpsVbatt() {
		return epsVbatt;
	}

	public void setEpsVbatt(long epsVbatt) {
		this.epsVbatt = epsVbatt;
	}

	public long getEpsCurSun() {
		return epsCurSun;
	}

	public void setEpsCurSun(long epsCurSun) {
		this.epsCurSun = epsCurSun;
	}

	public long getEpsCurSys() {
		return epsCurSys;
	}

	public void setEpsCurSys(long epsCurSys) {
		this.epsCurSys = epsCurSys;
	}

	public long getObcTemp1() {
		return obcTemp1;
	}

	public void setObcTemp1(long obcTemp1) {
		this.obcTemp1 = obcTemp1;
	}

	public long getEpsTempBat0() {
		return epsTempBat0;
	}

	public void setEpsTempBat0(long epsTempBat0) {
		this.epsTempBat0 = epsTempBat0;
	}

	public long getDrpMachAction() {
		return drpMachAction;
	}

	public void setDrpMachAction(long drpMachAction) {
		this.drpMachAction = drpMachAction;
	}

	public long getDrpMachState() {
		return drpMachState;
	}

	public void setDrpMachState(long drpMachState) {
		this.drpMachState = drpMachState;
	}

	public long getDrpMachPayloads() {
		return drpMachPayloads;
	}

	public void setDrpMachPayloads(long drpMachPayloads) {
		this.drpMachPayloads = drpMachPayloads;
	}

	public long getDrpMachStep() {
		return drpMachStep;
	}

	public void setDrpMachStep(long drpMachStep) {
		this.drpMachStep = drpMachStep;
	}

}
