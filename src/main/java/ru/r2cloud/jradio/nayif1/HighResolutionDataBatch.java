package ru.r2cloud.jradio.nayif1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.BitInputStream;

public class HighResolutionDataBatch {

	private HighResolutionData[] data;
	private int sequenceNumber;
	private String callsign;

	public HighResolutionDataBatch() {
		// do nothing
	}

	public HighResolutionDataBatch(int sequenceNumber, byte[] rawData) throws IOException {
		this.sequenceNumber = sequenceNumber;
		BitInputStream dis = new BitInputStream(rawData);
		this.data = new HighResolutionData[60];
		for (int i = 0; i < data.length; i++) {
			data[i] = new HighResolutionData(dis);
		}
		byte[] callsignBytes = new byte[10];
		dis.readFully(callsignBytes);
		callsign = new String(callsignBytes, StandardCharsets.ISO_8859_1).trim();
	}

	public void setData(HighResolutionData[] data) {
		this.data = data;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public String getCallsign() {
		return callsign;
	}

	public HighResolutionData[] getData() {
		return data;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

}
