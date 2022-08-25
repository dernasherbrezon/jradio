package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.cute.SunPointState;

public class FswAttCtrl {

	private float positionError1;
	private float positionError2;
	private float positionError3;
	private float rateError1;
	private float rateError2;
	private float rateError3;
	private float integralError1;
	private float integralError2;
	private float integralError3;
	private float maxRate1;
	private float maxRate2;
	private float maxRate3;
	private float maxAccel1;
	private float maxAccel2;
	private float maxAccel3;
	private float feedbackCtrlTorq1;
	private float feedbackCtrlTorq2;
	private float feedbackCtrlTorq3;
	private float ctrlTorque1;
	private float ctrlTorque2;
	private float ctrlTorque3;
	private int timeIntoSearch;
	private int waitTimer;
	private float sunPointAngleError;
	private SunPointState sunPointState;
	private int gainIndex;
	private boolean momentumTooHigh;

	public FswAttCtrl() {
		// do nothing
	}

	public FswAttCtrl(DataInputStream dis) throws IOException {
		positionError1 = dis.readInt() * 2e-09f;
		positionError2 = dis.readInt() * 2e-09f;
		positionError3 = dis.readInt() * 2e-09f;
		rateError1 = dis.readInt() / 20943951.0f;
		rateError2 = dis.readInt() / 20943951.0f;
		rateError3 = dis.readInt() / 20943951.0f;
		integralError1 = dis.readShort() / 100000.0f;
		integralError2 = dis.readShort() / 100000.0f;
		integralError3 = dis.readShort() / 100000.0f;
		maxRate1 = dis.readShort() / 523.5988f;
		maxRate2 = dis.readShort() / 523.5988f;
		maxRate3 = dis.readShort() / 523.5988f;
		maxAccel1 = dis.readShort() / 5000.0f;
		maxAccel2 = dis.readShort() / 5000.0f;
		maxAccel3 = dis.readShort() / 5000.0f;
		feedbackCtrlTorq1 = dis.readShort() / 5000000.0f;
		feedbackCtrlTorq2 = dis.readShort() / 5000000.0f;
		feedbackCtrlTorq3 = dis.readShort() / 5000000.0f;
		ctrlTorque1 = dis.readShort() / 5000000.0f;
		ctrlTorque2 = dis.readShort() / 5000000.0f;
		ctrlTorque3 = dis.readShort() / 5000000.0f;
		timeIntoSearch = dis.readUnsignedShort();
		waitTimer = dis.readUnsignedShort();
		sunPointAngleError = dis.readUnsignedShort() * 0.003f;
		sunPointState = SunPointState.valueOfCode(dis.readUnsignedByte());
		gainIndex = dis.readUnsignedByte();
		momentumTooHigh = dis.readBoolean();
	}

	public float getPositionError1() {
		return positionError1;
	}

	public void setPositionError1(float positionError1) {
		this.positionError1 = positionError1;
	}

	public float getPositionError2() {
		return positionError2;
	}

	public void setPositionError2(float positionError2) {
		this.positionError2 = positionError2;
	}

	public float getPositionError3() {
		return positionError3;
	}

	public void setPositionError3(float positionError3) {
		this.positionError3 = positionError3;
	}

	public float getRateError1() {
		return rateError1;
	}

	public void setRateError1(float rateError1) {
		this.rateError1 = rateError1;
	}

	public float getRateError2() {
		return rateError2;
	}

	public void setRateError2(float rateError2) {
		this.rateError2 = rateError2;
	}

	public float getRateError3() {
		return rateError3;
	}

	public void setRateError3(float rateError3) {
		this.rateError3 = rateError3;
	}

	public float getIntegralError1() {
		return integralError1;
	}

	public void setIntegralError1(float integralError1) {
		this.integralError1 = integralError1;
	}

	public float getIntegralError2() {
		return integralError2;
	}

	public void setIntegralError2(float integralError2) {
		this.integralError2 = integralError2;
	}

	public float getIntegralError3() {
		return integralError3;
	}

	public void setIntegralError3(float integralError3) {
		this.integralError3 = integralError3;
	}

	public float getMaxRate1() {
		return maxRate1;
	}

	public void setMaxRate1(float maxRate1) {
		this.maxRate1 = maxRate1;
	}

	public float getMaxRate2() {
		return maxRate2;
	}

	public void setMaxRate2(float maxRate2) {
		this.maxRate2 = maxRate2;
	}

	public float getMaxRate3() {
		return maxRate3;
	}

	public void setMaxRate3(float maxRate3) {
		this.maxRate3 = maxRate3;
	}

	public float getMaxAccel1() {
		return maxAccel1;
	}

	public void setMaxAccel1(float maxAccel1) {
		this.maxAccel1 = maxAccel1;
	}

	public float getMaxAccel2() {
		return maxAccel2;
	}

	public void setMaxAccel2(float maxAccel2) {
		this.maxAccel2 = maxAccel2;
	}

	public float getMaxAccel3() {
		return maxAccel3;
	}

	public void setMaxAccel3(float maxAccel3) {
		this.maxAccel3 = maxAccel3;
	}

	public float getFeedbackCtrlTorq1() {
		return feedbackCtrlTorq1;
	}

	public void setFeedbackCtrlTorq1(float feedbackCtrlTorq1) {
		this.feedbackCtrlTorq1 = feedbackCtrlTorq1;
	}

	public float getFeedbackCtrlTorq2() {
		return feedbackCtrlTorq2;
	}

	public void setFeedbackCtrlTorq2(float feedbackCtrlTorq2) {
		this.feedbackCtrlTorq2 = feedbackCtrlTorq2;
	}

	public float getFeedbackCtrlTorq3() {
		return feedbackCtrlTorq3;
	}

	public void setFeedbackCtrlTorq3(float feedbackCtrlTorq3) {
		this.feedbackCtrlTorq3 = feedbackCtrlTorq3;
	}

	public float getCtrlTorque1() {
		return ctrlTorque1;
	}

	public void setCtrlTorque1(float ctrlTorque1) {
		this.ctrlTorque1 = ctrlTorque1;
	}

	public float getCtrlTorque2() {
		return ctrlTorque2;
	}

	public void setCtrlTorque2(float ctrlTorque2) {
		this.ctrlTorque2 = ctrlTorque2;
	}

	public float getCtrlTorque3() {
		return ctrlTorque3;
	}

	public void setCtrlTorque3(float ctrlTorque3) {
		this.ctrlTorque3 = ctrlTorque3;
	}

	public int getTimeIntoSearch() {
		return timeIntoSearch;
	}

	public void setTimeIntoSearch(int timeIntoSearch) {
		this.timeIntoSearch = timeIntoSearch;
	}

	public int getWaitTimer() {
		return waitTimer;
	}

	public void setWaitTimer(int waitTimer) {
		this.waitTimer = waitTimer;
	}

	public float getSunPointAngleError() {
		return sunPointAngleError;
	}

	public void setSunPointAngleError(float sunPointAngleError) {
		this.sunPointAngleError = sunPointAngleError;
	}

	public SunPointState getSunPointState() {
		return sunPointState;
	}

	public void setSunPointState(SunPointState sunPointState) {
		this.sunPointState = sunPointState;
	}

	public int getGainIndex() {
		return gainIndex;
	}

	public void setGainIndex(int gainIndex) {
		this.gainIndex = gainIndex;
	}

	public boolean isMomentumTooHigh() {
		return momentumTooHigh;
	}

	public void setMomentumTooHigh(boolean momentumTooHigh) {
		this.momentumTooHigh = momentumTooHigh;
	}

}
