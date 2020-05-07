package ru.r2cloud.jradio.quetzal1;

import java.io.DataInputStream;
import java.io.IOException;

public class HeaterStatus {

	private HeaterMode mode;
	private boolean status;

	public HeaterStatus() {
		// do nothing
	}

	public HeaterStatus(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		mode = HeaterMode.valueOfCode(raw >> 4);
		if ((raw & 0b1111) == 0b1001) {
			status = true;
		} else {
			status = false;
		}
	}

	public HeaterMode getMode() {
		return mode;
	}

	public void setMode(HeaterMode mode) {
		this.mode = mode;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
