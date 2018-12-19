package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmAocsStateEstimationA {

	private float ESTA_Q_S;
	private float ESTA_Q_X;
	private float ESTA_Q_Y;
	private float ESTA_Q_Z;
	private float ESTA_RATE_X;
	private float ESTA_RATE_Y;
	private float ESTA_RATE_Z;
	private float ESTA_ACC_X;
	private float ESTA_ACC_Y;
	private float ESTA_ACC_Z;
	private int ESTA_B_SAT_X;
	private int ESTA_B_SAT_Y;
	private int ESTA_B_SAT_Z;
	private float ESTA_S_SAT_X;
	private float ESTA_S_SAT_Y;
	private float ESTA_S_SAT_Z;
	private int ESTA_B_TOD_X;
	private int ESTA_B_TOD_Y;
	private int ESTA_B_TOD_Z;
	private float ESTA_S_TOD_X;
	private float ESTA_S_TOD_Y;
	private float ESTA_S_TOD_Z;
	private int ESTA_R_X;
	private int ESTA_R_Y;
	private int ESTA_R_Z;
	private int ESTA_V_X;
	private int ESTA_V_Y;
	private int ESTA_V_Z;
	private boolean ESTA_OCCULTATIO;

	public TmAocsStateEstimationA(DataInputStream dis) throws IOException {
		ESTA_Q_S = dis.readInt() * 0.000001f;
		ESTA_Q_X = dis.readInt() * 0.000001f;
		ESTA_Q_Y = dis.readInt() * 0.000001f;
		ESTA_Q_Z = dis.readInt() * 0.000001f;
		ESTA_RATE_X = dis.readShort() * 0.001f;
		ESTA_RATE_Y = dis.readShort() * 0.001f;
		ESTA_RATE_Z = dis.readShort() * 0.001f;
		ESTA_ACC_X = dis.readShort() * 0.001f;
		ESTA_ACC_Y = dis.readShort() * 0.001f;
		ESTA_ACC_Z = dis.readShort() * 0.001f;
		ESTA_B_SAT_X = dis.readShort() * 50;
		ESTA_B_SAT_Y = dis.readShort() * 50;
		ESTA_B_SAT_Z = dis.readShort() * 50;
		ESTA_S_SAT_X = dis.readShort() * 0.001f;
		ESTA_S_SAT_Y = dis.readShort() * 0.001f;
		ESTA_S_SAT_Z = dis.readShort() * 0.001f;
		ESTA_B_TOD_X = dis.readShort() * 50;
		ESTA_B_TOD_Y = dis.readShort() * 50;
		ESTA_B_TOD_Z = dis.readShort() * 50;
		ESTA_S_TOD_X = dis.readShort() * 0.001f;
		ESTA_S_TOD_Y = dis.readShort() * 0.001f;
		ESTA_S_TOD_Z = dis.readShort() * 0.001f;
		ESTA_R_X = dis.readShort();
		ESTA_R_Y = dis.readShort();
		ESTA_R_Z = dis.readShort();
		ESTA_V_X = dis.readShort();
		ESTA_V_Y = dis.readShort();
		ESTA_V_Z = dis.readShort();
		ESTA_OCCULTATIO = dis.readByte() > 0;
	}

	public float getESTA_Q_S() {
		return ESTA_Q_S;
	}

	public void setESTA_Q_S(float eSTA_Q_S) {
		ESTA_Q_S = eSTA_Q_S;
	}

	public float getESTA_Q_X() {
		return ESTA_Q_X;
	}

	public void setESTA_Q_X(float eSTA_Q_X) {
		ESTA_Q_X = eSTA_Q_X;
	}

	public float getESTA_Q_Y() {
		return ESTA_Q_Y;
	}

	public void setESTA_Q_Y(float eSTA_Q_Y) {
		ESTA_Q_Y = eSTA_Q_Y;
	}

	public float getESTA_Q_Z() {
		return ESTA_Q_Z;
	}

	public void setESTA_Q_Z(float eSTA_Q_Z) {
		ESTA_Q_Z = eSTA_Q_Z;
	}

	public float getESTA_RATE_X() {
		return ESTA_RATE_X;
	}

	public void setESTA_RATE_X(float eSTA_RATE_X) {
		ESTA_RATE_X = eSTA_RATE_X;
	}

	public float getESTA_RATE_Y() {
		return ESTA_RATE_Y;
	}

	public void setESTA_RATE_Y(float eSTA_RATE_Y) {
		ESTA_RATE_Y = eSTA_RATE_Y;
	}

	public float getESTA_RATE_Z() {
		return ESTA_RATE_Z;
	}

	public void setESTA_RATE_Z(float eSTA_RATE_Z) {
		ESTA_RATE_Z = eSTA_RATE_Z;
	}

	public float getESTA_ACC_X() {
		return ESTA_ACC_X;
	}

	public void setESTA_ACC_X(float eSTA_ACC_X) {
		ESTA_ACC_X = eSTA_ACC_X;
	}

	public float getESTA_ACC_Y() {
		return ESTA_ACC_Y;
	}

	public void setESTA_ACC_Y(float eSTA_ACC_Y) {
		ESTA_ACC_Y = eSTA_ACC_Y;
	}

	public float getESTA_ACC_Z() {
		return ESTA_ACC_Z;
	}

	public void setESTA_ACC_Z(float eSTA_ACC_Z) {
		ESTA_ACC_Z = eSTA_ACC_Z;
	}

	public int getESTA_B_SAT_X() {
		return ESTA_B_SAT_X;
	}

	public void setESTA_B_SAT_X(int eSTA_B_SAT_X) {
		ESTA_B_SAT_X = eSTA_B_SAT_X;
	}

	public int getESTA_B_SAT_Y() {
		return ESTA_B_SAT_Y;
	}

	public void setESTA_B_SAT_Y(int eSTA_B_SAT_Y) {
		ESTA_B_SAT_Y = eSTA_B_SAT_Y;
	}

	public int getESTA_B_SAT_Z() {
		return ESTA_B_SAT_Z;
	}

	public void setESTA_B_SAT_Z(int eSTA_B_SAT_Z) {
		ESTA_B_SAT_Z = eSTA_B_SAT_Z;
	}

	public float getESTA_S_SAT_X() {
		return ESTA_S_SAT_X;
	}

	public void setESTA_S_SAT_X(float eSTA_S_SAT_X) {
		ESTA_S_SAT_X = eSTA_S_SAT_X;
	}

	public float getESTA_S_SAT_Y() {
		return ESTA_S_SAT_Y;
	}

	public void setESTA_S_SAT_Y(float eSTA_S_SAT_Y) {
		ESTA_S_SAT_Y = eSTA_S_SAT_Y;
	}

	public float getESTA_S_SAT_Z() {
		return ESTA_S_SAT_Z;
	}

	public void setESTA_S_SAT_Z(float eSTA_S_SAT_Z) {
		ESTA_S_SAT_Z = eSTA_S_SAT_Z;
	}

	public int getESTA_B_TOD_X() {
		return ESTA_B_TOD_X;
	}

	public void setESTA_B_TOD_X(int eSTA_B_TOD_X) {
		ESTA_B_TOD_X = eSTA_B_TOD_X;
	}

	public int getESTA_B_TOD_Y() {
		return ESTA_B_TOD_Y;
	}

	public void setESTA_B_TOD_Y(int eSTA_B_TOD_Y) {
		ESTA_B_TOD_Y = eSTA_B_TOD_Y;
	}

	public int getESTA_B_TOD_Z() {
		return ESTA_B_TOD_Z;
	}

	public void setESTA_B_TOD_Z(int eSTA_B_TOD_Z) {
		ESTA_B_TOD_Z = eSTA_B_TOD_Z;
	}

	public float getESTA_S_TOD_X() {
		return ESTA_S_TOD_X;
	}

	public void setESTA_S_TOD_X(float eSTA_S_TOD_X) {
		ESTA_S_TOD_X = eSTA_S_TOD_X;
	}

	public float getESTA_S_TOD_Y() {
		return ESTA_S_TOD_Y;
	}

	public void setESTA_S_TOD_Y(float eSTA_S_TOD_Y) {
		ESTA_S_TOD_Y = eSTA_S_TOD_Y;
	}

	public float getESTA_S_TOD_Z() {
		return ESTA_S_TOD_Z;
	}

	public void setESTA_S_TOD_Z(float eSTA_S_TOD_Z) {
		ESTA_S_TOD_Z = eSTA_S_TOD_Z;
	}

	public int getESTA_R_X() {
		return ESTA_R_X;
	}

	public void setESTA_R_X(int eSTA_R_X) {
		ESTA_R_X = eSTA_R_X;
	}

	public int getESTA_R_Y() {
		return ESTA_R_Y;
	}

	public void setESTA_R_Y(int eSTA_R_Y) {
		ESTA_R_Y = eSTA_R_Y;
	}

	public int getESTA_R_Z() {
		return ESTA_R_Z;
	}

	public void setESTA_R_Z(int eSTA_R_Z) {
		ESTA_R_Z = eSTA_R_Z;
	}

	public int getESTA_V_X() {
		return ESTA_V_X;
	}

	public void setESTA_V_X(int eSTA_V_X) {
		ESTA_V_X = eSTA_V_X;
	}

	public int getESTA_V_Y() {
		return ESTA_V_Y;
	}

	public void setESTA_V_Y(int eSTA_V_Y) {
		ESTA_V_Y = eSTA_V_Y;
	}

	public int getESTA_V_Z() {
		return ESTA_V_Z;
	}

	public void setESTA_V_Z(int eSTA_V_Z) {
		ESTA_V_Z = eSTA_V_Z;
	}

	public boolean isESTA_OCCULTATIO() {
		return ESTA_OCCULTATIO;
	}

	public void setESTA_OCCULTATIO(boolean eSTA_OCCULTATIO) {
		ESTA_OCCULTATIO = eSTA_OCCULTATIO;
	}

}
