package ru.r2cloud.jradio.delfic3;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class RdBlock {

	private int rdXpV;
	private int rdXmV;
	private int rdXpI;
	private int rdXmI;
	private int rdYmV;
	private int rdYpV;
	private int rdYmI;
	private int rdYpI;

	public RdBlock() {
		// do nothing
	}

	public RdBlock(LsbBitInputStream bis) throws IOException {
		rdXpV = bis.readBitsAsInt(8) * 2;
		rdXmV = bis.readBitsAsInt(8) * 2;
		rdXpI = bis.readBitsAsInt(8) * 4;
		rdXmI = bis.readBitsAsInt(8) * 4;
		rdYmV = bis.readBitsAsInt(8) * 2;
		rdYpV = bis.readBitsAsInt(8) * 2;
		rdYmI = bis.readBitsAsInt(8) * 4;
		rdYpI = bis.readBitsAsInt(8) * 4;
	}

	public int getRdXpV() {
		return rdXpV;
	}

	public void setRdXpV(int rdXpV) {
		this.rdXpV = rdXpV;
	}

	public int getRdXmV() {
		return rdXmV;
	}

	public void setRdXmV(int rdXmV) {
		this.rdXmV = rdXmV;
	}

	public int getRdXpI() {
		return rdXpI;
	}

	public void setRdXpI(int rdXpI) {
		this.rdXpI = rdXpI;
	}

	public int getRdXmI() {
		return rdXmI;
	}

	public void setRdXmI(int rdXmI) {
		this.rdXmI = rdXmI;
	}

	public int getRdYmV() {
		return rdYmV;
	}

	public void setRdYmV(int rdYmV) {
		this.rdYmV = rdYmV;
	}

	public int getRdYpV() {
		return rdYpV;
	}

	public void setRdYpV(int rdYpV) {
		this.rdYpV = rdYpV;
	}

	public int getRdYmI() {
		return rdYmI;
	}

	public void setRdYmI(int rdYmI) {
		this.rdYmI = rdYmI;
	}

	public int getRdYpI() {
		return rdYpI;
	}

	public void setRdYpI(int rdYpI) {
		this.rdYpI = rdYpI;
	}

}
