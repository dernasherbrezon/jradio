package ru.r2cloud.jradio.mobitex;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.tubix20.CMX909bBeacon;
import ru.r2cloud.jradio.tubix20.MobitexRandomizer;
import ru.r2cloud.jradio.util.GapData;

public class MobitexBeacon extends Beacon {

	public static final int MAX_SIZE = 3 + 32 * 30;
	private Header header;
	private byte[] payload;

	private final Integer forceNumberOfBlocks;

	public MobitexBeacon() {
		forceNumberOfBlocks = null;
	}

	public MobitexBeacon(int forceNumberOfBlocks) {
		this.forceNumberOfBlocks = forceNumberOfBlocks;
	}

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		int numberOfBlocks;
		if (forceNumberOfBlocks != null) {
			numberOfBlocks = forceNumberOfBlocks;
		} else {
			numberOfBlocks = header.getControl1().getNumberOfBlocks();
		}
		readBeacon(CMX909bBeacon.readGapDataBlocks(numberOfBlocks, new MobitexRandomizer(), dis));
	}

	@SuppressWarnings("unused")
	public void readBeacon(GapData data) throws IOException, UncorrectableException {
		// by default do not accept gapped data
		if (data.getNonEmptyBlocks() != header.getControl1().getNumberOfBlocks()) {
			throw new UncorrectableException("not all blocks recovered");
		}
		int total = 0;
		for (int cur : data.getLengths()) {
			total += cur;
		}
		payload = new byte[total];
		int totalWritten = 0;
		for (byte[] cur : data.getChunks()) {
			System.arraycopy(cur, 0, payload, totalWritten, cur.length);
			totalWritten += cur.length;
		}
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

}
