package ru.r2cloud.jradio.amical1;

public class M1Flags {

	private boolean imcAocsOk;
	private boolean imcCulOk;
	private boolean imcCurOk;
	private boolean imcVhfOk;
	private boolean imcUhfOk;
	private boolean vhfDownlink;
	private boolean uhfDownlink;
	private boolean imcCheck;
	private boolean beaconMode;
	private boolean cyclicResetOn;
	private boolean survivalMode;
	private boolean payloadOff;
	private boolean cuAutoOff;
	private boolean tmLog;
	private boolean culOn;
	private boolean culFault;
	private boolean curOn;
	private boolean curFault;
	private boolean cuOn;
	private int culDead;
	private int curDead;
	private boolean fault3v3r;
	private boolean fault3v3m;
	private boolean charger;
	private boolean chargem;
	private boolean survivalStart;
	private boolean survivalEnd;

	public M1Flags() {
		// do nothing
	}

	public M1Flags(long value) {
		imcAocsOk = (value & 0x1) > 0;
		imcCulOk = ((value >> 1) & 0x1) > 0;
		imcCurOk = ((value >> 2) & 0x1) > 0;
		imcVhfOk = ((value >> 3) & 0x1) > 0;
		imcUhfOk = ((value >> 4) & 0x1) > 0;
		vhfDownlink = ((value >> 5) & 0x1) > 0;
		uhfDownlink = ((value >> 6) & 0x1) > 0;
		imcCheck = ((value >> 7) & 0x1) > 0;
		beaconMode = ((value >> 8) & 0x1) > 0;
		cyclicResetOn = ((value >> 9) & 0x1) > 0;
		survivalMode = ((value >> 10) & 0x1) > 0;
		payloadOff = ((value >> 11) & 0x1) > 0;
		cuAutoOff = ((value >> 12) & 0x1) > 0;
		tmLog = ((value >> 13) & 0x1) > 0;
		culOn = ((value >> 16) & 0x1) > 0;
		culFault = ((value >> 17) & 0x1) > 0;
		curOn = ((value >> 18) & 0x1) > 0;
		curFault = ((value >> 19) & 0x1) > 0;
		cuOn = ((value >> 20) & 0x1) > 0;
		culDead = (int) (value >> 24) & 0xF;
		curDead = (int) (value >> 28) & 0xF;
		fault3v3r = ((value >> 40) & 0x1) > 0;
		fault3v3m = ((value >> 41) & 0x1) > 0;
		charger = ((value >> 42) & 0x1) > 0;
		chargem = ((value >> 43) & 0x1) > 0;
		survivalStart = ((value >> 51) & 0x1) > 0;
		survivalEnd = ((value >> 52) & 0x1) > 0;
	}

	public boolean isImcAocsOk() {
		return imcAocsOk;
	}

	public void setImcAocsOk(boolean imcAocsOk) {
		this.imcAocsOk = imcAocsOk;
	}

	public boolean isImcCulOk() {
		return imcCulOk;
	}

	public void setImcCulOk(boolean imcCulOk) {
		this.imcCulOk = imcCulOk;
	}

	public boolean isImcCurOk() {
		return imcCurOk;
	}

	public void setImcCurOk(boolean imcCurOk) {
		this.imcCurOk = imcCurOk;
	}

	public boolean isImcVhfOk() {
		return imcVhfOk;
	}

	public void setImcVhfOk(boolean imcVhfOk) {
		this.imcVhfOk = imcVhfOk;
	}

	public boolean isImcUhfOk() {
		return imcUhfOk;
	}

	public void setImcUhfOk(boolean imcUhfOk) {
		this.imcUhfOk = imcUhfOk;
	}

	public boolean isVhfDownlink() {
		return vhfDownlink;
	}

	public void setVhfDownlink(boolean vhfDownlink) {
		this.vhfDownlink = vhfDownlink;
	}

	public boolean isUhfDownlink() {
		return uhfDownlink;
	}

	public void setUhfDownlink(boolean uhfDownlink) {
		this.uhfDownlink = uhfDownlink;
	}

	public boolean isImcCheck() {
		return imcCheck;
	}

	public void setImcCheck(boolean imcCheck) {
		this.imcCheck = imcCheck;
	}

	public boolean isBeaconMode() {
		return beaconMode;
	}

	public void setBeaconMode(boolean beaconMode) {
		this.beaconMode = beaconMode;
	}

	public boolean isCyclicResetOn() {
		return cyclicResetOn;
	}

	public void setCyclicResetOn(boolean cyclicResetOn) {
		this.cyclicResetOn = cyclicResetOn;
	}

	public boolean isSurvivalMode() {
		return survivalMode;
	}

	public void setSurvivalMode(boolean survivalMode) {
		this.survivalMode = survivalMode;
	}

	public boolean isPayloadOff() {
		return payloadOff;
	}

	public void setPayloadOff(boolean payloadOff) {
		this.payloadOff = payloadOff;
	}

	public boolean isCuAutoOff() {
		return cuAutoOff;
	}

	public void setCuAutoOff(boolean cuAutoOff) {
		this.cuAutoOff = cuAutoOff;
	}

	public boolean isTmLog() {
		return tmLog;
	}

	public void setTmLog(boolean tmLog) {
		this.tmLog = tmLog;
	}

	public boolean isCulOn() {
		return culOn;
	}

	public void setCulOn(boolean culOn) {
		this.culOn = culOn;
	}

	public boolean isCulFault() {
		return culFault;
	}

	public void setCulFault(boolean culFault) {
		this.culFault = culFault;
	}

	public boolean isCurOn() {
		return curOn;
	}

	public void setCurOn(boolean curOn) {
		this.curOn = curOn;
	}

	public boolean isCurFault() {
		return curFault;
	}

	public void setCurFault(boolean curFault) {
		this.curFault = curFault;
	}

	public boolean isCuOn() {
		return cuOn;
	}

	public void setCuOn(boolean cuOn) {
		this.cuOn = cuOn;
	}

	public int getCulDead() {
		return culDead;
	}

	public void setCulDead(int culDead) {
		this.culDead = culDead;
	}

	public int getCurDead() {
		return curDead;
	}

	public void setCurDead(int curDead) {
		this.curDead = curDead;
	}

	public boolean isFault3v3r() {
		return fault3v3r;
	}

	public void setFault3v3r(boolean fault3v3r) {
		this.fault3v3r = fault3v3r;
	}

	public boolean isFault3v3m() {
		return fault3v3m;
	}

	public void setFault3v3m(boolean fault3v3m) {
		this.fault3v3m = fault3v3m;
	}

	public boolean isCharger() {
		return charger;
	}

	public void setCharger(boolean charger) {
		this.charger = charger;
	}

	public boolean isChargem() {
		return chargem;
	}

	public void setChargem(boolean chargem) {
		this.chargem = chargem;
	}

	public boolean isSurvivalStart() {
		return survivalStart;
	}

	public void setSurvivalStart(boolean survivalStart) {
		this.survivalStart = survivalStart;
	}

	public boolean isSurvivalEnd() {
		return survivalEnd;
	}

	public void setSurvivalEnd(boolean survivalEnd) {
		this.survivalEnd = survivalEnd;
	}

}
