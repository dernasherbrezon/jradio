package ru.r2cloud.jradio.sharjahsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Eps {

	private float vpcmbatv;
	private float ipcmbatv;
	private float vpcm3v3;
	private float ipcm3v3;
	private float vpcm5v;
	private float ipcm5v;
	private float i3v3drw;
	private float i5vdrw;
	private float tbrd;
	private float tbrdDb;
	private float ipcm12v;
	private float vpcm12v;

	public Eps() {
		// do nothing
	}

	public Eps(LittleEndianDataInputStream dis) throws IOException {
		vpcmbatv = dis.readUnsignedShort() * 0.008978f;
		ipcmbatv = dis.readShort() * 0.00681988679f;
		vpcm3v3 = dis.readUnsignedShort() * 0.004311f;
		ipcm3v3 = dis.readUnsignedShort() * 0.00681988679f;
		vpcm5v = dis.readUnsignedShort() * 0.005865f;
		ipcm5v = dis.readUnsignedShort() * 0.00681988679f;
		i3v3drw = dis.readUnsignedShort() * 0.001327547f;
		i5vdrw = dis.readUnsignedShort() * 0.001327547f;
		tbrd = (dis.readUnsignedShort() * 0.372434f) - 273.15f;
		tbrdDb = (dis.readUnsignedShort() * 0.372434f) - 273.15f;
		ipcm12v = dis.readUnsignedShort() * 0.002066632361f;
		vpcm12v = dis.readUnsignedShort() * 0.01349f;
	}

	public float getVpcmbatv() {
		return vpcmbatv;
	}

	public void setVpcmbatv(float vpcmbatv) {
		this.vpcmbatv = vpcmbatv;
	}

	public float getIpcmbatv() {
		return ipcmbatv;
	}

	public void setIpcmbatv(float ipcmbatv) {
		this.ipcmbatv = ipcmbatv;
	}

	public float getVpcm3v3() {
		return vpcm3v3;
	}

	public void setVpcm3v3(float vpcm3v3) {
		this.vpcm3v3 = vpcm3v3;
	}

	public float getIpcm3v3() {
		return ipcm3v3;
	}

	public void setIpcm3v3(float ipcm3v3) {
		this.ipcm3v3 = ipcm3v3;
	}

	public float getVpcm5v() {
		return vpcm5v;
	}

	public void setVpcm5v(float vpcm5v) {
		this.vpcm5v = vpcm5v;
	}

	public float getIpcm5v() {
		return ipcm5v;
	}

	public void setIpcm5v(float ipcm5v) {
		this.ipcm5v = ipcm5v;
	}

	public float getI3v3drw() {
		return i3v3drw;
	}

	public void setI3v3drw(float i3v3drw) {
		this.i3v3drw = i3v3drw;
	}

	public float getI5vdrw() {
		return i5vdrw;
	}

	public void setI5vdrw(float i5vdrw) {
		this.i5vdrw = i5vdrw;
	}

	public float getTbrd() {
		return tbrd;
	}

	public void setTbrd(float tbrd) {
		this.tbrd = tbrd;
	}

	public float getTbrdDb() {
		return tbrdDb;
	}

	public void setTbrdDb(float tbrdDb) {
		this.tbrdDb = tbrdDb;
	}

	public float getIpcm12v() {
		return ipcm12v;
	}

	public void setIpcm12v(float ipcm12v) {
		this.ipcm12v = ipcm12v;
	}

	public float getVpcm12v() {
		return vpcm12v;
	}

	public void setVpcm12v(float vpcm12v) {
		this.vpcm12v = vpcm12v;
	}

}
