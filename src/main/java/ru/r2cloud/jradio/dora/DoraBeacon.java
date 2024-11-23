package ru.r2cloud.jradio.dora;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ccsds.PacketPrimaryHeader;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.openlst.OpenLstBeacon;
import ru.r2cloud.jradio.util.BitInputStream;

public class DoraBeacon extends OpenLstBeacon {

	private String callsign;
	private PacketPrimaryHeader header;
	private FirstPacket first;
	private SecondPacket second;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		dis.skipBytes(1); // system_byte
		byte[] callsignBytes = new byte[6];
		dis.readFully(callsignBytes);
		callsign = new String(callsignBytes);
		dis.skipBytes(4); // sync bytes
		header = new PacketPrimaryHeader(new BitInputStream(dis));
		if (header.getSequenceFlag() == 1) {
			first = new FirstPacket(dis);
		} else if (header.getSequenceFlag() == 2) {
			second = new SecondPacket(dis);
		} else {
			unknownPayload = new byte[dis.available() - 2];
			dis.readFully(unknownPayload);
		}
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public PacketPrimaryHeader getHeader() {
		return header;
	}

	public void setHeader(PacketPrimaryHeader header) {
		this.header = header;
	}

	public FirstPacket getFirst() {
		return first;
	}

	public void setFirst(FirstPacket first) {
		this.first = first;
	}

	public SecondPacket getSecond() {
		return second;
	}

	public void setSecond(SecondPacket second) {
		this.second = second;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
