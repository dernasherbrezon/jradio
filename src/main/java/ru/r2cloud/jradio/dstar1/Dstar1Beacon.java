package ru.r2cloud.jradio.dstar1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.tubix20.CMX909bBeacon;
import ru.r2cloud.jradio.tubix20.CMX909bHeader;
import ru.r2cloud.jradio.tubix20.MobitexRandomizer;

public class Dstar1Beacon extends Beacon {

	private CMX909bHeader header;
	private PayloadData payload;

	@Override
	public void readBeacon(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		try {
			header = new CMX909bHeader(dis);
		} catch (UncorrectableException e) {
			throw new IOException(e);
		}
		MobitexRandomizer randomizer = new MobitexRandomizer();
		byte[] dataFromBlocks;
		try {
			dataFromBlocks = CMX909bBeacon.readDataBlocks(header, randomizer, dis);
		} catch (UncorrectableException e) {
			throw new IOException(e);
		}
		if (dataFromBlocks != null) {
			payload = new PayloadData(dataFromBlocks);
		}
	}

	public PayloadData getPayload() {
		return payload;
	}

	public void setPayload(PayloadData payload) {
		this.payload = payload;
	}

	public CMX909bHeader getHeader() {
		return header;
	}

	public void setHeader(CMX909bHeader header) {
		this.header = header;
	}

}
