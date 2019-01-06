package ru.r2cloud.jradio.nusat;

import java.io.IOException;

import ru.r2cloud.jradio.Externalizable;

public class NusatBeacon implements Externalizable {
	
	// telemetry format is unknown.
	
	private long beginSample;
	private long beginMillis;
	private byte[] rawData;

	@Override
	public void readExternal(byte[] data) throws IOException {
		this.rawData = data;
	}

	public long getBeginSample() {
		return beginSample;
	}

	public void setBeginSample(long beginSample) {
		this.beginSample = beginSample;
	}

	public long getBeginMillis() {
		return beginMillis;
	}

	public void setBeginMillis(long beginMillis) {
		this.beginMillis = beginMillis;
	}

	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

}
