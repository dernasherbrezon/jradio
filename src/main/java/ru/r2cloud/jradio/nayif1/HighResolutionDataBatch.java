package ru.r2cloud.jradio.nayif1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.BitInputStream;

public class HighResolutionDataBatch {

	private final HighResolutionData[] data = new HighResolutionData[60];
	private final int sequenceNumber;
	private final String callsign;

	public HighResolutionDataBatch(int sequenceNumber, byte[] rawData) throws IOException {
		this.sequenceNumber = sequenceNumber;
		BitInputStream dis = new BitInputStream(rawData);
		for (int i = 0; i < data.length; i++) {
			data[i] = new HighResolutionData(dis);
		}
		byte[] callsignBytes = new byte[10];
		dis.readFully(callsignBytes);
		callsign = new String(callsignBytes, StandardCharsets.ISO_8859_1).trim();
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
