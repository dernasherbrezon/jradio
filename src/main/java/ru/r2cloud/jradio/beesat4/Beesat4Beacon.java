package ru.r2cloud.jradio.beesat4;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.r2cloud.jradio.tubix20.CMX909bBeacon;

public class Beesat4Beacon extends CMX909bBeacon {

	private List<TransferFrame> frames = new ArrayList<>();

	@Override
	protected void readFrameData(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		while (true) {
			try {
				TransferFrame cur = new TransferFrame();
				cur.readExternal(dis);
				frames.add(cur);
			} catch (EOFException e) {
				break;
			}
		}
	}

	public List<TransferFrame> getFrames() {
		return frames;
	}

	public void setFrames(List<TransferFrame> frames) {
		this.frames = frames;
	}

}
