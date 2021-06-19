package ru.r2cloud.jradio.grifex;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class MxlHeader {

	public static final int LENGTH_BYTES = 11;

	private short sync;
	private int primaryId;
	private int secondaryId;
	private int flags;
	private int packetLength;
	private int headerChecksum;

	public MxlHeader() {
		// do nothing
	}

	public MxlHeader(DataInputStream dis) throws IOException, UncorrectableException {
		if (dis.available() < LENGTH_BYTES) {
			throw new UncorrectableException("not enough bytes in the input");
		}
		sync = dis.readShort();
		primaryId = dis.readUnsignedShort();
		secondaryId = dis.readUnsignedShort();
		flags = dis.readUnsignedByte();
		packetLength = dis.readUnsignedByte() | (dis.readUnsignedByte() << 8);
		headerChecksum = dis.readUnsignedShort();
	}

	public short getSync() {
		return sync;
	}

	public void setSync(short sync) {
		this.sync = sync;
	}

	public int getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(int primaryId) {
		this.primaryId = primaryId;
	}

	public int getSecondaryId() {
		return secondaryId;
	}

	public void setSecondaryId(int secondaryId) {
		this.secondaryId = secondaryId;
	}

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public int getPacketLength() {
		return packetLength;
	}

	public void setPacketLength(int packetLength) {
		this.packetLength = packetLength;
	}

	public int getHeaderChecksum() {
		return headerChecksum;
	}

	public void setHeaderChecksum(int headerChecksum) {
		this.headerChecksum = headerChecksum;
	}

}
