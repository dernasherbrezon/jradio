package ru.r2cloud.jradio.usp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class UspBeacon extends Beacon {

	private Header header;
	private byte[] payload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		if (data[0] == (byte) 0x08 && data[1] == (byte) 0xFF) {
			// discard external USP beacon fields
			int ax25Length = ((data[3] & 0xFF) << 8) + (data[2] & 0xFF);
			if (ax25Length <= data.length - 4) {
				byte[] ax25Frame = new byte[ax25Length];
				System.arraycopy(data, 4, ax25Frame, 0, ax25Frame.length);
				data = ax25Frame;
			}
		}
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		try {
			readBeacon(dis);
		} catch (EOFException e) {
			payload = new byte[data.length - Header.LENGTH_BYTES];
			System.arraycopy(data, Header.LENGTH_BYTES, payload, 0, payload.length);
		}

	}

	@SuppressWarnings("unused")
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		payload = new byte[dis.available()];
		dis.readFully(payload);
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

}
