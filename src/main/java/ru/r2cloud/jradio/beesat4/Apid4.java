package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Apid4 {

	private int tmmrrp; // tm memory read pointer
	private int tmmrwp; // tm memory write pointer
	private long obcswv; // obc software revision
	private int apb4gt; // b4 apid gathering rate
	private int apacgt; // acs apid gathering rate
	private int apepgt; // eps tcs obc apid gathering rate
	private int apaegt; // acs ext apid gathering rate
	private int apdegt; // debug apid gathering rate
	private int appdgt; // pdh apid gathering rate
	private int apgpgt; // gps apid gathering rate
	private int apicgt; // image catalog apid gathering rate
	private int apctgt; // pdh gps catalog apid gathering rate
	private long cstutc; // onboard time utc
	private long cstsys; // obdh uptime
	private long pcsyst; // pcu uptime
	private int cctccp; // ctr change param
	private int ccsuin; // ctr incoming sw upload tc
	private int ccsuhd; // ctr handled sw upload tc
	private int ccesuc; // err ctr check image
	private int ccesul; // err ctr load block
	private int ccesue; // err ctr erase block
	private int ccesuk; // err ctr erase sector
	private int ccesum; // err ctr crc upload message
	private int ccesus; // err ctr save upload message
	private int apaccgt; // acs calibration apid gathering rate

	public Apid4(DataInputStream dis) throws IOException {
		BitInputStream bis = new BitInputStream(dis);

		tmmrrp = bis.readUnsignedInt(16);
		tmmrwp = bis.readUnsignedInt(16);
		obcswv = bis.readUnsignedLong(32);
		apb4gt = bis.readUnsignedInt(16);
		apacgt = bis.readUnsignedInt(16);
		apepgt = bis.readUnsignedInt(16);
		apaegt = bis.readUnsignedInt(16);
		apdegt = bis.readUnsignedInt(16);
		appdgt = bis.readUnsignedInt(16);
		apgpgt = bis.readUnsignedInt(16);
		apicgt = bis.readUnsignedInt(16);
		apctgt = bis.readUnsignedInt(16);
		cstutc = bis.readUnsignedLong(32);
		cstsys = bis.readUnsignedLong(32);
		pcsyst = bis.readUnsignedLong(32);
		cctccp = bis.readUnsignedInt(8);
		ccsuin = bis.readUnsignedInt(12);
		ccsuhd = bis.readUnsignedInt(12);
		ccesuc = bis.readUnsignedInt(12);
		ccesul = bis.readUnsignedInt(12);
		ccesue = bis.readUnsignedInt(12);
		ccesuk = bis.readUnsignedInt(12);
		ccesum = bis.readUnsignedInt(12);
		ccesus = bis.readUnsignedInt(12);
		apacgt = bis.readUnsignedInt(16);
		dis.skipBytes(73);
	}

	public int getTmmrrp() {
		return tmmrrp;
	}

	public void setTmmrrp(int tmmrrp) {
		this.tmmrrp = tmmrrp;
	}

	public int getTmmrwp() {
		return tmmrwp;
	}

	public void setTmmrwp(int tmmrwp) {
		this.tmmrwp = tmmrwp;
	}

	public long getObcswv() {
		return obcswv;
	}

	public void setObcswv(long obcswv) {
		this.obcswv = obcswv;
	}

	public int getApb4gt() {
		return apb4gt;
	}

	public void setApb4gt(int apb4gt) {
		this.apb4gt = apb4gt;
	}

	public int getApacgt() {
		return apacgt;
	}

	public void setApacgt(int apacgt) {
		this.apacgt = apacgt;
	}

	public int getApepgt() {
		return apepgt;
	}

	public void setApepgt(int apepgt) {
		this.apepgt = apepgt;
	}

	public int getApaegt() {
		return apaegt;
	}

	public void setApaegt(int apaegt) {
		this.apaegt = apaegt;
	}

	public int getApdegt() {
		return apdegt;
	}

	public void setApdegt(int apdegt) {
		this.apdegt = apdegt;
	}

	public int getAppdgt() {
		return appdgt;
	}

	public void setAppdgt(int appdgt) {
		this.appdgt = appdgt;
	}

	public int getApgpgt() {
		return apgpgt;
	}

	public void setApgpgt(int apgpgt) {
		this.apgpgt = apgpgt;
	}

	public int getApicgt() {
		return apicgt;
	}

	public void setApicgt(int apicgt) {
		this.apicgt = apicgt;
	}

	public int getApctgt() {
		return apctgt;
	}

	public void setApctgt(int apctgt) {
		this.apctgt = apctgt;
	}

	public long getCstutc() {
		return cstutc;
	}

	public void setCstutc(long cstutc) {
		this.cstutc = cstutc;
	}

	public long getCstsys() {
		return cstsys;
	}

	public void setCstsys(long cstsys) {
		this.cstsys = cstsys;
	}

	public long getPcsyst() {
		return pcsyst;
	}

	public void setPcsyst(long pcsyst) {
		this.pcsyst = pcsyst;
	}

	public int getCctccp() {
		return cctccp;
	}

	public void setCctccp(int cctccp) {
		this.cctccp = cctccp;
	}

	public int getCcsuin() {
		return ccsuin;
	}

	public void setCcsuin(int ccsuin) {
		this.ccsuin = ccsuin;
	}

	public int getCcsuhd() {
		return ccsuhd;
	}

	public void setCcsuhd(int ccsuhd) {
		this.ccsuhd = ccsuhd;
	}

	public int getCcesuc() {
		return ccesuc;
	}

	public void setCcesuc(int ccesuc) {
		this.ccesuc = ccesuc;
	}

	public int getCcesul() {
		return ccesul;
	}

	public void setCcesul(int ccesul) {
		this.ccesul = ccesul;
	}

	public int getCcesue() {
		return ccesue;
	}

	public void setCcesue(int ccesue) {
		this.ccesue = ccesue;
	}

	public int getCcesuk() {
		return ccesuk;
	}

	public void setCcesuk(int ccesuk) {
		this.ccesuk = ccesuk;
	}

	public int getCcesum() {
		return ccesum;
	}

	public void setCcesum(int ccesum) {
		this.ccesum = ccesum;
	}

	public int getCcesus() {
		return ccesus;
	}

	public void setCcesus(int ccesus) {
		this.ccesus = ccesus;
	}

	public int getApaccgt() {
		return apaccgt;
	}

	public void setApaccgt(int apaccgt) {
		this.apaccgt = apaccgt;
	}

}
