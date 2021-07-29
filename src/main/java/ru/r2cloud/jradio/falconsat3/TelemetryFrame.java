package ru.r2cloud.jradio.falconsat3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public abstract class TelemetryFrame {

	private int channelNumber;
	private long timestamp;

	protected TelemetryFrame() {
		// do nothing
	}

	public TelemetryFrame(DataInputStream dis) throws IOException {
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		timestamp = ldis.readUnsignedInt();
		channelNumber = ldis.readUnsignedByte();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while (dis.available() > 0) {
			baos.write(dis.readUnsignedByte());
			baos.write(dis.readUnsignedByte());
			// this is important
			dis.skipBytes(1);
		}
		ldis = new LittleEndianDataInputStream(new DataInputStream(new ByteArrayInputStream(baos.toByteArray())));
		read(ldis);
	}

	public abstract void read(LittleEndianDataInputStream ldis) throws IOException;

	public int getChannelNumber() {
		return channelNumber;
	}

	public void setChannelNumber(int channelNumber) {
		this.channelNumber = channelNumber;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
