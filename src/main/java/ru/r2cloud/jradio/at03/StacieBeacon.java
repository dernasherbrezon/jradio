package ru.r2cloud.jradio.at03;

import java.io.DataInputStream;
import java.io.IOException;

public class StacieBeacon {

	private int USP;
	private byte TRXTemp;
	private int IdleRSSI;
	private int RXRSSI;
	private boolean AntennaDep;
	private StacieOperationalMode mode;
	private boolean TCompOn;
	private int ResetCounter;
	private int UplinkError;
	private int OBCSendPacketcounter;
	private int BeaconInterval;
	private byte SID;
	private int TxSelReason;
	private int reasonRemote;
	private long sTime;
	private int BeaconCount;

	public StacieBeacon(DataInputStream dis) throws IOException {
		USP = dis.readUnsignedByte() | (dis.readUnsignedByte() << 8);
		TRXTemp = dis.readByte();
		IdleRSSI = dis.readByte();
		RXRSSI = dis.readByte();
		AntennaDep = dis.readBoolean();
		mode = StacieOperationalMode.valueOfCode(dis.readUnsignedByte());
		TCompOn = dis.readBoolean();
		ResetCounter = dis.readUnsignedByte() | (dis.readUnsignedByte() << 8);
		UplinkError = dis.readUnsignedByte();
		OBCSendPacketcounter = dis.readUnsignedByte();
		BeaconInterval = dis.readUnsignedByte() | (dis.readUnsignedByte() << 8);
		dis.skipBytes(8);
		SID = dis.readByte();
		TxSelReason = dis.readUnsignedByte();
		reasonRemote = dis.readUnsignedByte();
		sTime = dis.readUnsignedByte() | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte() << 16) | ((long)dis.readUnsignedByte() << 24);
		dis.skipBytes(1);
		BeaconCount = dis.readUnsignedByte();
	}

	public int getUSP() {
		return USP;
	}

	public void setUSP(int uSP) {
		USP = uSP;
	}

	public byte getTRXTemp() {
		return TRXTemp;
	}

	public void setTRXTemp(byte tRXTemp) {
		TRXTemp = tRXTemp;
	}

	public int getIdleRSSI() {
		return IdleRSSI;
	}

	public void setIdleRSSI(int idleRSSI) {
		IdleRSSI = idleRSSI;
	}

	public int getRXRSSI() {
		return RXRSSI;
	}

	public void setRXRSSI(int rXRSSI) {
		RXRSSI = rXRSSI;
	}

	public boolean isAntennaDep() {
		return AntennaDep;
	}

	public void setAntennaDep(boolean antennaDep) {
		AntennaDep = antennaDep;
	}

	public StacieOperationalMode getMode() {
		return mode;
	}

	public void setMode(StacieOperationalMode mode) {
		this.mode = mode;
	}

	public boolean isTCompOn() {
		return TCompOn;
	}

	public void setTCompOn(boolean tCompOn) {
		TCompOn = tCompOn;
	}

	public int getResetCounter() {
		return ResetCounter;
	}

	public void setResetCounter(int resetCounter) {
		ResetCounter = resetCounter;
	}

	public int getUplinkError() {
		return UplinkError;
	}

	public void setUplinkError(int uplinkError) {
		UplinkError = uplinkError;
	}

	public int getOBCSendPacketcounter() {
		return OBCSendPacketcounter;
	}

	public void setOBCSendPacketcounter(int oBCSendPacketcounter) {
		OBCSendPacketcounter = oBCSendPacketcounter;
	}

	public int getBeaconInterval() {
		return BeaconInterval;
	}

	public void setBeaconInterval(int beaconInterval) {
		BeaconInterval = beaconInterval;
	}

	public byte getSID() {
		return SID;
	}

	public void setSID(byte sID) {
		SID = sID;
	}

	public int getTxSelReason() {
		return TxSelReason;
	}

	public void setTxSelReason(int txSelReason) {
		TxSelReason = txSelReason;
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
		return BeaconCount;
	}

	public void setBeaconCount(int beaconCount) {
		BeaconCount = beaconCount;
	}

}
