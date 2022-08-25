package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

public class FswAnalogs {

	private float voltage12p0;
	private float voltage8p0;
	private float voltage5p0;
	private float voltage3p3;
	private float detTemp;
	private float det2Temp;
	private float box1Temp;
	private float motor1Temp;
	private float motor2Temp;
	private float motor3Temp;
	private float busVoltage;
	private float batteryVoltage;
	private float batteryCurrent;
	private float battery1Temp;
	private float battery2Temp;
	private float radioTemp;
	private float userAnalog1;
	private float userAnalog2;

	public FswAnalogs() {
		// do nothing
	}

	public FswAnalogs(DataInputStream dis) throws IOException {
		voltage12p0 = dis.readUnsignedByte() / 10.0f;
		voltage8p0 = dis.readUnsignedByte() / 10.0f;
		voltage5p0 = dis.readUnsignedByte() / 40.0f;
		voltage3p3 = dis.readUnsignedByte() / 66.66666f;
		detTemp = dis.readByte() / 1.25f;
		det2Temp = dis.readByte() / 1.25f;
		box1Temp = dis.readShort() * 0.005f;
		motor1Temp = dis.readShort() / 200.0f;
		motor2Temp = dis.readShort() / 200.0f;
		motor3Temp = dis.readShort() / 200.0f;
		busVoltage = dis.readUnsignedShort() * 0.001f;
		batteryVoltage = dis.readUnsignedShort() * 0.002f;
		batteryCurrent = dis.readShort() * 0.002f;
		battery1Temp = dis.readShort() / 200.0f;
		battery2Temp = dis.readShort() / 200.0f;
		radioTemp = dis.readShort() / 200.0f;
		userAnalog1 = dis.readInt() / 2000.0f;
		userAnalog2 = dis.readInt() / 2000.0f;
	}

	public float getVoltage12p0() {
		return voltage12p0;
	}

	public void setVoltage12p0(float voltage12p0) {
		this.voltage12p0 = voltage12p0;
	}

	public float getVoltage8p0() {
		return voltage8p0;
	}

	public void setVoltage8p0(float voltage8p0) {
		this.voltage8p0 = voltage8p0;
	}

	public float getVoltage5p0() {
		return voltage5p0;
	}

	public void setVoltage5p0(float voltage5p0) {
		this.voltage5p0 = voltage5p0;
	}

	public float getVoltage3p3() {
		return voltage3p3;
	}

	public void setVoltage3p3(float voltage3p3) {
		this.voltage3p3 = voltage3p3;
	}

	public float getDetTemp() {
		return detTemp;
	}

	public void setDetTemp(float detTemp) {
		this.detTemp = detTemp;
	}

	public float getDet2Temp() {
		return det2Temp;
	}

	public void setDet2Temp(float det2Temp) {
		this.det2Temp = det2Temp;
	}

	public float getBox1Temp() {
		return box1Temp;
	}

	public void setBox1Temp(float box1Temp) {
		this.box1Temp = box1Temp;
	}

	public float getMotor1Temp() {
		return motor1Temp;
	}

	public void setMotor1Temp(float motor1Temp) {
		this.motor1Temp = motor1Temp;
	}

	public float getMotor2Temp() {
		return motor2Temp;
	}

	public void setMotor2Temp(float motor2Temp) {
		this.motor2Temp = motor2Temp;
	}

	public float getMotor3Temp() {
		return motor3Temp;
	}

	public void setMotor3Temp(float motor3Temp) {
		this.motor3Temp = motor3Temp;
	}

	public float getBusVoltage() {
		return busVoltage;
	}

	public void setBusVoltage(float busVoltage) {
		this.busVoltage = busVoltage;
	}

	public float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public float getBatteryCurrent() {
		return batteryCurrent;
	}

	public void setBatteryCurrent(float batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
	}

	public float getBattery1Temp() {
		return battery1Temp;
	}

	public void setBattery1Temp(float battery1Temp) {
		this.battery1Temp = battery1Temp;
	}

	public float getBattery2Temp() {
		return battery2Temp;
	}

	public void setBattery2Temp(float battery2Temp) {
		this.battery2Temp = battery2Temp;
	}

	public float getRadioTemp() {
		return radioTemp;
	}

	public void setRadioTemp(float radioTemp) {
		this.radioTemp = radioTemp;
	}

	public float getUserAnalog1() {
		return userAnalog1;
	}

	public void setUserAnalog1(float userAnalog1) {
		this.userAnalog1 = userAnalog1;
	}

	public float getUserAnalog2() {
		return userAnalog2;
	}

	public void setUserAnalog2(float userAnalog2) {
		this.userAnalog2 = userAnalog2;
	}

}
