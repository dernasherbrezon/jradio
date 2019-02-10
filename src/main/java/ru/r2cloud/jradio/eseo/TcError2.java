package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class TcError2 {

	private boolean PMMErrorCounterReached10;
	private boolean PMRErrorCounterReached10;
	private boolean TMMErrorCounterReached10;
	private boolean TMRErrorCounterReached10;
	private boolean SSMErrorCounterReached10;
	private boolean SSRErrorCounterReached10;
	private boolean ESEErrorCounterReached10;
	private boolean MWRErrorCounterReached10;
	private boolean MWMErrorCounterReached10;
	private boolean MPSErrorCounterReached10;
	private boolean MMMErrorCounterReached10;
	private boolean MMRErrorCounterReached10;
	private boolean MTMErrorCounterReached10;
	private boolean MTRErrorCounterReached10;
	private boolean TRIErrorCounterReached10;
	private boolean LMPErrorCounterReached10;
	private boolean CAMErrorCounterReached10;
	private boolean AMSErrorCounterReached10;
	private boolean STXErrorCounterReached10;
	private boolean GPSErrorCounterReached10;
	private boolean ADEErrorCounterReached10;
	private boolean SCAMErrorCounterReached10;

	public TcError2(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		PMMErrorCounterReached10 = ((raw >> 5) & 0x1) > 0;
		PMRErrorCounterReached10 = ((raw >> 4) & 0x1) > 0;
		TMMErrorCounterReached10 = ((raw >> 3) & 0x1) > 0;
		TMRErrorCounterReached10 = ((raw >> 2) & 0x1) > 0;
		SSMErrorCounterReached10 = ((raw >> 1) & 0x1) > 0;
		SSRErrorCounterReached10 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		ESEErrorCounterReached10 = ((raw >> 7) & 0x1) > 0;
		MWRErrorCounterReached10 = ((raw >> 6) & 0x1) > 0;
		MWMErrorCounterReached10 = ((raw >> 5) & 0x1) > 0;
		MPSErrorCounterReached10 = ((raw >> 4) & 0x1) > 0;
		MMMErrorCounterReached10 = ((raw >> 3) & 0x1) > 0;
		MMRErrorCounterReached10 = ((raw >> 2) & 0x1) > 0;
		MTMErrorCounterReached10 = ((raw >> 1) & 0x1) > 0;
		MTRErrorCounterReached10 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		TRIErrorCounterReached10 = ((raw >> 7) & 0x1) > 0;
		LMPErrorCounterReached10 = ((raw >> 6) & 0x1) > 0;
		CAMErrorCounterReached10 = ((raw >> 5) & 0x1) > 0;
		AMSErrorCounterReached10 = ((raw >> 4) & 0x1) > 0;
		STXErrorCounterReached10 = ((raw >> 3) & 0x1) > 0;
		GPSErrorCounterReached10 = ((raw >> 2) & 0x1) > 0;
		ADEErrorCounterReached10 = ((raw >> 1) & 0x1) > 0;
		SCAMErrorCounterReached10 = (raw & 0x1) > 0;
		dis.skipBytes(1);
	}

	public boolean isPMMErrorCounterReached10() {
		return PMMErrorCounterReached10;
	}

	public void setPMMErrorCounterReached10(boolean pMMErrorCounterReached10) {
		PMMErrorCounterReached10 = pMMErrorCounterReached10;
	}

	public boolean isPMRErrorCounterReached10() {
		return PMRErrorCounterReached10;
	}

	public void setPMRErrorCounterReached10(boolean pMRErrorCounterReached10) {
		PMRErrorCounterReached10 = pMRErrorCounterReached10;
	}

	public boolean isTMMErrorCounterReached10() {
		return TMMErrorCounterReached10;
	}

	public void setTMMErrorCounterReached10(boolean tMMErrorCounterReached10) {
		TMMErrorCounterReached10 = tMMErrorCounterReached10;
	}

	public boolean isTMRErrorCounterReached10() {
		return TMRErrorCounterReached10;
	}

	public void setTMRErrorCounterReached10(boolean tMRErrorCounterReached10) {
		TMRErrorCounterReached10 = tMRErrorCounterReached10;
	}

	public boolean isSSMErrorCounterReached10() {
		return SSMErrorCounterReached10;
	}

	public void setSSMErrorCounterReached10(boolean sSMErrorCounterReached10) {
		SSMErrorCounterReached10 = sSMErrorCounterReached10;
	}

	public boolean isSSRErrorCounterReached10() {
		return SSRErrorCounterReached10;
	}

	public void setSSRErrorCounterReached10(boolean sSRErrorCounterReached10) {
		SSRErrorCounterReached10 = sSRErrorCounterReached10;
	}

	public boolean isESEErrorCounterReached10() {
		return ESEErrorCounterReached10;
	}

	public void setESEErrorCounterReached10(boolean eSEErrorCounterReached10) {
		ESEErrorCounterReached10 = eSEErrorCounterReached10;
	}

	public boolean isMWRErrorCounterReached10() {
		return MWRErrorCounterReached10;
	}

	public void setMWRErrorCounterReached10(boolean mWRErrorCounterReached10) {
		MWRErrorCounterReached10 = mWRErrorCounterReached10;
	}

	public boolean isMWMErrorCounterReached10() {
		return MWMErrorCounterReached10;
	}

	public void setMWMErrorCounterReached10(boolean mWMErrorCounterReached10) {
		MWMErrorCounterReached10 = mWMErrorCounterReached10;
	}

	public boolean isMPSErrorCounterReached10() {
		return MPSErrorCounterReached10;
	}

	public void setMPSErrorCounterReached10(boolean mPSErrorCounterReached10) {
		MPSErrorCounterReached10 = mPSErrorCounterReached10;
	}

	public boolean isMMMErrorCounterReached10() {
		return MMMErrorCounterReached10;
	}

	public void setMMMErrorCounterReached10(boolean mMMErrorCounterReached10) {
		MMMErrorCounterReached10 = mMMErrorCounterReached10;
	}

	public boolean isMMRErrorCounterReached10() {
		return MMRErrorCounterReached10;
	}

	public void setMMRErrorCounterReached10(boolean mMRErrorCounterReached10) {
		MMRErrorCounterReached10 = mMRErrorCounterReached10;
	}

	public boolean isMTMErrorCounterReached10() {
		return MTMErrorCounterReached10;
	}

	public void setMTMErrorCounterReached10(boolean mTMErrorCounterReached10) {
		MTMErrorCounterReached10 = mTMErrorCounterReached10;
	}

	public boolean isMTRErrorCounterReached10() {
		return MTRErrorCounterReached10;
	}

	public void setMTRErrorCounterReached10(boolean mTRErrorCounterReached10) {
		MTRErrorCounterReached10 = mTRErrorCounterReached10;
	}

	public boolean isTRIErrorCounterReached10() {
		return TRIErrorCounterReached10;
	}

	public void setTRIErrorCounterReached10(boolean tRIErrorCounterReached10) {
		TRIErrorCounterReached10 = tRIErrorCounterReached10;
	}

	public boolean isLMPErrorCounterReached10() {
		return LMPErrorCounterReached10;
	}

	public void setLMPErrorCounterReached10(boolean lMPErrorCounterReached10) {
		LMPErrorCounterReached10 = lMPErrorCounterReached10;
	}

	public boolean isCAMErrorCounterReached10() {
		return CAMErrorCounterReached10;
	}

	public void setCAMErrorCounterReached10(boolean cAMErrorCounterReached10) {
		CAMErrorCounterReached10 = cAMErrorCounterReached10;
	}

	public boolean isAMSErrorCounterReached10() {
		return AMSErrorCounterReached10;
	}

	public void setAMSErrorCounterReached10(boolean aMSErrorCounterReached10) {
		AMSErrorCounterReached10 = aMSErrorCounterReached10;
	}

	public boolean isSTXErrorCounterReached10() {
		return STXErrorCounterReached10;
	}

	public void setSTXErrorCounterReached10(boolean sTXErrorCounterReached10) {
		STXErrorCounterReached10 = sTXErrorCounterReached10;
	}

	public boolean isGPSErrorCounterReached10() {
		return GPSErrorCounterReached10;
	}

	public void setGPSErrorCounterReached10(boolean gPSErrorCounterReached10) {
		GPSErrorCounterReached10 = gPSErrorCounterReached10;
	}

	public boolean isADEErrorCounterReached10() {
		return ADEErrorCounterReached10;
	}

	public void setADEErrorCounterReached10(boolean aDEErrorCounterReached10) {
		ADEErrorCounterReached10 = aDEErrorCounterReached10;
	}

	public boolean isSCAMErrorCounterReached10() {
		return SCAMErrorCounterReached10;
	}

	public void setSCAMErrorCounterReached10(boolean sCAMErrorCounterReached10) {
		SCAMErrorCounterReached10 = sCAMErrorCounterReached10;
	}

}
