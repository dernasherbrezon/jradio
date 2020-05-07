package ru.r2cloud.jradio.quetzal1;

import java.io.DataInputStream;
import java.io.IOException;

public class Ina260 {

	private Float voltage;
	private Float current;

	public Ina260() {
		// do nothing
	}

	public Ina260(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		if (raw != 253 && raw != 255) {
			voltage = 0.01785f * raw;
		}
		raw = dis.readUnsignedShort();
		if (raw < 4093) {
			current = 0.6109f * raw;
		}
	}

	public Float getVoltage() {
		return voltage;
	}

	public void setVoltage(Float voltage) {
		this.voltage = voltage;
	}

	public Float getCurrent() {
		return current;
	}

	public void setCurrent(Float current) {
		this.current = current;
	}

}
