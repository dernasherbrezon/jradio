package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ShortCvtValue {

	private short value;
	private Integer timeAgo;

	public ShortCvtValue(LittleEndianDataInputStream dis) throws IOException {
		short value = dis.readShort();
		timeAgo = BeaconInfo.convertTimeAgo(value);
		value = (short) (value >> 3);
	}

	public short getValue() {
		return value;
	}

	public void setValue(short value) {
		this.value = value;
	}

	public Integer getTimeAgo() {
		return timeAgo;
	}

	public void setTimeAgo(Integer timeAgo) {
		this.timeAgo = timeAgo;
	}

}
