package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class I2CStatus {

	private float i2c0Cur; // Current drawn on I2C-Bus 0
	private float i2c1Cur; // Current drawn on I2C-Bus 1
	private float i2c2Cur; // Current drawn on I2C-Bus 2
	private boolean i2c0Ena; // Enable-Status of the I2C-Bus 0
	private boolean i2c1Ena; // Enable-Status of the I2C-Bus 1
	private boolean i2c2Ena; // Enable-Status of the I2C-Bus 2
	private boolean i2c0Lck; // Lock-Status of the I2C-Bus 0
	private boolean i2c1Lck; // Lock-Status of the I2C-Bus 1
	private boolean i2c2Lck; // Lock-Status of the I2C-Bus 2

	public I2CStatus(DataInputStream dis) throws IOException {
		i2c0Cur = dis.readShort() * 0.0305185095f;
		i2c1Cur = dis.readShort() * 0.0305185095f;
		i2c2Cur = dis.readShort() * 0.0305185095f;
		int raw = dis.readUnsignedByte();
		i2c0Ena = ((raw >> 7) & 0x1) > 0;
		i2c1Ena = ((raw >> 6) & 0x1) > 0;
		i2c2Ena = ((raw >> 5) & 0x1) > 0;
		i2c0Lck = ((raw >> 4) & 0x1) > 0;
		i2c1Lck = ((raw >> 3) & 0x1) > 0;
		i2c2Lck = ((raw >> 2) & 0x1) > 0;
	}

	public float getI2c0Cur() {
		return i2c0Cur;
	}

	public void setI2c0Cur(float i2c0Cur) {
		this.i2c0Cur = i2c0Cur;
	}

	public float getI2c1Cur() {
		return i2c1Cur;
	}

	public void setI2c1Cur(float i2c1Cur) {
		this.i2c1Cur = i2c1Cur;
	}

	public float getI2c2Cur() {
		return i2c2Cur;
	}

	public void setI2c2Cur(float i2c2Cur) {
		this.i2c2Cur = i2c2Cur;
	}

	public boolean isI2c0Ena() {
		return i2c0Ena;
	}

	public void setI2c0Ena(boolean i2c0Ena) {
		this.i2c0Ena = i2c0Ena;
	}

	public boolean isI2c1Ena() {
		return i2c1Ena;
	}

	public void setI2c1Ena(boolean i2c1Ena) {
		this.i2c1Ena = i2c1Ena;
	}

	public boolean isI2c2Ena() {
		return i2c2Ena;
	}

	public void setI2c2Ena(boolean i2c2Ena) {
		this.i2c2Ena = i2c2Ena;
	}

	public boolean isI2c0Lck() {
		return i2c0Lck;
	}

	public void setI2c0Lck(boolean i2c0Lck) {
		this.i2c0Lck = i2c0Lck;
	}

	public boolean isI2c1Lck() {
		return i2c1Lck;
	}

	public void setI2c1Lck(boolean i2c1Lck) {
		this.i2c1Lck = i2c1Lck;
	}

	public boolean isI2c2Lck() {
		return i2c2Lck;
	}

	public void setI2c2Lck(boolean i2c2Lck) {
		this.i2c2Lck = i2c2Lck;
	}

}
