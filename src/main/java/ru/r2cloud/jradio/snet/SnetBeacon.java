package ru.r2cloud.jradio.snet;

import java.io.IOException;

import ru.r2cloud.jradio.Beacon;

public class SnetBeacon extends Beacon {
	
	private LTUFrameHeader header;

	@Override
	public void readBeacon(byte[] data) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	public void setHeader(LTUFrameHeader header) {
		this.header = header;
	}
	
	public LTUFrameHeader getHeader() {
		return header;
	}

}
