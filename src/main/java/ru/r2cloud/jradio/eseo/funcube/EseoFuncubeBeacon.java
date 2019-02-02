package ru.r2cloud.jradio.eseo.funcube;

import java.io.IOException;

import ru.r2cloud.jradio.Externalizable;
import ru.r2cloud.jradio.util.BitInputStream;

public class EseoFuncubeBeacon implements Externalizable {
	
	private int satId;
	private int frameType;
	private RealtimeTelemetry realtimeTelemetry;
	private byte[] payload;

	private byte[] rawData;
	private long beginSample;
	private long beginMillis;

	@Override
	public void readExternal(byte[] data) throws IOException {
		this.rawData = data;
		BitInputStream dis = new BitInputStream(data);
		satId = satId | (dis.readUnsignedInt(1) << 6);
		satId = satId | (dis.readUnsignedInt(1) << 7);
		
		frameType = frameType | (dis.readUnsignedInt(1) << 2);
		frameType = frameType | (dis.readUnsignedInt(1) << 3);
		frameType = frameType | (dis.readUnsignedInt(1) << 4);
		frameType = frameType | (dis.readUnsignedInt(1) << 5);
		frameType = frameType | (dis.readUnsignedInt(1) << 6);
		frameType = frameType | (dis.readUnsignedInt(1) << 7);
		
		satId = satId | (dis.readUnsignedInt(1) << 0);
		satId = satId | (dis.readUnsignedInt(1) << 1);
		satId = satId | (dis.readUnsignedInt(1) << 2);
		satId = satId | (dis.readUnsignedInt(1) << 3);
		satId = satId | (dis.readUnsignedInt(1) << 4);
		satId = satId | (dis.readUnsignedInt(1) << 5);
		
		frameType = frameType | (dis.readUnsignedInt(1) << 0);
		frameType = frameType | (dis.readUnsignedInt(1) << 1);
		
		realtimeTelemetry = new RealtimeTelemetry(dis);
		payload = new byte[200];
		dis.readFully(payload);
	}
	
	public int getSatId() {
		return satId;
	}
	
	public int getFrameType() {
		return frameType;
	}
	
	public byte[] getRawData() {
		return rawData;
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

	public void setSatId(int satId) {
		this.satId = satId;
	}

	public void setFrameType(int frameType) {
		this.frameType = frameType;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

	public RealtimeTelemetry getRealtimeTelemetry() {
		return realtimeTelemetry;
	}

	public void setRealtimeTelemetry(RealtimeTelemetry realtimeTelemetry) {
		this.realtimeTelemetry = realtimeTelemetry;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}
	
}
