package ru.r2cloud.jradio.at03;

import java.io.DataInputStream;
import java.io.IOException;

public class StacieBeacon {

	private int usp;
	private byte trxTemp;
	private int idleRSSI;
	private int rxRSSI;
	private boolean antennaDep;
	private StacieOperationalMode mode;
	private boolean tCompOn;
	private int resetCounter;
	private int uplinkError;
	private int obcSendPacketcounter;
	private int beaconInterval;
	private byte sid;
	private int txSelReason;
	private int reasonRemote;
	private long sTime;
	private int beaconCount;

	public StacieBeacon() {
		// do nothing
	}

	public StacieBeacon(DataInputStream dis) throws IOException {
		usp = dis.readUnsignedByte() | (dis.readUnsignedByte() << 8);
		trxTemp = dis.readByte();
		idleRSSI = dis.readByte();
		rxRSSI = dis.readByte();
		antennaDep = dis.readBoolean();
		mode = StacieOperationalMode.valueOfCode(dis.readUnsignedByte());
		tCompOn = dis.readBoolean();
		resetCounter = dis.readUnsignedByte() | (dis.readUnsignedByte() << 8);
		uplinkError = dis.readUnsignedByte();
		obcSendPacketcounter = dis.readUnsignedByte();
		beaconInterval = dis.readUnsignedByte() | (dis.readUnsignedByte() << 8);
		dis.skipBytes(8);
		sid = dis.readByte();
		txSelReason = dis.readUnsignedByte();
		reasonRemote = dis.readUnsignedByte();
		sTime = dis.readUnsignedByte() | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte() << 16) | ((long) dis.readUnsignedByte() << 24);
		dis.skipBytes(1);
		beaconCount = dis.readUnsignedByte();
	}

	public int getUsp() {
		return usp;
	}

	public void setUsp(int usp) {
		this.usp = usp;
	}

	public byte getTrxTemp() {
		return trxTemp;
	}

	public void setTrxTemp(byte trxTemp) {
		this.trxTemp = trxTemp;
	}

	public int getIdleRSSI() {
		return idleRSSI;
	}

	public void setIdleRSSI(int idleRSSI) {
		this.idleRSSI = idleRSSI;
	}

	public int getRxRSSI() {
		return rxRSSI;
	}

	public void setRxRSSI(int rxRSSI) {
		this.rxRSSI = rxRSSI;
	}

	public boolean isAntennaDep() {
		return antennaDep;
	}

	public void setAntennaDep(boolean antennaDep) {
		this.antennaDep = antennaDep;
	}

	public StacieOperationalMode getMode() {
		return mode;
	}

	public void setMode(StacieOperationalMode mode) {
		this.mode = mode;
	}

	public boolean istCompOn() {
		return tCompOn;
	}

	public void settCompOn(boolean tCompOn) {
		this.tCompOn = tCompOn;
	}

	public int getResetCounter() {
		return resetCounter;
	}

	public void setResetCounter(int resetCounter) {
		this.resetCounter = resetCounter;
	}

	public int getUplinkError() {
		return uplinkError;
	}

	public void setUplinkError(int uplinkError) {
		this.uplinkError = uplinkError;
	}

	public int getObcSendPacketcounter() {
		return obcSendPacketcounter;
	}

	public void setObcSendPacketcounter(int obcSendPacketcounter) {
		this.obcSendPacketcounter = obcSendPacketcounter;
	}

	public int getBeaconInterval() {
		return beaconInterval;
	}

	public void setBeaconInterval(int beaconInterval) {
		this.beaconInterval = beaconInterval;
	}

	public byte getSid() {
		return sid;
	}

	public void setSid(byte sid) {
		this.sid = sid;
	}

	public int getTxSelReason() {
		return txSelReason;
	}

	public void setTxSelReason(int txSelReason) {
		this.txSelReason = txSelReason;
	}

	public int getReasonRemote() {
		return reasonRemote;
	}

	public void setReasonRemote(int reasonRemote) {
		this.reasonRemote = reasonRemote;
	}

	public long getsTime() {
		return sTime;
	}

	public void setsTime(long sTime) {
		this.sTime = sTime;
	}

	public int getBeaconCount() {
		return beaconCount;
	}

	public void setBeaconCount(int beaconCount) {
		this.beaconCount = beaconCount;
	}

}
