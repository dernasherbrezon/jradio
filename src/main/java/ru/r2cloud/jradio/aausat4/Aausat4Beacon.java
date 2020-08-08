package ru.r2cloud.jradio.aausat4;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.csp.Header;

public class Aausat4Beacon extends Beacon {

	private int length;
	private Header header;
	private byte[] hmac;
	private Eps eps;
	private Com com;
	private Adcs1 adcs1;
	private Adcs2 adcs2;
	private Ais ais1;
	private Ais ais2;

	// # [ 1 byte | 20 bytes | 10 bytes | 7 bytes | 6 bytes | 20 bytes | 20
	// bytes ]
	// # [ Valid | EPS | COM | ADCS1 | ADCS2 | AIS1 | AIS2 ]
	@Override
	public void readBeacon(byte[] rawData) throws IOException {
		length = ((rawData[0] & 0xFF) << 8) | (rawData[1] & 0xFF);
		header = new Header(Arrays.copyOfRange(rawData, 2, 2 + Header.LENGTH));
		int endIndex = rawData.length;
		if (header.isFhmac()) {
			if (rawData.length < 6) {
				throw new IOException("invalid csp header size: " + rawData.length);
			}
			hmac = Arrays.copyOfRange(rawData, endIndex - 2, endIndex);
			endIndex = endIndex - 2;
		}
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(Arrays.copyOfRange(rawData, 2 + Header.LENGTH, endIndex)));
		int valid = data.readUnsignedByte();
		boolean epsValid = (valid & (1 << 0)) > 0;
		boolean comValid = (valid & (1 << 1)) > 0;
		boolean adcs1Valid = (valid & (1 << 2)) > 0;
		boolean adcs2Valid = (valid & (1 << 3)) > 0;
		boolean ais1Valid = (valid & (1 << 4)) > 0;
		boolean ais2Valid = (valid & (1 << 5)) > 0;

		if (epsValid) {
			eps = new Eps(data);
		} else {
			data.skipBytes(20);
		}
		if (comValid) {
			com = new Com(data);
		} else {
			data.skipBytes(10);
		}
		if (adcs1Valid) {
			adcs1 = new Adcs1(data);
		} else {
			data.skipBytes(7);
		}
		if (adcs2Valid) {
			adcs2 = new Adcs2(data);
		} else {
			data.skipBytes(6);
		}
		if (ais1Valid) {
			ais1 = new Ais(data);
		} else {
			data.skipBytes(20);
		}
		if (ais2Valid) {
			ais2 = new Ais(data);
		} else {
			data.skipBytes(20);
		}
	}

	public byte[] getHmac() {
		return hmac;
	}

	public Header getHeader() {
		return header;
	}

	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public Com getCom() {
		return com;
	}

	public void setCom(Com com) {
		this.com = com;
	}

	public Adcs1 getAdcs1() {
		return adcs1;
	}

	public void setAdcs1(Adcs1 adcs1) {
		this.adcs1 = adcs1;
	}

	public Adcs2 getAdcs2() {
		return adcs2;
	}

	public void setAdcs2(Adcs2 adcs2) {
		this.adcs2 = adcs2;
	}

	public Ais getAis1() {
		return ais1;
	}

	public void setAis1(Ais ais1) {
		this.ais1 = ais1;
	}

	public Ais getAis2() {
		return ais2;
	}

	public void setAis2(Ais ais2) {
		this.ais2 = ais2;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public void setHmac(byte[] hmac) {
		this.hmac = hmac;
	}

}
