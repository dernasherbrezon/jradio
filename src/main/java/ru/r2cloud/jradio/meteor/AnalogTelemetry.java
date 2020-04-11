package ru.r2cloud.jradio.meteor;

import java.io.DataInputStream;
import java.io.IOException;

public class AnalogTelemetry {

	private byte rawControlPoint1Temperature;
	private byte rawFpFkzTemperature;
	private byte rawControlHighVoltageFpVk2;
	private byte rawControlHighVoltageFpVk1;
	private byte rawIRTemperature;
	private int rawCurrentVk3;
	private int rawCurrentVk2;
	private int rawCurrentVk1;
	private byte rawG3Temperature;
	private byte rawG2Temperature;
	private byte rawG1Temperature;
	private byte rawX3Temperature;
	private byte rawX2Temperature;
	private byte rawX1Temperature;
	private byte rawFp6Temperature;
	private byte rawFp5Temperature;

	public AnalogTelemetry() {
		// do nothing
	}

	public AnalogTelemetry(DataInputStream dis) throws IOException {
		rawControlPoint1Temperature = dis.readByte();
		rawFpFkzTemperature = dis.readByte();
		rawControlHighVoltageFpVk2 = dis.readByte();
		rawControlHighVoltageFpVk1 = dis.readByte();
		rawIRTemperature = dis.readByte();
		rawCurrentVk3 = dis.readUnsignedByte();
		rawCurrentVk2 = dis.readUnsignedByte();
		rawCurrentVk1 = dis.readUnsignedByte();
		rawG3Temperature = dis.readByte();
		rawG2Temperature = dis.readByte();
		rawG1Temperature = dis.readByte();
		rawX3Temperature = dis.readByte();
		rawX2Temperature = dis.readByte();
		rawX1Temperature = dis.readByte();
		rawFp6Temperature = dis.readByte();
		rawFp5Temperature = dis.readByte();
	}

	public byte getRawControlPoint1Temperature() {
		return rawControlPoint1Temperature;
	}

	public void setRawControlPoint1Temperature(byte rawControlPoint1Temperature) {
		this.rawControlPoint1Temperature = rawControlPoint1Temperature;
	}

	public byte getRawFpFkzTemperature() {
		return rawFpFkzTemperature;
	}

	public void setRawFpFkzTemperature(byte rawFpFkzTemperature) {
		this.rawFpFkzTemperature = rawFpFkzTemperature;
	}

	public byte getRawControlHighVoltageFpVk2() {
		return rawControlHighVoltageFpVk2;
	}

	public void setRawControlHighVoltageFpVk2(byte rawControlHighVoltageFpVk2) {
		this.rawControlHighVoltageFpVk2 = rawControlHighVoltageFpVk2;
	}

	public byte getRawControlHighVoltageFpVk1() {
		return rawControlHighVoltageFpVk1;
	}

	public void setRawControlHighVoltageFpVk1(byte rawControlHighVoltageFpVk1) {
		this.rawControlHighVoltageFpVk1 = rawControlHighVoltageFpVk1;
	}

	public byte getRawIRTemperature() {
		return rawIRTemperature;
	}

	public void setRawIRTemperature(byte rawIRTemperature) {
		this.rawIRTemperature = rawIRTemperature;
	}

	public int getRawCurrentVk3() {
		return rawCurrentVk3;
	}

	public void setRawCurrentVk3(int rawCurrentVk3) {
		this.rawCurrentVk3 = rawCurrentVk3;
	}

	public int getRawCurrentVk2() {
		return rawCurrentVk2;
	}

	public void setRawCurrentVk2(int rawCurrentVk2) {
		this.rawCurrentVk2 = rawCurrentVk2;
	}

	public int getRawCurrentVk1() {
		return rawCurrentVk1;
	}

	public void setRawCurrentVk1(int rawCurrentVk1) {
		this.rawCurrentVk1 = rawCurrentVk1;
	}

	public byte getRawG3Temperature() {
		return rawG3Temperature;
	}

	public void setRawG3Temperature(byte rawG3Temperature) {
		this.rawG3Temperature = rawG3Temperature;
	}

	public byte getRawG2Temperature() {
		return rawG2Temperature;
	}

	public void setRawG2Temperature(byte rawG2Temperature) {
		this.rawG2Temperature = rawG2Temperature;
	}

	public byte getRawG1Temperature() {
		return rawG1Temperature;
	}

	public void setRawG1Temperature(byte rawG1Temperature) {
		this.rawG1Temperature = rawG1Temperature;
	}

	public byte getRawX3Temperature() {
		return rawX3Temperature;
	}

	public void setRawX3Temperature(byte rawX3Temperature) {
		this.rawX3Temperature = rawX3Temperature;
	}

	public byte getRawX2Temperature() {
		return rawX2Temperature;
	}

	public void setRawX2Temperature(byte rawX2Temperature) {
		this.rawX2Temperature = rawX2Temperature;
	}

	public byte getRawX1Temperature() {
		return rawX1Temperature;
	}

	public void setRawX1Temperature(byte rawX1Temperature) {
		this.rawX1Temperature = rawX1Temperature;
	}

	public byte getRawFp6Temperature() {
		return rawFp6Temperature;
	}

	public void setRawFp6Temperature(byte rawFp6Temperature) {
		this.rawFp6Temperature = rawFp6Temperature;
	}

	public byte getRawFp5Temperature() {
		return rawFp5Temperature;
	}

	public void setRawFp5Temperature(byte rawFp5Temperature) {
		this.rawFp5Temperature = rawFp5Temperature;
	}

}
