package ru.r2cloud.jradio.beesat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmCameraStatus {

	private boolean I2CEN;
	private boolean I2CLK;

	public TmCameraStatus(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		I2CEN = ((raw >> 7) & 0x1) > 0;
		I2CLK = ((raw >> 6) & 0x1) > 0;
	}

	public boolean isI2CEN() {
		return I2CEN;
	}

	public void setI2CEN(boolean i2cen) {
		I2CEN = i2cen;
	}

	public boolean isI2CLK() {
		return I2CLK;
	}

	public void setI2CLK(boolean i2clk) {
		I2CLK = i2clk;
	}

}
