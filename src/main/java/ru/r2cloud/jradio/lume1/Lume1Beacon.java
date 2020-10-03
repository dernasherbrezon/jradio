package ru.r2cloud.jradio.lume1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ccsds.TmTransferFrame;
import ru.r2cloud.jradio.csp.Header;
import ru.r2cloud.jradio.util.BitInputStream;

public class Lume1Beacon extends Beacon {

	private Header header;
	private TmTransferFrame transferFrame;

	private int id;
	private B1Obc b1obc;
	private B2Eps b2eps;
	private B3TtcGssb b3TtcGssb;
	private B4Adcs b4Adcs;
	private B5Temps b5Temps;

	@Override
	public void readBeacon(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		transferFrame = new TmTransferFrame(dis);
		BitInputStream bis = new BitInputStream(transferFrame.getPayload());
		id = bis.readUnsignedInt(16);
		switch (id) {
		case 1:
			b1obc = new B1Obc(bis);
			break;
		case 2:
			b2eps = new B2Eps(bis);
			break;
		case 3:
			b3TtcGssb = new B3TtcGssb(bis);
			break;
		case 4:
			b4Adcs = new B4Adcs(bis);
			break;
		case 5:
			b5Temps = new B5Temps(bis);
			break;
		default:
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

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
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
}
