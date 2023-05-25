package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.ccsds.PacketPrimaryHeader;
import ru.r2cloud.jradio.cute.SecondaryHeader;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.BitInputStream;

public class CirbeBeacon extends Ax25Beacon {

	private PacketPrimaryHeader primaryHeader;
	private SecondaryHeader secondary;
	private BctSoh bctSoh;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		BitInputStream bis = new BitInputStream(dis);
		primaryHeader = new PacketPrimaryHeader(bis);
		if (primaryHeader.isSecondaryHeader()) {
			secondary = new SecondaryHeader(dis);
		}
		if ((primaryHeader.getApplicationProcessId() & 0xFF) == 0x050) {
			bctSoh = new BctSoh(dis);
		} else {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public PacketPrimaryHeader getPrimaryHeader() {
		return primaryHeader;
	}

	public void setPrimaryHeader(PacketPrimaryHeader primaryHeader) {
		this.primaryHeader = primaryHeader;
	}

	public SecondaryHeader getSecondary() {
		return secondary;
	}

	public void setSecondary(SecondaryHeader secondary) {
		this.secondary = secondary;
	}

	public BctSoh getBctSoh() {
		return bctSoh;
	}

	public void setBctSoh(BctSoh bctSoh) {
		this.bctSoh = bctSoh;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
