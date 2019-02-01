package ru.r2cloud.jradio.ao73;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.BitInputStream;

public class WholeOrbitDataBatch {

	private WholeOrbit[] data = new WholeOrbit[104];
	private String callsign;
	private final int sequenceNumber;

	public WholeOrbitDataBatch(int sequenceNumber, byte[] rawData) throws IOException {
		this.sequenceNumber = sequenceNumber;
		BitInputStream dis = new BitInputStream(rawData);
		for (int i = 0; i < data.length; i++) {
			data[i] = new WholeOrbit(dis);
		}
		byte[] callsignBytes = new byte[8];
		dis.readFully(callsignBytes);
		callsign = new String(callsignBytes, StandardCharsets.ISO_8859_1);
	}

	public WholeOrbit[] getData() {
		return data;
	}

	public void setData(WholeOrbit[] data) {
		this.data = data;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}
	
	public int getSequenceNumber() {
		return sequenceNumber;
	}	

}
