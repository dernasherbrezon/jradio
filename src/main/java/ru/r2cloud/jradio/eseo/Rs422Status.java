package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Rs422Status {

	private boolean USARTTMTCMainInitializedCorrectly;
	private boolean USARTTMTCMainMarkedAsActive;
	private boolean USARTTMTCMainRTSStatus;
	private boolean USARTTMTCMainCTSStatus;
	private boolean USARTTMTCMainPreviousTransmissionCompleted;
	private boolean USARTTMTCMainIdleLineDetected;
	private boolean USARTTMTCRedundantInitializedCorrectly;
	private boolean USARTTMTCRedundantMarkedAsActive;
	private boolean USARTTMTCRedundantRTSStatus;
	private boolean USARTTMTCRedundantCTSStatus;
	private boolean USARTTMTCRedundantPreviousTransmissionCompleted;
	private boolean USARTTMTCRedundantIdleLineDetected;

	public Rs422Status(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		USARTTMTCMainInitializedCorrectly = ((raw >> 7) & 0x1) > 0;
		USARTTMTCMainMarkedAsActive = ((raw >> 6) & 0x1) > 0;
		USARTTMTCMainRTSStatus = ((raw >> 5) & 0x1) > 0;
		USARTTMTCMainCTSStatus = ((raw >> 4) & 0x1) > 0;
		USARTTMTCMainPreviousTransmissionCompleted = ((raw >> 3) & 0x1) > 0;
		USARTTMTCMainIdleLineDetected = ((raw >> 2) & 0x1) > 0;

		dis.skipBytes(1);
		raw = dis.readUnsignedByte();
		USARTTMTCRedundantInitializedCorrectly = ((raw >> 7) & 0x1) > 0;
		USARTTMTCRedundantMarkedAsActive = ((raw >> 6) & 0x1) > 0;
		USARTTMTCRedundantRTSStatus = ((raw >> 5) & 0x1) > 0;
		USARTTMTCRedundantCTSStatus = ((raw >> 4) & 0x1) > 0;
		USARTTMTCRedundantPreviousTransmissionCompleted = ((raw >> 3) & 0x1) > 0;
		USARTTMTCRedundantIdleLineDetected = ((raw >> 2) & 0x1) > 0;
		dis.skipBytes(1);
	}

	public boolean isUSARTTMTCMainInitializedCorrectly() {
		return USARTTMTCMainInitializedCorrectly;
	}

	public void setUSARTTMTCMainInitializedCorrectly(boolean uSARTTMTCMainInitializedCorrectly) {
		USARTTMTCMainInitializedCorrectly = uSARTTMTCMainInitializedCorrectly;
	}

	public boolean isUSARTTMTCMainMarkedAsActive() {
		return USARTTMTCMainMarkedAsActive;
	}

	public void setUSARTTMTCMainMarkedAsActive(boolean uSARTTMTCMainMarkedAsActive) {
		USARTTMTCMainMarkedAsActive = uSARTTMTCMainMarkedAsActive;
	}

	public boolean isUSARTTMTCMainRTSStatus() {
		return USARTTMTCMainRTSStatus;
	}

	public void setUSARTTMTCMainRTSStatus(boolean uSARTTMTCMainRTSStatus) {
		USARTTMTCMainRTSStatus = uSARTTMTCMainRTSStatus;
	}

	public boolean isUSARTTMTCMainCTSStatus() {
		return USARTTMTCMainCTSStatus;
	}

	public void setUSARTTMTCMainCTSStatus(boolean uSARTTMTCMainCTSStatus) {
		USARTTMTCMainCTSStatus = uSARTTMTCMainCTSStatus;
	}

	public boolean isUSARTTMTCMainPreviousTransmissionCompleted() {
		return USARTTMTCMainPreviousTransmissionCompleted;
	}

	public void setUSARTTMTCMainPreviousTransmissionCompleted(boolean uSARTTMTCMainPreviousTransmissionCompleted) {
		USARTTMTCMainPreviousTransmissionCompleted = uSARTTMTCMainPreviousTransmissionCompleted;
	}

	public boolean isUSARTTMTCMainIdleLineDetected() {
		return USARTTMTCMainIdleLineDetected;
	}

	public void setUSARTTMTCMainIdleLineDetected(boolean uSARTTMTCMainIdleLineDetected) {
		USARTTMTCMainIdleLineDetected = uSARTTMTCMainIdleLineDetected;
	}

	public boolean isUSARTTMTCRedundantInitializedCorrectly() {
		return USARTTMTCRedundantInitializedCorrectly;
	}

	public void setUSARTTMTCRedundantInitializedCorrectly(boolean uSARTTMTCRedundantInitializedCorrectly) {
		USARTTMTCRedundantInitializedCorrectly = uSARTTMTCRedundantInitializedCorrectly;
	}

	public boolean isUSARTTMTCRedundantMarkedAsActive() {
		return USARTTMTCRedundantMarkedAsActive;
	}

	public void setUSARTTMTCRedundantMarkedAsActive(boolean uSARTTMTCRedundantMarkedAsActive) {
		USARTTMTCRedundantMarkedAsActive = uSARTTMTCRedundantMarkedAsActive;
	}

	public boolean isUSARTTMTCRedundantRTSStatus() {
		return USARTTMTCRedundantRTSStatus;
	}

	public void setUSARTTMTCRedundantRTSStatus(boolean uSARTTMTCRedundantRTSStatus) {
		USARTTMTCRedundantRTSStatus = uSARTTMTCRedundantRTSStatus;
	}

	public boolean isUSARTTMTCRedundantCTSStatus() {
		return USARTTMTCRedundantCTSStatus;
	}

	public void setUSARTTMTCRedundantCTSStatus(boolean uSARTTMTCRedundantCTSStatus) {
		USARTTMTCRedundantCTSStatus = uSARTTMTCRedundantCTSStatus;
	}

	public boolean isUSARTTMTCRedundantPreviousTransmissionCompleted() {
		return USARTTMTCRedundantPreviousTransmissionCompleted;
	}

	public void setUSARTTMTCRedundantPreviousTransmissionCompleted(boolean uSARTTMTCRedundantPreviousTransmissionCompleted) {
		USARTTMTCRedundantPreviousTransmissionCompleted = uSARTTMTCRedundantPreviousTransmissionCompleted;
	}

	public boolean isUSARTTMTCRedundantIdleLineDetected() {
		return USARTTMTCRedundantIdleLineDetected;
	}

	public void setUSARTTMTCRedundantIdleLineDetected(boolean uSARTTMTCRedundantIdleLineDetected) {
		USARTTMTCRedundantIdleLineDetected = uSARTTMTCRedundantIdleLineDetected;
	}

}
