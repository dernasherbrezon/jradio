package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class AcsError {

	private boolean safeMode; // Transition between Nominal to Detumbling & Safe has been triggered
	private boolean omegaDetWarning; // Angular velocity out of nominal range in Detumbling and Safe mode
	private boolean attitudeError; // Attitude Euler angles out of nominal range in Nominal mode
	private boolean omegaError; // Angular velocity out of nominal range in Nominal mode
	private boolean mwFailure; // MW critical failure
	private boolean pwrFailure; // Severe low power (S2)
	private boolean commError; // A communication error is occurred
	private boolean itemsMngError; // Error between the items used and the PMU feedback
	private boolean mwmState; // MWM not reliable - from FDIR algorithm
	private boolean mwrState; // MWR not reliable - from FDIR algorithm
	private boolean mtmXState; // MT Main on x-axis not reliable - from FDIR algorithm
	private boolean mtrXState; // MTR on x-axis not reliable - from FDIR algorithm
	private boolean mtmYState; // MT Main on y-axis not reliable - from FDIR algorithm
	private boolean mtrYState; // MTR on y-axis not reliable - from FDIR algorithm
	private boolean mtmZState; // MT Main on z-axis not reliable - from FDIR algorithm
	private boolean mtrZState; // MTR on z-axis not reliable - from FDIR algorithm
	private boolean mtmState; // MT Main assembly not reliable
	private boolean mtrState; // MT Redundant assembly not reliable
	private boolean mpsState; // MPS not reliable - from FDIR algorithm
	private boolean ss1State; // SS1 not reliable - from FDIR algorithm
	private boolean ss2State; // SS2 not reliable - from FDIR algorithm
	private boolean cssState; // CSS not reliable - from FDIR algorithm
	private boolean esState; // ES not reliable - from FDIR algorithm
	private boolean mmmState; // MM Main not reliable - from FDIR algorithm
	private boolean mmrState; // MM Redundant not reliable - from FDIR algorithm
	private boolean mpsMeneuverAbort; // MPS maneuver aborted from FDIR (MPS not reliable) or AOCS not in Nominal state

	private boolean aocsSwError1; // TC execution returns an error
	private boolean aocsSwError2; // AOCS task overrun
	private boolean aocsSwError3; // AOCS FDIR task overrun
	private boolean aocsSwError4;

	public AcsError() {
		//do nothing
	}
	
	public AcsError(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		safeMode = ((raw >> 7) & 0x1) > 0;
		omegaDetWarning = ((raw >> 6) & 0x1) > 0;
		attitudeError = ((raw >> 5) & 0x1) > 0;
		omegaError = ((raw >> 4) & 0x1) > 0;
		mwFailure = ((raw >> 3) & 0x1) > 0;
		pwrFailure = ((raw >> 2) & 0x1) > 0;
		commError = ((raw >> 1) & 0x1) > 0;
		itemsMngError = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		mwmState = ((raw >> 7) & 0x1) > 0;
		mwrState = ((raw >> 6) & 0x1) > 0;
		mtmXState = ((raw >> 5) & 0x1) > 0;
		mtrXState = ((raw >> 4) & 0x1) > 0;
		mtmYState = ((raw >> 3) & 0x1) > 0;
		mtrYState = ((raw >> 2) & 0x1) > 0;
		mtmZState = ((raw >> 1) & 0x1) > 0;
		mtrZState = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		mtmState = ((raw >> 7) & 0x1) > 0;
		mtrState = ((raw >> 6) & 0x1) > 0;
		mpsState = ((raw >> 5) & 0x1) > 0;
		ss1State = ((raw >> 4) & 0x1) > 0;
		ss2State = ((raw >> 3) & 0x1) > 0;
		cssState = ((raw >> 2) & 0x1) > 0;
		esState = ((raw >> 1) & 0x1) > 0;
		mmmState = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		mmrState = ((raw >> 7) & 0x1) > 0;
		mpsMeneuverAbort = ((raw >> 6) & 0x1) > 0;

		aocsSwError1 = ((raw >> 3) & 0x1) > 0;
		aocsSwError2 = ((raw >> 2) & 0x1) > 0;
		aocsSwError3 = ((raw >> 1) & 0x1) > 0;
		aocsSwError4 = (raw & 0x1) > 0;
	}

	public boolean isSafeMode() {
		return safeMode;
	}

	public void setSafeMode(boolean safeMode) {
		this.safeMode = safeMode;
	}

	public boolean isOmegaDetWarning() {
		return omegaDetWarning;
	}

	public void setOmegaDetWarning(boolean omegaDetWarning) {
		this.omegaDetWarning = omegaDetWarning;
	}

	public boolean isAttitudeError() {
		return attitudeError;
	}

	public void setAttitudeError(boolean attitudeError) {
		this.attitudeError = attitudeError;
	}

	public boolean isOmegaError() {
		return omegaError;
	}

	public void setOmegaError(boolean omegaError) {
		this.omegaError = omegaError;
	}

	public boolean isMwFailure() {
		return mwFailure;
	}

	public void setMwFailure(boolean mwFailure) {
		this.mwFailure = mwFailure;
	}

	public boolean isPwrFailure() {
		return pwrFailure;
	}

	public void setPwrFailure(boolean pwrFailure) {
		this.pwrFailure = pwrFailure;
	}

	public boolean isCommError() {
		return commError;
	}

	public void setCommError(boolean commError) {
		this.commError = commError;
	}

	public boolean isItemsMngError() {
		return itemsMngError;
	}

	public void setItemsMngError(boolean itemsMngError) {
		this.itemsMngError = itemsMngError;
	}

	public boolean isMwmState() {
		return mwmState;
	}

	public void setMwmState(boolean mwmState) {
		this.mwmState = mwmState;
	}

	public boolean isMwrState() {
		return mwrState;
	}

	public void setMwrState(boolean mwrState) {
		this.mwrState = mwrState;
	}

	public boolean isMtmXState() {
		return mtmXState;
	}

	public void setMtmXState(boolean mtmXState) {
		this.mtmXState = mtmXState;
	}

	public boolean isMtrXState() {
		return mtrXState;
	}

	public void setMtrXState(boolean mtrXState) {
		this.mtrXState = mtrXState;
	}

	public boolean isMtmYState() {
		return mtmYState;
	}

	public void setMtmYState(boolean mtmYState) {
		this.mtmYState = mtmYState;
	}

	public boolean isMtrYState() {
		return mtrYState;
	}

	public void setMtrYState(boolean mtrYState) {
		this.mtrYState = mtrYState;
	}

	public boolean isMtmZState() {
		return mtmZState;
	}

	public void setMtmZState(boolean mtmZState) {
		this.mtmZState = mtmZState;
	}

	public boolean isMtrZState() {
		return mtrZState;
	}

	public void setMtrZState(boolean mtrZState) {
		this.mtrZState = mtrZState;
	}

	public boolean isMtmState() {
		return mtmState;
	}

	public void setMtmState(boolean mtmState) {
		this.mtmState = mtmState;
	}

	public boolean isMtrState() {
		return mtrState;
	}

	public void setMtrState(boolean mtrState) {
		this.mtrState = mtrState;
	}

	public boolean isMpsState() {
		return mpsState;
	}

	public void setMpsState(boolean mpsState) {
		this.mpsState = mpsState;
	}

	public boolean isSs1State() {
		return ss1State;
	}

	public void setSs1State(boolean ss1State) {
		this.ss1State = ss1State;
	}

	public boolean isSs2State() {
		return ss2State;
	}

	public void setSs2State(boolean ss2State) {
		this.ss2State = ss2State;
	}

	public boolean isCssState() {
		return cssState;
	}

	public void setCssState(boolean cssState) {
		this.cssState = cssState;
	}

	public boolean isEsState() {
		return esState;
	}

	public void setEsState(boolean esState) {
		this.esState = esState;
	}

	public boolean isMmmState() {
		return mmmState;
	}

	public void setMmmState(boolean mmmState) {
		this.mmmState = mmmState;
	}

	public boolean isMmrState() {
		return mmrState;
	}

	public void setMmrState(boolean mmrState) {
		this.mmrState = mmrState;
	}

	public boolean isMpsMeneuverAbort() {
		return mpsMeneuverAbort;
	}

	public void setMpsMeneuverAbort(boolean mpsMeneuverAbort) {
		this.mpsMeneuverAbort = mpsMeneuverAbort;
	}

	public boolean isAocsSwError1() {
		return aocsSwError1;
	}

	public void setAocsSwError1(boolean aocsSwError1) {
		this.aocsSwError1 = aocsSwError1;
	}

	public boolean isAocsSwError2() {
		return aocsSwError2;
	}

	public void setAocsSwError2(boolean aocsSwError2) {
		this.aocsSwError2 = aocsSwError2;
	}

	public boolean isAocsSwError3() {
		return aocsSwError3;
	}

	public void setAocsSwError3(boolean aocsSwError3) {
		this.aocsSwError3 = aocsSwError3;
	}

	public boolean isAocsSwError4() {
		return aocsSwError4;
	}

	public void setAocsSwError4(boolean aocsSwError4) {
		this.aocsSwError4 = aocsSwError4;
	}

}
