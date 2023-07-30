package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class BooleanValue {

	private boolean value;
	private Integer timeAgo;

	public BooleanValue(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		timeAgo = BeaconInfo.convertByteSecondsAgo(raw);
		value = ((raw & 0x80) > 0);
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public Integer getTimeAgo() {
		return timeAgo;
	}

	public void setTimeAgo(Integer timeAgo) {
		this.timeAgo = timeAgo;
	}

}
