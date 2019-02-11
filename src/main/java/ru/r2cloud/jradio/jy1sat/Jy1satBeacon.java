package ru.r2cloud.jradio.jy1sat;

import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ao40.Ao40Header;
import ru.r2cloud.jradio.util.BitInputStream;

public class Jy1satBeacon extends Beacon {
	
	private Ao40Header header;
	private RealtimeTelemetry realtimeTelemetry;
	private byte[] payload;
	
	@Override
	public void readBeacon(byte[] data) throws IOException {
		BitInputStream dis = new BitInputStream(data);
		header = new Ao40Header(dis);
		realtimeTelemetry = new RealtimeTelemetry(dis);
		payload = new byte[200];
		dis.readFully(payload);
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

	public Ao40Header getHeader() {
		return header;
	}
	
	public void setHeader(Ao40Header header) {
		this.header = header;
	}

}
