package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Type4 {

	private AcsState ACS_STATE; // AOCS mode of the state machine
	private SunMode ACS_SUN_MODE; // ACS sun-eclipse evaluation mode
	private AcsError ACS_ERR;
	private float ACS_ATTITUDE_Q1; // First component of AOCS quaternion vector
	private float ACS_ATTITUDE_Q2; // Second component of AOCS quaternion vector
	private float ACS_ATTITUDE_Q3; // Third component of AOCS quaternion vector
	private float ACS_ATTITUDE_Q4; // Scalar component of AOCS quaternion vector
	private float ACS_OMEGA_P; // Roll angular velocity
	private float ACS_OMEGA_Q; // Pitch angular velocity
	private float ACS_OMEGA_R; // Yaw angular velocity
	private float ACS_ORBIT_x; // SGP4 x component
	private float ACS_ORBIT_y; // SGP4 y component
	private float ACS_ORBIT_z; // SGP4 z component
	private float ACS_ORBIT_Vx; // SGP4 Vx component
	private float ACS_ORBIT_Vy; // SGP4 Vy component
	private float ACS_ORBIT_Vz; // SGP4 Vz component
	private long ACS_STATE_TRANSITION; // Seconds past from the last transition
	private float ACS_FDIR_MPS_time_err; // If a maneuver is aborted the variable returns the time
	private long PM_SPIN_RATE; // Spin rate calculated by PMM through Coarse Sun Sensors
	private float SSM_uC_PCB_TEMP; // TMP36 sensor inside Digital PCB
	private float SS_ADC1_PCB_TEMP1; // Internal temperature sensor ADC1
	private float SS_ADC2_PCB_TEMP1; // Internal temperature sensor ADC2
	private float SSM_TOPCASE_TEMP; // External TMP36 sensor on top side of case
	private float SSM_SIDECASE_TEMP; // External TMP36 sensor on lateral side of case
	private float SSR_uC_PCB_TEMP; // TMP36 sensor inside Digital PCB
	private float SS_ADC1_PCB_TEMP2; // Internal temperature sensor ADC1
	private float SS_ADC2_PCB_TEMP2; // Internal temperature sensor ADC2
	private float SS_DCDC_TEMP; // External temperature sensor on top side of case
	private float SS_SIDECASE_TEMP; // External TMP36 sensor on lateral side of case
	private float ESE_uC_PCB_TEMP; // TMP36 sensor inside Digital PCB
	private float ESE_TAU_TEMP; // Internal TAU Camera temperature
	private float MWR_TEMP; // Wheel temperature at TRP
	private float MWR_IF_TEMP1; // Wheel interface board temperature at TRP
	private float MWM_TEMPERATURE_1; // Motor temperature
	private float MWM_TEMPERATURE_2; // PCB temperature (power)
	private float MWM_TEMPERATURE_3; // PCB temperature (controller)
	private int MPS_HPT01; // High Pressure Transducer measures tank pressure
	private int MPS_LPT01; // Pressure downstream the pressure regulator
	private float MPS_PVTT01; // Pressure Vessel Temperature Transducer
	private float MM_DC_DC_TEMP1; // TMP36 sensor inside Digital PCB
	private float MM_DC_DC_TEMP2; // TMP36 sensor inside Digital PCB
	private float MT_TEMP1;  // TMP36 sensor inside Digital PCB
	private float MT_TEMP2; // TMP36 sensor inside Digital PCB

	public Type4(DataInputStream source) throws IOException {
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(source);
		ACS_STATE = AcsState.valueOfCode(dis.readUnsignedByte());
		ACS_SUN_MODE = SunMode.valueOfCode(dis.readUnsignedByte());
		ACS_ERR = new AcsError(dis);
		ACS_ATTITUDE_Q1 = dis.readFloat();
		ACS_ATTITUDE_Q2 = dis.readFloat();
		ACS_ATTITUDE_Q3 = dis.readFloat();
		ACS_ATTITUDE_Q4 = dis.readFloat();
		ACS_OMEGA_P = dis.readFloat();
		ACS_OMEGA_Q = dis.readFloat();
		ACS_OMEGA_R = dis.readFloat();
		ACS_ORBIT_x = dis.readFloat();
		ACS_ORBIT_y = dis.readFloat();
		ACS_ORBIT_z = dis.readFloat();
		ACS_ORBIT_Vx = dis.readFloat();
		ACS_ORBIT_Vy = dis.readFloat();
		ACS_ORBIT_Vz = dis.readFloat();
		ACS_STATE_TRANSITION = dis.readLong();
		ACS_FDIR_MPS_time_err = dis.readFloat();
		PM_SPIN_RATE = dis.readUnsignedInt();
		SSM_uC_PCB_TEMP = dis.readShort() * 0.1f;
		SS_ADC1_PCB_TEMP1 = dis.readShort() * 0.1f;
		SS_ADC2_PCB_TEMP1 = dis.readShort() * 0.1f;
		SSM_TOPCASE_TEMP = dis.readShort() * 0.1f;
		SSM_SIDECASE_TEMP = dis.readShort() * 0.1f;
		SSR_uC_PCB_TEMP = dis.readShort() * 0.1f;
		SS_ADC1_PCB_TEMP2 = dis.readShort() * 0.1f;
		SS_ADC2_PCB_TEMP2 = dis.readShort() * 0.1f;
		SS_DCDC_TEMP = dis.readShort() * 0.1f;
		SS_SIDECASE_TEMP = dis.readShort() * 0.1f;
		ESE_uC_PCB_TEMP = dis.readShort() * 0.1f;
		ESE_TAU_TEMP = dis.readShort() * 0.1f;
		MWR_TEMP = dis.readShort() * 0.1f;
		MWR_IF_TEMP1 = dis.readShort() * 0.1f;
		MWM_TEMPERATURE_1 = dis.readShort() * 0.1f;
		MWM_TEMPERATURE_2 = dis.readShort() * 0.1f;
		MWM_TEMPERATURE_3 = dis.readShort() * 0.1f;
		MPS_HPT01 = dis.readUnsignedShort();
		MPS_LPT01 = dis.readUnsignedShort();
		MPS_PVTT01 = dis.readShort() * 0.1f;
		MM_DC_DC_TEMP1 = dis.readShort() * 0.1f;
		MM_DC_DC_TEMP2 = dis.readShort() * 0.1f;
		MT_TEMP1 = dis.readShort() * 0.1f;
		MT_TEMP2 = dis.readShort() * 0.1f;
	}

	public AcsState getACS_STATE() {
		return ACS_STATE;
	}

	public void setACS_STATE(AcsState aCS_STATE) {
		ACS_STATE = aCS_STATE;
	}

	public SunMode getACS_SUN_MODE() {
		return ACS_SUN_MODE;
	}

	public void setACS_SUN_MODE(SunMode aCS_SUN_MODE) {
		ACS_SUN_MODE = aCS_SUN_MODE;
	}

	public AcsError getACS_ERR() {
		return ACS_ERR;
	}

	public void setACS_ERR(AcsError aCS_ERR) {
		ACS_ERR = aCS_ERR;
	}

	public float getACS_ATTITUDE_Q1() {
		return ACS_ATTITUDE_Q1;
	}

	public void setACS_ATTITUDE_Q1(float aCS_ATTITUDE_Q1) {
		ACS_ATTITUDE_Q1 = aCS_ATTITUDE_Q1;
	}

	public float getACS_ATTITUDE_Q2() {
		return ACS_ATTITUDE_Q2;
	}

	public void setACS_ATTITUDE_Q2(float aCS_ATTITUDE_Q2) {
		ACS_ATTITUDE_Q2 = aCS_ATTITUDE_Q2;
	}

	public float getACS_ATTITUDE_Q3() {
		return ACS_ATTITUDE_Q3;
	}

	public void setACS_ATTITUDE_Q3(float aCS_ATTITUDE_Q3) {
		ACS_ATTITUDE_Q3 = aCS_ATTITUDE_Q3;
	}

	public float getACS_ATTITUDE_Q4() {
		return ACS_ATTITUDE_Q4;
	}

	public void setACS_ATTITUDE_Q4(float aCS_ATTITUDE_Q4) {
		ACS_ATTITUDE_Q4 = aCS_ATTITUDE_Q4;
	}

	public float getACS_OMEGA_P() {
		return ACS_OMEGA_P;
	}

	public void setACS_OMEGA_P(float aCS_OMEGA_P) {
		ACS_OMEGA_P = aCS_OMEGA_P;
	}

	public float getACS_OMEGA_Q() {
		return ACS_OMEGA_Q;
	}

	public void setACS_OMEGA_Q(float aCS_OMEGA_Q) {
		ACS_OMEGA_Q = aCS_OMEGA_Q;
	}

	public float getACS_OMEGA_R() {
		return ACS_OMEGA_R;
	}

	public void setACS_OMEGA_R(float aCS_OMEGA_R) {
		ACS_OMEGA_R = aCS_OMEGA_R;
	}

	public float getACS_ORBIT_x() {
		return ACS_ORBIT_x;
	}

	public void setACS_ORBIT_x(float aCS_ORBIT_x) {
		ACS_ORBIT_x = aCS_ORBIT_x;
	}

	public float getACS_ORBIT_y() {
		return ACS_ORBIT_y;
	}

	public void setACS_ORBIT_y(float aCS_ORBIT_y) {
		ACS_ORBIT_y = aCS_ORBIT_y;
	}

	public float getACS_ORBIT_z() {
		return ACS_ORBIT_z;
	}

	public void setACS_ORBIT_z(float aCS_ORBIT_z) {
		ACS_ORBIT_z = aCS_ORBIT_z;
	}

	public float getACS_ORBIT_Vx() {
		return ACS_ORBIT_Vx;
	}

	public void setACS_ORBIT_Vx(float aCS_ORBIT_Vx) {
		ACS_ORBIT_Vx = aCS_ORBIT_Vx;
	}

	public float getACS_ORBIT_Vy() {
		return ACS_ORBIT_Vy;
	}

	public void setACS_ORBIT_Vy(float aCS_ORBIT_Vy) {
		ACS_ORBIT_Vy = aCS_ORBIT_Vy;
	}

	public float getACS_ORBIT_Vz() {
		return ACS_ORBIT_Vz;
	}

	public void setACS_ORBIT_Vz(float aCS_ORBIT_Vz) {
		ACS_ORBIT_Vz = aCS_ORBIT_Vz;
	}

	public long getACS_STATE_TRANSITION() {
		return ACS_STATE_TRANSITION;
	}

	public void setACS_STATE_TRANSITION(long aCS_STATE_TRANSITION) {
		ACS_STATE_TRANSITION = aCS_STATE_TRANSITION;
	}

	public float getACS_FDIR_MPS_time_err() {
		return ACS_FDIR_MPS_time_err;
	}

	public void setACS_FDIR_MPS_time_err(float aCS_FDIR_MPS_time_err) {
		ACS_FDIR_MPS_time_err = aCS_FDIR_MPS_time_err;
	}

	public long getPM_SPIN_RATE() {
		return PM_SPIN_RATE;
	}

	public void setPM_SPIN_RATE(long pM_SPIN_RATE) {
		PM_SPIN_RATE = pM_SPIN_RATE;
	}

	public float getSSM_uC_PCB_TEMP() {
		return SSM_uC_PCB_TEMP;
	}

	public void setSSM_uC_PCB_TEMP(float sSM_uC_PCB_TEMP) {
		SSM_uC_PCB_TEMP = sSM_uC_PCB_TEMP;
	}

	public float getSS_ADC1_PCB_TEMP1() {
		return SS_ADC1_PCB_TEMP1;
	}

	public void setSS_ADC1_PCB_TEMP1(float sS_ADC1_PCB_TEMP1) {
		SS_ADC1_PCB_TEMP1 = sS_ADC1_PCB_TEMP1;
	}

	public float getSS_ADC2_PCB_TEMP1() {
		return SS_ADC2_PCB_TEMP1;
	}

	public void setSS_ADC2_PCB_TEMP1(float sS_ADC2_PCB_TEMP1) {
		SS_ADC2_PCB_TEMP1 = sS_ADC2_PCB_TEMP1;
	}

	public float getSSM_TOPCASE_TEMP() {
		return SSM_TOPCASE_TEMP;
	}

	public void setSSM_TOPCASE_TEMP(float sSM_TOPCASE_TEMP) {
		SSM_TOPCASE_TEMP = sSM_TOPCASE_TEMP;
	}

	public float getSSM_SIDECASE_TEMP() {
		return SSM_SIDECASE_TEMP;
	}

	public void setSSM_SIDECASE_TEMP(float sSM_SIDECASE_TEMP) {
		SSM_SIDECASE_TEMP = sSM_SIDECASE_TEMP;
	}

	public float getSSR_uC_PCB_TEMP() {
		return SSR_uC_PCB_TEMP;
	}

	public void setSSR_uC_PCB_TEMP(float sSR_uC_PCB_TEMP) {
		SSR_uC_PCB_TEMP = sSR_uC_PCB_TEMP;
	}

	public float getSS_ADC1_PCB_TEMP2() {
		return SS_ADC1_PCB_TEMP2;
	}

	public void setSS_ADC1_PCB_TEMP2(float sS_ADC1_PCB_TEMP2) {
		SS_ADC1_PCB_TEMP2 = sS_ADC1_PCB_TEMP2;
	}

	public float getSS_ADC2_PCB_TEMP2() {
		return SS_ADC2_PCB_TEMP2;
	}

	public void setSS_ADC2_PCB_TEMP2(float sS_ADC2_PCB_TEMP2) {
		SS_ADC2_PCB_TEMP2 = sS_ADC2_PCB_TEMP2;
	}

	public float getSS_DCDC_TEMP() {
		return SS_DCDC_TEMP;
	}

	public void setSS_DCDC_TEMP(float sS_DCDC_TEMP) {
		SS_DCDC_TEMP = sS_DCDC_TEMP;
	}

	public float getSS_SIDECASE_TEMP() {
		return SS_SIDECASE_TEMP;
	}

	public void setSS_SIDECASE_TEMP(float sS_SIDECASE_TEMP) {
		SS_SIDECASE_TEMP = sS_SIDECASE_TEMP;
	}

	public float getESE_uC_PCB_TEMP() {
		return ESE_uC_PCB_TEMP;
	}

	public void setESE_uC_PCB_TEMP(float eSE_uC_PCB_TEMP) {
		ESE_uC_PCB_TEMP = eSE_uC_PCB_TEMP;
	}

	public float getESE_TAU_TEMP() {
		return ESE_TAU_TEMP;
	}

	public void setESE_TAU_TEMP(float eSE_TAU_TEMP) {
		ESE_TAU_TEMP = eSE_TAU_TEMP;
	}

	public float getMWR_TEMP() {
		return MWR_TEMP;
	}

	public void setMWR_TEMP(float mWR_TEMP) {
		MWR_TEMP = mWR_TEMP;
	}

	public float getMWR_IF_TEMP1() {
		return MWR_IF_TEMP1;
	}

	public void setMWR_IF_TEMP1(float mWR_IF_TEMP1) {
		MWR_IF_TEMP1 = mWR_IF_TEMP1;
	}

	public float getMWM_TEMPERATURE_1() {
		return MWM_TEMPERATURE_1;
	}

	public void setMWM_TEMPERATURE_1(float mWM_TEMPERATURE_1) {
		MWM_TEMPERATURE_1 = mWM_TEMPERATURE_1;
	}

	public float getMWM_TEMPERATURE_2() {
		return MWM_TEMPERATURE_2;
	}

	public void setMWM_TEMPERATURE_2(float mWM_TEMPERATURE_2) {
		MWM_TEMPERATURE_2 = mWM_TEMPERATURE_2;
	}

	public float getMWM_TEMPERATURE_3() {
		return MWM_TEMPERATURE_3;
	}

	public void setMWM_TEMPERATURE_3(float mWM_TEMPERATURE_3) {
		MWM_TEMPERATURE_3 = mWM_TEMPERATURE_3;
	}

	public int getMPS_HPT01() {
		return MPS_HPT01;
	}

	public void setMPS_HPT01(int mPS_HPT01) {
		MPS_HPT01 = mPS_HPT01;
	}

	public int getMPS_LPT01() {
		return MPS_LPT01;
	}

	public void setMPS_LPT01(int mPS_LPT01) {
		MPS_LPT01 = mPS_LPT01;
	}

	public float getMPS_PVTT01() {
		return MPS_PVTT01;
	}

	public void setMPS_PVTT01(float mPS_PVTT01) {
		MPS_PVTT01 = mPS_PVTT01;
	}

	public float getMM_DC_DC_TEMP1() {
		return MM_DC_DC_TEMP1;
	}

	public void setMM_DC_DC_TEMP1(float mM_DC_DC_TEMP1) {
		MM_DC_DC_TEMP1 = mM_DC_DC_TEMP1;
	}

	public float getMM_DC_DC_TEMP2() {
		return MM_DC_DC_TEMP2;
	}

	public void setMM_DC_DC_TEMP2(float mM_DC_DC_TEMP2) {
		MM_DC_DC_TEMP2 = mM_DC_DC_TEMP2;
	}

	public float getMT_TEMP1() {
		return MT_TEMP1;
	}

	public void setMT_TEMP1(float mT_TEMP1) {
		MT_TEMP1 = mT_TEMP1;
	}

	public float getMT_TEMP2() {
		return MT_TEMP2;
	}

	public void setMT_TEMP2(float mT_TEMP2) {
		MT_TEMP2 = mT_TEMP2;
	}

}
