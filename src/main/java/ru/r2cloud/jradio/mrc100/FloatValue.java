package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class FloatValue {

	private float value;
	private Integer timeAgo;

	public FloatValue() {
		// do nothing
	}

	public FloatValue(LittleEndianDataInputStream dis) throws IOException {
		timeAgo = BeaconInfo.convertByteSecondsAgo(dis.readUnsignedByte());
		value = dis.readFloat();
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Integer getTimeAgo() {
		return timeAgo;
	}

	public void setTimeAgo(Integer timeAgo) {
		this.timeAgo = timeAgo;
	}

}
