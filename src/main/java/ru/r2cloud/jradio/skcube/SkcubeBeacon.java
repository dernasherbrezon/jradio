package ru.r2cloud.jradio.skcube;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SkcubeBeacon extends Ax25Beacon {

	private Cdhs cdhs;
	private Adcs adcs;
	private Com com;
	private Pwr pwr;

	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		int type = dis.readUnsignedByte();
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		switch (type) {
		case 0x01:
			cdhs = new Cdhs(ldis);
			break;
		case 0x02:
			adcs = new Adcs(ldis);
			break;
		case 0x03:
			com = new Com(ldis);
			break;
		case 0x05:
			pwr = new Pwr(ldis);
			break;
		default:
			unknownPayload = new byte[ldis.available()];
			ldis.readFully(unknownPayload);
			break;
		}
	}

	public Cdhs getCdhs() {
		return cdhs;
	}

	public void setCdhs(Cdhs cdhs) {
		this.cdhs = cdhs;
	}

	public Adcs getAdcs() {
		return adcs;
	}

	public void setAdcs(Adcs adcs) {
		this.adcs = adcs;
	}

	public Com getCom() {
		return com;
	}

	public void setCom(Com com) {
		this.com = com;
	}

	public Pwr getPwr() {
		return pwr;
	}

	public void setPwr(Pwr pwr) {
		this.pwr = pwr;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}
	
}
