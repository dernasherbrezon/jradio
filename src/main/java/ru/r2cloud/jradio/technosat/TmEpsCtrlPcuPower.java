package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmEpsCtrlPcuPower {

	private float SOLVOLTA;
	private float SOLVOLTB;
	private float SP_V_ADC_A;
	private short SP_C_ADC_A;
	private float SP_V_ADC_B;
	private short SP_C_ADC_B;
	private float BATVOLTA_ADC;
	private float BATVOLTB_ADC;
	private float MAIN_12V_A;
	private float MAIN_12V_B;
	private float MAIN_5V_A;
	private float MAIN_5V_B;
	private float MAIN_3V3_A;
	private float MAIN_3V3_B;
	private int SUM_MAIN_12V_A;
	private int SUM_MAIN_12V_B;
	private int SUM_MAIN_5V_A;
	private int SUM_MAIN_5V_B;
	private int SUM_MAIN_3V3_A;
	private int SUM_MAIN_3V3_B;
	private float MAIN_Unreg_A;
	private float MAIN_Unreg_B;
	private int SUM_MAIN_Unreg_A;
	private int SUM_MAIN_Unreg_B;
	private float PCU_TEMP_A;
	private float PCU_TEMP_B;

	public TmEpsCtrlPcuPower(DataInputStream dis) throws IOException {
		SOLVOLTA = dis.readUnsignedShort() * 0.001f;
		SOLVOLTB = dis.readUnsignedShort() * 0.001f;
		SP_V_ADC_A = dis.readUnsignedShort() * 0.001f;
		SP_C_ADC_A = dis.readShort();
		SP_V_ADC_B = dis.readUnsignedShort() * 0.001f;
		SP_C_ADC_B = dis.readShort();
		BATVOLTA_ADC = dis.readUnsignedShort() * 0.001f;
		BATVOLTB_ADC = dis.readUnsignedShort() * 0.001f;
		MAIN_12V_A = dis.readUnsignedByte() * 0.055f;
		MAIN_12V_B = dis.readUnsignedByte() * 0.055f;
		MAIN_5V_A = dis.readUnsignedByte() * 0.022f;
		MAIN_5V_B = dis.readUnsignedByte() * 0.022f;
		MAIN_3V3_A = dis.readUnsignedByte() * 0.022f;
		MAIN_3V3_B = dis.readUnsignedByte() * 0.022f;
		SUM_MAIN_12V_A = dis.readUnsignedByte() * 5;
		SUM_MAIN_12V_B = dis.readUnsignedByte() * 5;
		SUM_MAIN_5V_A = dis.readUnsignedByte() * 25;
		SUM_MAIN_5V_B = dis.readUnsignedByte() * 25;
		SUM_MAIN_3V3_A = dis.readUnsignedByte() * 25;
		SUM_MAIN_3V3_B = dis.readUnsignedByte() * 25;
		MAIN_Unreg_A = dis.readUnsignedByte() * 0.4f;
		MAIN_Unreg_B = dis.readUnsignedByte() * 0.4f;
		SUM_MAIN_Unreg_A = dis.readUnsignedByte() * 50;
		SUM_MAIN_Unreg_B = dis.readUnsignedByte() * 15;
		PCU_TEMP_A = dis.readByte() * 0.9862519685f;
		PCU_TEMP_B = dis.readByte() * 0.9862519685f;
	}

	public float getSOLVOLTA() {
		return SOLVOLTA;
	}

	public void setSOLVOLTA(float sOLVOLTA) {
		SOLVOLTA = sOLVOLTA;
	}

	public float getSOLVOLTB() {
		return SOLVOLTB;
	}

	public void setSOLVOLTB(float sOLVOLTB) {
		SOLVOLTB = sOLVOLTB;
	}

	public float getSP_V_ADC_A() {
		return SP_V_ADC_A;
	}

	public void setSP_V_ADC_A(float sP_V_ADC_A) {
		SP_V_ADC_A = sP_V_ADC_A;
	}

	public short getSP_C_ADC_A() {
		return SP_C_ADC_A;
	}

	public void setSP_C_ADC_A(short sP_C_ADC_A) {
		SP_C_ADC_A = sP_C_ADC_A;
	}

	public float getSP_V_ADC_B() {
		return SP_V_ADC_B;
	}

	public void setSP_V_ADC_B(float sP_V_ADC_B) {
		SP_V_ADC_B = sP_V_ADC_B;
	}

	public short getSP_C_ADC_B() {
		return SP_C_ADC_B;
	}

	public void setSP_C_ADC_B(short sP_C_ADC_B) {
		SP_C_ADC_B = sP_C_ADC_B;
	}

	public float getBATVOLTA_ADC() {
		return BATVOLTA_ADC;
	}

	public void setBATVOLTA_ADC(float bATVOLTA_ADC) {
		BATVOLTA_ADC = bATVOLTA_ADC;
	}

	public float getBATVOLTB_ADC() {
		return BATVOLTB_ADC;
	}

	public void setBATVOLTB_ADC(float bATVOLTB_ADC) {
		BATVOLTB_ADC = bATVOLTB_ADC;
	}

	public float getMAIN_12V_A() {
		return MAIN_12V_A;
	}

	public void setMAIN_12V_A(float mAIN_12V_A) {
		MAIN_12V_A = mAIN_12V_A;
	}

	public float getMAIN_12V_B() {
		return MAIN_12V_B;
	}

	public void setMAIN_12V_B(float mAIN_12V_B) {
		MAIN_12V_B = mAIN_12V_B;
	}

	public float getMAIN_5V_A() {
		return MAIN_5V_A;
	}

	public void setMAIN_5V_A(float mAIN_5V_A) {
		MAIN_5V_A = mAIN_5V_A;
	}

	public float getMAIN_5V_B() {
		return MAIN_5V_B;
	}

	public void setMAIN_5V_B(float mAIN_5V_B) {
		MAIN_5V_B = mAIN_5V_B;
	}

	public float getMAIN_3V3_A() {
		return MAIN_3V3_A;
	}

	public void setMAIN_3V3_A(float mAIN_3V3_A) {
		MAIN_3V3_A = mAIN_3V3_A;
	}

	public float getMAIN_3V3_B() {
		return MAIN_3V3_B;
	}

	public void setMAIN_3V3_B(float mAIN_3V3_B) {
		MAIN_3V3_B = mAIN_3V3_B;
	}

	public int getSUM_MAIN_12V_A() {
		return SUM_MAIN_12V_A;
	}

	public void setSUM_MAIN_12V_A(int sUM_MAIN_12V_A) {
		SUM_MAIN_12V_A = sUM_MAIN_12V_A;
	}

	public int getSUM_MAIN_12V_B() {
		return SUM_MAIN_12V_B;
	}

	public void setSUM_MAIN_12V_B(int sUM_MAIN_12V_B) {
		SUM_MAIN_12V_B = sUM_MAIN_12V_B;
	}

	public int getSUM_MAIN_5V_A() {
		return SUM_MAIN_5V_A;
	}

	public void setSUM_MAIN_5V_A(int sUM_MAIN_5V_A) {
		SUM_MAIN_5V_A = sUM_MAIN_5V_A;
	}

	public int getSUM_MAIN_5V_B() {
		return SUM_MAIN_5V_B;
	}

	public void setSUM_MAIN_5V_B(int sUM_MAIN_5V_B) {
		SUM_MAIN_5V_B = sUM_MAIN_5V_B;
	}

	public int getSUM_MAIN_3V3_A() {
		return SUM_MAIN_3V3_A;
	}

	public void setSUM_MAIN_3V3_A(int sUM_MAIN_3V3_A) {
		SUM_MAIN_3V3_A = sUM_MAIN_3V3_A;
	}

	public int getSUM_MAIN_3V3_B() {
		return SUM_MAIN_3V3_B;
	}

	public void setSUM_MAIN_3V3_B(int sUM_MAIN_3V3_B) {
		SUM_MAIN_3V3_B = sUM_MAIN_3V3_B;
	}

	public float getMAIN_Unreg_A() {
		return MAIN_Unreg_A;
	}

	public void setMAIN_Unreg_A(float mAIN_Unreg_A) {
		MAIN_Unreg_A = mAIN_Unreg_A;
	}

	public float getMAIN_Unreg_B() {
		return MAIN_Unreg_B;
	}

	public void setMAIN_Unreg_B(float mAIN_Unreg_B) {
		MAIN_Unreg_B = mAIN_Unreg_B;
	}

	public int getSUM_MAIN_Unreg_A() {
		return SUM_MAIN_Unreg_A;
	}

	public void setSUM_MAIN_Unreg_A(int sUM_MAIN_Unreg_A) {
		SUM_MAIN_Unreg_A = sUM_MAIN_Unreg_A;
	}

	public int getSUM_MAIN_Unreg_B() {
		return SUM_MAIN_Unreg_B;
	}

	public void setSUM_MAIN_Unreg_B(int sUM_MAIN_Unreg_B) {
		SUM_MAIN_Unreg_B = sUM_MAIN_Unreg_B;
	}

	public float getPCU_TEMP_A() {
		return PCU_TEMP_A;
	}

	public void setPCU_TEMP_A(float pCU_TEMP_A) {
		PCU_TEMP_A = pCU_TEMP_A;
	}

	public float getPCU_TEMP_B() {
		return PCU_TEMP_B;
	}

	public void setPCU_TEMP_B(float pCU_TEMP_B) {
		PCU_TEMP_B = pCU_TEMP_B;
	}

}
