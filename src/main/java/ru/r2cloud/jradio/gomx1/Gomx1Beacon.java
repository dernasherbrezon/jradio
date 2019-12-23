package ru.r2cloud.jradio.gomx1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.csp.Header;

public class Gomx1Beacon extends Beacon {

	private Header header;

	private TypeA typeA;
	private TypeB typeB;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		switch (data.length) {
		case 216:
			typeA = new TypeA(dis);
			break;
		case 214:
			typeB = new TypeB(dis);
			break;
		default:
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

	public TypeA getTypeA() {
		return typeA;
	}

	public void setTypeA(TypeA typeA) {
		this.typeA = typeA;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public TypeB getTypeB() {
		return typeB;
	}

	public void setTypeB(TypeB typeB) {
		this.typeB = typeB;
	}
}
