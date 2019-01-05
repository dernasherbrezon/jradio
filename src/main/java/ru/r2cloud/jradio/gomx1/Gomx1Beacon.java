package ru.r2cloud.jradio.gomx1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Externalizable;
import ru.r2cloud.jradio.csp.Header;
import ru.r2cloud.jradio.util.StreamUtils;

public class Gomx1Beacon implements Externalizable {

	private Header header;
	private long beaconTime;
	private int flags;

	private TypeA typeA;
	private TypeB typeB;

	private long beginSample;
	private long beginMillis;
	private byte[] rawData;

	@Override
	public void readExternal(byte[] data) throws IOException {
		this.rawData = data;
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		beaconTime = StreamUtils.readUnsignedInt(dis);
		flags = dis.readUnsignedByte();
		switch (data.length) {
		case 216:
			typeA = new TypeA(dis);
			break;
		case 214:
			typeB = new TypeB(dis);
			break;
		default:
			throw new IOException("unknown beacon type: " + data.length);
		}
	}

	public long getBeginSample() {
		return beginSample;
	}

	public void setBeginSample(long beginSample) {
		this.beginSample = beginSample;
	}

	public long getBeginMillis() {
		return beginMillis;
	}

	public void setBeginMillis(long beginMillis) {
		this.beginMillis = beginMillis;
	}

	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
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

	public long getBeaconTime() {
		return beaconTime;
	}

	public void setBeaconTime(long beaconTime) {
		this.beaconTime = beaconTime;
	}

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public TypeB getTypeB() {
		return typeB;
	}

	public void setTypeB(TypeB typeB) {
		this.typeB = typeB;
	}
}
