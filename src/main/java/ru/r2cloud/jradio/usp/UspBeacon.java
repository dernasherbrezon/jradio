package ru.r2cloud.jradio.usp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class UspBeacon extends Beacon {

	private int etherType;
	private Header header;
	private Integer ax25Length;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		etherType = dis.readUnsignedShort();
		if (etherType == 0x08FF) {
			int lengthByte1 = dis.readUnsignedByte();
			int lengthByte2 = dis.readUnsignedByte();
			// little-endian length
			ax25Length = (lengthByte2 << 8) + lengthByte1;
			header = new Header(dis);
		}
		readBeacon(etherType, dis);
	}

	@SuppressWarnings("unused")
	public void readBeacon(int etherType, DataInputStream dis) throws IOException, UncorrectableException {
		// do nothing
	}

	public Integer getAx25Length() {
		return ax25Length;
	}

	public void setAx25Length(Integer ax25Length) {
		this.ax25Length = ax25Length;
	}

	public int getEtherType() {
		return etherType;
	}

	public void setEtherType(int etherType) {
		this.etherType = etherType;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

}
