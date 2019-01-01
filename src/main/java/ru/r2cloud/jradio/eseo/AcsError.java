package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

public class AcsError {

	private boolean SAFE_MODE; // Transition between Nominal to Detumbling & Safe has been triggered
	private boolean OMEGA_DET_WARNING; // Angular velocity out of nominal range in Detumbling and Safe mode
	private boolean ATTITUDE_ERROR; // Attitude Euler angles out of nominal range in Nominal mode
	private boolean OMEGA_ERROR; // Angular velocity out of nominal range in Nominal mode
	private boolean MW_Failure; // MW critical failure
	private boolean PWR_Failure; // Severe low power (S2)
	private boolean COMM_Error; // A communication error is occurred
	private boolean ITEMS_MNG_Error; // Error between the items used and the PMU feedback
	private boolean MWM_State; // MWM not reliable - from FDIR algorithm
	private boolean MWR_State; // MWR not reliable - from FDIR algorithm
	private boolean MTM_x_State; // MT Main on x-axis not reliable - from FDIR algorithm
	private boolean MTR_x_State; // MTR on x-axis not reliable - from FDIR algorithm
	private boolean MTM_y_State; // MT Main on y-axis not reliable - from FDIR algorithm
	private boolean MTR_y_State; // MTR on y-axis not reliable - from FDIR algorithm
	private boolean MTM_z_State; // MT Main on z-axis not reliable - from FDIR algorithm
	private boolean MTR_z_State; // MTR on z-axis not reliable - from FDIR algorithm
	private boolean MTM_state; // MT Main assembly not reliable
	private boolean MTR_state; // MT Redundant assembly not reliable
	private boolean MPS_State; // MPS not reliable - from FDIR algorithm
	private boolean SS1_State; // SS1 not reliable - from FDIR algorithm
	private boolean SS2_State; // SS2 not reliable - from FDIR algorithm
	private boolean CSS_State; // CSS not reliable - from FDIR algorithm
	private boolean ES_State; // ES not reliable - from FDIR algorithm
	private boolean MMM_State; // MM Main not reliable - from FDIR algorithm
	private boolean MMR_State; // MM Redundant not reliable - from FDIR algorithm
	private boolean MPS_maneuver_ABORT; // MPS maneuver aborted from FDIR (MPS not reliable) or AOCS not in Nominal state

	private boolean AOCS_SW_ERROR1; // TC execution returns an error
	private boolean AOCS_SW_ERROR2; // AOCS task overrun
	private boolean AOCS_SW_ERROR3; // AOCS FDIR task overrun
	private boolean AOCS_SW_ERROR4;

	public AcsError(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		SAFE_MODE = ((raw >> 7) & 0x1) > 0;
		OMEGA_DET_WARNING = ((raw >> 6) & 0x1) > 0;
		ATTITUDE_ERROR = ((raw >> 5) & 0x1) > 0;
		OMEGA_ERROR = ((raw >> 4) & 0x1) > 0;
		MW_Failure = ((raw >> 3) & 0x1) > 0;
		PWR_Failure = ((raw >> 2) & 0x1) > 0;
		COMM_Error = ((raw >> 1) & 0x1) > 0;
		ITEMS_MNG_Error = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		MWM_State = ((raw >> 7) & 0x1) > 0;
		MWR_State = ((raw >> 6) & 0x1) > 0;
		MTM_x_State = ((raw >> 5) & 0x1) > 0;
		MTR_x_State = ((raw >> 4) & 0x1) > 0;
		MTM_y_State = ((raw >> 3) & 0x1) > 0;
		MTR_y_State = ((raw >> 2) & 0x1) > 0;
		MTM_z_State = ((raw >> 1) & 0x1) > 0;
		MTR_z_State = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		MTM_state = ((raw >> 7) & 0x1) > 0;
		MTR_state = ((raw >> 6) & 0x1) > 0;
		MPS_State = ((raw >> 5) & 0x1) > 0;
		SS1_State = ((raw >> 4) & 0x1) > 0;
		SS2_State = ((raw >> 3) & 0x1) > 0;
		CSS_State = ((raw >> 2) & 0x1) > 0;
		ES_State = ((raw >> 1) & 0x1) > 0;
		MMM_State = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		MMR_State = ((raw >> 7) & 0x1) > 0;
		MPS_maneuver_ABORT = ((raw >> 6) & 0x1) > 0;

		AOCS_SW_ERROR1 = ((raw >> 3) & 0x1) > 0;
		AOCS_SW_ERROR2 = ((raw >> 2) & 0x1) > 0;
		AOCS_SW_ERROR3 = ((raw >> 1) & 0x1) > 0;
		AOCS_SW_ERROR4 = ((raw >> 0) & 0x1) > 0;
	}

	public boolean isSAFE_MODE() {
		return SAFE_MODE;
	}

	public void setSAFE_MODE(boolean sAFE_MODE) {
		SAFE_MODE = sAFE_MODE;
	}

	public boolean isOMEGA_DET_WARNING() {
		return OMEGA_DET_WARNING;
	}

	public void setOMEGA_DET_WARNING(boolean oMEGA_DET_WARNING) {
		OMEGA_DET_WARNING = oMEGA_DET_WARNING;
	}

	public boolean isATTITUDE_ERROR() {
		return ATTITUDE_ERROR;
	}

	public void setATTITUDE_ERROR(boolean aTTITUDE_ERROR) {
		ATTITUDE_ERROR = aTTITUDE_ERROR;
	}

	public boolean isOMEGA_ERROR() {
		return OMEGA_ERROR;
	}

	public void setOMEGA_ERROR(boolean oMEGA_ERROR) {
		OMEGA_ERROR = oMEGA_ERROR;
	}

	public boolean isMW_Failure() {
		return MW_Failure;
	}

	public void setMW_Failure(boolean mW_Failure) {
		MW_Failure = mW_Failure;
	}

	public boolean isPWR_Failure() {
		return PWR_Failure;
	}

	public void setPWR_Failure(boolean pWR_Failure) {
		PWR_Failure = pWR_Failure;
	}

	public boolean isCOMM_Error() {
		return COMM_Error;
	}

	public void setCOMM_Error(boolean cOMM_Error) {
		COMM_Error = cOMM_Error;
	}

	public boolean isITEMS_MNG_Error() {
		return ITEMS_MNG_Error;
	}

	public void setITEMS_MNG_Error(boolean iTEMS_MNG_Error) {
		ITEMS_MNG_Error = iTEMS_MNG_Error;
	}

	public boolean isMWM_State() {
		return MWM_State;
	}

	public void setMWM_State(boolean mWM_State) {
		MWM_State = mWM_State;
	}

	public boolean isMWR_State() {
		return MWR_State;
	}

	public void setMWR_State(boolean mWR_State) {
		MWR_State = mWR_State;
	}

	public boolean isMTM_x_State() {
		return MTM_x_State;
	}

	public void setMTM_x_State(boolean mTM_x_State) {
		MTM_x_State = mTM_x_State;
	}

	public boolean isMTR_x_State() {
		return MTR_x_State;
	}

	public void setMTR_x_State(boolean mTR_x_State) {
		MTR_x_State = mTR_x_State;
	}

	public boolean isMTM_y_State() {
		return MTM_y_State;
	}

	public void setMTM_y_State(boolean mTM_y_State) {
		MTM_y_State = mTM_y_State;
	}

	public boolean isMTR_y_State() {
		return MTR_y_State;
	}

	public void setMTR_y_State(boolean mTR_y_State) {
		MTR_y_State = mTR_y_State;
	}

	public boolean isMTM_z_State() {
		return MTM_z_State;
	}

	public void setMTM_z_State(boolean mTM_z_State) {
		MTM_z_State = mTM_z_State;
	}

	public boolean isMTR_z_State() {
		return MTR_z_State;
	}

	public void setMTR_z_State(boolean mTR_z_State) {
		MTR_z_State = mTR_z_State;
	}

	public boolean isMTM_state() {
		return MTM_state;
	}

	public void setMTM_state(boolean mTM_state) {
		MTM_state = mTM_state;
	}

	public boolean isMTR_state() {
		return MTR_state;
	}

	public void setMTR_state(boolean mTR_state) {
		MTR_state = mTR_state;
	}

	public boolean isMPS_State() {
		return MPS_State;
	}

	public void setMPS_State(boolean mPS_State) {
		MPS_State = mPS_State;
	}

	public boolean isSS1_State() {
		return SS1_State;
	}

	public void setSS1_State(boolean sS1_State) {
		SS1_State = sS1_State;
	}

	public boolean isSS2_State() {
		return SS2_State;
	}

	public void setSS2_State(boolean sS2_State) {
		SS2_State = sS2_State;
	}

	public boolean isCSS_State() {
		return CSS_State;
	}

	public void setCSS_State(boolean cSS_State) {
		CSS_State = cSS_State;
	}

	public boolean isES_State() {
		return ES_State;
	}

	public void setES_State(boolean eS_State) {
		ES_State = eS_State;
	}

	public boolean isMMM_State() {
		return MMM_State;
	}

	public void setMMM_State(boolean mMM_State) {
		MMM_State = mMM_State;
	}

	public boolean isMMR_State() {
		return MMR_State;
	}

	public void setMMR_State(boolean mMR_State) {
		MMR_State = mMR_State;
	}

	public boolean isMPS_maneuver_ABORT() {
		return MPS_maneuver_ABORT;
	}

	public void setMPS_maneuver_ABORT(boolean mPS_maneuver_ABORT) {
		MPS_maneuver_ABORT = mPS_maneuver_ABORT;
	}

	public boolean isAOCS_SW_ERROR1() {
		return AOCS_SW_ERROR1;
	}

	public void setAOCS_SW_ERROR1(boolean aOCS_SW_ERROR1) {
		AOCS_SW_ERROR1 = aOCS_SW_ERROR1;
	}

	public boolean isAOCS_SW_ERROR2() {
		return AOCS_SW_ERROR2;
	}

	public void setAOCS_SW_ERROR2(boolean aOCS_SW_ERROR2) {
		AOCS_SW_ERROR2 = aOCS_SW_ERROR2;
	}

	public boolean isAOCS_SW_ERROR3() {
		return AOCS_SW_ERROR3;
	}

	public void setAOCS_SW_ERROR3(boolean aOCS_SW_ERROR3) {
		AOCS_SW_ERROR3 = aOCS_SW_ERROR3;
	}

	public boolean isAOCS_SW_ERROR4() {
		return AOCS_SW_ERROR4;
	}

	public void setAOCS_SW_ERROR4(boolean aOCS_SW_ERROR4) {
		AOCS_SW_ERROR4 = aOCS_SW_ERROR4;
	}

}
