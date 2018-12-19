package ru.r2cloud.jradio.beesat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmNodeCtrlNodePower {

	private float AOCS0_5V;
	private float AOCS1_5V;
	private float FOR0_5V;
	private float FOR1_5V;
	private float OBC0_5V;
	private float OBC1_5V;
	private float PDH0_5V;
	private float PDH1_5V;
	private float SOLID0_5V;
	private float SOLID1_5V;
	private float FDA0_5V;
	private float FDA1_5V;
	private float STELLA0_5V;
	private float STELLA1_5V;
	private float COM0_5V;
	private float COM1_5V;
	private float AOCS0_3V3;
	private float AOCS1_3V3;
	private float FOR0_3V3;
	private float FOR1_3V3;
	private float OBC0_3V3;
	private float OBC1_3V3;
	private float PDH0_3V3;
	private float PDH1_3V3;
	private float SOLID0_3V3;
	private float SOLID1_3V3;
	private float FDA0_3V3;
	private float FDA1_3V3;
	private float STELLA0_3V3;
	private float STELLA1_3V3;
	private float COM0_3V3;
	private float COM1_3V3;
	private float S_AOCS0_5V;
	private float S_AOCS1_5V;
	private float S_FOR0_5V;
	private float S_FOR1_5V;
	private float S_OBC0_5V;
	private float S_OBC1_5V;
	private int S_PDH0_5V;
	private int S_PDH1_5V;
	private float S_SOLID0_5V;
	private float S_SOLID1_5V;
	private float S_FDA0_5V;
	private float S_FDA1_5V;
	private float S_STELLA0_5V;
	private float S_STELLA1_5V;
	private float S_COM0_5V;
	private float S_COM1_5V;
	private float S_AOCS0_3V3;
	private float S_AOCS1_3V3;
	private float S_FOR0_3V3;
	private float S_FOR1_3V3;
	private float S_OBC0_3V3;
	private float S_OBC1_3V3;
	private float S_PDH0_3V3;
	private float S_PDH1_3V3;
	private float S_SOLID0_3V3;
	private float S_SOLID1_3V3;
	private float S_FDA0_3V3;
	private float S_FDA1_3V3;
	private float S_STELLA0_3V3;
	private float S_STELLA1_3V3;
	private float S_COM0_3V3;
	private float S_COM1_3V3;
	private float RWS00_V;
	private float RWS10_V;
	private float RWS20_V;
	private float RWS30_V;
	private int RWS00_C;
	private int RWS10_C;
	private int RWS20_C;
	private int RWS30_C;

	public TmNodeCtrlNodePower(DataInputStream dis) throws IOException {
		AOCS0_5V = dis.readUnsignedByte() * 0.022f;
		AOCS1_5V = dis.readUnsignedByte() * 0.022f;
		FOR0_5V = dis.readUnsignedByte() * 0.022f;
		FOR1_5V = dis.readUnsignedByte() * 0.022f;
		OBC0_5V = dis.readUnsignedByte() * 0.022f;
		OBC1_5V = dis.readUnsignedByte() * 0.022f;
		PDH0_5V = dis.readUnsignedByte() * 0.022f;
		PDH1_5V = dis.readUnsignedByte() * 0.022f;
		SOLID0_5V = dis.readUnsignedByte() * 0.022f;
		SOLID1_5V = dis.readUnsignedByte() * 0.022f;
		FDA0_5V = dis.readUnsignedByte() * 0.022f;
		FDA1_5V = dis.readUnsignedByte() * 0.022f;
		STELLA0_5V = dis.readUnsignedByte() * 0.022f;
		STELLA1_5V = dis.readUnsignedByte() * 0.022f;
		COM0_5V = dis.readUnsignedByte() * 0.022f;
		COM1_5V = dis.readUnsignedByte() * 0.022f;
		AOCS0_3V3 = dis.readUnsignedByte() * 0.022f;
		AOCS1_3V3 = dis.readUnsignedByte() * 0.022f;
		FOR0_3V3 = dis.readUnsignedByte() * 0.022f;
		FOR1_3V3 = dis.readUnsignedByte() * 0.022f;
		OBC0_3V3 = dis.readUnsignedByte() * 0.022f;
		OBC1_3V3 = dis.readUnsignedByte() * 0.022f;
		PDH0_3V3 = dis.readUnsignedByte() * 0.022f;
		PDH1_3V3 = dis.readUnsignedByte() * 0.022f;
		SOLID0_3V3 = dis.readUnsignedByte() * 0.022f;
		SOLID1_3V3 = dis.readUnsignedByte() * 0.022f;
		FDA0_3V3 = dis.readUnsignedByte() * 0.022f;
		FDA1_3V3 = dis.readUnsignedByte() * 0.022f;
		STELLA0_3V3 = dis.readUnsignedByte() * 0.022f;
		STELLA1_3V3 = dis.readUnsignedByte() * 0.022f;
		COM0_3V3 = dis.readUnsignedByte() * 0.022f;
		COM1_3V3 = dis.readUnsignedByte() * 0.022f;
		S_AOCS0_5V = dis.readUnsignedByte() * 6.25f;
		S_AOCS1_5V = dis.readUnsignedByte() * 6.25f;
		S_FOR0_5V = dis.readUnsignedByte() * 6.25f;
		S_FOR1_5V = dis.readUnsignedByte() * 6.25f;
		S_OBC0_5V = dis.readUnsignedByte() * 6.25f;
		S_OBC1_5V = dis.readUnsignedByte() * 6.25f;
		S_PDH0_5V = dis.readUnsignedByte() * 20;
		S_PDH1_5V = dis.readUnsignedByte() * 20;
		S_SOLID0_5V = dis.readUnsignedByte() * 6.25f;
		S_SOLID1_5V = dis.readUnsignedByte() * 6.25f;
		S_FDA0_5V = dis.readUnsignedByte() * 6.25f;
		S_FDA1_5V = dis.readUnsignedByte() * 6.25f;
		S_STELLA0_5V = dis.readUnsignedByte() * 6.25f;
		S_STELLA1_5V = dis.readUnsignedByte() * 6.25f;
		S_COM0_5V = dis.readUnsignedByte() * 6.25f;
		S_COM1_5V = dis.readUnsignedByte() * 6.25f;
		S_AOCS0_3V3 = dis.readUnsignedByte() * 6.25f;
		S_AOCS1_3V3 = dis.readUnsignedByte() * 6.25f;
		S_FOR0_3V3 = dis.readUnsignedByte() * 6.25f;
		S_FOR1_3V3 = dis.readUnsignedByte() * 6.25f;
		S_OBC0_3V3 = dis.readUnsignedByte() * 6.25f;
		S_OBC1_3V3 = dis.readUnsignedByte() * 6.25f;
		S_PDH0_3V3 = dis.readUnsignedByte() * 6.25f;
		S_PDH1_3V3 = dis.readUnsignedByte() * 6.25f;
		S_SOLID0_3V3 = dis.readUnsignedByte() * 6.25f;
		S_SOLID1_3V3 = dis.readUnsignedByte() * 6.25f;
		S_FDA0_3V3 = dis.readUnsignedByte() * 6.25f;
		S_FDA1_3V3 = dis.readUnsignedByte() * 6.25f;
		S_STELLA0_3V3 = dis.readUnsignedByte() * 6.25f;
		S_STELLA1_3V3 = dis.readUnsignedByte() * 6.25f;
		S_COM0_3V3 = dis.readUnsignedByte() * 6.25f;
		S_COM1_3V3 = dis.readUnsignedByte() * 6.25f;
		RWS00_V = dis.readUnsignedByte() * 0.4f;
		RWS10_V = dis.readUnsignedByte() * 0.4f;
		RWS20_V = dis.readUnsignedByte() * 0.4f;
		RWS30_V = dis.readUnsignedByte() * 0.4f;
		RWS00_C = dis.readUnsignedByte() * 12;
		RWS10_C = dis.readUnsignedByte() * 12;
		RWS20_C = dis.readUnsignedByte() * 12;
		RWS30_C = dis.readUnsignedByte() * 12;
	}

	public float getAOCS0_5V() {
		return AOCS0_5V;
	}

	public void setAOCS0_5V(float aOCS0_5V) {
		AOCS0_5V = aOCS0_5V;
	}

	public float getAOCS1_5V() {
		return AOCS1_5V;
	}

	public void setAOCS1_5V(float aOCS1_5V) {
		AOCS1_5V = aOCS1_5V;
	}

	public float getFOR0_5V() {
		return FOR0_5V;
	}

	public void setFOR0_5V(float fOR0_5V) {
		FOR0_5V = fOR0_5V;
	}

	public float getFOR1_5V() {
		return FOR1_5V;
	}

	public void setFOR1_5V(float fOR1_5V) {
		FOR1_5V = fOR1_5V;
	}

	public float getOBC0_5V() {
		return OBC0_5V;
	}

	public void setOBC0_5V(float oBC0_5V) {
		OBC0_5V = oBC0_5V;
	}

	public float getOBC1_5V() {
		return OBC1_5V;
	}

	public void setOBC1_5V(float oBC1_5V) {
		OBC1_5V = oBC1_5V;
	}

	public float getPDH0_5V() {
		return PDH0_5V;
	}

	public void setPDH0_5V(float pDH0_5V) {
		PDH0_5V = pDH0_5V;
	}

	public float getPDH1_5V() {
		return PDH1_5V;
	}

	public void setPDH1_5V(float pDH1_5V) {
		PDH1_5V = pDH1_5V;
	}

	public float getSOLID0_5V() {
		return SOLID0_5V;
	}

	public void setSOLID0_5V(float sOLID0_5V) {
		SOLID0_5V = sOLID0_5V;
	}

	public float getSOLID1_5V() {
		return SOLID1_5V;
	}

	public void setSOLID1_5V(float sOLID1_5V) {
		SOLID1_5V = sOLID1_5V;
	}

	public float getFDA0_5V() {
		return FDA0_5V;
	}

	public void setFDA0_5V(float fDA0_5V) {
		FDA0_5V = fDA0_5V;
	}

	public float getFDA1_5V() {
		return FDA1_5V;
	}

	public void setFDA1_5V(float fDA1_5V) {
		FDA1_5V = fDA1_5V;
	}

	public float getSTELLA0_5V() {
		return STELLA0_5V;
	}

	public void setSTELLA0_5V(float sTELLA0_5V) {
		STELLA0_5V = sTELLA0_5V;
	}

	public float getSTELLA1_5V() {
		return STELLA1_5V;
	}

	public void setSTELLA1_5V(float sTELLA1_5V) {
		STELLA1_5V = sTELLA1_5V;
	}

	public float getCOM0_5V() {
		return COM0_5V;
	}

	public void setCOM0_5V(float cOM0_5V) {
		COM0_5V = cOM0_5V;
	}

	public float getCOM1_5V() {
		return COM1_5V;
	}

	public void setCOM1_5V(float cOM1_5V) {
		COM1_5V = cOM1_5V;
	}

	public float getAOCS0_3V3() {
		return AOCS0_3V3;
	}

	public void setAOCS0_3V3(float aOCS0_3V3) {
		AOCS0_3V3 = aOCS0_3V3;
	}

	public float getAOCS1_3V3() {
		return AOCS1_3V3;
	}

	public void setAOCS1_3V3(float aOCS1_3V3) {
		AOCS1_3V3 = aOCS1_3V3;
	}

	public float getFOR0_3V3() {
		return FOR0_3V3;
	}

	public void setFOR0_3V3(float fOR0_3V3) {
		FOR0_3V3 = fOR0_3V3;
	}

	public float getFOR1_3V3() {
		return FOR1_3V3;
	}

	public void setFOR1_3V3(float fOR1_3V3) {
		FOR1_3V3 = fOR1_3V3;
	}

	public float getOBC0_3V3() {
		return OBC0_3V3;
	}

	public void setOBC0_3V3(float oBC0_3V3) {
		OBC0_3V3 = oBC0_3V3;
	}

	public float getOBC1_3V3() {
		return OBC1_3V3;
	}

	public void setOBC1_3V3(float oBC1_3V3) {
		OBC1_3V3 = oBC1_3V3;
	}

	public float getPDH0_3V3() {
		return PDH0_3V3;
	}

	public void setPDH0_3V3(float pDH0_3V3) {
		PDH0_3V3 = pDH0_3V3;
	}

	public float getPDH1_3V3() {
		return PDH1_3V3;
	}

	public void setPDH1_3V3(float pDH1_3V3) {
		PDH1_3V3 = pDH1_3V3;
	}

	public float getSOLID0_3V3() {
		return SOLID0_3V3;
	}

	public void setSOLID0_3V3(float sOLID0_3V3) {
		SOLID0_3V3 = sOLID0_3V3;
	}

	public float getSOLID1_3V3() {
		return SOLID1_3V3;
	}

	public void setSOLID1_3V3(float sOLID1_3V3) {
		SOLID1_3V3 = sOLID1_3V3;
	}

	public float getFDA0_3V3() {
		return FDA0_3V3;
	}

	public void setFDA0_3V3(float fDA0_3V3) {
		FDA0_3V3 = fDA0_3V3;
	}

	public float getFDA1_3V3() {
		return FDA1_3V3;
	}

	public void setFDA1_3V3(float fDA1_3V3) {
		FDA1_3V3 = fDA1_3V3;
	}

	public float getSTELLA0_3V3() {
		return STELLA0_3V3;
	}

	public void setSTELLA0_3V3(float sTELLA0_3V3) {
		STELLA0_3V3 = sTELLA0_3V3;
	}

	public float getSTELLA1_3V3() {
		return STELLA1_3V3;
	}

	public void setSTELLA1_3V3(float sTELLA1_3V3) {
		STELLA1_3V3 = sTELLA1_3V3;
	}

	public float getCOM0_3V3() {
		return COM0_3V3;
	}

	public void setCOM0_3V3(float cOM0_3V3) {
		COM0_3V3 = cOM0_3V3;
	}

	public float getCOM1_3V3() {
		return COM1_3V3;
	}

	public void setCOM1_3V3(float cOM1_3V3) {
		COM1_3V3 = cOM1_3V3;
	}

	public float getS_AOCS0_5V() {
		return S_AOCS0_5V;
	}

	public void setS_AOCS0_5V(float s_AOCS0_5V) {
		S_AOCS0_5V = s_AOCS0_5V;
	}

	public float getS_AOCS1_5V() {
		return S_AOCS1_5V;
	}

	public void setS_AOCS1_5V(float s_AOCS1_5V) {
		S_AOCS1_5V = s_AOCS1_5V;
	}

	public float getS_FOR0_5V() {
		return S_FOR0_5V;
	}

	public void setS_FOR0_5V(float s_FOR0_5V) {
		S_FOR0_5V = s_FOR0_5V;
	}

	public float getS_FOR1_5V() {
		return S_FOR1_5V;
	}

	public void setS_FOR1_5V(float s_FOR1_5V) {
		S_FOR1_5V = s_FOR1_5V;
	}

	public float getS_OBC0_5V() {
		return S_OBC0_5V;
	}

	public void setS_OBC0_5V(float s_OBC0_5V) {
		S_OBC0_5V = s_OBC0_5V;
	}

	public float getS_OBC1_5V() {
		return S_OBC1_5V;
	}

	public void setS_OBC1_5V(float s_OBC1_5V) {
		S_OBC1_5V = s_OBC1_5V;
	}

	public int getS_PDH0_5V() {
		return S_PDH0_5V;
	}

	public void setS_PDH0_5V(int s_PDH0_5V) {
		S_PDH0_5V = s_PDH0_5V;
	}

	public int getS_PDH1_5V() {
		return S_PDH1_5V;
	}

	public void setS_PDH1_5V(int s_PDH1_5V) {
		S_PDH1_5V = s_PDH1_5V;
	}

	public float getS_SOLID0_5V() {
		return S_SOLID0_5V;
	}

	public void setS_SOLID0_5V(float s_SOLID0_5V) {
		S_SOLID0_5V = s_SOLID0_5V;
	}

	public float getS_SOLID1_5V() {
		return S_SOLID1_5V;
	}

	public void setS_SOLID1_5V(float s_SOLID1_5V) {
		S_SOLID1_5V = s_SOLID1_5V;
	}

	public float getS_FDA0_5V() {
		return S_FDA0_5V;
	}

	public void setS_FDA0_5V(float s_FDA0_5V) {
		S_FDA0_5V = s_FDA0_5V;
	}

	public float getS_FDA1_5V() {
		return S_FDA1_5V;
	}

	public void setS_FDA1_5V(float s_FDA1_5V) {
		S_FDA1_5V = s_FDA1_5V;
	}

	public float getS_STELLA0_5V() {
		return S_STELLA0_5V;
	}

	public void setS_STELLA0_5V(float s_STELLA0_5V) {
		S_STELLA0_5V = s_STELLA0_5V;
	}

	public float getS_STELLA1_5V() {
		return S_STELLA1_5V;
	}

	public void setS_STELLA1_5V(float s_STELLA1_5V) {
		S_STELLA1_5V = s_STELLA1_5V;
	}

	public float getS_COM0_5V() {
		return S_COM0_5V;
	}

	public void setS_COM0_5V(float s_COM0_5V) {
		S_COM0_5V = s_COM0_5V;
	}

	public float getS_COM1_5V() {
		return S_COM1_5V;
	}

	public void setS_COM1_5V(float s_COM1_5V) {
		S_COM1_5V = s_COM1_5V;
	}

	public float getS_AOCS0_3V3() {
		return S_AOCS0_3V3;
	}

	public void setS_AOCS0_3V3(float s_AOCS0_3V3) {
		S_AOCS0_3V3 = s_AOCS0_3V3;
	}

	public float getS_AOCS1_3V3() {
		return S_AOCS1_3V3;
	}

	public void setS_AOCS1_3V3(float s_AOCS1_3V3) {
		S_AOCS1_3V3 = s_AOCS1_3V3;
	}

	public float getS_FOR0_3V3() {
		return S_FOR0_3V3;
	}

	public void setS_FOR0_3V3(float s_FOR0_3V3) {
		S_FOR0_3V3 = s_FOR0_3V3;
	}

	public float getS_FOR1_3V3() {
		return S_FOR1_3V3;
	}

	public void setS_FOR1_3V3(float s_FOR1_3V3) {
		S_FOR1_3V3 = s_FOR1_3V3;
	}

	public float getS_OBC0_3V3() {
		return S_OBC0_3V3;
	}

	public void setS_OBC0_3V3(float s_OBC0_3V3) {
		S_OBC0_3V3 = s_OBC0_3V3;
	}

	public float getS_OBC1_3V3() {
		return S_OBC1_3V3;
	}

	public void setS_OBC1_3V3(float s_OBC1_3V3) {
		S_OBC1_3V3 = s_OBC1_3V3;
	}

	public float getS_PDH0_3V3() {
		return S_PDH0_3V3;
	}

	public void setS_PDH0_3V3(float s_PDH0_3V3) {
		S_PDH0_3V3 = s_PDH0_3V3;
	}

	public float getS_PDH1_3V3() {
		return S_PDH1_3V3;
	}

	public void setS_PDH1_3V3(float s_PDH1_3V3) {
		S_PDH1_3V3 = s_PDH1_3V3;
	}

	public float getS_SOLID0_3V3() {
		return S_SOLID0_3V3;
	}

	public void setS_SOLID0_3V3(float s_SOLID0_3V3) {
		S_SOLID0_3V3 = s_SOLID0_3V3;
	}

	public float getS_SOLID1_3V3() {
		return S_SOLID1_3V3;
	}

	public void setS_SOLID1_3V3(float s_SOLID1_3V3) {
		S_SOLID1_3V3 = s_SOLID1_3V3;
	}

	public float getS_FDA0_3V3() {
		return S_FDA0_3V3;
	}

	public void setS_FDA0_3V3(float s_FDA0_3V3) {
		S_FDA0_3V3 = s_FDA0_3V3;
	}

	public float getS_FDA1_3V3() {
		return S_FDA1_3V3;
	}

	public void setS_FDA1_3V3(float s_FDA1_3V3) {
		S_FDA1_3V3 = s_FDA1_3V3;
	}

	public float getS_STELLA0_3V3() {
		return S_STELLA0_3V3;
	}

	public void setS_STELLA0_3V3(float s_STELLA0_3V3) {
		S_STELLA0_3V3 = s_STELLA0_3V3;
	}

	public float getS_STELLA1_3V3() {
		return S_STELLA1_3V3;
	}

	public void setS_STELLA1_3V3(float s_STELLA1_3V3) {
		S_STELLA1_3V3 = s_STELLA1_3V3;
	}

	public float getS_COM0_3V3() {
		return S_COM0_3V3;
	}

	public void setS_COM0_3V3(float s_COM0_3V3) {
		S_COM0_3V3 = s_COM0_3V3;
	}

	public float getS_COM1_3V3() {
		return S_COM1_3V3;
	}

	public void setS_COM1_3V3(float s_COM1_3V3) {
		S_COM1_3V3 = s_COM1_3V3;
	}

	public float getRWS00_V() {
		return RWS00_V;
	}

	public void setRWS00_V(float rWS00_V) {
		RWS00_V = rWS00_V;
	}

	public float getRWS10_V() {
		return RWS10_V;
	}

	public void setRWS10_V(float rWS10_V) {
		RWS10_V = rWS10_V;
	}

	public float getRWS20_V() {
		return RWS20_V;
	}

	public void setRWS20_V(float rWS20_V) {
		RWS20_V = rWS20_V;
	}

	public float getRWS30_V() {
		return RWS30_V;
	}

	public void setRWS30_V(float rWS30_V) {
		RWS30_V = rWS30_V;
	}

	public int getRWS00_C() {
		return RWS00_C;
	}

	public void setRWS00_C(int rWS00_C) {
		RWS00_C = rWS00_C;
	}

	public int getRWS10_C() {
		return RWS10_C;
	}

	public void setRWS10_C(int rWS10_C) {
		RWS10_C = rWS10_C;
	}

	public int getRWS20_C() {
		return RWS20_C;
	}

	public void setRWS20_C(int rWS20_C) {
		RWS20_C = rWS20_C;
	}

	public int getRWS30_C() {
		return RWS30_C;
	}

	public void setRWS30_C(int rWS30_C) {
		RWS30_C = rWS30_C;
	}

}
