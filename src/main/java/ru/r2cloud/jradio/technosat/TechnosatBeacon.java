package ru.r2cloud.jradio.technosat;

import java.io.IOException;

import ru.r2cloud.jradio.tubix20.CMX909bBeacon;

public class TechnosatBeacon extends CMX909bBeacon {

	private TransferFrame frame;

	@Override
	protected void readFrameData(byte[] data) throws IOException {
		frame = new TransferFrame();
		frame.readExternal(data);
	}

	public TransferFrame getFrame() {
		return frame;
	}

	public void setFrame(TransferFrame frame) {
		this.frame = frame;
	}

}
