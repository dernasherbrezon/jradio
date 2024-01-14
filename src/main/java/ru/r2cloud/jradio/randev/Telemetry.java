package ru.r2cloud.jradio.randev;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry {

	private int systemLeading;
	private long systemTime;
	private int systemMode;
	private int systemWodMode;
	private int systemWodCounter;
	private Obc obc;
	private Eps eps;
	private CommRx commRx;
	private CommTx commTx;
	private CommAntenna commAntenna;
	private Adcs adcs;
	private Hstx hstx;

	public Telemetry() {
		// do nothing
	}

	public Telemetry(LittleEndianDataInputStream dis) throws IOException {
		systemLeading = dis.readUnsignedByte();
		systemTime = dis.readUnsignedInt();
		systemMode = dis.readUnsignedByte();
		systemWodMode = dis.readUnsignedByte();
		systemWodCounter = dis.readUnsignedShort();
		obc = new Obc(dis);
		eps = new Eps(dis);
		commRx = new CommRx(dis);
		commTx = new CommTx(dis);
		commAntenna = new CommAntenna(dis);
		adcs = new Adcs(dis);
		hstx = new Hstx(dis);
	}

	public int getSystemLeading() {
		return systemLeading;
	}

	public void setSystemLeading(int systemLeading) {
		this.systemLeading = systemLeading;
	}

	public long getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(long systemTime) {
		this.systemTime = systemTime;
	}

	public int getSystemMode() {
		return systemMode;
	}

	public void setSystemMode(int systemMode) {
		this.systemMode = systemMode;
	}

	public int getSystemWodMode() {
		return systemWodMode;
	}

	public void setSystemWodMode(int systemWodMode) {
		this.systemWodMode = systemWodMode;
	}

	public int getSystemWodCounter() {
		return systemWodCounter;
	}

	public void setSystemWodCounter(int systemWodCounter) {
		this.systemWodCounter = systemWodCounter;
	}

	public Obc getObc() {
		return obc;
	}

	public void setObc(Obc obc) {
		this.obc = obc;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public CommRx getCommRx() {
		return commRx;
	}

	public void setCommRx(CommRx commRx) {
		this.commRx = commRx;
	}

	public CommTx getCommTx() {
		return commTx;
	}

	public void setCommTx(CommTx commTx) {
		this.commTx = commTx;
	}

	public CommAntenna getCommAntenna() {
		return commAntenna;
	}

	public void setCommAntenna(CommAntenna commAntenna) {
		this.commAntenna = commAntenna;
	}

	public Adcs getAdcs() {
		return adcs;
	}

	public void setAdcs(Adcs adcs) {
		this.adcs = adcs;
	}

	public Hstx getHstx() {
		return hstx;
	}

	public void setHstx(Hstx hstx) {
		this.hstx = hstx;
	}

}
