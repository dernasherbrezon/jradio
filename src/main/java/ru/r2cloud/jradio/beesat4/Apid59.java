package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid59 {

	private float ACSIXX;           //  Moment Of Inertia x,x
	private float ACSIYX;           //  Moment Of Inertia y,x
	private float ACSIZX;           //  Moment Of Inertia z,x
	private float ACSIXY;           //  Moment Of Inertia x,y
	private float ACSIYY;           //  Moment Of Inertia y,y
	private float ACSIZY;           //  Moment Of Inertia z,y
	private float ACSIXZ;           //  Moment Of Inertia x,z
	private float ACSIYZ;           //  Moment Of Inertia y,z
	private float ACSIZZ;           //  Moment Of Inertia z,z
	private float ACSCDM;           //  c_dpm
	private float ACSCSM;           //  c_slm
	private float ACSKSM;           //  k_slm
	private float ACSISM;           //  i_slm
	private float ACSASM;           //  Slew Amplifier
	private float ACSDSM;           //  Disturbance Amplifier
	private float ACSDIX;           //  Dipole X
	private float ACSDIY;           //  Dipole Y
	private float ACSDIZ;           //  Dipole Z
	private float ACSSEK;           //  SEPM k
	private float ACSSEG;           //  SEPM g
	private float ACSSEE;           //  SEPM epsilon
	private float ACSSIK;           //  SIPM k
	private float ACSSIG;           //  SIPM g
	private float ACSSIE;           //  SIPM epsilon
	private float ACSSTK;           //  STPM k
	private float ACSSTG;           //  STPM g
	private float ACSSTE;           //  STPM epsilon
	private float ACSSZK;           //  ZPM k
	private float ACSSZG;           //  ZPM g
	private float ACSSZE;           //  ZPM epsilon
                                    
	private boolean ACSOSM;         //  Only Slew Mode

	public Apid59(DataInputStream dis) throws IOException {
		ACSIXX = dis.readFloat();
		ACSIYX = dis.readFloat();
		ACSIZX = dis.readFloat();
		ACSIXY = dis.readFloat();
		ACSIYY = dis.readFloat();
		ACSIZY = dis.readFloat();
		ACSIXZ = dis.readFloat();
		ACSIYZ = dis.readFloat();
		ACSIZZ = dis.readFloat();
		ACSCDM = dis.readFloat();
		ACSCSM = dis.readFloat();
		ACSKSM = dis.readFloat();
		ACSISM = dis.readFloat();
		ACSASM = dis.readFloat();
		ACSDSM = dis.readFloat();
		ACSDIX = dis.readFloat();
		ACSDIY = dis.readFloat();
		ACSDIZ = dis.readFloat();
		ACSSEK = dis.readFloat();
		ACSSEG = dis.readFloat();
		ACSSEE = dis.readFloat();
		ACSSIK = dis.readFloat();
		ACSSIG = dis.readFloat();
		ACSSIE = dis.readFloat();
		ACSSTK = dis.readFloat();
		ACSSTG = dis.readFloat();
		ACSSTE = dis.readFloat();
		ACSSZK = dis.readFloat();
		ACSSZG = dis.readFloat();
		ACSSZE = dis.readFloat();
		dis.skipBytes(5);
		int raw = dis.readUnsignedByte();
		ACSOSM = (raw & 0x1) > 0;
	}

	public float getACSIXX() {
		return ACSIXX;
	}

	public void setACSIXX(float aCSIXX) {
		ACSIXX = aCSIXX;
	}

	public float getACSIYX() {
		return ACSIYX;
	}

	public void setACSIYX(float aCSIYX) {
		ACSIYX = aCSIYX;
	}

	public float getACSIZX() {
		return ACSIZX;
	}

	public void setACSIZX(float aCSIZX) {
		ACSIZX = aCSIZX;
	}

	public float getACSIXY() {
		return ACSIXY;
	}

	public void setACSIXY(float aCSIXY) {
		ACSIXY = aCSIXY;
	}

	public float getACSIYY() {
		return ACSIYY;
	}

	public void setACSIYY(float aCSIYY) {
		ACSIYY = aCSIYY;
	}

	public float getACSIZY() {
		return ACSIZY;
	}

	public void setACSIZY(float aCSIZY) {
		ACSIZY = aCSIZY;
	}

	public float getACSIXZ() {
		return ACSIXZ;
	}

	public void setACSIXZ(float aCSIXZ) {
		ACSIXZ = aCSIXZ;
	}

	public float getACSIYZ() {
		return ACSIYZ;
	}

	public void setACSIYZ(float aCSIYZ) {
		ACSIYZ = aCSIYZ;
	}

	public float getACSIZZ() {
		return ACSIZZ;
	}

	public void setACSIZZ(float aCSIZZ) {
		ACSIZZ = aCSIZZ;
	}

	public float getACSCDM() {
		return ACSCDM;
	}

	public void setACSCDM(float aCSCDM) {
		ACSCDM = aCSCDM;
	}

	public float getACSCSM() {
		return ACSCSM;
	}

	public void setACSCSM(float aCSCSM) {
		ACSCSM = aCSCSM;
	}

	public float getACSKSM() {
		return ACSKSM;
	}

	public void setACSKSM(float aCSKSM) {
		ACSKSM = aCSKSM;
	}

	public float getACSISM() {
		return ACSISM;
	}

	public void setACSISM(float aCSISM) {
		ACSISM = aCSISM;
	}

	public float getACSASM() {
		return ACSASM;
	}

	public void setACSASM(float aCSASM) {
		ACSASM = aCSASM;
	}

	public float getACSDSM() {
		return ACSDSM;
	}

	public void setACSDSM(float aCSDSM) {
		ACSDSM = aCSDSM;
	}

	public float getACSDIX() {
		return ACSDIX;
	}

	public void setACSDIX(float aCSDIX) {
		ACSDIX = aCSDIX;
	}

	public float getACSDIY() {
		return ACSDIY;
	}

	public void setACSDIY(float aCSDIY) {
		ACSDIY = aCSDIY;
	}

	public float getACSDIZ() {
		return ACSDIZ;
	}

	public void setACSDIZ(float aCSDIZ) {
		ACSDIZ = aCSDIZ;
	}

	public float getACSSEK() {
		return ACSSEK;
	}

	public void setACSSEK(float aCSSEK) {
		ACSSEK = aCSSEK;
	}

	public float getACSSEG() {
		return ACSSEG;
	}

	public void setACSSEG(float aCSSEG) {
		ACSSEG = aCSSEG;
	}

	public float getACSSEE() {
		return ACSSEE;
	}

	public void setACSSEE(float aCSSEE) {
		ACSSEE = aCSSEE;
	}

	public float getACSSIK() {
		return ACSSIK;
	}

	public void setACSSIK(float aCSSIK) {
		ACSSIK = aCSSIK;
	}

	public float getACSSIG() {
		return ACSSIG;
	}

	public void setACSSIG(float aCSSIG) {
		ACSSIG = aCSSIG;
	}

	public float getACSSIE() {
		return ACSSIE;
	}

	public void setACSSIE(float aCSSIE) {
		ACSSIE = aCSSIE;
	}

	public float getACSSTK() {
		return ACSSTK;
	}

	public void setACSSTK(float aCSSTK) {
		ACSSTK = aCSSTK;
	}

	public float getACSSTG() {
		return ACSSTG;
	}

	public void setACSSTG(float aCSSTG) {
		ACSSTG = aCSSTG;
	}

	public float getACSSTE() {
		return ACSSTE;
	}

	public void setACSSTE(float aCSSTE) {
		ACSSTE = aCSSTE;
	}

	public float getACSSZK() {
		return ACSSZK;
	}

	public void setACSSZK(float aCSSZK) {
		ACSSZK = aCSSZK;
	}

	public float getACSSZG() {
		return ACSSZG;
	}

	public void setACSSZG(float aCSSZG) {
		ACSSZG = aCSSZG;
	}

	public float getACSSZE() {
		return ACSSZE;
	}

	public void setACSSZE(float aCSSZE) {
		ACSSZE = aCSSZE;
	}

	public boolean isACSOSM() {
		return ACSOSM;
	}

	public void setACSOSM(boolean aCSOSM) {
		ACSOSM = aCSOSM;
	}

}
