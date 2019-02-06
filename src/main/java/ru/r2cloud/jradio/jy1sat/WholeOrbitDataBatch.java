package ru.r2cloud.jradio.jy1sat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class WholeOrbitDataBatch {

	private WholeOrbit[] data = new WholeOrbit[96];
	private final int sequenceNumber;

	public WholeOrbitDataBatch(int sequenceNumber, byte[] rawData) throws IOException {
		this.sequenceNumber = sequenceNumber;
		BitInputStream dis = new BitInputStream(rawData);
		for (int i = 0; i < data.length; i++) {
			data[i] = new WholeOrbit(dis);
		}
	}

	public WholeOrbit[] getData() {
		return data;
	}

	public void setData(WholeOrbit[] data) {
		this.data = data;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}	

}
