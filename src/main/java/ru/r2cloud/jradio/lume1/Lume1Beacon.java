package ru.r2cloud.jradio.lume1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ccsds.TmTransferFrame;
import ru.r2cloud.jradio.csp.CspBeacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Lume1Beacon extends CspBeacon {

	private TmTransferFrame transferFrame;

	private int id;
	private B1Obc b1obc;
	private B2Eps b2eps;
	private B3TtcGssb b3TtcGssb;
	private B4Adcs b4Adcs;
	private B5Temps b5Temps;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		transferFrame = new TmTransferFrame(dis);
		if (transferFrame.getSecondaryHeader() == null || transferFrame.getSecondaryHeader().getServiceType() != 0x3 || transferFrame.getSecondaryHeader().getMessageSubtype() != 0x19) {
			return;
		}
		dis = new DataInputStream(new ByteArrayInputStream(transferFrame.getPayload()));
		id = dis.readUnsignedShort();
		switch (id) {
		case 1:
			b1obc = new B1Obc(dis);
			break;
		case 2:
			b2eps = new B2Eps(dis);
			break;
		case 3:
			b3TtcGssb = new B3TtcGssb(dis);
			break;
		case 4:
			b4Adcs = new B4Adcs(dis);
			break;
		case 5:
			b5Temps = new B5Temps(dis);
			break;
		default:
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
			break;
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public B2Eps getB2eps() {
		return b2eps;
	}

	public void setB2eps(B2Eps b2eps) {
		this.b2eps = b2eps;
	}

	public B3TtcGssb getB3TtcGssb() {
		return b3TtcGssb;
	}

	public void setB3TtcGssb(B3TtcGssb b3TtcGssb) {
		this.b3TtcGssb = b3TtcGssb;
	}

	public B4Adcs getB4Adcs() {
		return b4Adcs;
	}

	public void setB4Adcs(B4Adcs b4Adcs) {
		this.b4Adcs = b4Adcs;
	}

	public B5Temps getB5Temps() {
		return b5Temps;
	}

	public void setB5Temps(B5Temps b5Temps) {
		this.b5Temps = b5Temps;
	}

	public TmTransferFrame getTransferFrame() {
		return transferFrame;
	}

	public void setTransferFrame(TmTransferFrame transferFrame) {
		this.transferFrame = transferFrame;
	}

	public B1Obc getB1obc() {
		return b1obc;
	}

	public void setB1obc(B1Obc b1obc) {
		this.b1obc = b1obc;
	}
	
	public byte[] getUnknownPayload() {
		return unknownPayload;
	}
	
	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}
}
