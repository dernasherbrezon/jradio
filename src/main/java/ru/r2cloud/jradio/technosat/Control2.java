package ru.r2cloud.jradio.technosat;

public class Control2 {

	private boolean baud9600;
	private boolean ack;
	private byte subaddress;
	private byte address;

	public boolean isBaud9600() {
		return baud9600;
	}

	public void setBaud9600(boolean baud9600) {
		this.baud9600 = baud9600;
	}

	public boolean isAck() {
		return ack;
	}

	public void setAck(boolean ack) {
		this.ack = ack;
	}

	public byte getSubaddress() {
		return subaddress;
	}

	public void setSubaddress(byte subaddress) {
		this.subaddress = subaddress;
	}

	public byte getAddress() {
		return address;
	}

	public void setAddress(byte address) {
		this.address = address;
	}

}
