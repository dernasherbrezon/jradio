package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswAttDet {

	private float qBodyWrtEci1;
	private float qBodyWrtEci2;
	private float qBodyWrtEci3;
	private float qBodyWrtEci4;
	private float residual1;
	private float residual2;
	private float residual3;
	private float bodyRate1;
	private float bodyRate2;
	private float bodyRate3;
	private float gyroBiasEst1;
	private float gyroBiasEst2;
	private float gyroBiasEst3;
	private AltitudeAlg altitudeAlg;
	private long goodAttTimer;
	private long badAttTimer;
	private long badRateTimer;
	private long reinitCount;
	private boolean attitudeValid;
	private boolean measAttValid;
	private boolean measRateValid;
	private int trackerUsed;
	private int trackerPreference;

	public FswAttDet() {
		// do nothing
	}

	public FswAttDet(DataInputStream dis) throws IOException {
		qBodyWrtEci1 = dis.readInt() * 5e-10f;
		qBodyWrtEci2 = dis.readInt() * 5e-10f;
		qBodyWrtEci3 = dis.readInt() * 5e-10f;
		qBodyWrtEci4 = dis.readInt() * 5e-10f;
		residual1 = dis.readInt() * 5e-10f;
		residual2 = dis.readInt() * 5e-10f;
		residual3 = dis.readInt() * 5e-10f;
		bodyRate1 = dis.readInt() * 5e-09f;
		bodyRate2 = dis.readInt() * 5e-09f;
		bodyRate3 = dis.readInt() * 5e-09f;
		gyroBiasEst1 = dis.readShort() / 209439.5f;
		gyroBiasEst2 = dis.readShort() / 209439.5f;
		gyroBiasEst3 = dis.readShort() / 209439.5f;
		altitudeAlg = AltitudeAlg.valueOfCode(dis.readUnsignedByte());
		goodAttTimer = StreamUtils.readUnsignedInt(dis);
		badAttTimer = StreamUtils.readUnsignedInt(dis);
		badRateTimer = StreamUtils.readUnsignedInt(dis);
		reinitCount = StreamUtils.readUnsignedInt(dis);
		attitudeValid = dis.readBoolean();
		measAttValid = dis.readBoolean();
		measRateValid = dis.readBoolean();
		trackerUsed = dis.readUnsignedByte();
		trackerPreference = dis.readUnsignedByte();
	}

	public float getqBodyWrtEci1() {
		return qBodyWrtEci1;
	}

	public void setqBodyWrtEci1(float qBodyWrtEci1) {
		this.qBodyWrtEci1 = qBodyWrtEci1;
	}

	public float getqBodyWrtEci2() {
		return qBodyWrtEci2;
	}

	public void setqBodyWrtEci2(float qBodyWrtEci2) {
		this.qBodyWrtEci2 = qBodyWrtEci2;
	}

	public float getqBodyWrtEci3() {
		return qBodyWrtEci3;
	}

	public void setqBodyWrtEci3(float qBodyWrtEci3) {
		this.qBodyWrtEci3 = qBodyWrtEci3;
	}

	public float getqBodyWrtEci4() {
		return qBodyWrtEci4;
	}

	public void setqBodyWrtEci4(float qBodyWrtEci4) {
		this.qBodyWrtEci4 = qBodyWrtEci4;
	}

	public float getResidual1() {
		return residual1;
	}

	public void setResidual1(float residual1) {
		this.residual1 = residual1;
	}

	public float getResidual2() {
		return residual2;
	}

	public void setResidual2(float residual2) {
		this.residual2 = residual2;
	}

	public float getResidual3() {
		return residual3;
	}

	public void setResidual3(float residual3) {
		this.residual3 = residual3;
	}

	public float getBodyRate1() {
		return bodyRate1;
	}

	public void setBodyRate1(float bodyRate1) {
		this.bodyRate1 = bodyRate1;
	}

	public float getBodyRate2() {
		return bodyRate2;
	}

	public void setBodyRate2(float bodyRate2) {
		this.bodyRate2 = bodyRate2;
	}

	public float getBodyRate3() {
		return bodyRate3;
	}

	public void setBodyRate3(float bodyRate3) {
		this.bodyRate3 = bodyRate3;
	}

	public float getGyroBiasEst1() {
		return gyroBiasEst1;
	}

	public void setGyroBiasEst1(float gyroBiasEst1) {
		this.gyroBiasEst1 = gyroBiasEst1;
	}

	public float getGyroBiasEst2() {
		return gyroBiasEst2;
	}

	public void setGyroBiasEst2(float gyroBiasEst2) {
		this.gyroBiasEst2 = gyroBiasEst2;
	}

	public float getGyroBiasEst3() {
		return gyroBiasEst3;
	}

	public void setGyroBiasEst3(float gyroBiasEst3) {
		this.gyroBiasEst3 = gyroBiasEst3;
	}

	public AltitudeAlg getAltitudeAlg() {
		return altitudeAlg;
	}

	public void setAltitudeAlg(AltitudeAlg altitudeAlg) {
		this.altitudeAlg = altitudeAlg;
	}

	public long getGoodAttTimer() {
		return goodAttTimer;
	}

	public void setGoodAttTimer(long goodAttTimer) {
		this.goodAttTimer = goodAttTimer;
	}

	public long getBadAttTimer() {
		return badAttTimer;
	}

	public void setBadAttTimer(long badAttTimer) {
		this.badAttTimer = badAttTimer;
	}

	public long getBadRateTimer() {
		return badRateTimer;
	}

	public void setBadRateTimer(long badRateTimer) {
		this.badRateTimer = badRateTimer;
	}

	public long getReinitCount() {
		return reinitCount;
	}

	public void setReinitCount(long reinitCount) {
		this.reinitCount = reinitCount;
	}

	public boolean isAttitudeValid() {
		return attitudeValid;
	}

	public void setAttitudeValid(boolean attitudeValid) {
		this.attitudeValid = attitudeValid;
	}

	public boolean isMeasAttValid() {
		return measAttValid;
	}

	public void setMeasAttValid(boolean measAttValid) {
		this.measAttValid = measAttValid;
	}

	public boolean isMeasRateValid() {
		return measRateValid;
	}

	public void setMeasRateValid(boolean measRateValid) {
		this.measRateValid = measRateValid;
	}

	public int getTrackerUsed() {
		return trackerUsed;
	}

	public void setTrackerUsed(int trackerUsed) {
		this.trackerUsed = trackerUsed;
	}

	public int getTrackerPreference() {
		return trackerPreference;
	}

	public void setTrackerPreference(int trackerPreference) {
		this.trackerPreference = trackerPreference;
	}

}
