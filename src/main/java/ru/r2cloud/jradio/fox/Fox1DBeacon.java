package ru.r2cloud.jradio.fox;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LsbBitInputStream;

public class Fox1DBeacon extends Beacon {

	private static final String FOX1D_TABLE_PREFIX = "FOX1D";
	private static final int MAX_HERCI_PAYLOADS = 5;

	private FoxHeader header;
	private Payload1BRealtime payloadRealtime;
	private Payload1BMaxValues payloadMax;
	private Payload1BMinValues payloadMin;
	private PayloadRadExpData payloadRadExp;
	private List<PictureScanLine> pictureScanLines;
	private List<HerciPayload> herciPayloads;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		LsbBitInputStream dis = new LsbBitInputStream(new ByteArrayInputStream(data));
		header = new FoxHeader(dis);
		if (header.getFoxId() != 4) {
			throw new UncorrectableException("invalid fox id: " + header.getFoxId());
		}
		if( data.length > 64 ) {
			readHighSpeedData(dis);
		} else {
			readSlowSpeedData(dis);
		}
	}

	private void readHighSpeedData(LsbBitInputStream dis) throws IOException {
		payloadRealtime = new Payload1BRealtime(dis, FOX1D_TABLE_PREFIX, true);
		payloadMax = new Payload1BMaxValues(dis, FOX1D_TABLE_PREFIX, true);
		payloadMin = new Payload1BMinValues(dis, FOX1D_TABLE_PREFIX, true);
		int lineCount = dis.readBitsAsInt(8);
		if (lineCount != 0) {
			pictureScanLines = new ArrayList<>(lineCount);
			for (int i = 0; i < lineCount; i++) {
				pictureScanLines.add(new PictureScanLine(dis));
			}
		} else {
			int herciLineCount = dis.readBitsAsInt(8);
			if (herciLineCount > 0 && herciLineCount <= MAX_HERCI_PAYLOADS) {
				herciPayloads = new ArrayList<>(herciLineCount);
				for (int i = 0; i < herciLineCount; i++) {
					herciPayloads.add(new HerciPayload(dis));
				}
			}
		}
	}
	
	private void readSlowSpeedData(LsbBitInputStream dis) throws IOException, UncorrectableException {
		switch (header.getType()) {
		case 1:
			payloadRealtime = new Payload1BRealtime(dis, FOX1D_TABLE_PREFIX, true);
			break;
		case 2:
			payloadMax = new Payload1BMaxValues(dis, FOX1D_TABLE_PREFIX, true);
			break;
		case 3:
			payloadMin = new Payload1BMinValues(dis, FOX1D_TABLE_PREFIX, true);
			break;
		case 4:
			payloadRadExp = new PayloadRadExpData(dis);
			break;
		default:
			throw new UncorrectableException("unknown type: " + header.getType());
		}
	}

	public FoxHeader getHeader() {
		return header;
	}

	public void setHeader(FoxHeader header) {
		this.header = header;
	}

	public Payload1BRealtime getPayloadRealtime() {
		return payloadRealtime;
	}

	public void setPayloadRealtime(Payload1BRealtime payloadRealtime) {
		this.payloadRealtime = payloadRealtime;
	}

	public Payload1BMaxValues getPayloadMax() {
		return payloadMax;
	}

	public void setPayloadMax(Payload1BMaxValues payloadMax) {
		this.payloadMax = payloadMax;
	}

	public Payload1BMinValues getPayloadMin() {
		return payloadMin;
	}

	public void setPayloadMin(Payload1BMinValues payloadMin) {
		this.payloadMin = payloadMin;
	}

	public PayloadRadExpData getPayloadRadExp() {
		return payloadRadExp;
	}

	public void setPayloadRadExp(PayloadRadExpData payloadRadExp) {
		this.payloadRadExp = payloadRadExp;
	}

	public List<PictureScanLine> getPictureScanLines() {
		return pictureScanLines;
	}

	public void setPictureScanLines(List<PictureScanLine> pictureScanLines) {
		this.pictureScanLines = pictureScanLines;
	}

	public List<HerciPayload> getHerciPayloads() {
		return herciPayloads;
	}

	public void setHerciPayloads(List<HerciPayload> herciPayloads) {
		this.herciPayloads = herciPayloads;
	}

}
