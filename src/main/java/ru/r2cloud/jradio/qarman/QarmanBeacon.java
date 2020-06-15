package ru.r2cloud.jradio.qarman;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.BitInputStream;

public class QarmanBeacon extends Ax25Beacon {

	private static final int LONG_FRAME_SIZE = 74;
	private static final int SHORT_FRAME_SIZE = 39;

	private ShortFrame shortFrame;
	private LongFrame longFrame;
	private byte[] unknownPayload;
	
	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		if (!getHeader().getSourceAddress().getCallsign().equals("ON05BE")) {
			throw new UncorrectableException("invalid source: " + getHeader().getSourceAddress().getCallsign());
		}
		BitInputStream bis = new BitInputStream(dis);
		if (dis.available() == SHORT_FRAME_SIZE) {
			shortFrame = new ShortFrame(bis);
		} else if (dis.available() == LONG_FRAME_SIZE) {
			longFrame = new LongFrame(bis);
		} else {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public ShortFrame getShortFrame() {
		return shortFrame;
	}

	public void setShortFrame(ShortFrame shortFrame) {
		this.shortFrame = shortFrame;
	}

	public LongFrame getLongFrame() {
		return longFrame;
	}

	public void setLongFrame(LongFrame longFrame) {
		this.longFrame = longFrame;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
