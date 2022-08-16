package ru.r2cloud.jradio.delfic3;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LsbBitInputStream;

public class DelfiC3Beacon extends Ax25Beacon {

	private int bootCounter;
	private int sequenceCounter;
	private Payload payloadData;
	private Housekeeping housekeeping;
	private String auxiliaryData;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		LsbBitInputStream bis = new LsbBitInputStream(dis);
		bootCounter = bis.readBitsAsInt(12);
		bis.readBitsAsInt(4);
		sequenceCounter = bis.readBitsAsInt(16);
		int type = bis.readBitsAsInt(2);
		switch (type) {
		case 1:
			payloadData = new Payload(bis);
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

	public Payload getPayloadData() {
		return payloadData;
	}
	
	public void setPayloadData(Payload payloadData) {
		this.payloadData = payloadData;
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
