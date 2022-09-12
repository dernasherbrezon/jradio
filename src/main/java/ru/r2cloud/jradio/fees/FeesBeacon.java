package ru.r2cloud.jradio.fees;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class FeesBeacon extends Beacon {

	private FeesHeader header;
	private Tmi254 tmi254;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new FeesHeader(dis);
		if (header.getMsgTypeId1() == 254) {
			tmi254 = new Tmi254(dis);
		} else {
			unknownPayload = new byte[dis.available() - 3];
			dis.readFully(unknownPayload);
		}
	}

	public FeesHeader getHeader() {
		return header;
	}

	public void setHeader(FeesHeader header) {
		this.header = header;
	}

	public Tmi254 getTmi254() {
		return tmi254;
	}

	public void setTmi254(Tmi254 tmi254) {
		this.tmi254 = tmi254;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
