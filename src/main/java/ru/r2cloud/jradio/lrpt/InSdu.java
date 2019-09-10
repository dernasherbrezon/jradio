package ru.r2cloud.jradio.lrpt;

public class InSdu {

	private boolean encryption;
	private byte keyNumber;

	public boolean isEncryption() {
		return encryption;
	}

	public void setEncryption(boolean encryption) {
		this.encryption = encryption;
	}

	public byte getKeyNumber() {
		return keyNumber;
	}

	public void setKeyNumber(byte keyNumber) {
		this.keyNumber = keyNumber;
	}

	@Override
	public String toString() {
		return "IN_SDU [encryption=" + encryption + ", keyNumber=" + keyNumber + "]";
	}

}
