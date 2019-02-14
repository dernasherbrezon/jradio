package ru.r2cloud.jradio.lume1;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class B3TtcGssb {

	private GssbStatus nx;
	private GssbStatus ny;
	private GssbStatus px;
	private GssbStatus py;

	private float tempBrd;
	private short lastRferr;
	private short lastRssi;

	private long totRxBytes;
	private long rxBytes;

	private long totRxCount;
	private long rxCount;

	private long totTxBytes;
	private long txBytes;

	private long totTxCount;
	private long txCount;

	private float tempPa;
	private long bootCause;
	private short bgndRssi;
	private int activeConf;
	private int bootCount;
	private long lastContact;
	private int txDuty;

	public B3TtcGssb(BitInputStream bis) throws IOException {
		nx = new GssbStatus(bis);
		ny = new GssbStatus(bis);
		px = new GssbStatus(bis);
		py = new GssbStatus(bis);

		tempBrd = bis.readShort() * 0.1f;
		lastRferr = bis.readShort();
		lastRssi = bis.readShort();

		totRxBytes = bis.readUnsignedLong(32);
		rxBytes = bis.readUnsignedLong(32);
		totRxCount = bis.readUnsignedLong(32);
		rxCount = bis.readUnsignedLong(32);
		totTxBytes = bis.readUnsignedLong(32);
		txBytes = bis.readUnsignedLong(32);
		totTxCount = bis.readUnsignedLong(32);
		txCount = bis.readUnsignedLong(32);

		tempPa = bis.readShort() * 0.1f;
		bootCause = bis.readUnsignedLong(32);
		bgndRssi = bis.readShort();
		activeConf = bis.readUnsignedByte();
		bootCount = bis.readUnsignedInt(16);
		lastContact = bis.readUnsignedLong(32);
		txDuty = bis.readUnsignedByte();
	}

	public GssbStatus getNx() {
		return nx;
	}

	public void setNx(GssbStatus nx) {
		this.nx = nx;
	}

	public GssbStatus getNy() {
		return ny;
	}

	public void setNy(GssbStatus ny) {
		this.ny = ny;
	}

	public GssbStatus getPx() {
		return px;
	}

	public void setPx(GssbStatus px) {
		this.px = px;
	}

	public GssbStatus getPy() {
		return py;
	}

	public void setPy(GssbStatus py) {
		this.py = py;
	}

	public float getTempBrd() {
		return tempBrd;
	}

	public void setTempBrd(float tempBrd) {
		this.tempBrd = tempBrd;
	}

	public short getLastRferr() {
		return lastRferr;
	}

	public void setLastRferr(short lastRferr) {
		this.lastRferr = lastRferr;
	}

	public short getLastRssi() {
		return lastRssi;
	}

	public void setLastRssi(short lastRssi) {
		this.lastRssi = lastRssi;
	}

	public long getTotRxBytes() {
		return totRxBytes;
	}

	public void setTotRxBytes(long totRxBytes) {
		this.totRxBytes = totRxBytes;
	}

	public long getRxBytes() {
		return rxBytes;
	}

	public void setRxBytes(long rxBytes) {
		this.rxBytes = rxBytes;
	}

	public long getTotRxCount() {
		return totRxCount;
	}

	public void setTotRxCount(long totRxCount) {
		this.totRxCount = totRxCount;
	}

	public long getRxCount() {
		return rxCount;
	}

	public void setRxCount(long rxCount) {
		this.rxCount = rxCount;
	}

	public long getTotTxBytes() {
		return totTxBytes;
	}

	public void setTotTxBytes(long totTxBytes) {
		this.totTxBytes = totTxBytes;
	}

	public long getTxBytes() {
		return txBytes;
	}

	public void setTxBytes(long txBytes) {
		this.txBytes = txBytes;
	}

	public long getTotTxCount() {
		return totTxCount;
	}

	public void setTotTxCount(long totTxCount) {
		this.totTxCount = totTxCount;
	}

	public long getTxCount() {
		return txCount;
	}

	public void setTxCount(long txCount) {
		this.txCount = txCount;
	}

	public float getTempPa() {
		return tempPa;
	}

	public void setTempPa(float tempPa) {
		this.tempPa = tempPa;
	}

	public long getBootCause() {
		return bootCause;
	}

	public void setBootCause(long bootCause) {
		this.bootCause = bootCause;
	}

	public short getBgndRssi() {
		return bgndRssi;
	}

	public void setBgndRssi(short bgndRssi) {
		this.bgndRssi = bgndRssi;
	}

	public int getActiveConf() {
		return activeConf;
	}

	public void setActiveConf(int activeConf) {
		this.activeConf = activeConf;
	}

	public int getBootCount() {
		return bootCount;
	}

	public void setBootCount(int bootCount) {
		this.bootCount = bootCount;
	}

	public long getLastContact() {
		return lastContact;
	}

	public void setLastContact(long lastContact) {
		this.lastContact = lastContact;
	}

	public int getTxDuty() {
		return txDuty;
	}

	public void setTxDuty(int txDuty) {
		this.txDuty = txDuty;
	}

}
