package ru.r2cloud.jradio.ao73;

import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ao40.Ao40Header;
import ru.r2cloud.jradio.util.BitInputStream;

public class Ao73Beacon extends Beacon {

	private int id;
	private FrameType frameType;
	private RealtimeTelemetry realtimeTelemetry;
	private byte[] payload;

	@Override
	public void readBeacon(byte[] data) throws IOException {
		BitInputStream dis = new BitInputStream(data);
		Ao40Header ao40Header = new Ao40Header(dis);
		id = ao40Header.getId();
		frameType = FrameType.valueOfCode(ao40Header.getFrameType());
		realtimeTelemetry = new RealtimeTelemetry(dis);
		payload = new byte[200];
		dis.readFully(payload);
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public FrameType getFrameType() {
		return frameType;
	}

	public void setFrameType(FrameType frameType) {
		this.frameType = frameType;
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
