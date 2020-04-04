package ru.r2cloud.jradio.delfic3;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LsbBitInputStream;

public class DelfiC3Beacon extends Beacon {

	private Header header;
	private int bootCounter;
	private int sequenceCounter;
	private Payload payload;
	private Housekeeping housekeeping;
	private String auxiliaryData;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);

		LsbBitInputStream bis = new LsbBitInputStream(dis);
		bootCounter = bis.readBitsAsInt(12);
		bis.readBitsAsInt(4);
		sequenceCounter = bis.readBitsAsInt(16);
		int type = bis.readBitsAsInt(2);
		switch (type) {
		case 1:
			payload = new Payload(bis);
			break;
		case 2:
			housekeeping = new Housekeeping(bis);
			break;
		case 3:
			bis.readBitsAsInt(6);
			auxiliaryData = bis.readBitsAsString(bis.available());
			break;
		default:
			throw new UncorrectableException("unknown type: " + type);
		}
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public int getBootCounter() {
		return bootCounter;
	}

	public void setBootCounter(int bootCounter) {
		this.bootCounter = bootCounter;
	}

	public int getSequenceCounter() {
		return sequenceCounter;
	}

	public void setSequenceCounter(int sequenceCounter) {
		this.sequenceCounter = sequenceCounter;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public Housekeeping getHousekeeping() {
		return housekeeping;
	}

	public void setHousekeeping(Housekeeping housekeeping) {
		this.housekeeping = housekeeping;
	}

	public String getAuxiliaryData() {
		return auxiliaryData;
	}
	
	public void setAuxiliaryData(String auxiliaryData) {
		this.auxiliaryData = auxiliaryData;
	}

}
