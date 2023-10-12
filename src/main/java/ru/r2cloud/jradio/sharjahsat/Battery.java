package ru.r2cloud.jradio.sharjahsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Battery {

	private float vbat;
	private float ibat;
	private float vpcm3v3;
	private float vpcm5v;
	private float ipcm3v3;
	private float ipcm5v;
	private float tbrd;
	private float tbat1;
	private float tbat2;
	private float tbat3;

	public Battery() {
		// do nothing
	}

	public Battery(LittleEndianDataInputStream dis) throws IOException {
		vbat = dis.readUnsignedShort() * 0.008993f;
		ibat = dis.readShort() * 14.662757f;
		vpcm3v3 = dis.readUnsignedShort() * 0.004311f;
		vpcm5v = dis.readUnsignedShort() * 0.005865f;
		ipcm3v3 = dis.readUnsignedShort() * 1.327547f;
		ipcm5v = dis.readUnsignedShort() * 1.327547f;
		tbrd = (dis.readShort() * 0.372434f) - 273.15f;
		tbat1 = (dis.readShort() * 0.3976f) - 238.57f;
		tbat2 = (dis.readShort() * 0.3976f) - 238.57f;
		tbat3 = (dis.readShort() * 0.3976f) - 238.57f;
	}

	public float getVbat() {
		return vbat;
	}

	public void setVbat(float vbat) {
		this.vbat = vbat;
	}

	public float getIbat() {
		return ibat;
	}

	public void setIbat(float ibat) {
		this.ibat = ibat;
	}

	public float getVpcm3v3() {
		return vpcm3v3;
	}

	public void setVpcm3v3(float vpcm3v3) {
		this.vpcm3v3 = vpcm3v3;
	}

	public float getVpcm5v() {
		return vpcm5v;
	}

	public void setVpcm5v(float vpcm5v) {
		this.vpcm5v = vpcm5v;
	}

	public float getIpcm3v3() {
		return ipcm3v3;
	}

	public void setIpcm3v3(float ipcm3v3) {
		this.ipcm3v3 = ipcm3v3;
	}

	public float getIpcm5v() {
		return ipcm5v;
	}

	public void setIpcm5v(float ipcm5v) {
		this.ipcm5v = ipcm5v;
	}

	public float getTbrd() {
		return tbrd;
	}

	public void setTbrd(float tbrd) {
		this.tbrd = tbrd;
	}

	public float getTbat1() {
		return tbat1;
	}

	public void setTbat1(float tbat1) {
		this.tbat1 = tbat1;
	}

	public float getTbat2() {
		return tbat2;
	}

	public void setTbat2(float tbat2) {
		this.tbat2 = tbat2;
	}

	public float getTbat3() {
		return tbat3;
	}

	public void setTbat3(float tbat3) {
		this.tbat3 = tbat3;
	}

}
