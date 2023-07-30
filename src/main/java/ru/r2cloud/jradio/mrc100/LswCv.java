package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class LswCv {

	private String name;
	private Integer timestamp;
	private boolean on;
	private int voltage;
	private int current;

	public LswCv(String name, LittleEndianDataInputStream dis) throws IOException {
		this.name = name;
		long raw = dis.readUnsignedInt();
		timestamp = BeaconInfo.convert5ByteTimestamp(raw);
		on = (raw & 0x20) > 0;
		current = (int) (raw >> 6) & 0x1FFF;
		voltage = (int) (raw >> 19) & 0x1FFF;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public int getVoltage() {
		return voltage;
	}

	public void setVoltage(int voltage) {
		this.voltage = voltage;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

}
