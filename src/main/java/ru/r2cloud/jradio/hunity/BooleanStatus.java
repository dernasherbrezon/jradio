package ru.r2cloud.jradio.hunity;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class BooleanStatus {

	private SampleTime time;
	private boolean status;

	public BooleanStatus() {
		// do nothing
	}

	public BooleanStatus(LittleEndianDataInputStream dis) throws IOException {
		int u8 = dis.readUnsignedByte();
		time = SampleTime.valueOfByte(u8);
		status = (u8 & 0x80) > 0;
	}

	public SampleTime getTime() {
		return time;
	}

	public void setTime(SampleTime time) {
		this.time = time;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
