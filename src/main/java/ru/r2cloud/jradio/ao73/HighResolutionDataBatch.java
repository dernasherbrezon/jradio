package ru.r2cloud.jradio.ao73;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class HighResolutionDataBatch {

	private HighResolutionData[] data = new HighResolutionData[60];
	private final int sequenceNumber;

	public HighResolutionDataBatch(int sequenceNumber, byte[] rawData) throws IOException {
		this.sequenceNumber = sequenceNumber;
		BitInputStream dis = new BitInputStream(rawData);
		for (int i = 0; i < data.length; i++) {
			data[i] = new HighResolutionData(dis);
		}
	}

	public HighResolutionData[] getData() {
		return data;
	}

	public void setData(HighResolutionData[] data) {
		this.data = data;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

}
