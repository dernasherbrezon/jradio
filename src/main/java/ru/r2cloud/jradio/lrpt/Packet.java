package ru.r2cloud.jradio.lrpt;

public class Packet {

	private byte version = -1;
	private int apid;
	private boolean secondaryHeader;
	private byte sequence;
	private int sequenceCount;
	private int length;
	private int numberOfDays = -1;
	private long millisecondOfDay;
	private int microsecondOfMillisecond;
	private byte[] userData;

	public byte[] getUserData() {
		return userData;
	}

	public void setUserData(byte[] userData) {
		this.userData = userData;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public long getMillisecondOfDay() {
		return millisecondOfDay;
	}

	public void setMillisecondOfDay(long millisecondOfDay) {
		this.millisecondOfDay = millisecondOfDay;
	}

	public int getMicrosecondOfMillisecond() {
		return microsecondOfMillisecond;
	}

	public void setMicrosecondOfMillisecond(int microsecondOfMillisecond) {
		this.microsecondOfMillisecond = microsecondOfMillisecond;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getSequenceCount() {
		return sequenceCount;
	}

	public void setSequenceCount(int sequenceCount) {
		this.sequenceCount = sequenceCount;
	}

	public byte getSequence() {
		return sequence;
	}

	public void setSequence(byte sequence) {
		this.sequence = sequence;
	}

	public boolean isSecondaryHeader() {
		return secondaryHeader;
	}

	public void setSecondaryHeader(boolean secondaryHeader) {
		this.secondaryHeader = secondaryHeader;
	}

	public int getApid() {
		return apid;
	}

	public void setApid(int apid) {
		this.apid = apid;
	}

	public byte getVersion() {
		return version;
	}

	public void setVersion(byte version) {
		this.version = version;
	}

}
