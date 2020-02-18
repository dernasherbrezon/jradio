package ru.r2cloud.jradio.dstar1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.tubix20.CMX909bBeacon;
import ru.r2cloud.jradio.tubix20.CMX909bHeader;
import ru.r2cloud.jradio.tubix20.MobitexRandomizer;
import ru.r2cloud.jradio.util.GapData;
import ru.r2cloud.jradio.util.GapDataInputStream;

public class Dstar1Beacon extends Beacon {

	// always 6
	private static final int NUMBER_OF_BLOCKS = 6;
	private CMX909bHeader header;
	private PayloadData payload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new CMX909bHeader(dis);
		MobitexRandomizer randomizer = new MobitexRandomizer();
		GapData gapData = CMX909bBeacon.readGapDataBlocks(NUMBER_OF_BLOCKS, randomizer, dis);
		payload = new PayloadData(new GapDataInputStream(gapData));
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
