package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class I2CStatus {

	private float I2C0CUR;         // Current drawn on I2C-Bus 0
	private float I2C1CUR;         // Current drawn on I2C-Bus 1
	private float I2C2CUR;         // Current drawn on I2C-Bus 2
	private boolean I2C0ENA;       // Enable-Status of the I2C-Bus 0
	private boolean I2C1ENA;       // Enable-Status of the I2C-Bus 1
	private boolean I2C2ENA;       // Enable-Status of the I2C-Bus 2
	private boolean I2C0LCK;       // Lock-Status of the I2C-Bus 0
	private boolean I2C1LCK;       // Lock-Status of the I2C-Bus 1
	private boolean I2C2LCK;       // Lock-Status of the I2C-Bus 2

	public I2CStatus(DataInputStream dis) throws IOException {
		I2C0CUR = dis.readShort() * 0.0305185095f;
		I2C1CUR = dis.readShort() * 0.0305185095f;
		I2C2CUR = dis.readShort() * 0.0305185095f;
		int raw = dis.readUnsignedByte();
		I2C0ENA = ((raw >> 7) & 0x1) > 0;
		I2C1ENA = ((raw >> 6) & 0x1) > 0;
		I2C2ENA = ((raw >> 5) & 0x1) > 0;
		I2C0LCK = ((raw >> 4) & 0x1) > 0;
		I2C1LCK = ((raw >> 3) & 0x1) > 0;
		I2C2LCK = ((raw >> 2) & 0x1) > 0;
	}

	public float getI2C0CUR() {
		return I2C0CUR;
	}

	public void setI2C0CUR(float i2c0cur) {
		I2C0CUR = i2c0cur;
	}

	public float getI2C1CUR() {
		return I2C1CUR;
	}

	public void setI2C1CUR(float i2c1cur) {
		I2C1CUR = i2c1cur;
	}

	public float getI2C2CUR() {
		return I2C2CUR;
	}

	public void setI2C2CUR(float i2c2cur) {
		I2C2CUR = i2c2cur;
	}

	public boolean isI2C0ENA() {
		return I2C0ENA;
	}

	public void setI2C0ENA(boolean i2c0ena) {
		I2C0ENA = i2c0ena;
	}

	public boolean isI2C1ENA() {
		return I2C1ENA;
	}

	public void setI2C1ENA(boolean i2c1ena) {
		I2C1ENA = i2c1ena;
	}

	public boolean isI2C2ENA() {
		return I2C2ENA;
	}

	public void setI2C2ENA(boolean i2c2ena) {
		I2C2ENA = i2c2ena;
	}

	public boolean isI2C0LCK() {
		return I2C0LCK;
	}

	public void setI2C0LCK(boolean i2c0lck) {
		I2C0LCK = i2c0lck;
	}

	public boolean isI2C1LCK() {
		return I2C1LCK;
	}

	public void setI2C1LCK(boolean i2c1lck) {
		I2C1LCK = i2c1lck;
	}

	public boolean isI2C2LCK() {
		return I2C2LCK;
	}

	public void setI2C2LCK(boolean i2c2lck) {
		I2C2LCK = i2c2lck;
	}

}
