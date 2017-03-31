package ru.r2cloud.jradio.aausat4;

import java.io.DataInputStream;
import java.io.IOException;

public class ADCS2 {

	private short gyro1;
	private short gyro2;
	private short gyro3;

	public ADCS2(DataInputStream data) throws IOException {
		gyro1 = data.readShort();
		gyro2 = data.readShort();
		gyro3 = data.readShort();
	}

	public short getGyro1() {
		return gyro1;
	}

	public void setGyro1(short gyro1) {
		this.gyro1 = gyro1;
	}

	public short getGyro2() {
		return gyro2;
	}

	public void setGyro2(short gyro2) {
		this.gyro2 = gyro2;
	}

	public short getGyro3() {
		return gyro3;
	}

	public void setGyro3(short gyro3) {
		this.gyro3 = gyro3;
	}

}
