package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class TcError1 {

	private boolean PMMTcRequestNotAnswered;
	private boolean PMRTcRequestNotAnswered;
	private boolean TMMTcRequestNotAnswered;
	private boolean TMRTcRequestNotAnswered;
	private boolean SSMTcRequestNotAnswered;
	private boolean SSRTcRequestNotAnswered;
	private boolean ESETcRequestNotAnswered;
	private boolean MWRTcRequestNotAnswered;
	private boolean MWMTcRequestNotAnswered;
	private boolean MPSTcRequestNotAnswered;
	private boolean MMMTcRequestNotAnswered;
	private boolean MMRTcRequestNotAnswered;
	private boolean MTMTcRequestNotAnswered;
	private boolean MTRTcRequestNotAnswered;
	private boolean TRITcRequestNotAnswered;
	private boolean LMPTcRequestNotAnswered;
	private boolean CAMTcRequestNotAnswered;
	private boolean AMSTcRequestNotAnswered;
	private boolean STXTcRequestNotAnswered;
	private boolean GPSTcRequestNotAnswered;

	private boolean SCAMTcRequestNotAnswered;
	
	public TcError1(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		PMMTcRequestNotAnswered = ((raw >> 5) & 0x1) > 0;
		PMRTcRequestNotAnswered = ((raw >> 4) & 0x1) > 0;
		TMMTcRequestNotAnswered = ((raw >> 3) & 0x1) > 0;
		TMRTcRequestNotAnswered = ((raw >> 2) & 0x1) > 0;
		SSMTcRequestNotAnswered = ((raw >> 1) & 0x1) > 0;
		SSRTcRequestNotAnswered = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		ESETcRequestNotAnswered = ((raw >> 7) & 0x1) > 0;
		MWRTcRequestNotAnswered = ((raw >> 6) & 0x1) > 0;
		MWMTcRequestNotAnswered = ((raw >> 5) & 0x1) > 0;
		MPSTcRequestNotAnswered = ((raw >> 4) & 0x1) > 0;
		MMMTcRequestNotAnswered = ((raw >> 3) & 0x1) > 0;
		MMRTcRequestNotAnswered = ((raw >> 2) & 0x1) > 0;
		MTMTcRequestNotAnswered = ((raw >> 1) & 0x1) > 0;
		MTRTcRequestNotAnswered = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		TRITcRequestNotAnswered = ((raw >> 7) & 0x1) > 0;
		LMPTcRequestNotAnswered = ((raw >> 6) & 0x1) > 0;
		CAMTcRequestNotAnswered = ((raw >> 5) & 0x1) > 0;
		AMSTcRequestNotAnswered = ((raw >> 4) & 0x1) > 0;
		STXTcRequestNotAnswered = ((raw >> 3) & 0x1) > 0;
		GPSTcRequestNotAnswered = ((raw >> 2) & 0x1) > 0;

		SCAMTcRequestNotAnswered = (raw & 0x1) > 0;
		dis.skipBytes(1);
	}

	public boolean isPMMTcRequestNotAnswered() {
		return PMMTcRequestNotAnswered;
	}

	public void setPMMTcRequestNotAnswered(boolean pMMTcRequestNotAnswered) {
		PMMTcRequestNotAnswered = pMMTcRequestNotAnswered;
	}

	public boolean isPMRTcRequestNotAnswered() {
		return PMRTcRequestNotAnswered;
	}

	public void setPMRTcRequestNotAnswered(boolean pMRTcRequestNotAnswered) {
		PMRTcRequestNotAnswered = pMRTcRequestNotAnswered;
	}

	public boolean isTMMTcRequestNotAnswered() {
		return TMMTcRequestNotAnswered;
	}

	public void setTMMTcRequestNotAnswered(boolean tMMTcRequestNotAnswered) {
		TMMTcRequestNotAnswered = tMMTcRequestNotAnswered;
	}

	public boolean isTMRTcRequestNotAnswered() {
		return TMRTcRequestNotAnswered;
	}

	public void setTMRTcRequestNotAnswered(boolean tMRTcRequestNotAnswered) {
		TMRTcRequestNotAnswered = tMRTcRequestNotAnswered;
	}

	public boolean isSSMTcRequestNotAnswered() {
		return SSMTcRequestNotAnswered;
	}

	public void setSSMTcRequestNotAnswered(boolean sSMTcRequestNotAnswered) {
		SSMTcRequestNotAnswered = sSMTcRequestNotAnswered;
	}

	public boolean isSSRTcRequestNotAnswered() {
		return SSRTcRequestNotAnswered;
	}

	public void setSSRTcRequestNotAnswered(boolean sSRTcRequestNotAnswered) {
		SSRTcRequestNotAnswered = sSRTcRequestNotAnswered;
	}

	public boolean isESETcRequestNotAnswered() {
		return ESETcRequestNotAnswered;
	}

	public void setESETcRequestNotAnswered(boolean eSETcRequestNotAnswered) {
		ESETcRequestNotAnswered = eSETcRequestNotAnswered;
	}

	public boolean isMWRTcRequestNotAnswered() {
		return MWRTcRequestNotAnswered;
	}

	public void setMWRTcRequestNotAnswered(boolean mWRTcRequestNotAnswered) {
		MWRTcRequestNotAnswered = mWRTcRequestNotAnswered;
	}

	public boolean isMWMTcRequestNotAnswered() {
		return MWMTcRequestNotAnswered;
	}

	public void setMWMTcRequestNotAnswered(boolean mWMTcRequestNotAnswered) {
		MWMTcRequestNotAnswered = mWMTcRequestNotAnswered;
	}

	public boolean isMPSTcRequestNotAnswered() {
		return MPSTcRequestNotAnswered;
	}

	public void setMPSTcRequestNotAnswered(boolean mPSTcRequestNotAnswered) {
		MPSTcRequestNotAnswered = mPSTcRequestNotAnswered;
	}

	public boolean isMMMTcRequestNotAnswered() {
		return MMMTcRequestNotAnswered;
	}

	public void setMMMTcRequestNotAnswered(boolean mMMTcRequestNotAnswered) {
		MMMTcRequestNotAnswered = mMMTcRequestNotAnswered;
	}

	public boolean isMMRTcRequestNotAnswered() {
		return MMRTcRequestNotAnswered;
	}

	public void setMMRTcRequestNotAnswered(boolean mMRTcRequestNotAnswered) {
		MMRTcRequestNotAnswered = mMRTcRequestNotAnswered;
	}

	public boolean isMTMTcRequestNotAnswered() {
		return MTMTcRequestNotAnswered;
	}

	public void setMTMTcRequestNotAnswered(boolean mTMTcRequestNotAnswered) {
		MTMTcRequestNotAnswered = mTMTcRequestNotAnswered;
	}

	public boolean isMTRTcRequestNotAnswered() {
		return MTRTcRequestNotAnswered;
	}

	public void setMTRTcRequestNotAnswered(boolean mTRTcRequestNotAnswered) {
		MTRTcRequestNotAnswered = mTRTcRequestNotAnswered;
	}

	public boolean isTRITcRequestNotAnswered() {
		return TRITcRequestNotAnswered;
	}

	public void setTRITcRequestNotAnswered(boolean tRITcRequestNotAnswered) {
		TRITcRequestNotAnswered = tRITcRequestNotAnswered;
	}

	public boolean isLMPTcRequestNotAnswered() {
		return LMPTcRequestNotAnswered;
	}

	public void setLMPTcRequestNotAnswered(boolean lMPTcRequestNotAnswered) {
		LMPTcRequestNotAnswered = lMPTcRequestNotAnswered;
	}

	public boolean isCAMTcRequestNotAnswered() {
		return CAMTcRequestNotAnswered;
	}

	public void setCAMTcRequestNotAnswered(boolean cAMTcRequestNotAnswered) {
		CAMTcRequestNotAnswered = cAMTcRequestNotAnswered;
	}

	public boolean isAMSTcRequestNotAnswered() {
		return AMSTcRequestNotAnswered;
	}

	public void setAMSTcRequestNotAnswered(boolean aMSTcRequestNotAnswered) {
		AMSTcRequestNotAnswered = aMSTcRequestNotAnswered;
	}

	public boolean isSTXTcRequestNotAnswered() {
		return STXTcRequestNotAnswered;
	}

	public void setSTXTcRequestNotAnswered(boolean sTXTcRequestNotAnswered) {
		STXTcRequestNotAnswered = sTXTcRequestNotAnswered;
	}

	public boolean isGPSTcRequestNotAnswered() {
		return GPSTcRequestNotAnswered;
	}

	public void setGPSTcRequestNotAnswered(boolean gPSTcRequestNotAnswered) {
		GPSTcRequestNotAnswered = gPSTcRequestNotAnswered;
	}

	public boolean isSCAMTcRequestNotAnswered() {
		return SCAMTcRequestNotAnswered;
	}

	public void setSCAMTcRequestNotAnswered(boolean sCAMTcRequestNotAnswered) {
		SCAMTcRequestNotAnswered = sCAMTcRequestNotAnswered;
	}
	
}
