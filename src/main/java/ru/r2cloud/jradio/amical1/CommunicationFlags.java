package ru.r2cloud.jradio.amical1;

public class CommunicationFlags {

	private boolean fec;
	private boolean downlinkOn;
	private boolean bandLock;
	private boolean xor;
	private boolean aes128;
	private boolean amplifierOverTemperature;

	public CommunicationFlags() {
		// do nothing
	}

	public CommunicationFlags(int value) {
		fec = (value & 0x1) > 0;
		downlinkOn = ((value >> 1) & 0x1) > 0;
		bandLock = ((value >> 2) & 0x1) > 0;
		xor = ((value >> 3) & 0x1) > 0;
		aes128 = ((value >> 4) & 0x1) > 0;
		amplifierOverTemperature = ((value >> 5) & 0x1) > 0;
	}

	public boolean isFec() {
		return fec;
	}

	public void setFec(boolean fec) {
		this.fec = fec;
	}

	public boolean isDownlinkOn() {
		return downlinkOn;
	}

	public void setDownlinkOn(boolean downlinkOn) {
		this.downlinkOn = downlinkOn;
	}

	public boolean isBandLock() {
		return bandLock;
	}

	public void setBandLock(boolean bandLock) {
		this.bandLock = bandLock;
	}

	public boolean isXor() {
		return xor;
	}

	public void setXor(boolean xor) {
		this.xor = xor;
	}

	public boolean isAes128() {
		return aes128;
	}

	public void setAes128(boolean aes128) {
		this.aes128 = aes128;
	}

	public boolean isAmplifierOverTemperature() {
		return amplifierOverTemperature;
	}

	public void setAmplifierOverTemperature(boolean amplifierOverTemperature) {
		this.amplifierOverTemperature = amplifierOverTemperature;
	}

}
