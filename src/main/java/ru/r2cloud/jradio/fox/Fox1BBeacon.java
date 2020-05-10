package ru.r2cloud.jradio.fox;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LsbBitInputStream;

public class Fox1BBeacon extends Beacon {

	private FoxHeader header;
	private Payload1BRealtime payloadRealtime;
	private Payload1BMaxValues payloadMax;
	private Payload1BMinValues payloadMin;
	private PayloadRadExpData payloadRadExp;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		LsbBitInputStream dis = new LsbBitInputStream(new ByteArrayInputStream(data));
		header = new FoxHeader(dis);
		if (header.getFoxId() != 2) {
			throw new UncorrectableException("invalid fox id: " + header.getFoxId());
		}
		switch (header.getType()) {
		case 1:
			payloadRealtime = new Payload1BRealtime(dis);
			break;
		case 2:
			payloadMax = new Payload1BMaxValues(dis);
			break;
		case 3:
			payloadMin = new Payload1BMinValues(dis);
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

}
