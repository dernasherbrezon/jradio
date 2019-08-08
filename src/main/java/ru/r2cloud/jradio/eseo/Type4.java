package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Type4 {

	private AcsState acsState; // AOCS mode of the state machine
	private SunMode acsSunMode; // ACS sun-eclipse evaluation mode
	private AcsError acsErr;
	private float acsAttitudeQ1; // First component of AOCS quaternion vector
	private float acsAttitudeQ2; // Second component of AOCS quaternion vector
	private float acsAttitudeQ3; // Third component of AOCS quaternion vector
	private float acsAttitudeQ4; // Scalar component of AOCS quaternion vector
	private float acsOmegaP; // Roll angular velocity
	private float acsOmegaQ; // Pitch angular velocity
	private float acsOmegaR; // Yaw angular velocity
	private float acsOrbitX; // SGP4 x component
	private float acsOrbitY; // SGP4 y component
	private float acsOrbitZ; // SGP4 z component
	private float acsOrbitVx; // SGP4 Vx component
	private float acsOrbitVy; // SGP4 Vy component
	private float acsOrbitVz; // SGP4 Vz component
	private long acsStateTransition; // Seconds past from the last transition
	private float acsFdirMpsTimeErr; // If a maneuver is aborted the variable returns the time
	private long pmSpinRate; // Spin rate calculated by PMM through Coarse Sun Sensors
	private float ssmUcPcbTemp; // TMP36 sensor inside Digital PCB
	private float ssAdc1PcbTemp1; // Internal temperature sensor ADC1
	private float ssAdc2PcbTemp1; // Internal temperature sensor ADC2
	private float ssmTopcaseTemp; // External TMP36 sensor on top side of case
	private float ssmSidecaseTemp; // External TMP36 sensor on lateral side of case
	private float ssrUcPcbTemp; // TMP36 sensor inside Digital PCB
	private float ssAdc1PcbTemp2; // Internal temperature sensor ADC1
	private float ssAdc2PcbTemp2; // Internal temperature sensor ADC2
	private float ssDcdcTemp; // External temperature sensor on top side of case
	private float ssSidecaseTemp; // External TMP36 sensor on lateral side of case
	private float eseUcPcbTemp; // TMP36 sensor inside Digital PCB
	private float eseTauTemp; // Internal TAU Camera temperature
	private float mwrTemp; // Wheel temperature at TRP
	private float mwrIfTemp1; // Wheel interface board temperature at TRP
	private float mwmTemperature1; // Motor temperature
	private float mwmTemperature2; // PCB temperature (power)
	private float mwmTemperature3; // PCB temperature (controller)
	private int mpsHpt01; // High Pressure Transducer measures tank pressure
	private int mpsLpt01; // Pressure downstream the pressure regulator
	private float mpsPvtt01; // Pressure Vessel Temperature Transducer
	private float mmDcDcTemp1; // TMP36 sensor inside Digital PCB
	private float mmDcDcTemp2; // TMP36 sensor inside Digital PCB
	private float mtTemp1; // TMP36 sensor inside Digital PCB
	private float mtTemp2; // TMP36 sensor inside Digital PCB

	public Type4(DataInputStream source) throws IOException {
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(source);
		acsState = AcsState.valueOfCode(dis.readUnsignedByte());
		acsSunMode = SunMode.valueOfCode(dis.readUnsignedByte());
		acsErr = new AcsError(dis);
		acsAttitudeQ1 = dis.readFloat();
		acsAttitudeQ2 = dis.readFloat();
		acsAttitudeQ3 = dis.readFloat();
		acsAttitudeQ4 = dis.readFloat();
		acsOmegaP = dis.readFloat();
		acsOmegaQ = dis.readFloat();
		acsOmegaR = dis.readFloat();
		acsOrbitX = dis.readFloat();
		acsOrbitY = dis.readFloat();
		acsOrbitZ = dis.readFloat();
		acsOrbitVx = dis.readFloat();
		acsOrbitVy = dis.readFloat();
		acsOrbitVz = dis.readFloat();
		acsStateTransition = dis.readLong();
		acsFdirMpsTimeErr = dis.readFloat();
		pmSpinRate = dis.readUnsignedInt();
		ssmUcPcbTemp = dis.readShort() * 0.1f;
		ssAdc1PcbTemp1 = dis.readShort() * 0.1f;
		ssAdc2PcbTemp1 = dis.readShort() * 0.1f;
		ssmTopcaseTemp = dis.readShort() * 0.1f;
		ssmSidecaseTemp = dis.readShort() * 0.1f;
		ssrUcPcbTemp = dis.readShort() * 0.1f;
		ssAdc1PcbTemp2 = dis.readShort() * 0.1f;
		ssAdc2PcbTemp2 = dis.readShort() * 0.1f;
		ssDcdcTemp = dis.readShort() * 0.1f;
		ssSidecaseTemp = dis.readShort() * 0.1f;
		eseUcPcbTemp = dis.readShort() * 0.1f;
		eseTauTemp = dis.readShort() * 0.1f;
		mwrTemp = dis.readShort() * 0.1f;
		mwrIfTemp1 = dis.readShort() * 0.1f;
		mwmTemperature1 = dis.readShort() * 0.1f;
		mwmTemperature2 = dis.readShort() * 0.1f;
		mwmTemperature3 = dis.readShort() * 0.1f;
		mpsHpt01 = dis.readUnsignedShort();
		mpsLpt01 = dis.readUnsignedShort();
		mpsPvtt01 = dis.readShort() * 0.1f;
		mmDcDcTemp1 = dis.readShort() * 0.1f;
		mmDcDcTemp2 = dis.readShort() * 0.1f;
		mtTemp1 = dis.readShort() * 0.1f;
		mtTemp2 = dis.readShort() * 0.1f;
	}

	public AcsState getAcsState() {
		return acsState;
	}

	public void setAcsState(AcsState acsState) {
		this.acsState = acsState;
	}

	public SunMode getAcsSunMode() {
		return acsSunMode;
	}

	public void setAcsSunMode(SunMode acsSunMode) {
		this.acsSunMode = acsSunMode;
	}

	public AcsError getAcsErr() {
		return acsErr;
	}

	public void setAcsErr(AcsError acsErr) {
		this.acsErr = acsErr;
	}

	public float getAcsAttitudeQ1() {
		return acsAttitudeQ1;
	}

	public void setAcsAttitudeQ1(float acsAttitudeQ1) {
		this.acsAttitudeQ1 = acsAttitudeQ1;
	}

	public float getAcsAttitudeQ2() {
		return acsAttitudeQ2;
	}

	public void setAcsAttitudeQ2(float acsAttitudeQ2) {
		this.acsAttitudeQ2 = acsAttitudeQ2;
	}

	public float getAcsAttitudeQ3() {
		return acsAttitudeQ3;
	}

	public void setAcsAttitudeQ3(float acsAttitudeQ3) {
		this.acsAttitudeQ3 = acsAttitudeQ3;
	}

	public float getAcsAttitudeQ4() {
		return acsAttitudeQ4;
	}

	public void setAcsAttitudeQ4(float acsAttitudeQ4) {
		this.acsAttitudeQ4 = acsAttitudeQ4;
	}

	public float getAcsOmegaP() {
		return acsOmegaP;
	}

	public void setAcsOmegaP(float acsOmegaP) {
		this.acsOmegaP = acsOmegaP;
	}

	public float getAcsOmegaQ() {
		return acsOmegaQ;
	}

	public void setAcsOmegaQ(float acsOmegaQ) {
		this.acsOmegaQ = acsOmegaQ;
	}

	public float getAcsOmegaR() {
		return acsOmegaR;
	}

	public void setAcsOmegaR(float acsOmegaR) {
		this.acsOmegaR = acsOmegaR;
	}

	public float getAcsOrbitX() {
		return acsOrbitX;
	}

	public void setAcsOrbitX(float acsOrbitX) {
		this.acsOrbitX = acsOrbitX;
	}

	public float getAcsOrbitY() {
		return acsOrbitY;
	}

	public void setAcsOrbitY(float acsOrbitY) {
		this.acsOrbitY = acsOrbitY;
	}

	public float getAcsOrbitZ() {
		return acsOrbitZ;
	}

	public void setAcsOrbitZ(float acsOrbitZ) {
		this.acsOrbitZ = acsOrbitZ;
	}

	public float getAcsOrbitVx() {
		return acsOrbitVx;
	}

	public void setAcsOrbitVx(float acsOrbitVx) {
		this.acsOrbitVx = acsOrbitVx;
	}

	public float getAcsOrbitVy() {
		return acsOrbitVy;
	}

	public void setAcsOrbitVy(float acsOrbitVy) {
		this.acsOrbitVy = acsOrbitVy;
	}

	public float getAcsOrbitVz() {
		return acsOrbitVz;
	}

	public void setAcsOrbitVz(float acsOrbitVz) {
		this.acsOrbitVz = acsOrbitVz;
	}

	public long getAcsStateTransition() {
		return acsStateTransition;
	}

	public void setAcsStateTransition(long acsStateTransition) {
		this.acsStateTransition = acsStateTransition;
	}

	public float getAcsFdirMpsTimeErr() {
		return acsFdirMpsTimeErr;
	}

	public void setAcsFdirMpsTimeErr(float acsFdirMpsTimeErr) {
		this.acsFdirMpsTimeErr = acsFdirMpsTimeErr;
	}

	public long getPmSpinRate() {
		return pmSpinRate;
	}

	public void setPmSpinRate(long pmSpinRate) {
		this.pmSpinRate = pmSpinRate;
	}

	public float getSsmUcPcbTemp() {
		return ssmUcPcbTemp;
	}

	public void setSsmUcPcbTemp(float ssmUcPcbTemp) {
		this.ssmUcPcbTemp = ssmUcPcbTemp;
	}

	public float getSsAdc1PcbTemp1() {
		return ssAdc1PcbTemp1;
	}

	public void setSsAdc1PcbTemp1(float ssAdc1PcbTemp1) {
		this.ssAdc1PcbTemp1 = ssAdc1PcbTemp1;
	}

	public float getSsAdc2PcbTemp1() {
		return ssAdc2PcbTemp1;
	}

	public void setSsAdc2PcbTemp1(float ssAdc2PcbTemp1) {
		this.ssAdc2PcbTemp1 = ssAdc2PcbTemp1;
	}

	public float getSsmTopcaseTemp() {
		return ssmTopcaseTemp;
	}

	public void setSsmTopcaseTemp(float ssmTopcaseTemp) {
		this.ssmTopcaseTemp = ssmTopcaseTemp;
	}

	public float getSsmSidecaseTemp() {
		return ssmSidecaseTemp;
	}

	public void setSsmSidecaseTemp(float ssmSidecaseTemp) {
		this.ssmSidecaseTemp = ssmSidecaseTemp;
	}

	public float getSsrUcPcbTemp() {
		return ssrUcPcbTemp;
	}

	public void setSsrUcPcbTemp(float ssrUcPcbTemp) {
		this.ssrUcPcbTemp = ssrUcPcbTemp;
	}

	public float getSsAdc1PcbTemp2() {
		return ssAdc1PcbTemp2;
	}

	public void setSsAdc1PcbTemp2(float ssAdc1PcbTemp2) {
		this.ssAdc1PcbTemp2 = ssAdc1PcbTemp2;
	}

	public float getSsAdc2PcbTemp2() {
		return ssAdc2PcbTemp2;
	}

	public void setSsAdc2PcbTemp2(float ssAdc2PcbTemp2) {
		this.ssAdc2PcbTemp2 = ssAdc2PcbTemp2;
	}

	public float getSsDcdcTemp() {
		return ssDcdcTemp;
	}

	public void setSsDcdcTemp(float ssDcdcTemp) {
		this.ssDcdcTemp = ssDcdcTemp;
	}

	public float getSsSidecaseTemp() {
		return ssSidecaseTemp;
	}

	public void setSsSidecaseTemp(float ssSidecaseTemp) {
		this.ssSidecaseTemp = ssSidecaseTemp;
	}

	public float getEseUcPcbTemp() {
		return eseUcPcbTemp;
	}

	public void setEseUcPcbTemp(float eseUcPcbTemp) {
		this.eseUcPcbTemp = eseUcPcbTemp;
	}

	public float getEseTauTemp() {
		return eseTauTemp;
	}

	public void setEseTauTemp(float eseTauTemp) {
		this.eseTauTemp = eseTauTemp;
	}

	public float getMwrTemp() {
		return mwrTemp;
	}

	public void setMwrTemp(float mwrTemp) {
		this.mwrTemp = mwrTemp;
	}

	public float getMwrIfTemp1() {
		return mwrIfTemp1;
	}

	public void setMwrIfTemp1(float mwrIfTemp1) {
		this.mwrIfTemp1 = mwrIfTemp1;
	}

	public float getMwmTemperature1() {
		return mwmTemperature1;
	}

	public void setMwmTemperature1(float mwmTemperature1) {
		this.mwmTemperature1 = mwmTemperature1;
	}

	public float getMwmTemperature2() {
		return mwmTemperature2;
	}

	public void setMwmTemperature2(float mwmTemperature2) {
		this.mwmTemperature2 = mwmTemperature2;
	}

	public float getMwmTemperature3() {
		return mwmTemperature3;
	}

	public void setMwmTemperature3(float mwmTemperature3) {
		this.mwmTemperature3 = mwmTemperature3;
	}

	public int getMpsHpt01() {
		return mpsHpt01;
	}

	public void setMpsHpt01(int mpsHpt01) {
		this.mpsHpt01 = mpsHpt01;
	}

	public int getMpsLpt01() {
		return mpsLpt01;
	}

	public void setMpsLpt01(int mpsLpt01) {
		this.mpsLpt01 = mpsLpt01;
	}

	public float getMpsPvtt01() {
		return mpsPvtt01;
	}

	public void setMpsPvtt01(float mpsPvtt01) {
		this.mpsPvtt01 = mpsPvtt01;
	}

	public float getMmDcDcTemp1() {
		return mmDcDcTemp1;
	}

	public void setMmDcDcTemp1(float mmDcDcTemp1) {
		this.mmDcDcTemp1 = mmDcDcTemp1;
	}

	public float getMmDcDcTemp2() {
		return mmDcDcTemp2;
	}

	public void setMmDcDcTemp2(float mmDcDcTemp2) {
		this.mmDcDcTemp2 = mmDcDcTemp2;
	}

	public float getMtTemp1() {
		return mtTemp1;
	}

	public void setMtTemp1(float mtTemp1) {
		this.mtTemp1 = mtTemp1;
	}

	public float getMtTemp2() {
		return mtTemp2;
	}

	public void setMtTemp2(float mtTemp2) {
		this.mtTemp2 = mtTemp2;
	}

}
