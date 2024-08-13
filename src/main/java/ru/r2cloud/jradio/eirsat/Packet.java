package ru.r2cloud.jradio.eirsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ccsds.PacketPrimaryHeader;
import ru.r2cloud.jradio.ccsds.PacketSecondaryHeader;
import ru.r2cloud.jradio.crc.Crc16CcittFalse;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.BitInputStream;

public class Packet {

	private PacketPrimaryHeader header;
	private PacketSecondaryHeader secondaryHeader;
	private Housekeeping housekeeping;
	private byte[] payload;

	public Packet() {
		// do nothing
	}

	public Packet(DataInputStream dis) throws IOException, UncorrectableException {
		// partial packet. keep payload
		if (PacketPrimaryHeader.LENGTH_BYTES > dis.available()) {
			this.payload = new byte[dis.available()];
			dis.readFully(this.payload);
			return;
		}
		byte[] headerBytes = new byte[PacketPrimaryHeader.LENGTH_BYTES];
		dis.readFully(headerBytes);
		BitInputStream bis = new BitInputStream(headerBytes);
		header = new PacketPrimaryHeader(bis);

		if (header.getPacketDataLength() < 1) {
			throw new UncorrectableException("invalid header length");
		}

		// partial packet. keep payload
		if ((header.getPacketDataLength() - 1 + 2) > dis.available()) {
			this.payload = new byte[dis.available()];
			dis.readFully(this.payload);
			return;
		}

		byte[] payload = new byte[header.getPacketDataLength() - 1];
		dis.readFully(payload);

		byte[] fullMessage = new byte[headerBytes.length + payload.length];
		System.arraycopy(headerBytes, 0, fullMessage, 0, headerBytes.length);
		System.arraycopy(payload, 0, fullMessage, headerBytes.length, payload.length);

		int expected = dis.readUnsignedShort();
		int actual = Crc16CcittFalse.calculate(fullMessage, 0, fullMessage.length);
		if (expected != actual) {
			throw new UncorrectableException("crc mismatch");
		}

		bis = new BitInputStream(payload);

		if (header.isSecondaryHeader()) {
			secondaryHeader = new PacketSecondaryHeader(bis, header.getPacketType() == 0);
		}
		if (secondaryHeader != null && secondaryHeader.getServiceType() == 0x3 && secondaryHeader.getServiceSubType() == 0x19) {
			housekeeping = new Housekeeping(bis);
		} else {
			this.payload = payload;
		}
	}

	public PacketPrimaryHeader getHeader() {
		return header;
	}

	public void setHeader(PacketPrimaryHeader header) {
		this.header = header;
	}

	public PacketSecondaryHeader getSecondaryHeader() {
		return secondaryHeader;
	}

	public void setSecondaryHeader(PacketSecondaryHeader secondaryHeader) {
		this.secondaryHeader = secondaryHeader;
	}

	public Housekeeping getHousekeeping() {
		return housekeeping;
	}

	public void setHousekeeping(Housekeeping housekeeping) {
		this.housekeeping = housekeeping;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

}
