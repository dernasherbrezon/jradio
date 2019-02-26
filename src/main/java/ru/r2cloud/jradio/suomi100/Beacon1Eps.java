package ru.r2cloud.jradio.suomi100;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Beacon1Eps {

	private long timestamp;
	private long wdtI2c;
	private long wdtGnd;
	private long bootCount;
	private long wdtI2cCount;
	private long wdtGndCount;
	private long[] wdtCspCount;
	private int[] wdtCsp;
	private int bootCause;
	private int[] latchup;
	private int[] outVal;
	private int pptMode;
	
	public Beacon1Eps() {
		//do nothing
	}

	public Beacon1Eps(DataInputStream dis) throws IOException {
		timestamp = StreamUtils.readUnsignedInt(dis);
		wdtI2c = StreamUtils.readUnsignedInt(dis);
		wdtGnd = StreamUtils.readUnsignedInt(dis);
		bootCount = StreamUtils.readUnsignedInt(dis);
		wdtI2cCount = StreamUtils.readUnsignedInt(dis);
		wdtGndCount = StreamUtils.readUnsignedInt(dis);
		wdtCspCount = new long[2];
		wdtCspCount[0] = StreamUtils.readUnsignedInt(dis);
		wdtCspCount[1] = StreamUtils.readUnsignedInt(dis);
		wdtCsp = new int[2];
		wdtCsp[0] = dis.readUnsignedByte();
		wdtCsp[1] = dis.readUnsignedByte();
		bootCause = dis.readUnsignedByte();
		latchup = new int[6];
		for (int i = 0; i < latchup.length; i++) {
			latchup[i] = dis.readUnsignedShort();
		}
		outVal = new int[8];
		for (int i = 0; i < outVal.length; i++) {
			outVal[i] = dis.readUnsignedByte();
		}
		pptMode = dis.readUnsignedByte();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getWdtI2c() {
		return wdtI2c;
	}

	public void setWdtI2c(long wdtI2c) {
		this.wdtI2c = wdtI2c;
	}

	public long getWdtGnd() {
		return wdtGnd;
	}

	public void setWdtGnd(long wdtGnd) {
		this.wdtGnd = wdtGnd;
	}

	public long getBootCount() {
		return bootCount;
	}

	public void setBootCount(long bootCount) {
		this.bootCount = bootCount;
	}

	public long getWdtI2cCount() {
		return wdtI2cCount;
	}

	public void setWdtI2cCount(long wdtI2cCount) {
		this.wdtI2cCount = wdtI2cCount;
	}

	public long getWdtGndCount() {
		return wdtGndCount;
	}

	public void setWdtGndCount(long wdtGndCount) {
		this.wdtGndCount = wdtGndCount;
	}

	public long[] getWdtCspCount() {
		return wdtCspCount;
	}

	public void setWdtCspCount(long[] wdtCspCount) {
		this.wdtCspCount = wdtCspCount;
	}

	public int[] getWdtCsp() {
		return wdtCsp;
	}

	public void setWdtCsp(int[] wdtCsp) {
		this.wdtCsp = wdtCsp;
	}

	public int getBootCause() {
		return bootCause;
	}

	public void setBootCause(int bootCause) {
		this.bootCause = bootCause;
	}

	public int[] getLatchup() {
		return latchup;
	}

	public void setLatchup(int[] latchup) {
		this.latchup = latchup;
	}

	public int[] getOutVal() {
		return outVal;
	}

	public void setOutVal(int[] outVal) {
		this.outVal = outVal;
	}

	public int getPptMode() {
		return pptMode;
	}

	public void setPptMode(int pptMode) {
		this.pptMode = pptMode;
	}

}
