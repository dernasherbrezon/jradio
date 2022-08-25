package ru.r2cloud.jradio.cute.soh;

import java.io.DataInputStream;
import java.io.IOException;

public class SohGeneral {

	private boolean scrubStatusOverall;
	private int imageBooted;
	private int imageAutoFailover;

	public SohGeneral() {
		// do nothing
	}

	public SohGeneral(DataInputStream dis) throws IOException {
		scrubStatusOverall = (dis.readUnsignedByte() == 0);
		imageBooted = dis.readUnsignedByte();
		imageAutoFailover = dis.readUnsignedByte();
	}

	public boolean getScrubStatusOverall() {
		return scrubStatusOverall;
	}

	public void setScrubStatusOverall(boolean scrubStatusOverall) {
		this.scrubStatusOverall = scrubStatusOverall;
	}

	public int getImageBooted() {
		return imageBooted;
	}

	public void setImageBooted(int imageBooted) {
		this.imageBooted = imageBooted;
	}

	public int getImageAutoFailover() {
		return imageAutoFailover;
	}

	public void setImageAutoFailover(int imageAutoFailover) {
		this.imageAutoFailover = imageAutoFailover;
	}

}
