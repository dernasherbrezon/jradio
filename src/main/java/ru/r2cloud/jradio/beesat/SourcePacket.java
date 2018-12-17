package ru.r2cloud.jradio.beesat;

import java.io.DataInputStream;
import java.io.IOException;

public class SourcePacket {

	private int length;
	private byte virtualChannelIdentifier;
	private byte recordingTimeFraction;
	private int recordingTimeSeconds; // since 01.01.2000
	private int nodeOfOrigin;
	private int sourcePacketIdentifier;

	public void readExternal(DataInputStream dis) throws IOException {
		length = dis.readUnsignedShort();
		int raw = dis.readUnsignedByte();
		virtualChannelIdentifier = (byte) (raw >> 4);
		recordingTimeFraction = (byte) (raw & 0xF);
		recordingTimeSeconds = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		nodeOfOrigin = dis.readUnsignedByte();
		sourcePacketIdentifier = dis.readUnsignedByte();
		byte[] userData = new byte[length];
		dis.readFully(userData);
		
		//validate crc?
		int crc = dis.readUnsignedShort();
	}

}
