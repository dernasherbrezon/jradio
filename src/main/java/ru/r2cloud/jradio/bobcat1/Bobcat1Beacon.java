package ru.r2cloud.jradio.bobcat1;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.csp.CspBeacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Bobcat1Beacon extends CspBeacon {

	private LeopFrame leopFrame;
	private LongFrame longFrame;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		switch (dis.available()) {
		case 57:
			leopFrame = new LeopFrame(dis);
			break;
		case 144:
			longFrame = new LongFrame(dis);
			break;
		default:
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
			break;
		}
	}

	public LeopFrame getLeopFrame() {
		return leopFrame;
	}

	public void setLeopFrame(LeopFrame leopFrame) {
		this.leopFrame = leopFrame;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

	public LongFrame getLongFrame() {
		return longFrame;
	}

	public void setLongFrame(LongFrame longFrame) {
		this.longFrame = longFrame;
	}
}
