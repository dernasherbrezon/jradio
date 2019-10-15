package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmCameraStatus {

	private boolean i2cEn; // I2C enabled
	private boolean i2cLk; // I2C locked

	public TmCameraStatus(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		i2cEn = ((raw >> 7) & 0x1) > 0;
		i2cLk = ((raw >> 6) & 0x1) > 0;
	}

	public boolean isI2cEn() {
		return i2cEn;
	}

	public void setI2cEn(boolean i2cEn) {
		this.i2cEn = i2cEn;
	}

	public boolean isI2cLk() {
		return i2cLk;
	}

	public void setI2cLk(boolean i2cLk) {
		this.i2cLk = i2cLk;
	}

}
