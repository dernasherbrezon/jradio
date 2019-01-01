package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Rs485Status {

	private boolean USARTMWMInitializedCorrectly;
	private boolean USARTMWMMarkedAsActive;
	private boolean USARTMWMRTSStatus;
	private boolean USARTMWMCTSStatus;
	private boolean USARTMWMPreviousTransmissionCompleted;
	private boolean USARTMWMIdleLineDetected;

	public Rs485Status(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		USARTMWMInitializedCorrectly = ((raw >> 7) & 0x1) > 0;
		USARTMWMMarkedAsActive = ((raw >> 6) & 0x1) > 0;
		USARTMWMRTSStatus = ((raw >> 5) & 0x1) > 0;
		USARTMWMCTSStatus = ((raw >> 4) & 0x1) > 0;
		USARTMWMPreviousTransmissionCompleted = ((raw >> 3) & 0x1) > 0;
		USARTMWMIdleLineDetected = ((raw >> 2) & 0x1) > 0;
		dis.skipBytes(3);
	}

	public boolean isUSARTMWMInitializedCorrectly() {
		return USARTMWMInitializedCorrectly;
	}

	public void setUSARTMWMInitializedCorrectly(boolean uSARTMWMInitializedCorrectly) {
		USARTMWMInitializedCorrectly = uSARTMWMInitializedCorrectly;
	}

	public boolean isUSARTMWMMarkedAsActive() {
		return USARTMWMMarkedAsActive;
	}

	public void setUSARTMWMMarkedAsActive(boolean uSARTMWMMarkedAsActive) {
		USARTMWMMarkedAsActive = uSARTMWMMarkedAsActive;
	}

	public boolean isUSARTMWMRTSStatus() {
		return USARTMWMRTSStatus;
	}

	public void setUSARTMWMRTSStatus(boolean uSARTMWMRTSStatus) {
		USARTMWMRTSStatus = uSARTMWMRTSStatus;
	}

	public boolean isUSARTMWMCTSStatus() {
		return USARTMWMCTSStatus;
	}

	public void setUSARTMWMCTSStatus(boolean uSARTMWMCTSStatus) {
		USARTMWMCTSStatus = uSARTMWMCTSStatus;
	}

	public boolean isUSARTMWMPreviousTransmissionCompleted() {
		return USARTMWMPreviousTransmissionCompleted;
	}

	public void setUSARTMWMPreviousTransmissionCompleted(boolean uSARTMWMPreviousTransmissionCompleted) {
		USARTMWMPreviousTransmissionCompleted = uSARTMWMPreviousTransmissionCompleted;
	}

	public boolean isUSARTMWMIdleLineDetected() {
		return USARTMWMIdleLineDetected;
	}

	public void setUSARTMWMIdleLineDetected(boolean uSARTMWMIdleLineDetected) {
		USARTMWMIdleLineDetected = uSARTMWMIdleLineDetected;
	}

}
