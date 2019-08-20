package ru.r2cloud.jradio.aistechsat3;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class ADCSFineSunSensorBeacon {

	private float[] extmag;
	private float[] gyro;
	private float[] torquerDuty;
	private float[] wheelSpeed;
	private int[] wheelCur;
	private short[] wheelTemp;
	private float[] fssTemp;
	private byte spinMode;
	private byte statusUkf;
	private byte statusSgp4;
	private byte statusigrf;

	public ADCSFineSunSensorBeacon() {
		// do nothing
	}

	public ADCSFineSunSensorBeacon(DataInputStream dis) throws IOException {
		extmag = StreamUtils.readFloatArray(dis, 3);
		gyro = StreamUtils.readFloatArray(dis, 3);
		torquerDuty = StreamUtils.readFloatArray(dis, 3);
		wheelSpeed = StreamUtils.readFloatArray(dis, 4);
		wheelCur = StreamUtils.readUnsignedShortArray(dis, 4);
		wheelTemp = StreamUtils.readShortArray(dis, 4);
		fssTemp = StreamUtils.readFloatArray(dis, 8);
		spinMode = dis.readByte();
		statusUkf = dis.readByte();
		statusSgp4 = dis.readByte();
		statusigrf = dis.readByte();
	}

	public float[] getExtmag() {
		return extmag;
	}

	public void setExtmag(float[] extmag) {
		this.extmag = extmag;
	}

	public float[] getGyro() {
		return gyro;
	}

	public void setGyro(float[] gyro) {
		this.gyro = gyro;
	}

	public float[] getTorquerDuty() {
		return torquerDuty;
	}

	public void setTorquerDuty(float[] torquerDuty) {
		this.torquerDuty = torquerDuty;
	}

	public float[] getWheelSpeed() {
		return wheelSpeed;
	}

	public void setWheelSpeed(float[] wheelSpeed) {
		this.wheelSpeed = wheelSpeed;
	}

	public int[] getWheelCur() {
		return wheelCur;
	}

	public void setWheelCur(int[] wheelCur) {
		this.wheelCur = wheelCur;
	}

	public short[] getWheelTemp() {
		return wheelTemp;
	}

	public void setWheelTemp(short[] wheelTemp) {
		this.wheelTemp = wheelTemp;
	}

	public float[] getFssTemp() {
		return fssTemp;
	}

	public void setFssTemp(float[] fssTemp) {
		this.fssTemp = fssTemp;
	}

	public byte getSpinMode() {
		return spinMode;
	}

	public void setSpinMode(byte spinMode) {
		this.spinMode = spinMode;
	}

	public byte getStatusUkf() {
		return statusUkf;
	}

	public void setStatusUkf(byte statusUkf) {
		this.statusUkf = statusUkf;
	}

	public byte getStatusSgp4() {
		return statusSgp4;
	}

	public void setStatusSgp4(byte statusSgp4) {
		this.statusSgp4 = statusSgp4;
	}

	public byte getStatusigrf() {
		return statusigrf;
	}

	public void setStatusigrf(byte statusigrf) {
		this.statusigrf = statusigrf;
	}

}
