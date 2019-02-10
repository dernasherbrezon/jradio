package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class CanTimeoutError {

	private boolean PMM;
	private boolean PMR;
	private boolean TMM;
	private boolean TMR;
	private boolean SSM;
	private boolean SSR;
	private boolean ESE;
	private boolean MWR;
	private boolean MPS;
	private boolean MMM;
	private boolean MMR;
	private boolean MTM;
	private boolean MTR;
	private boolean TRI;
	private boolean LMP;
	private boolean PCAM;
	private boolean AMS;
	private boolean STX;
	private boolean GPS;
	private boolean SCAM;

	public CanTimeoutError(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		PMM = ((raw >> 5) & 0x1) > 0;
		PMR = ((raw >> 4) & 0x1) > 0;
		TMM = ((raw >> 3) & 0x1) > 0;
		TMR = ((raw >> 2) & 0x1) > 0;
		SSM = ((raw >> 1) & 0x1) > 0;
		SSR = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		ESE = ((raw >> 7) & 0x1) > 0;
		MWR = ((raw >> 6) & 0x1) > 0;
		MPS = ((raw >> 4) & 0x1) > 0;
		MMM = ((raw >> 3) & 0x1) > 0;
		MMR = ((raw >> 2) & 0x1) > 0;
		MTM = ((raw >> 1) & 0x1) > 0;
		MTR = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		TRI = ((raw >> 7) & 0x1) > 0;
		LMP = ((raw >> 6) & 0x1) > 0;
		PCAM = ((raw >> 5) & 0x1) > 0;
		AMS = ((raw >> 4) & 0x1) > 0;
		STX = ((raw >> 3) & 0x1) > 0;
		GPS = ((raw >> 2) & 0x1) > 0;
		SCAM = (raw & 0x1) > 0;
		dis.skipBytes(1);
	}

	public boolean isPMM() {
		return PMM;
	}

	public void setPMM(boolean pMM) {
		PMM = pMM;
	}

	public boolean isPMR() {
		return PMR;
	}

	public void setPMR(boolean pMR) {
		PMR = pMR;
	}

	public boolean isTMM() {
		return TMM;
	}

	public void setTMM(boolean tMM) {
		TMM = tMM;
	}

	public boolean isTMR() {
		return TMR;
	}

	public void setTMR(boolean tMR) {
		TMR = tMR;
	}

	public boolean isSSM() {
		return SSM;
	}

	public void setSSM(boolean sSM) {
		SSM = sSM;
	}

	public boolean isSSR() {
		return SSR;
	}

	public void setSSR(boolean sSR) {
		SSR = sSR;
	}

	public boolean isESE() {
		return ESE;
	}

	public void setESE(boolean eSE) {
		ESE = eSE;
	}

	public boolean isMWR() {
		return MWR;
	}

	public void setMWR(boolean mWR) {
		MWR = mWR;
	}

	public boolean isMPS() {
		return MPS;
	}

	public void setMPS(boolean mPS) {
		MPS = mPS;
	}

	public boolean isMMM() {
		return MMM;
	}

	public void setMMM(boolean mMM) {
		MMM = mMM;
	}

	public boolean isMMR() {
		return MMR;
	}

	public void setMMR(boolean mMR) {
		MMR = mMR;
	}

	public boolean isMTM() {
		return MTM;
	}

	public void setMTM(boolean mTM) {
		MTM = mTM;
	}

	public boolean isMTR() {
		return MTR;
	}

	public void setMTR(boolean mTR) {
		MTR = mTR;
	}

	public boolean isTRI() {
		return TRI;
	}

	public void setTRI(boolean tRI) {
		TRI = tRI;
	}

	public boolean isLMP() {
		return LMP;
	}

	public void setLMP(boolean lMP) {
		LMP = lMP;
	}

	public boolean isPCAM() {
		return PCAM;
	}

	public void setPCAM(boolean pCAM) {
		PCAM = pCAM;
	}

	public boolean isAMS() {
		return AMS;
	}

	public void setAMS(boolean aMS) {
		AMS = aMS;
	}

	public boolean isSTX() {
		return STX;
	}

	public void setSTX(boolean sTX) {
		STX = sTX;
	}

	public boolean isGPS() {
		return GPS;
	}

	public void setGPS(boolean gPS) {
		GPS = gPS;
	}

	public boolean isSCAM() {
		return SCAM;
	}

	public void setSCAM(boolean sCAM) {
		SCAM = sCAM;
	}
	
}
