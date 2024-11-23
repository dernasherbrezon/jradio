package ru.r2cloud.jradio.openlst;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class OpenLstBeacon extends Beacon {

	private int flags;
	private int seqnum;
	private int hwid;

	private byte[] payload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		flags = dis.readUnsignedByte();
		seqnum = dis.readUnsignedShort();
		readBeacon(dis);
		hwid = dis.readUnsignedShort();
	}

	@SuppressWarnings("unused")
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		payload = new byte[dis.available() - 2];
		dis.readFully(payload);
	}

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public int getSeqnum() {
		return seqnum;
	}

	public void setSeqnum(int seqnum) {
		this.seqnum = seqnum;
	}

	public int getHwid() {
		return hwid;
	}

	public void setHwid(int hwid) {
		this.hwid = hwid;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

}
