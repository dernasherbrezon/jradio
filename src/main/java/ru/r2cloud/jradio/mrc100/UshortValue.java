package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class UshortValue {

	private int value;
	private Integer timeAgo;

	public UshortValue(LittleEndianDataInputStream dis) throws IOException {
		timeAgo = BeaconInfo.convertByteSecondsAgo(dis.readUnsignedByte());
		value = dis.readUnsignedShort();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Integer getTimeAgo() {
		return timeAgo;
	}

	public void setTimeAgo(Integer timeAgo) {
		this.timeAgo = timeAgo;
	}

}
