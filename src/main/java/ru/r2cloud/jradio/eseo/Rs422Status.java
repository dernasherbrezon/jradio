package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Rs422Status {

	private boolean usartTMTCMainInitializedCorrectly;
	private boolean usartTMTCMainMarkedAsActive;
	private boolean usartTMTCMainRTSStatus;
	private boolean usartTMTCMainCTSStatus;
	private boolean usartTMTCMainPreviousTransmissionCompleted;
	private boolean usartTMTCMainIdleLineDetected;
	private boolean usartTMTCRedundantInitializedCorrectly;
	private boolean usartTMTCRedundantMarkedAsActive;
	private boolean usartTMTCRedundantRTSStatus;
	private boolean usartTMTCRedundantCTSStatus;
	private boolean usartTMTCRedundantPreviousTransmissionCompleted;
	private boolean usartTMTCRedundantIdleLineDetected;

	public Rs422Status() {
		// do nothing
	}

	public Rs422Status(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		usartTMTCMainInitializedCorrectly = ((raw >> 7) & 0x1) > 0;
		usartTMTCMainMarkedAsActive = ((raw >> 6) & 0x1) > 0;
		usartTMTCMainRTSStatus = ((raw >> 5) & 0x1) > 0;
		usartTMTCMainCTSStatus = ((raw >> 4) & 0x1) > 0;
		usartTMTCMainPreviousTransmissionCompleted = ((raw >> 3) & 0x1) > 0;
		usartTMTCMainIdleLineDetected = ((raw >> 2) & 0x1) > 0;

		dis.skipBytes(1);
		raw = dis.readUnsignedByte();
		usartTMTCRedundantInitializedCorrectly = ((raw >> 7) & 0x1) > 0;
		usartTMTCRedundantMarkedAsActive = ((raw >> 6) & 0x1) > 0;
		usartTMTCRedundantRTSStatus = ((raw >> 5) & 0x1) > 0;
		usartTMTCRedundantCTSStatus = ((raw >> 4) & 0x1) > 0;
		usartTMTCRedundantPreviousTransmissionCompleted = ((raw >> 3) & 0x1) > 0;
		usartTMTCRedundantIdleLineDetected = ((raw >> 2) & 0x1) > 0;
		dis.skipBytes(1);
	}

	public boolean isUsartTMTCMainInitializedCorrectly() {
		return usartTMTCMainInitializedCorrectly;
	}

	public void setUsartTMTCMainInitializedCorrectly(boolean usartTMTCMainInitializedCorrectly) {
		this.usartTMTCMainInitializedCorrectly = usartTMTCMainInitializedCorrectly;
	}

	public boolean isUsartTMTCMainMarkedAsActive() {
		return usartTMTCMainMarkedAsActive;
	}

	public void setUsartTMTCMainMarkedAsActive(boolean usartTMTCMainMarkedAsActive) {
		this.usartTMTCMainMarkedAsActive = usartTMTCMainMarkedAsActive;
	}

	public boolean isUsartTMTCMainRTSStatus() {
		return usartTMTCMainRTSStatus;
	}

	public void setUsartTMTCMainRTSStatus(boolean usartTMTCMainRTSStatus) {
		this.usartTMTCMainRTSStatus = usartTMTCMainRTSStatus;
	}

	public boolean isUsartTMTCMainCTSStatus() {
		return usartTMTCMainCTSStatus;
	}

	public void setUsartTMTCMainCTSStatus(boolean usartTMTCMainCTSStatus) {
		this.usartTMTCMainCTSStatus = usartTMTCMainCTSStatus;
	}

	public boolean isUsartTMTCMainPreviousTransmissionCompleted() {
		return usartTMTCMainPreviousTransmissionCompleted;
	}

	public void setUsartTMTCMainPreviousTransmissionCompleted(boolean usartTMTCMainPreviousTransmissionCompleted) {
		this.usartTMTCMainPreviousTransmissionCompleted = usartTMTCMainPreviousTransmissionCompleted;
	}

	public boolean isUsartTMTCMainIdleLineDetected() {
		return usartTMTCMainIdleLineDetected;
	}

	public void setUsartTMTCMainIdleLineDetected(boolean usartTMTCMainIdleLineDetected) {
		this.usartTMTCMainIdleLineDetected = usartTMTCMainIdleLineDetected;
	}

	public boolean isUsartTMTCRedundantInitializedCorrectly() {
		return usartTMTCRedundantInitializedCorrectly;
	}

	public void setUsartTMTCRedundantInitializedCorrectly(boolean usartTMTCRedundantInitializedCorrectly) {
		this.usartTMTCRedundantInitializedCorrectly = usartTMTCRedundantInitializedCorrectly;
	}

	public boolean isUsartTMTCRedundantMarkedAsActive() {
		return usartTMTCRedundantMarkedAsActive;
	}

	public void setUsartTMTCRedundantMarkedAsActive(boolean usartTMTCRedundantMarkedAsActive) {
		this.usartTMTCRedundantMarkedAsActive = usartTMTCRedundantMarkedAsActive;
	}

	public boolean isUsartTMTCRedundantRTSStatus() {
		return usartTMTCRedundantRTSStatus;
	}

	public void setUsartTMTCRedundantRTSStatus(boolean usartTMTCRedundantRTSStatus) {
		this.usartTMTCRedundantRTSStatus = usartTMTCRedundantRTSStatus;
	}

	public boolean isUsartTMTCRedundantCTSStatus() {
		return usartTMTCRedundantCTSStatus;
	}

	public void setUsartTMTCRedundantCTSStatus(boolean usartTMTCRedundantCTSStatus) {
		this.usartTMTCRedundantCTSStatus = usartTMTCRedundantCTSStatus;
	}

	public boolean isUsartTMTCRedundantPreviousTransmissionCompleted() {
		return usartTMTCRedundantPreviousTransmissionCompleted;
	}

	public void setUsartTMTCRedundantPreviousTransmissionCompleted(boolean usartTMTCRedundantPreviousTransmissionCompleted) {
		this.usartTMTCRedundantPreviousTransmissionCompleted = usartTMTCRedundantPreviousTransmissionCompleted;
	}

	public boolean isUsartTMTCRedundantIdleLineDetected() {
		return usartTMTCRedundantIdleLineDetected;
	}

	public void setUsartTMTCRedundantIdleLineDetected(boolean usartTMTCRedundantIdleLineDetected) {
		this.usartTMTCRedundantIdleLineDetected = usartTMTCRedundantIdleLineDetected;
	}

}
