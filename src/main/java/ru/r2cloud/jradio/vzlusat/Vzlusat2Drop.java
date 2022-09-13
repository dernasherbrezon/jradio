package ru.r2cloud.jradio.vzlusat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Vzlusat2Drop {

	private int flag;
	private long chunk;
	private long time;
	private byte[] data;

	public Vzlusat2Drop() {
		// do nothing
	}

	public Vzlusat2Drop(DataInputStream dis) throws IOException {
		flag = dis.readUnsignedByte();
		chunk = StreamUtils.readUnsignedInt(dis);
		time = StreamUtils.readUnsignedInt(dis);
		data = new byte[dis.available()];
		dis.readFully(data);
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public long getChunk() {
		return chunk;
	}

	public void setChunk(long chunk) {
		this.chunk = chunk;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
