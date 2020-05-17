package ru.r2cloud.jradio.huskysat1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class WodCanTelemetry {

	private boolean transponder;
	private boolean i2cFailureAntenna1;
	private boolean i2cFailureAntenna2;
	private boolean i2cFailureRF;

	private boolean overflow;
	private byte[] data;
	private int WODTimestampReset;
	private int WODTimestampUptime;
	private boolean crcError;

	public WodCanTelemetry() {
		// do nothing
	}

	public WodCanTelemetry(LsbBitInputStream dis) throws IOException {
		transponder = dis.readBit();
		i2cFailureAntenna1 = dis.readBit();
		i2cFailureAntenna2 = dis.readBit();
		i2cFailureRF = dis.readBit();
		dis.readBit();
		dis.readBit();
		dis.readBit();
		overflow = dis.readBit();
		data = new byte[71];
		dis.readFully(data);
		WODTimestampReset = dis.readBitsAsInt(16);
		WODTimestampUptime = dis.readBitsAsInt(25);
		crcError = dis.readBit();
		dis.readBitsAsInt(6);
	}

	public boolean isTransponder() {
		return transponder;
	}

	public void setTransponder(boolean transponder) {
		this.transponder = transponder;
	}

	public boolean isI2cFailureAntenna1() {
		return i2cFailureAntenna1;
	}

	public void setI2cFailureAntenna1(boolean i2cFailureAntenna1) {
		this.i2cFailureAntenna1 = i2cFailureAntenna1;
	}

	public boolean isI2cFailureAntenna2() {
		return i2cFailureAntenna2;
	}

	public void setI2cFailureAntenna2(boolean i2cFailureAntenna2) {
		this.i2cFailureAntenna2 = i2cFailureAntenna2;
	}

	public boolean isI2cFailureRF() {
		return i2cFailureRF;
	}

	public void setI2cFailureRF(boolean i2cFailureRF) {
		this.i2cFailureRF = i2cFailureRF;
	}

	public boolean isOverflow() {
		return overflow;
	}

	public void setOverflow(boolean overflow) {
		this.overflow = overflow;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public int getWODTimestampReset() {
		return WODTimestampReset;
	}

	public void setWODTimestampReset(int wODTimestampReset) {
		WODTimestampReset = wODTimestampReset;
	}

	public int getWODTimestampUptime() {
		return WODTimestampUptime;
	}

	public void setWODTimestampUptime(int wODTimestampUptime) {
		WODTimestampUptime = wODTimestampUptime;
	}

	public boolean isCrcError() {
		return crcError;
	}

	public void setCrcError(boolean crcError) {
		this.crcError = crcError;
	}

}
