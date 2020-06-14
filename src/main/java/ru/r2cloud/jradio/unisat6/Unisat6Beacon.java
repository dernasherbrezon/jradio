package ru.r2cloud.jradio.unisat6;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Unisat6Beacon extends Beacon {

	private static final byte[] SYN_PACKET = "US6".getBytes(StandardCharsets.ISO_8859_1);

	private Header header;
	private int packetIndex;
	private int groundIndexAck; // ACK to ground commands
	private int packetType;
	private Beacon2 beacon2;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		byte[] synPacket = new byte[3];
		dis.readFully(synPacket);
		if (!Arrays.equals(synPacket, SYN_PACKET)) {
			throw new UncorrectableException("invalid syn packet");
		}
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		packetIndex = ldis.readUnsignedShort();
		groundIndexAck = ldis.readUnsignedShort();
		packetType = ldis.readUnsignedByte();
		if (packetType == 1) {
			beacon2 = new Beacon2(ldis);
		} else {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public int getPacketIndex() {
		return packetIndex;
	}

	public void setPacketIndex(int packetIndex) {
		this.packetIndex = packetIndex;
	}

	public int getGroundIndexAck() {
		return groundIndexAck;
	}

	public void setGroundIndexAck(int groundIndexAck) {
		this.groundIndexAck = groundIndexAck;
	}

	public int getPacketType() {
		return packetType;
	}

	public void setPacketType(int packetType) {
		this.packetType = packetType;
	}

	public Beacon2 getBeacon2() {
		return beacon2;
	}

	public void setBeacon2(Beacon2 beacon2) {
		this.beacon2 = beacon2;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
