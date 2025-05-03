package ru.r2cloud.jradio.lrpt;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ccsds.PacketPrimaryHeader;
import ru.r2cloud.jradio.util.BitInputStream;
import ru.r2cloud.jradio.util.StreamUtils;

public class Packet {

	private static final int SECONDARY_HEADER_LENGTH = 8;

	private PacketPrimaryHeader primaryHeader;
	private int numberOfDays;
	private long millisecondOfDay;
	private int microsecondOfMillisecond;

	private byte[] userData;

	public Packet() {
		// do nothing
	}

	public Packet(DataInputStream dis) throws IOException {
		primaryHeader = new PacketPrimaryHeader(new BitInputStream(dis));
		int userDataLength = primaryHeader.getPacketDataLength() + 1;
		if (primaryHeader.isSecondaryHeader()) {
			numberOfDays = dis.readUnsignedShort();
			millisecondOfDay = StreamUtils.readUnsignedInt(dis);
			microsecondOfMillisecond = dis.readUnsignedShort();
			userDataLength -= SECONDARY_HEADER_LENGTH;
		}
		if (dis.available() >= userDataLength) {
			userData = new byte[userDataLength];
			dis.readFully(userData);
		}
	}

	public PacketPrimaryHeader getPrimaryHeader() {
		return primaryHeader;
	}

	public void setPrimaryHeader(PacketPrimaryHeader primaryHeader) {
		this.primaryHeader = primaryHeader;
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

	public byte[] getUserData() {
		return userData;
	}

	public void setUserData(byte[] userData) {
		this.userData = userData;
	}

}
