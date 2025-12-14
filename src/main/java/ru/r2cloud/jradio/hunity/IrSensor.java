package ru.r2cloud.jradio.hunity;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class IrSensor {

	private SampleTime time;
	private int value;

	public IrSensor() {
		// do nothing
	}

	public IrSensor(LittleEndianDataInputStream dis) throws IOException {
		time = SampleTime.valueOfByte(dis.readUnsignedByte());
		value = dis.readUnsignedByte() * 256;
	}

	public SampleTime getTime() {
		return time;
	}

	public void setTime(SampleTime time) {
		this.time = time;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
