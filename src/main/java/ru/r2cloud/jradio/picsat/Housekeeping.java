package ru.r2cloud.jradio.picsat;

import java.io.DataInputStream;
import java.io.IOException;

public class Housekeeping {

	private float volt5;
	private float amp5;
	private float amp3;
	private float volthv;
	private float amphv;
	private int vitec;
	private int temp0;
	private int errortherm;
	private float vref;
	private float temp1;
	private float temp2;
	private float temp3;
	private float temp4;
	private short pitch;
	private short roll;
	private short yaw;

	public Housekeeping() {
		// do nothing
	}

	public Housekeeping(DataInputStream dis) throws IOException {
		volt5 = 4.59e-3f * dis.readUnsignedShort();
		amp5 = 1.16e-3f * dis.readUnsignedShort();
		amp3 = 1.16e-3f * dis.readUnsignedShort();
		volthv = 0.172226f * dis.readUnsignedShort();
		amphv = 0.00161f * dis.readUnsignedShort();
		vitec = dis.readUnsignedShort();
		temp0 = dis.readUnsignedShort();
		errortherm = dis.readUnsignedShort();
		vref = 0.00161f * dis.readUnsignedShort();
		temp1 = dis.readShort() / 16.0f;
		temp2 = dis.readShort() / 16.0f;
		temp3 = dis.readShort() / 16.0f;
		temp4 = dis.readShort() / 16.0f;
		pitch = dis.readShort();
		roll = dis.readShort();
		yaw = dis.readShort();
	}

	public float getVolt5() {
		return volt5;
	}

	public void setVolt5(float volt5) {
		this.volt5 = volt5;
	}

	public float getAmp5() {
		return amp5;
	}

	public void setAmp5(float amp5) {
		this.amp5 = amp5;
	}

	public float getAmp3() {
		return amp3;
	}

	public void setAmp3(float amp3) {
		this.amp3 = amp3;
	}

	public float getVolthv() {
		return volthv;
	}

	public void setVolthv(float volthv) {
		this.volthv = volthv;
	}

	public float getAmphv() {
		return amphv;
	}

	public void setAmphv(float amphv) {
		this.amphv = amphv;
	}

	public int getVitec() {
		return vitec;
	}

	public void setVitec(int vitec) {
		this.vitec = vitec;
	}

	public int getTemp0() {
		return temp0;
	}

	public void setTemp0(int temp0) {
		this.temp0 = temp0;
	}

	public int getErrortherm() {
		return errortherm;
	}

	public void setErrortherm(int errortherm) {
		this.errortherm = errortherm;
	}

	public float getVref() {
		return vref;
	}

	public void setVref(float vref) {
		this.vref = vref;
	}

	public float getTemp1() {
		return temp1;
	}

	public void setTemp1(float temp1) {
		this.temp1 = temp1;
	}

	public float getTemp2() {
		return temp2;
	}

	public void setTemp2(float temp2) {
		this.temp2 = temp2;
	}

	public float getTemp3() {
		return temp3;
	}

	public void setTemp3(float temp3) {
		this.temp3 = temp3;
	}

	public float getTemp4() {
		return temp4;
	}

	public void setTemp4(float temp4) {
		this.temp4 = temp4;
	}

	public short getPitch() {
		return pitch;
	}

	public void setPitch(short pitch) {
		this.pitch = pitch;
	}

	public short getRoll() {
		return roll;
	}

	public void setRoll(short roll) {
		this.roll = roll;
	}

	public short getYaw() {
		return yaw;
	}

	public void setYaw(short yaw) {
		this.yaw = yaw;
	}

}
