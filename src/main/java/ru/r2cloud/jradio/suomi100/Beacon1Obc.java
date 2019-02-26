package ru.r2cloud.jradio.suomi100;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Beacon1Obc {

	private long timestamp;
	private int[] pwr;
	private int swCount;
	private int filesystem;
	private int bootCount;
	private long bootCause;
	private long clock;
	
	public Beacon1Obc() {
		//do nothing
	}

	public Beacon1Obc(DataInputStream dis) throws IOException {
		timestamp = StreamUtils.readUnsignedInt(dis);
		pwr = new int[6];
		for (int i = 0; i < pwr.length; i++) {
			pwr[i] = dis.readUnsignedByte();
		}
		swCount = dis.readUnsignedShort();
		filesystem = dis.readUnsignedByte();
		bootCount = dis.readUnsignedShort();
		bootCause = StreamUtils.readUnsignedInt(dis);
		clock = StreamUtils.readUnsignedInt(dis);
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int[] getPwr() {
		return pwr;
	}

	public void setPwr(int[] pwr) {
		this.pwr = pwr;
	}

	public int getSwCount() {
		return swCount;
	}

	public void setSwCount(int swCount) {
		this.swCount = swCount;
	}

	public int getFilesystem() {
		return filesystem;
	}

	public void setFilesystem(int filesystem) {
		this.filesystem = filesystem;
	}

	public int getBootCount() {
		return bootCount;
	}

	public void setBootCount(int bootCount) {
		this.bootCount = bootCount;
	}

	public long getBootCause() {
		return bootCause;
	}

	public void setBootCause(long bootCause) {
		this.bootCause = bootCause;
	}

	public long getClock() {
		return clock;
	}

	public void setClock(long clock) {
		this.clock = clock;
	}

}
