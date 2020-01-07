package ru.r2cloud.jradio.nayif1;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class WholeOrbitDataBatch {

	private WholeOrbit[] data;
	private int sequenceNumber;

	public WholeOrbitDataBatch() {
		// do nothing
	}

	public WholeOrbitDataBatch(int sequenceNumber, byte[] rawData) throws IOException {
		this.sequenceNumber = sequenceNumber;
		BitInputStream dis = new BitInputStream(rawData);
		this.data = new WholeOrbit[96];
		for (int i = 0; i < data.length; i++) {
			data[i] = new WholeOrbit(dis);
		}
	}

	public void setData(WholeOrbit[] data) {
		this.data = data;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public WholeOrbit[] getData() {
		return data;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

}
