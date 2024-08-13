package ru.r2cloud.jradio.eirsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class EpsSolar {

	private float currents1Plusx;
	private float currents4Minusx;
	private float currents3Plusy;
	private float currents2Minusy;
	private float voltages0;
	private float voltages1;
	private float voltages2;

	public EpsSolar() {
		// do nothing
	}

	public EpsSolar(BitInputStream dis) throws IOException {
		currents1Plusx = dis.readUnsignedInt(10) * 0.0009775f;
		currents4Minusx = dis.readUnsignedInt(10) * 0.0009775f;
		currents3Plusy = dis.readUnsignedInt(10) * 0.0009775f;
		currents2Minusy = dis.readUnsignedInt(10) * 0.0009775f;
		voltages0 = dis.readUnsignedInt(10) * 0.0322581f;
		voltages1 = dis.readUnsignedInt(10) * 0.0322581f;
		voltages2 = dis.readUnsignedInt(10) * 0.0322581f;
	}

	public float getCurrents1Plusx() {
		return currents1Plusx;
	}

	public void setCurrents1Plusx(float currents1Plusx) {
		this.currents1Plusx = currents1Plusx;
	}

	public float getCurrents4Minusx() {
		return currents4Minusx;
	}

	public void setCurrents4Minusx(float currents4Minusx) {
		this.currents4Minusx = currents4Minusx;
	}

	public float getCurrents3Plusy() {
		return currents3Plusy;
	}

	public void setCurrents3Plusy(float currents3Plusy) {
		this.currents3Plusy = currents3Plusy;
	}

	public float getCurrents2Minusy() {
		return currents2Minusy;
	}

	public void setCurrents2Minusy(float currents2Minusy) {
		this.currents2Minusy = currents2Minusy;
	}

	public float getVoltages0() {
		return voltages0;
	}

	public void setVoltages0(float voltages0) {
		this.voltages0 = voltages0;
	}

	public float getVoltages1() {
		return voltages1;
	}

	public void setVoltages1(float voltages1) {
		this.voltages1 = voltages1;
	}

	public float getVoltages2() {
		return voltages2;
	}

	public void setVoltages2(float voltages2) {
		this.voltages2 = voltages2;
	}

}
