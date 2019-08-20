package ru.r2cloud.jradio.aistechsat3;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class DataFieldMeta {

	private int checksum;
	private long timestamp;
	private int source;

	public DataFieldMeta() {
		// do nothing
	}

	public DataFieldMeta(DataInputStream dis) throws IOException {
		checksum = dis.readUnsignedShort();
		timestamp = StreamUtils.readUnsignedInt(dis);
		source = dis.readUnsignedShort();
	}

	public int getChecksum() {
		return checksum;
	}

	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

}
