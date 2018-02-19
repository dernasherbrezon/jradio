package ru.r2cloud.jradio.lrpt;

public class M_PDU {

	private byte spareBits;
	private int headerFirstPointer;
	
	public byte getSpareBits() {
		return spareBits;
	}
	
	public void setSpareBits(byte spareBits) {
		this.spareBits = spareBits;
	}
	
	public int getHeaderFirstPointer() {
		return headerFirstPointer;
	}
	
	public void setHeaderFirstPointer(int headerFirstPointer) {
		this.headerFirstPointer = headerFirstPointer;
	}

	@Override
	public String toString() {
		return "M_PDU [spareBits=" + spareBits + ", headerFirstPointer=" + headerFirstPointer + "]";
	}
	
}
