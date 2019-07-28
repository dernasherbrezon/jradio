package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Rs485Status {

	private boolean usartMWMInitializedCorrectly;
	private boolean usartMWMMarkedAsActive;
	private boolean usartMWMRTSStatus;
	private boolean usartMWMCTSStatus;
	private boolean usartMWMPreviousTransmissionCompleted;
	private boolean usartMWMIdleLineDetected;

	public Rs485Status(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		usartMWMInitializedCorrectly = ((raw >> 7) & 0x1) > 0;
		usartMWMMarkedAsActive = ((raw >> 6) & 0x1) > 0;
		usartMWMRTSStatus = ((raw >> 5) & 0x1) > 0;
		usartMWMCTSStatus = ((raw >> 4) & 0x1) > 0;
		usartMWMPreviousTransmissionCompleted = ((raw >> 3) & 0x1) > 0;
		usartMWMIdleLineDetected = ((raw >> 2) & 0x1) > 0;
		dis.skipBytes(3);
	}

	public boolean isUsartMWMInitializedCorrectly() {
		return usartMWMInitializedCorrectly;
	}

	public void setUsartMWMInitializedCorrectly(boolean usartMWMInitializedCorrectly) {
		this.usartMWMInitializedCorrectly = usartMWMInitializedCorrectly;
	}

	public boolean isUsartMWMMarkedAsActive() {
		return usartMWMMarkedAsActive;
	}

	public void setUsartMWMMarkedAsActive(boolean usartMWMMarkedAsActive) {
		this.usartMWMMarkedAsActive = usartMWMMarkedAsActive;
	}

	public boolean isUsartMWMRTSStatus() {
		return usartMWMRTSStatus;
	}

	public void setUsartMWMRTSStatus(boolean usartMWMRTSStatus) {
		this.usartMWMRTSStatus = usartMWMRTSStatus;
	}

	public boolean isUsartMWMCTSStatus() {
		return usartMWMCTSStatus;
	}

	public void setUsartMWMCTSStatus(boolean usartMWMCTSStatus) {
		this.usartMWMCTSStatus = usartMWMCTSStatus;
	}

	public boolean isUsartMWMPreviousTransmissionCompleted() {
		return usartMWMPreviousTransmissionCompleted;
	}

	public void setUsartMWMPreviousTransmissionCompleted(boolean usartMWMPreviousTransmissionCompleted) {
		this.usartMWMPreviousTransmissionCompleted = usartMWMPreviousTransmissionCompleted;
	}

	public boolean isUsartMWMIdleLineDetected() {
		return usartMWMIdleLineDetected;
	}

	public void setUsartMWMIdleLineDetected(boolean usartMWMIdleLineDetected) {
		this.usartMWMIdleLineDetected = usartMWMIdleLineDetected;
	}

}
