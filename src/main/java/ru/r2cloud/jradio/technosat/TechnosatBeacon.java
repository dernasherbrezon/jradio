package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.tubix20.TUBiX20Beacon;

public class TechnosatBeacon extends TUBiX20Beacon {

	private TransferFrame frame;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		frame = new TransferFrame();
		frame.readExternal(dis);
	}

	public TransferFrame getFrame() {
		return frame;
	}

	public void setFrame(TransferFrame frame) {
		this.frame = frame;
	}

}
