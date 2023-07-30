package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class UintValue {

	private long value;
	private Integer timeAgo;

	public UintValue(LittleEndianDataInputStream dis) throws IOException {
		timeAgo = BeaconInfo.convertByteSecondsAgo(dis.readUnsignedByte());
		value = dis.readUnsignedInt();
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public Integer getTimeAgo() {
		return timeAgo;
	}

	public void setTimeAgo(Integer timeAgo) {
		this.timeAgo = timeAgo;
	}

}
