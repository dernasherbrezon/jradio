package ru.r2cloud.jradio.fox;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LsbBitInputStream;

public class Fox1ABeacon extends Beacon {

	private FoxHeader header;
	private PayloadRealtime payloadRealtime;
	private PayloadMaxValues payloadMax;
	private PayloadMinValues payloadMin;
	private PayloadRadExpData payloadRadExp;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		LsbBitInputStream dis = new LsbBitInputStream(new ByteArrayInputStream(data));
		header = new FoxHeader(dis);
		switch (header.getType()) {
		case 1:
			payloadRealtime = new PayloadRealtime(dis);
			break;
		case 2:
			payloadMax = new PayloadMaxValues(dis);
			break;
		 case 3:
		 payloadMin = new PayloadMinValues(dis);
		 break;
		// case 4:
		// payloadRadExp = new PayloadRadExpData(dis);
		// break;
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

	public PayloadRealtime getPayloadRealtime() {
		return payloadRealtime;
	}

	public void setPayloadRealtime(PayloadRealtime payloadRealtime) {
		this.payloadRealtime = payloadRealtime;
	}

	public PayloadMaxValues getPayloadMax() {
		return payloadMax;
	}

	public void setPayloadMax(PayloadMaxValues payloadMax) {
		this.payloadMax = payloadMax;
	}

	public PayloadMinValues getPayloadMin() {
		return payloadMin;
	}

	public void setPayloadMin(PayloadMinValues payloadMin) {
		this.payloadMin = payloadMin;
	}

	public PayloadRadExpData getPayloadRadExp() {
		return payloadRadExp;
	}

	public void setPayloadRadExp(PayloadRadExpData payloadRadExp) {
		this.payloadRadExp = payloadRadExp;
	}

}
