package ru.r2cloud.jradio;

public class Ax25G3ruhBerStatHeader {

	private float ebno;
	private double fskBer;
	private double noHeader;
	private double header;
	private double syncword;
	
	public double getSyncword() {
		return syncword;
	}
	
	public void setSyncword(double syncword) {
		this.syncword = syncword;
	}

	public float getEbno() {
		return ebno;
	}

	public void setEbno(float ebno) {
		this.ebno = ebno;
	}

	public double getFskBer() {
		return fskBer;
	}

	public void setFskBer(double fskBer) {
		this.fskBer = fskBer;
	}

	public double getNoHeader() {
		return noHeader;
	}

	public void setNoHeader(double noHeader) {
		this.noHeader = noHeader;
	}

	public double getHeader() {
		return header;
	}

	public void setHeader(double header) {
		this.header = header;
	}

}
