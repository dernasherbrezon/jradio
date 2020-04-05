package ru.r2cloud.jradio.qarman;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.BitInputStream;

public class QarmanBeacon extends Beacon {

	private static final int LONG_FRAME_SIZE = 74;
	private static final int SHORT_FRAME_SIZE = 39;

	private Header header;
	private ShortFrame shortFrame;
	private LongFrame longFrame;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		if (!header.getSourceAddress().getCallsign().equals("ON05BE")) {
			throw new UncorrectableException("invalid source: " + header.getSourceAddress().getCallsign());
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

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
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
