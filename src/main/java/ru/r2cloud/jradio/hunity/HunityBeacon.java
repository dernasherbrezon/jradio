package ru.r2cloud.jradio.hunity;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class HunityBeacon extends Beacon {

	private int srcAddress;
	private int dstAddress;
	private Obc1 obc1;
	private Obc2 obc2;
	private Obc3 obc3;
	private Obc4 obc4;
	private Obc5 obc5;
	private Com1 com1;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		ldis.skipBytes(7);
		int payloadLength = ldis.readUnsignedShort();
		ldis.skipBytes(2);
		srcAddress = ldis.readUnsignedByte();
		ldis.skipBytes(2);
		dstAddress = ldis.readUnsignedByte();
		ldis.skipBytes(4);
		int dataLength = payloadLength - 10;
		boolean unknown = false;
		if (srcAddress == 4 || srcAddress == 5) {
			switch (dstAddress) {
			case 64:
				obc1 = new Obc1(ldis);
				break;
			case 65:
				obc2 = new Obc2(ldis);
				break;
			case 66:
				obc3 = new Obc3(ldis);
				break;
			case 67:
				obc4 = new Obc4(ldis);
				break;
			case 68:
				obc5 = new Obc5(ldis);
				break;
			default:
				unknown = true;
				break;
			}
		} else if ((srcAddress == 2 || srcAddress == 3) && dstAddress == 40) {
			com1 = new Com1(ldis);
		} else {
			unknown = true;
		}

		if (unknown) {
			unknownPayload = new byte[dataLength];
			ldis.readFully(unknownPayload);
		}
	}

	public int getSrcAddress() {
		return srcAddress;
	}

	public void setSrcAddress(int srcAddress) {
		this.srcAddress = srcAddress;
	}

	public int getDstAddress() {
		return dstAddress;
	}

	public void setDstAddress(int dstAddress) {
		this.dstAddress = dstAddress;
	}

	public Obc1 getObc1() {
		return obc1;
	}

	public void setObc1(Obc1 obc1) {
		this.obc1 = obc1;
	}

	public Obc2 getObc2() {
		return obc2;
	}

	public void setObc2(Obc2 obc2) {
		this.obc2 = obc2;
	}

	public Obc3 getObc3() {
		return obc3;
	}

	public void setObc3(Obc3 obc3) {
		this.obc3 = obc3;
	}

	public Obc4 getObc4() {
		return obc4;
	}

	public void setObc4(Obc4 obc4) {
		this.obc4 = obc4;
	}

	public Obc5 getObc5() {
		return obc5;
	}

	public void setObc5(Obc5 obc5) {
		this.obc5 = obc5;
	}

	public Com1 getCom1() {
		return com1;
	}

	public void setCom1(Com1 com1) {
		this.com1 = com1;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
