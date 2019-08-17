package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class CanStatus {

	private boolean platformMainControllerCorrectlyInitialized;
	private boolean platformMainControllerInnormalMode;
	private boolean platformMainControllerInloopbackMode;
	private boolean platformMainControllerInsilentMode;
	private boolean platformMainControllerInsilentLoopbackMode;
	private boolean platformMainTransceiverLoopbackActive;
	private boolean platformMainMarkedAsActiveBus;
	private boolean platformRedundantControllerCorrectlyInitialized;
	private boolean platformRedundantControllerInnormalMode;
	private boolean platformRedundantControllerInloopbackMode;
	private boolean platformRedundantControllerInsilentMode;
	private boolean platformRedundantControllerInsilentLoopbackMode;
	private boolean platformRedundantTransceiverLoopbackActive;
	private boolean platformRedundantMarkedAsActiveBus;
	private boolean payloadMainControllerCorrectlyInitialized;
	private boolean payloadMainControllerInnormalMode;
	private boolean payloadMainControllerInloopbackMode;
	private boolean payloadMainControllerInsilentMode;
	private boolean payloadMaintransceiverLoopbackActive;
	private boolean payloadMainmarkedAsActiveBus;
	private boolean payloadRedundantControllerCorrectlyInitialized;
	private boolean payloadRedundantControllerInnormalMode;
	private boolean payloadRedundantControllerInloopbackMode;
	private boolean payloadRedundantControllerInsilentMode;
	private boolean payloadRedundantTransceiverLoopbackActive;
	private boolean payloadRedundantMarkedAsActiveBus;

	public CanStatus() {
		// do nothing
	}

	public CanStatus(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		platformMainControllerCorrectlyInitialized = ((raw >> 7) & 0x1) > 0;
		platformMainControllerInnormalMode = ((raw >> 6) & 0x1) > 0;
		platformMainControllerInloopbackMode = ((raw >> 5) & 0x1) > 0;
		platformMainControllerInsilentMode = ((raw >> 4) & 0x1) > 0;
		platformMainControllerInsilentLoopbackMode = ((raw >> 3) & 0x1) > 0;
		platformMainTransceiverLoopbackActive = ((raw >> 2) & 0x1) > 0;
		platformMainMarkedAsActiveBus = ((raw >> 1) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		platformRedundantControllerCorrectlyInitialized = ((raw >> 7) & 0x1) > 0;
		platformRedundantControllerInnormalMode = ((raw >> 6) & 0x1) > 0;
		platformRedundantControllerInloopbackMode = ((raw >> 5) & 0x1) > 0;
		platformRedundantControllerInsilentMode = ((raw >> 4) & 0x1) > 0;
		platformRedundantControllerInsilentLoopbackMode = ((raw >> 3) & 0x1) > 0;
		platformRedundantTransceiverLoopbackActive = ((raw >> 2) & 0x1) > 0;
		platformRedundantMarkedAsActiveBus = ((raw >> 1) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		payloadMainControllerCorrectlyInitialized = ((raw >> 7) & 0x1) > 0;
		payloadMainControllerInnormalMode = ((raw >> 6) & 0x1) > 0;
		payloadMainControllerInloopbackMode = ((raw >> 5) & 0x1) > 0;
		payloadMainControllerInsilentMode = ((raw >> 4) & 0x1) > 0;
		payloadMaintransceiverLoopbackActive = ((raw >> 3) & 0x1) > 0;
		payloadMainmarkedAsActiveBus = ((raw >> 2) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		payloadRedundantControllerCorrectlyInitialized = ((raw >> 7) & 0x1) > 0;
		payloadRedundantControllerInnormalMode = ((raw >> 6) & 0x1) > 0;
		payloadRedundantControllerInloopbackMode = ((raw >> 5) & 0x1) > 0;
		payloadRedundantControllerInsilentMode = ((raw >> 4) & 0x1) > 0;
		payloadRedundantTransceiverLoopbackActive = ((raw >> 3) & 0x1) > 0;
		payloadRedundantMarkedAsActiveBus = ((raw >> 2) & 0x1) > 0;
	}

	public boolean isPlatformMainControllerCorrectlyInitialized() {
		return platformMainControllerCorrectlyInitialized;
	}

	public void setPlatformMainControllerCorrectlyInitialized(boolean platformMainControllerCorrectlyInitialized) {
		this.platformMainControllerCorrectlyInitialized = platformMainControllerCorrectlyInitialized;
	}

	public boolean isPlatformMainControllerInnormalMode() {
		return platformMainControllerInnormalMode;
	}

	public void setPlatformMainControllerInnormalMode(boolean platformMainControllerInnormalMode) {
		this.platformMainControllerInnormalMode = platformMainControllerInnormalMode;
	}

	public boolean isPlatformMainControllerInloopbackMode() {
		return platformMainControllerInloopbackMode;
	}

	public void setPlatformMainControllerInloopbackMode(boolean platformMainControllerInloopbackMode) {
		this.platformMainControllerInloopbackMode = platformMainControllerInloopbackMode;
	}

	public boolean isPlatformMainControllerInsilentMode() {
		return platformMainControllerInsilentMode;
	}

	public void setPlatformMainControllerInsilentMode(boolean platformMainControllerInsilentMode) {
		this.platformMainControllerInsilentMode = platformMainControllerInsilentMode;
	}

	public boolean isPlatformMainControllerInsilentLoopbackMode() {
		return platformMainControllerInsilentLoopbackMode;
	}

	public void setPlatformMainControllerInsilentLoopbackMode(boolean platformMainControllerInsilentLoopbackMode) {
		this.platformMainControllerInsilentLoopbackMode = platformMainControllerInsilentLoopbackMode;
	}

	public boolean isPlatformMainTransceiverLoopbackActive() {
		return platformMainTransceiverLoopbackActive;
	}

	public void setPlatformMainTransceiverLoopbackActive(boolean platformMainTransceiverLoopbackActive) {
		this.platformMainTransceiverLoopbackActive = platformMainTransceiverLoopbackActive;
	}

	public boolean isPlatformMainMarkedAsActiveBus() {
		return platformMainMarkedAsActiveBus;
	}

	public void setPlatformMainMarkedAsActiveBus(boolean platformMainMarkedAsActiveBus) {
		this.platformMainMarkedAsActiveBus = platformMainMarkedAsActiveBus;
	}

	public boolean isPlatformRedundantControllerCorrectlyInitialized() {
		return platformRedundantControllerCorrectlyInitialized;
	}

	public void setPlatformRedundantControllerCorrectlyInitialized(boolean platformRedundantControllerCorrectlyInitialized) {
		this.platformRedundantControllerCorrectlyInitialized = platformRedundantControllerCorrectlyInitialized;
	}

	public boolean isPlatformRedundantControllerInnormalMode() {
		return platformRedundantControllerInnormalMode;
	}

	public void setPlatformRedundantControllerInnormalMode(boolean platformRedundantControllerInnormalMode) {
		this.platformRedundantControllerInnormalMode = platformRedundantControllerInnormalMode;
	}

	public boolean isPlatformRedundantControllerInloopbackMode() {
		return platformRedundantControllerInloopbackMode;
	}

	public void setPlatformRedundantControllerInloopbackMode(boolean platformRedundantControllerInloopbackMode) {
		this.platformRedundantControllerInloopbackMode = platformRedundantControllerInloopbackMode;
	}

	public boolean isPlatformRedundantControllerInsilentMode() {
		return platformRedundantControllerInsilentMode;
	}

	public void setPlatformRedundantControllerInsilentMode(boolean platformRedundantControllerInsilentMode) {
		this.platformRedundantControllerInsilentMode = platformRedundantControllerInsilentMode;
	}

	public boolean isPlatformRedundantControllerInsilentLoopbackMode() {
		return platformRedundantControllerInsilentLoopbackMode;
	}

	public void setPlatformRedundantControllerInsilentLoopbackMode(boolean platformRedundantControllerInsilentLoopbackMode) {
		this.platformRedundantControllerInsilentLoopbackMode = platformRedundantControllerInsilentLoopbackMode;
	}

	public boolean isPlatformRedundantTransceiverLoopbackActive() {
		return platformRedundantTransceiverLoopbackActive;
	}

	public void setPlatformRedundantTransceiverLoopbackActive(boolean platformRedundantTransceiverLoopbackActive) {
		this.platformRedundantTransceiverLoopbackActive = platformRedundantTransceiverLoopbackActive;
	}

	public boolean isPlatformRedundantMarkedAsActiveBus() {
		return platformRedundantMarkedAsActiveBus;
	}

	public void setPlatformRedundantMarkedAsActiveBus(boolean platformRedundantMarkedAsActiveBus) {
		this.platformRedundantMarkedAsActiveBus = platformRedundantMarkedAsActiveBus;
	}

	public boolean isPayloadMainControllerCorrectlyInitialized() {
		return payloadMainControllerCorrectlyInitialized;
	}

	public void setPayloadMainControllerCorrectlyInitialized(boolean payloadMainControllerCorrectlyInitialized) {
		this.payloadMainControllerCorrectlyInitialized = payloadMainControllerCorrectlyInitialized;
	}

	public boolean isPayloadMainControllerInnormalMode() {
		return payloadMainControllerInnormalMode;
	}

	public void setPayloadMainControllerInnormalMode(boolean payloadMainControllerInnormalMode) {
		this.payloadMainControllerInnormalMode = payloadMainControllerInnormalMode;
	}

	public boolean isPayloadMainControllerInloopbackMode() {
		return payloadMainControllerInloopbackMode;
	}

	public void setPayloadMainControllerInloopbackMode(boolean payloadMainControllerInloopbackMode) {
		this.payloadMainControllerInloopbackMode = payloadMainControllerInloopbackMode;
	}

	public boolean isPayloadMainControllerInsilentMode() {
		return payloadMainControllerInsilentMode;
	}

	public void setPayloadMainControllerInsilentMode(boolean payloadMainControllerInsilentMode) {
		this.payloadMainControllerInsilentMode = payloadMainControllerInsilentMode;
	}

	public boolean isPayloadMaintransceiverLoopbackActive() {
		return payloadMaintransceiverLoopbackActive;
	}

	public void setPayloadMaintransceiverLoopbackActive(boolean payloadMaintransceiverLoopbackActive) {
		this.payloadMaintransceiverLoopbackActive = payloadMaintransceiverLoopbackActive;
	}

	public boolean isPayloadMainmarkedAsActiveBus() {
		return payloadMainmarkedAsActiveBus;
	}

	public void setPayloadMainmarkedAsActiveBus(boolean payloadMainmarkedAsActiveBus) {
		this.payloadMainmarkedAsActiveBus = payloadMainmarkedAsActiveBus;
	}

	public boolean isPayloadRedundantControllerCorrectlyInitialized() {
		return payloadRedundantControllerCorrectlyInitialized;
	}

	public void setPayloadRedundantControllerCorrectlyInitialized(boolean payloadRedundantControllerCorrectlyInitialized) {
		this.payloadRedundantControllerCorrectlyInitialized = payloadRedundantControllerCorrectlyInitialized;
	}

	public boolean isPayloadRedundantControllerInnormalMode() {
		return payloadRedundantControllerInnormalMode;
	}

	public void setPayloadRedundantControllerInnormalMode(boolean payloadRedundantControllerInnormalMode) {
		this.payloadRedundantControllerInnormalMode = payloadRedundantControllerInnormalMode;
	}

	public boolean isPayloadRedundantControllerInloopbackMode() {
		return payloadRedundantControllerInloopbackMode;
	}

	public void setPayloadRedundantControllerInloopbackMode(boolean payloadRedundantControllerInloopbackMode) {
		this.payloadRedundantControllerInloopbackMode = payloadRedundantControllerInloopbackMode;
	}

	public boolean isPayloadRedundantControllerInsilentMode() {
		return payloadRedundantControllerInsilentMode;
	}

	public void setPayloadRedundantControllerInsilentMode(boolean payloadRedundantControllerInsilentMode) {
		this.payloadRedundantControllerInsilentMode = payloadRedundantControllerInsilentMode;
	}

	public boolean isPayloadRedundantTransceiverLoopbackActive() {
		return payloadRedundantTransceiverLoopbackActive;
	}

	public void setPayloadRedundantTransceiverLoopbackActive(boolean payloadRedundantTransceiverLoopbackActive) {
		this.payloadRedundantTransceiverLoopbackActive = payloadRedundantTransceiverLoopbackActive;
	}

	public boolean isPayloadRedundantMarkedAsActiveBus() {
		return payloadRedundantMarkedAsActiveBus;
	}

	public void setPayloadRedundantMarkedAsActiveBus(boolean payloadRedundantMarkedAsActiveBus) {
		this.payloadRedundantMarkedAsActiveBus = payloadRedundantMarkedAsActiveBus;
	}

}
