package ru.r2cloud.jradio.jy1sat;

import java.io.IOException;

import ru.r2cloud.jradio.Externalizable;
import ru.r2cloud.jradio.ao40.Ao40Header;
import ru.r2cloud.jradio.util.BitInputStream;

public class Jy1satBeacon implements Externalizable {
	
	private Ao40Header header;

	private byte[] rawData;
	private long beginSample;
	private long beginMillis;
	
	@Override
	public void readExternal(byte[] data) throws IOException {
		this.rawData = data;
		BitInputStream dis = new BitInputStream(data);
		header = new Ao40Header(dis);
		//FIXME parse realtime telemetry and payload
	}
	
	public Ao40Header getHeader() {
		return header;
	}
	
	public void setHeader(Ao40Header header) {
		this.header = header;
	}

	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
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

}
