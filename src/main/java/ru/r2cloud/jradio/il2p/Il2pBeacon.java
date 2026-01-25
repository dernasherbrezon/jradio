package ru.r2cloud.jradio.il2p;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Il2pBeacon extends Beacon {

	private Il2pHeader header;
	private byte[] payload;

	public Il2pBeacon() {
		// do nothing
	}

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Il2pHeader(dis);
		try {
			readBeacon(dis);
		} catch (EOFException e) {
			payload = new byte[dis.available()];
			dis.readFully(payload);
		}
	}

	@SuppressWarnings("unused")
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		payload = new byte[dis.available()];
		dis.readFully(payload);
	}

	public Il2pHeader getHeader() {
		return header;
	}

	public void setHeader(Il2pHeader header) {
		this.header = header;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

}
