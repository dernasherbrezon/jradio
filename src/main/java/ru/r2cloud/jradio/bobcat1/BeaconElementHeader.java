package ru.r2cloud.jradio.bobcat1;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class BeaconElementHeader {

	private int checksum;
	private long timestamp;
	private int source;

	public BeaconElementHeader() {
		// do nothing
	}

	public BeaconElementHeader(DataInputStream dis) throws IOException {
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
