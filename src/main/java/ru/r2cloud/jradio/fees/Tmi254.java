package ru.r2cloud.jradio.fees;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Tmi254 {

	private int frameId;
	private long timestamp;
	private int vacd2v5;
	private int vbat;
	private int vsol;
	private int iload;
	private int icharger;

	public Tmi254() {
		// do nothing
	}

	public Tmi254(DataInputStream bedis) throws IOException {
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(bedis);
		frameId = dis.readUnsignedShort();
		timestamp = dis.readUnsignedInt();
		dis.skipBytes(11);
		vacd2v5 = dis.readUnsignedShort();
		vbat = dis.readUnsignedShort();
		vsol = dis.readUnsignedShort();
		dis.skipBytes(8);
		iload = dis.readUnsignedShort();
		icharger = dis.readUnsignedShort();
		dis.skipBytes(9);
	}

	public int getFrameId() {
		return frameId;
	}

	public void setFrameId(int frameId) {
		this.frameId = frameId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getVacd2v5() {
		return vacd2v5;
	}

	public void setVacd2v5(int vacd2v5) {
		this.vacd2v5 = vacd2v5;
	}

	public int getVbat() {
		return vbat;
	}

	public void setVbat(int vbat) {
		this.vbat = vbat;
	}

	public int getVsol() {
		return vsol;
	}

	public void setVsol(int vsol) {
		this.vsol = vsol;
	}

	public int getIload() {
		return iload;
	}

	public void setIload(int iload) {
		this.iload = iload;
	}

	public int getIcharger() {
		return icharger;
	}

	public void setIcharger(int icharger) {
		this.icharger = icharger;
	}

}
