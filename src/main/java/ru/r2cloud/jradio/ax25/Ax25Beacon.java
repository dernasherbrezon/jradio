package ru.r2cloud.jradio.ax25;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Ax25Beacon extends Beacon {

	private Header header;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		readBeacon(dis);
	}

	@SuppressWarnings("unused")
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		// do nothing
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	@Override
	public String toString() {
		if (header == null) {
			return "null";
		} else {
			return header.toString();
		}
	}
}
