package ru.r2cloud.jradio.gomx1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.csp.Header;
import ru.r2cloud.jradio.util.StreamUtils;

public class Gomx1Beacon extends Beacon {

	private Header header;
	private long beaconTime;
	private int flags;

	private TypeA typeA;
	private TypeB typeB;

	@Override
	public void readBeacon(byte[] data) throws IOException {
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
