package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class UplinkFeedback {

	private long timestamp;
	private long packetCount;
	private long[] serialNumbers;

	public UplinkFeedback() {
		// do nothing
	}

	public UplinkFeedback(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		packetCount = dis.readUnsignedInt();
		serialNumbers = new long[(int) packetCount];
		for (int i = 0; i < serialNumbers.length; i++) {
			serialNumbers[i] = dis.readUnsignedInt();
		}
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getPacketCount() {
		return packetCount;
	}

	public void setPacketCount(long packetCount) {
		this.packetCount = packetCount;
	}

	public long[] getSerialNumbers() {
		return serialNumbers;
	}

	public void setSerialNumbers(long[] serialNumbers) {
		this.serialNumbers = serialNumbers;
	}

}
