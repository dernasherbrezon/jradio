package ru.r2cloud.jradio.aistechsat3;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class PlatformBeacon {

	private DataFieldMeta meta;
	private boolean fsMounted;
	private boolean ramImage;
	private short tempMcu;
	private short tempRam;
	private int iGssb1;
	private int iGssb2;
	private int iFlash;
	private int iPwm;
	private long resetCause;
	private long bootCause;
	private int bootCount;
	private long clock;
	private long uptime;
	private short lastRssi;
	private short lastRfErr;
	private short bgndRssi;
	private int txDuty;
	private long totTxCount;
	private long totRxCount;
	private long totTxBytes;
	private long totRxBytes;
	private int bootCount2;
	private long bootCause2;
	private long txBytes;
	private long rxBytes;
	private byte activeConf;
	private long txCount;
	private long rxCount;
	private short tempBrd;
	private short tempPa;
	private int[] vboost;
	private int vbatt;
	private DataFieldMeta curMeta;
	private int[] curOut;
	private int[] curIn;
	private int curSun;
	private int curSys;
	private short[] temp;
	private int[] outVal;
	private int battMode;
	private int pptMode;
	private long wdtI2cS;
	private long wdtGndS;
	private long bootCount3;
	private long cntWdtI2c;
	private long cntWdtGnd;
	private long[] cntWdtCsp;
	private long[] wdtCspC;
	private int[] latchups;
	private int bootCause3;

	public PlatformBeacon() {
		// do nothing
	}

	public PlatformBeacon(DataInputStream dis) throws IOException {
		meta = new DataFieldMeta(dis);
		fsMounted = dis.readBoolean();
		ramImage = dis.readBoolean();
		tempMcu = dis.readShort();
		tempRam = dis.readShort();
		iGssb1 = dis.readUnsignedShort();
		iGssb2 = dis.readUnsignedShort();
		iFlash = dis.readUnsignedShort();
		iPwm = dis.readUnsignedShort();
		resetCause = StreamUtils.readUnsignedInt(dis);
		bootCause = StreamUtils.readUnsignedInt(dis);
		bootCount = dis.readUnsignedShort();
		clock = StreamUtils.readUnsignedInt(dis);
		uptime = StreamUtils.readUnsignedInt(dis);
		lastRssi = dis.readShort();
		lastRfErr = dis.readShort();
		bgndRssi = dis.readShort();
		txDuty = dis.readUnsignedByte();
		totTxCount = StreamUtils.readUnsignedInt(dis);
		totRxCount = StreamUtils.readUnsignedInt(dis);
		totTxBytes = StreamUtils.readUnsignedInt(dis);
		totRxBytes = StreamUtils.readUnsignedInt(dis);
		bootCount2 = dis.readUnsignedShort();
		bootCause2 = StreamUtils.readUnsignedInt(dis);
		txBytes = StreamUtils.readUnsignedInt(dis);
		rxBytes = StreamUtils.readUnsignedInt(dis);
		activeConf = dis.readByte();
		txCount = StreamUtils.readUnsignedInt(dis);
		rxCount = StreamUtils.readUnsignedInt(dis);
		tempBrd = dis.readShort();
		tempPa = dis.readShort();
		vboost = StreamUtils.readUnsignedShortArray(dis, 3);
		vbatt = dis.readUnsignedShort();
		curMeta = new DataFieldMeta(dis);
		curOut = StreamUtils.readUnsignedShortArray(dis, 6);
		curIn = StreamUtils.readUnsignedShortArray(dis, 3);
		curSun = dis.readUnsignedShort();
		curSys = dis.readUnsignedShort();
		temp = StreamUtils.readShortArray(dis, 6);
		outVal = StreamUtils.readUnsignedByteArray(dis, 8);
		battMode = dis.readUnsignedByte();
		pptMode = dis.readUnsignedByte();
		wdtI2cS = StreamUtils.readUnsignedInt(dis);
		wdtGndS = StreamUtils.readUnsignedInt(dis);
		bootCount3 = StreamUtils.readUnsignedInt(dis);
		cntWdtI2c = StreamUtils.readUnsignedInt(dis);
		cntWdtGnd = StreamUtils.readUnsignedInt(dis);
		cntWdtCsp = StreamUtils.readUnsignedIntArray(dis, 2);
		wdtCspC = StreamUtils.readUnsignedIntArray(dis, 2);
		latchups = StreamUtils.readUnsignedShortArray(dis, 6);
		bootCause3 = dis.readUnsignedByte();
	}

	public boolean isFsMounted() {
		return fsMounted;
	}

	public void setFsMounted(boolean fsMounted) {
		this.fsMounted = fsMounted;
	}

	public boolean isRamImage() {
		return ramImage;
	}

	public void setRamImage(boolean ramImage) {
		this.ramImage = ramImage;
	}

	public short getTempMcu() {
		return tempMcu;
	}

	public void setTempMcu(short tempMcu) {
		this.tempMcu = tempMcu;
	}

	public short getTempRam() {
		return tempRam;
	}

	public void setTempRam(short tempRam) {
		this.tempRam = tempRam;
	}

	public int getiGssb1() {
		return iGssb1;
	}

	public void setiGssb1(int iGssb1) {
		this.iGssb1 = iGssb1;
	}

	public int getiGssb2() {
		return iGssb2;
	}

	public void setiGssb2(int iGssb2) {
		this.iGssb2 = iGssb2;
	}

	public int getiFlash() {
		return iFlash;
	}

	public void setiFlash(int iFlash) {
		this.iFlash = iFlash;
	}

	public int getiPwm() {
		return iPwm;
	}

	public void setiPwm(int iPwm) {
		this.iPwm = iPwm;
	}

	public long getResetCause() {
		return resetCause;
	}

	public void setResetCause(long resetCause) {
		this.resetCause = resetCause;
	}

	public long getBootCause() {
		return bootCause;
	}

	public void setBootCause(long bootCause) {
		this.bootCause = bootCause;
	}

	public int getBootCount() {
		return bootCount;
	}

	public void setBootCount(int bootCount) {
		this.bootCount = bootCount;
	}

	public long getClock() {
		return clock;
	}

	public void setClock(long clock) {
		this.clock = clock;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public short getLastRssi() {
		return lastRssi;
	}

	public void setLastRssi(short lastRssi) {
		this.lastRssi = lastRssi;
	}

	public short getLastRfErr() {
		return lastRfErr;
	}

	public void setLastRfErr(short lastRfErr) {
		this.lastRfErr = lastRfErr;
	}

	public short getBgndRssi() {
		return bgndRssi;
	}

	public void setBgndRssi(short bgndRssi) {
		this.bgndRssi = bgndRssi;
	}

	public int getTxDuty() {
		return txDuty;
	}

	public void setTxDuty(int txDuty) {
		this.txDuty = txDuty;
	}

	public long getTotTxCount() {
		return totTxCount;
	}

	public void setTotTxCount(long totTxCount) {
		this.totTxCount = totTxCount;
	}

	public long getTotRxCount() {
		return totRxCount;
	}

	public void setTotRxCount(long totRxCount) {
		this.totRxCount = totRxCount;
	}

	public long getTotTxBytes() {
		return totTxBytes;
	}

	public void setTotTxBytes(long totTxBytes) {
		this.totTxBytes = totTxBytes;
	}

	public long getTotRxBytes() {
		return totRxBytes;
	}

	public void setTotRxBytes(long totRxBytes) {
		this.totRxBytes = totRxBytes;
	}

	public int getBootCount2() {
		return bootCount2;
	}

	public void setBootCount2(int bootCount2) {
		this.bootCount2 = bootCount2;
	}

	public long getBootCause2() {
		return bootCause2;
	}

	public void setBootCause2(long bootCause2) {
		this.bootCause2 = bootCause2;
	}

	public long getTxBytes() {
		return txBytes;
	}

	public void setTxBytes(long txBytes) {
		this.txBytes = txBytes;
	}

	public long getRxBytes() {
		return rxBytes;
	}

	public void setRxBytes(long rxBytes) {
		this.rxBytes = rxBytes;
	}

	public byte getActiveConf() {
		return activeConf;
	}

	public void setActiveConf(byte activeConf) {
		this.activeConf = activeConf;
	}

	public long getTxCount() {
		return txCount;
	}

	public void setTxCount(long txCount) {
		this.txCount = txCount;
	}

	public long getRxCount() {
		return rxCount;
	}

	public void setRxCount(long rxCount) {
		this.rxCount = rxCount;
	}

	public short getTempBrd() {
		return tempBrd;
	}

	public void setTempBrd(short tempBrd) {
		this.tempBrd = tempBrd;
	}

	public short getTempPa() {
		return tempPa;
	}

	public void setTempPa(short tempPa) {
		this.tempPa = tempPa;
	}

	public int[] getVboost() {
		return vboost;
	}

	public void setVboost(int[] vboost) {
		this.vboost = vboost;
	}

	public int getVbatt() {
		return vbatt;
	}

	public void setVbatt(int vbatt) {
		this.vbatt = vbatt;
	}

	public int[] getCurOut() {
		return curOut;
	}

	public void setCurOut(int[] curOut) {
		this.curOut = curOut;
	}

	public int[] getCurIn() {
		return curIn;
	}

	public void setCurIn(int[] curIn) {
		this.curIn = curIn;
	}

	public int getCurSun() {
		return curSun;
	}

	public void setCurSun(int curSun) {
		this.curSun = curSun;
	}

	public int getCurSys() {
		return curSys;
	}

	public void setCurSys(int curSys) {
		this.curSys = curSys;
	}

	public short[] getTemp() {
		return temp;
	}

	public void setTemp(short[] temp) {
		this.temp = temp;
	}

	public int[] getOutVal() {
		return outVal;
	}

	public void setOutVal(int[] outVal) {
		this.outVal = outVal;
	}

	public int getBattMode() {
		return battMode;
	}

	public void setBattMode(int battMode) {
		this.battMode = battMode;
	}

	public int getPptMode() {
		return pptMode;
	}

	public void setPptMode(int pptMode) {
		this.pptMode = pptMode;
	}

	public long getWdtI2cS() {
		return wdtI2cS;
	}

	public void setWdtI2cS(long wdtI2cS) {
		this.wdtI2cS = wdtI2cS;
	}

	public long getWdtGndS() {
		return wdtGndS;
	}

	public void setWdtGndS(long wdtGndS) {
		this.wdtGndS = wdtGndS;
	}

	public long getBootCount3() {
		return bootCount3;
	}

	public void setBootCount3(long bootCount3) {
		this.bootCount3 = bootCount3;
	}

	public long getCntWdtI2c() {
		return cntWdtI2c;
	}

	public void setCntWdtI2c(long cntWdtI2c) {
		this.cntWdtI2c = cntWdtI2c;
	}

	public long getCntWdtGnd() {
		return cntWdtGnd;
	}

	public void setCntWdtGnd(long cntWdtGnd) {
		this.cntWdtGnd = cntWdtGnd;
	}

	public long[] getCntWdtCsp() {
		return cntWdtCsp;
	}

	public void setCntWdtCsp(long[] cntWdtCsp) {
		this.cntWdtCsp = cntWdtCsp;
	}

	public long[] getWdtCspC() {
		return wdtCspC;
	}

	public void setWdtCspC(long[] wdtCspC) {
		this.wdtCspC = wdtCspC;
	}

	public int[] getLatchups() {
		return latchups;
	}

	public void setLatchups(int[] latchups) {
		this.latchups = latchups;
	}

	public int getBootCause3() {
		return bootCause3;
	}

	public void setBootCause3(int bootCause3) {
		this.bootCause3 = bootCause3;
	}

	public DataFieldMeta getMeta() {
		return meta;
	}

	public void setMeta(DataFieldMeta meta) {
		this.meta = meta;
	}

	public DataFieldMeta getCurMeta() {
		return curMeta;
	}

	public void setCurMeta(DataFieldMeta curMeta) {
		this.curMeta = curMeta;
	}

}
