package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class UshortCvtValue {

	private int value;
	private Integer timeAgo;

	public UshortCvtValue(LittleEndianDataInputStream dis) throws IOException {
		int value = dis.readUnsignedShort();
		timeAgo = BeaconInfo.convertTimeAgo(value);
		value = (value >> 3);
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
