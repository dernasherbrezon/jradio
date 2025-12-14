package ru.r2cloud.jradio.hunity;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SensorStatus {

	private SampleTime time;
	private Status status;
	private int current;
	private int voltage;

	public SensorStatus() {
		// do nothing
	}

	public SensorStatus(LittleEndianDataInputStream dis) throws IOException {
		long u32 = dis.readUnsignedInt();
		time = SampleTime.valueOf5Bit((int) (u32 & 0xFF));
		status = Status.valueOfCode((int) ((u32 >> 5) & 0x03));
		current = (int) ((u32 >> 7) & 0x0FFF);
		voltage = (int) ((u32 >> 19) & 0x1FFF);
	}

	public SampleTime getTime() {
		return time;
	}

	public void setTime(SampleTime time) {
		this.time = time;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getVoltage() {
		return voltage;
	}

	public void setVoltage(int voltage) {
		this.voltage = voltage;
	}

}
