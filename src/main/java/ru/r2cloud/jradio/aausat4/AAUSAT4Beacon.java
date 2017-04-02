package ru.r2cloud.jradio.aausat4;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.csp.Packet;

public class AAUSAT4Beacon extends Packet {

	private EPS eps;
	private COM com;
	private ADCS1 adcs1;
	private ADCS2 adcs2;
	private AIS ais1;
	private AIS ais2;

	// # [ 1 byte | 20 bytes | 10 bytes | 7 bytes | 6 bytes | 20 bytes | 20
	// bytes ]
	// # [ Valid | EPS | COM | ADCS1 | ADCS2 | AIS1 | AIS2 ]
	@Override
	public void readExternal(byte[] rawData) throws IOException {
		super.readExternal(rawData);
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(getData()));
		byte valid = data.readByte();
		boolean eps_valid = (valid & (1 << 0)) > 0 ? true : false;
		boolean com_valid = (valid & (1 << 1)) > 0 ? true : false;
		boolean adcs1_valid = (valid & (1 << 2)) > 0 ? true : false;
		boolean adcs2_valid = (valid & (1 << 3)) > 0 ? true : false;
		boolean ais1_valid = (valid & (1 << 4)) > 0 ? true : false;
		boolean ais2_valid = (valid & (1 << 5)) > 0 ? true : false;
		
		if (eps_valid) {
			eps = new EPS(data);
		} else {
			data.skipBytes(20);
		}
		if (com_valid) {
			com = new COM(data);
		} else {
			data.skipBytes(10);
		}
		if (adcs1_valid) {
			adcs1 = new ADCS1(data);
		} else {
			data.skipBytes(7);
		}
		if (adcs2_valid) {
			adcs2 = new ADCS2(data);
		} else {
			data.skipBytes(6);
		}
		if (ais1_valid) {
			ais1 = new AIS(data);
		} else {
			data.skipBytes(20);
		}
		if (ais2_valid) {
			ais2 = new AIS(data);
		} else {
			data.skipBytes(20);
		}
	}

	public EPS getEps() {
		return eps;
	}

	public void setEps(EPS eps) {
		this.eps = eps;
	}

	public COM getCom() {
		return com;
	}

	public void setCom(COM com) {
		this.com = com;
	}

	public ADCS1 getAdcs1() {
		return adcs1;
	}

	public void setAdcs1(ADCS1 adcs1) {
		this.adcs1 = adcs1;
	}

	public ADCS2 getAdcs2() {
		return adcs2;
	}

	public void setAdcs2(ADCS2 adcs2) {
		this.adcs2 = adcs2;
	}

	public AIS getAis1() {
		return ais1;
	}

	public void setAis1(AIS ais1) {
		this.ais1 = ais1;
	}

	public AIS getAis2() {
		return ais2;
	}

	public void setAis2(AIS ais2) {
		this.ais2 = ais2;
	}

}
