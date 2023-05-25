package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

public class SohGeneral {

	private SohStatus scrubStatusOverall;
	private int imageBooted;
	private int imageAutoFailover;
	private int inertiaIndex;

	public SohGeneral() {
		// do nothing
	}

	public SohGeneral(DataInputStream dis) throws IOException {
		scrubStatusOverall = SohStatus.valueOfCode(dis.readByte());
		imageBooted = dis.readUnsignedByte();
		imageAutoFailover = dis.readUnsignedByte();
		inertiaIndex = dis.readUnsignedByte();
	}
	
	public int getInertiaIndex() {
		return inertiaIndex;
	}
	
	public void setInertiaIndex(int inertiaIndex) {
		this.inertiaIndex = inertiaIndex;
	}

	public SohStatus getScrubStatusOverall() {
		return scrubStatusOverall;
	}

	public void setScrubStatusOverall(SohStatus scrubStatusOverall) {
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
