package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid59 {

	private float acsixx; // Moment Of Inertia x,x
	private float acsiyx; // Moment Of Inertia y,x
	private float acsizx; // Moment Of Inertia z,x
	private float acsixy; // Moment Of Inertia x,y
	private float acsiyy; // Moment Of Inertia y,y
	private float acsizy; // Moment Of Inertia z,y
	private float acsixz; // Moment Of Inertia x,z
	private float acsiyz; // Moment Of Inertia y,z
	private float acsizz; // Moment Of Inertia z,z
	private float acscdm; // c_dpm
	private float acscsm; // c_slm
	private float acsksm; // k_slm
	private float acsism; // i_slm
	private float acsasm; // Slew Amplifier
	private float acsdsm; // Disturbance Amplifier
	private float acsdix; // Dipole X
	private float acsdiy; // Dipole Y
	private float acsdiz; // Dipole Z
	private float acssek; // SEPM k
	private float acsseg; // SEPM g
	private float acssee; // SEPM epsilon
	private float acssik; // SIPM k
	private float acssig; // SIPM g
	private float acssie; // SIPM epsilon
	private float acsstk; // STPM k
	private float acsstg; // STPM g
	private float acsste; // STPM epsilon
	private float acsszk; // ZPM k
	private float acsszg; // ZPM g
	private float acssze; // ZPM epsilon

	private boolean acsosm; // Only Slew Mode

	public Apid59(DataInputStream dis) throws IOException {
		acsixx = dis.readFloat();
		acsiyx = dis.readFloat();
		acsizx = dis.readFloat();
		acsixy = dis.readFloat();
		acsiyy = dis.readFloat();
		acsizy = dis.readFloat();
		acsixz = dis.readFloat();
		acsiyz = dis.readFloat();
		acsizz = dis.readFloat();
		acscdm = dis.readFloat();
		acscsm = dis.readFloat();
		acsksm = dis.readFloat();
		acsism = dis.readFloat();
		acsasm = dis.readFloat();
		acsdsm = dis.readFloat();
		acsdix = dis.readFloat();
		acsdiy = dis.readFloat();
		acsdiz = dis.readFloat();
		acssek = dis.readFloat();
		acsseg = dis.readFloat();
		acssee = dis.readFloat();
		acssik = dis.readFloat();
		acssig = dis.readFloat();
		acssie = dis.readFloat();
		acsstk = dis.readFloat();
		acsstg = dis.readFloat();
		acsste = dis.readFloat();
		acsszk = dis.readFloat();
		acsszg = dis.readFloat();
		acssze = dis.readFloat();
		dis.skipBytes(5);
		int raw = dis.readUnsignedByte();
		acsosm = (raw & 0x1) > 0;
	}

	public float getAcsixx() {
		return acsixx;
	}

	public void setAcsixx(float acsixx) {
		this.acsixx = acsixx;
	}

	public float getAcsiyx() {
		return acsiyx;
	}

	public void setAcsiyx(float acsiyx) {
		this.acsiyx = acsiyx;
	}

	public float getAcsizx() {
		return acsizx;
	}

	public void setAcsizx(float acsizx) {
		this.acsizx = acsizx;
	}

	public float getAcsixy() {
		return acsixy;
	}

	public void setAcsixy(float acsixy) {
		this.acsixy = acsixy;
	}

	public float getAcsiyy() {
		return acsiyy;
	}

	public void setAcsiyy(float acsiyy) {
		this.acsiyy = acsiyy;
	}

	public float getAcsizy() {
		return acsizy;
	}

	public void setAcsizy(float acsizy) {
		this.acsizy = acsizy;
	}

	public float getAcsixz() {
		return acsixz;
	}

	public void setAcsixz(float acsixz) {
		this.acsixz = acsixz;
	}

	public float getAcsiyz() {
		return acsiyz;
	}

	public void setAcsiyz(float acsiyz) {
		this.acsiyz = acsiyz;
	}

	public float getAcsizz() {
		return acsizz;
	}

	public void setAcsizz(float acsizz) {
		this.acsizz = acsizz;
	}

	public float getAcscdm() {
		return acscdm;
	}

	public void setAcscdm(float acscdm) {
		this.acscdm = acscdm;
	}

	public float getAcscsm() {
		return acscsm;
	}

	public void setAcscsm(float acscsm) {
		this.acscsm = acscsm;
	}

	public float getAcsksm() {
		return acsksm;
	}

	public void setAcsksm(float acsksm) {
		this.acsksm = acsksm;
	}

	public float getAcsism() {
		return acsism;
	}

	public void setAcsism(float acsism) {
		this.acsism = acsism;
	}

	public float getAcsasm() {
		return acsasm;
	}

	public void setAcsasm(float acsasm) {
		this.acsasm = acsasm;
	}

	public float getAcsdsm() {
		return acsdsm;
	}

	public void setAcsdsm(float acsdsm) {
		this.acsdsm = acsdsm;
	}

	public float getAcsdix() {
		return acsdix;
	}

	public void setAcsdix(float acsdix) {
		this.acsdix = acsdix;
	}

	public float getAcsdiy() {
		return acsdiy;
	}

	public void setAcsdiy(float acsdiy) {
		this.acsdiy = acsdiy;
	}

	public float getAcsdiz() {
		return acsdiz;
	}

	public void setAcsdiz(float acsdiz) {
		this.acsdiz = acsdiz;
	}

	public float getAcssek() {
		return acssek;
	}

	public void setAcssek(float acssek) {
		this.acssek = acssek;
	}

	public float getAcsseg() {
		return acsseg;
	}

	public void setAcsseg(float acsseg) {
		this.acsseg = acsseg;
	}

	public float getAcssee() {
		return acssee;
	}

	public void setAcssee(float acssee) {
		this.acssee = acssee;
	}

	public float getAcssik() {
		return acssik;
	}

	public void setAcssik(float acssik) {
		this.acssik = acssik;
	}

	public float getAcssig() {
		return acssig;
	}

	public void setAcssig(float acssig) {
		this.acssig = acssig;
	}

	public float getAcssie() {
		return acssie;
	}

	public void setAcssie(float acssie) {
		this.acssie = acssie;
	}

	public float getAcsstk() {
		return acsstk;
	}

	public void setAcsstk(float acsstk) {
		this.acsstk = acsstk;
	}

	public float getAcsstg() {
		return acsstg;
	}

	public void setAcsstg(float acsstg) {
		this.acsstg = acsstg;
	}

	public float getAcsste() {
		return acsste;
	}

	public void setAcsste(float acsste) {
		this.acsste = acsste;
	}

	public float getAcsszk() {
		return acsszk;
	}

	public void setAcsszk(float acsszk) {
		this.acsszk = acsszk;
	}

	public float getAcsszg() {
		return acsszg;
	}

	public void setAcsszg(float acsszg) {
		this.acsszg = acsszg;
	}

	public float getAcssze() {
		return acssze;
	}

	public void setAcssze(float acssze) {
		this.acssze = acssze;
	}

	public boolean isAcsosm() {
		return acsosm;
	}

	public void setAcsosm(boolean acsosm) {
		this.acsosm = acsosm;
	}

}
