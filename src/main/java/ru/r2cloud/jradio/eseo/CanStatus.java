package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

public class CanStatus {

	private boolean PlatformMainControllerCorrectlyInitialized;
	private boolean PlatformMainControllerInnormalMode;
	private boolean PlatformMainControllerInloopbackMode;
	private boolean PlatformMainControllerInsilentMode;
	private boolean PlatformMainControllerInsilentLoopbackMode;
	private boolean PlatformMainTransceiverLoopbackActive;
	private boolean PlatformMainMarkedAsActiveBus;
	private boolean PlatformRedundantControllerCorrectlyInitialized;
	private boolean PlatformRedundantControllerInnormalMode;
	private boolean PlatformRedundantControllerInloopbackMode;
	private boolean PlatformRedundantControllerInsilentMode;
	private boolean PlatformRedundantControllerInsilentLoopbackMode;
	private boolean PlatformRedundantTransceiverLoopbackActive;
	private boolean PlatformRedundantMarkedAsActiveBus;
	private boolean PayloadMainControllerCorrectlyInitialized;
	private boolean PayloadMainControllerInnormalMode;
	private boolean PayloadMainControllerInloopbackMode;
	private boolean PayloadMainControllerInsilentMode;
	private boolean PayloadMaintransceiverLoopbackActive;
	private boolean PayloadMainmarkedAsActiveBus;
	private boolean PayloadRedundantControllerCorrectlyInitialized;
	private boolean PayloadRedundantControllerInnormalMode;
	private boolean PayloadRedundantControllerInloopbackMode;
	private boolean PayloadRedundantControllerInsilentMode;
	private boolean PayloadRedundantTransceiverLoopbackActive;
	private boolean PayloadRedundantMarkedAsActiveBus;

	public CanStatus(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		PlatformMainControllerCorrectlyInitialized = ((raw >> 7) & 0x1) > 0;
		PlatformMainControllerInnormalMode = ((raw >> 6) & 0x1) > 0;
		PlatformMainControllerInloopbackMode = ((raw >> 5) & 0x1) > 0;
		PlatformMainControllerInsilentMode = ((raw >> 4) & 0x1) > 0;
		PlatformMainControllerInsilentLoopbackMode = ((raw >> 3) & 0x1) > 0;
		PlatformMainTransceiverLoopbackActive = ((raw >> 2) & 0x1) > 0;
		PlatformMainMarkedAsActiveBus = ((raw >> 1) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		PlatformRedundantControllerCorrectlyInitialized = ((raw >> 7) & 0x1) > 0;
		PlatformRedundantControllerInnormalMode = ((raw >> 6) & 0x1) > 0;
		PlatformRedundantControllerInloopbackMode = ((raw >> 5) & 0x1) > 0;
		PlatformRedundantControllerInsilentMode = ((raw >> 4) & 0x1) > 0;
		PlatformRedundantControllerInsilentLoopbackMode = ((raw >> 3) & 0x1) > 0;
		PlatformRedundantTransceiverLoopbackActive = ((raw >> 2) & 0x1) > 0;
		PlatformRedundantMarkedAsActiveBus = ((raw >> 1) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		PayloadMainControllerCorrectlyInitialized = ((raw >> 7) & 0x1) > 0;
		PayloadMainControllerInnormalMode = ((raw >> 6) & 0x1) > 0;
		PayloadMainControllerInloopbackMode = ((raw >> 5) & 0x1) > 0;
		PayloadMainControllerInsilentMode = ((raw >> 4) & 0x1) > 0;
		PayloadMaintransceiverLoopbackActive = ((raw >> 3) & 0x1) > 0;
		PayloadMainmarkedAsActiveBus = ((raw >> 2) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		PayloadRedundantControllerCorrectlyInitialized = ((raw >> 7) & 0x1) > 0;
		PayloadRedundantControllerInnormalMode = ((raw >> 6) & 0x1) > 0;
		PayloadRedundantControllerInloopbackMode = ((raw >> 5) & 0x1) > 0;
		PayloadRedundantControllerInsilentMode = ((raw >> 4) & 0x1) > 0;
		PayloadRedundantTransceiverLoopbackActive = ((raw >> 3) & 0x1) > 0;
		PayloadRedundantMarkedAsActiveBus = ((raw >> 2) & 0x1) > 0;
	}

	public boolean isPlatformMainControllerCorrectlyInitialized() {
		return PlatformMainControllerCorrectlyInitialized;
	}

	public void setPlatformMainControllerCorrectlyInitialized(boolean platformMainControllerCorrectlyInitialized) {
		PlatformMainControllerCorrectlyInitialized = platformMainControllerCorrectlyInitialized;
	}

	public boolean isPlatformMainControllerInnormalMode() {
		return PlatformMainControllerInnormalMode;
	}

	public void setPlatformMainControllerInnormalMode(boolean platformMainControllerInnormalMode) {
		PlatformMainControllerInnormalMode = platformMainControllerInnormalMode;
	}

	public boolean isPlatformMainControllerInloopbackMode() {
		return PlatformMainControllerInloopbackMode;
	}

	public void setPlatformMainControllerInloopbackMode(boolean platformMainControllerInloopbackMode) {
		PlatformMainControllerInloopbackMode = platformMainControllerInloopbackMode;
	}

	public boolean isPlatformMainControllerInsilentMode() {
		return PlatformMainControllerInsilentMode;
	}

	public void setPlatformMainControllerInsilentMode(boolean platformMainControllerInsilentMode) {
		PlatformMainControllerInsilentMode = platformMainControllerInsilentMode;
	}

	public boolean isPlatformMainControllerInsilentLoopbackMode() {
		return PlatformMainControllerInsilentLoopbackMode;
	}

	public void setPlatformMainControllerInsilentLoopbackMode(boolean platformMainControllerInsilentLoopbackMode) {
		PlatformMainControllerInsilentLoopbackMode = platformMainControllerInsilentLoopbackMode;
	}

	public boolean isPlatformMainTransceiverLoopbackActive() {
		return PlatformMainTransceiverLoopbackActive;
	}

	public void setPlatformMainTransceiverLoopbackActive(boolean platformMainTransceiverLoopbackActive) {
		PlatformMainTransceiverLoopbackActive = platformMainTransceiverLoopbackActive;
	}

	public boolean isPlatformMainMarkedAsActiveBus() {
		return PlatformMainMarkedAsActiveBus;
	}

	public void setPlatformMainMarkedAsActiveBus(boolean platformMainMarkedAsActiveBus) {
		PlatformMainMarkedAsActiveBus = platformMainMarkedAsActiveBus;
	}

	public boolean isPlatformRedundantControllerCorrectlyInitialized() {
		return PlatformRedundantControllerCorrectlyInitialized;
	}

	public void setPlatformRedundantControllerCorrectlyInitialized(boolean platformRedundantControllerCorrectlyInitialized) {
		PlatformRedundantControllerCorrectlyInitialized = platformRedundantControllerCorrectlyInitialized;
	}

	public boolean isPlatformRedundantControllerInnormalMode() {
		return PlatformRedundantControllerInnormalMode;
	}

	public void setPlatformRedundantControllerInnormalMode(boolean platformRedundantControllerInnormalMode) {
		PlatformRedundantControllerInnormalMode = platformRedundantControllerInnormalMode;
	}

	public boolean isPlatformRedundantControllerInloopbackMode() {
		return PlatformRedundantControllerInloopbackMode;
	}

	public void setPlatformRedundantControllerInloopbackMode(boolean platformRedundantControllerInloopbackMode) {
		PlatformRedundantControllerInloopbackMode = platformRedundantControllerInloopbackMode;
	}

	public boolean isPlatformRedundantControllerInsilentMode() {
		return PlatformRedundantControllerInsilentMode;
	}

	public void setPlatformRedundantControllerInsilentMode(boolean platformRedundantControllerInsilentMode) {
		PlatformRedundantControllerInsilentMode = platformRedundantControllerInsilentMode;
	}

	public boolean isPlatformRedundantControllerInsilentLoopbackMode() {
		return PlatformRedundantControllerInsilentLoopbackMode;
	}

	public void setPlatformRedundantControllerInsilentLoopbackMode(boolean platformRedundantControllerInsilentLoopbackMode) {
		PlatformRedundantControllerInsilentLoopbackMode = platformRedundantControllerInsilentLoopbackMode;
	}

	public boolean isPlatformRedundantTransceiverLoopbackActive() {
		return PlatformRedundantTransceiverLoopbackActive;
	}

	public void setPlatformRedundantTransceiverLoopbackActive(boolean platformRedundantTransceiverLoopbackActive) {
		PlatformRedundantTransceiverLoopbackActive = platformRedundantTransceiverLoopbackActive;
	}

	public boolean isPlatformRedundantMarkedAsActiveBus() {
		return PlatformRedundantMarkedAsActiveBus;
	}

	public void setPlatformRedundantMarkedAsActiveBus(boolean platformRedundantMarkedAsActiveBus) {
		PlatformRedundantMarkedAsActiveBus = platformRedundantMarkedAsActiveBus;
	}

	public boolean isPayloadMainControllerCorrectlyInitialized() {
		return PayloadMainControllerCorrectlyInitialized;
	}

	public void setPayloadMainControllerCorrectlyInitialized(boolean payloadMainControllerCorrectlyInitialized) {
		PayloadMainControllerCorrectlyInitialized = payloadMainControllerCorrectlyInitialized;
	}

	public boolean isPayloadMainControllerInnormalMode() {
		return PayloadMainControllerInnormalMode;
	}

	public void setPayloadMainControllerInnormalMode(boolean payloadMainControllerInnormalMode) {
		PayloadMainControllerInnormalMode = payloadMainControllerInnormalMode;
	}

	public boolean isPayloadMainControllerInloopbackMode() {
		return PayloadMainControllerInloopbackMode;
	}

	public void setPayloadMainControllerInloopbackMode(boolean payloadMainControllerInloopbackMode) {
		PayloadMainControllerInloopbackMode = payloadMainControllerInloopbackMode;
	}

	public boolean isPayloadMainControllerInsilentMode() {
		return PayloadMainControllerInsilentMode;
	}

	public void setPayloadMainControllerInsilentMode(boolean payloadMainControllerInsilentMode) {
		PayloadMainControllerInsilentMode = payloadMainControllerInsilentMode;
	}

	public boolean isPayloadMaintransceiverLoopbackActive() {
		return PayloadMaintransceiverLoopbackActive;
	}

	public void setPayloadMaintransceiverLoopbackActive(boolean payloadMaintransceiverLoopbackActive) {
		PayloadMaintransceiverLoopbackActive = payloadMaintransceiverLoopbackActive;
	}

	public boolean isPayloadMainmarkedAsActiveBus() {
		return PayloadMainmarkedAsActiveBus;
	}

	public void setPayloadMainmarkedAsActiveBus(boolean payloadMainmarkedAsActiveBus) {
		PayloadMainmarkedAsActiveBus = payloadMainmarkedAsActiveBus;
	}

	public boolean isPayloadRedundantControllerCorrectlyInitialized() {
		return PayloadRedundantControllerCorrectlyInitialized;
	}

	public void setPayloadRedundantControllerCorrectlyInitialized(boolean payloadRedundantControllerCorrectlyInitialized) {
		PayloadRedundantControllerCorrectlyInitialized = payloadRedundantControllerCorrectlyInitialized;
	}

	public boolean isPayloadRedundantControllerInnormalMode() {
		return PayloadRedundantControllerInnormalMode;
	}

	public void setPayloadRedundantControllerInnormalMode(boolean payloadRedundantControllerInnormalMode) {
		PayloadRedundantControllerInnormalMode = payloadRedundantControllerInnormalMode;
	}

	public boolean isPayloadRedundantControllerInloopbackMode() {
		return PayloadRedundantControllerInloopbackMode;
	}

	public void setPayloadRedundantControllerInloopbackMode(boolean payloadRedundantControllerInloopbackMode) {
		PayloadRedundantControllerInloopbackMode = payloadRedundantControllerInloopbackMode;
	}

	public boolean isPayloadRedundantControllerInsilentMode() {
		return PayloadRedundantControllerInsilentMode;
	}

	public void setPayloadRedundantControllerInsilentMode(boolean payloadRedundantControllerInsilentMode) {
		PayloadRedundantControllerInsilentMode = payloadRedundantControllerInsilentMode;
	}

	public boolean isPayloadRedundantTransceiverLoopbackActive() {
		return PayloadRedundantTransceiverLoopbackActive;
	}

	public void setPayloadRedundantTransceiverLoopbackActive(boolean payloadRedundantTransceiverLoopbackActive) {
		PayloadRedundantTransceiverLoopbackActive = payloadRedundantTransceiverLoopbackActive;
	}

	public boolean isPayloadRedundantMarkedAsActiveBus() {
		return PayloadRedundantMarkedAsActiveBus;
	}

	public void setPayloadRedundantMarkedAsActiveBus(boolean payloadRedundantMarkedAsActiveBus) {
		PayloadRedundantMarkedAsActiveBus = payloadRedundantMarkedAsActiveBus;
	}

}
