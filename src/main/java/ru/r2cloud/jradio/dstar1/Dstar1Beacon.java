package ru.r2cloud.jradio.dstar1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.tubix20.CMX909bBeacon;
import ru.r2cloud.jradio.tubix20.CMX909bHeader;
import ru.r2cloud.jradio.tubix20.MobitexRandomizer;

public class Dstar1Beacon {

	private CMX909bHeader header;
	private PayloadData payload;

	private long beginSample;
	private long beginMillis;
	private byte[] rawData;

	public void readExternal(byte[] data) throws IOException, UncorrectableException {
		this.rawData = data;
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new CMX909bHeader(dis);
		MobitexRandomizer randomizer = new MobitexRandomizer();
		byte[] dataFromBlocks = CMX909bBeacon.readDataBlocks(header, randomizer, dis);
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

}
