package ru.r2cloud.jradio.cute;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class SecondaryHeader {

	private long timestampSeconds;
	private int subSeconds;

	public SecondaryHeader() {
		// do nothing
	}

	public SecondaryHeader(DataInputStream dis) throws IOException {
		timestampSeconds = StreamUtils.readUnsignedInt(dis);
		subSeconds = dis.readUnsignedByte();
		dis.skipBytes(1);
	}

	public long getTimestampSeconds() {
		return timestampSeconds;
	}

	public void setTimestampSeconds(long timestampSeconds) {
		this.timestampSeconds = timestampSeconds;
	}

	public int getSubSeconds() {
		return subSeconds;
	}

	public void setSubSeconds(int subSeconds) {
		this.subSeconds = subSeconds;
	}

}
