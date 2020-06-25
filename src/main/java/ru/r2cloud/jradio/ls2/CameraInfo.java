package ru.r2cloud.jradio.ls2;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class CameraInfo {

	private boolean poweredOn;
	private boolean heaterOn;
	private boolean enabled;
	private boolean heaterEnabled;
	private boolean busy;
	private boolean overtempOn;
	private boolean overtempErr;
	private boolean heaterOnOn;

	private float temperature;
	private int lastContact;
	private int picsRemaining;
	private int retryFails;

	public CameraInfo() {
		// do nothing
	}

	public CameraInfo(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		poweredOn = (raw & 0x1) > 0;
		heaterOn = ((raw >> 1) & 0x1) > 0;
		enabled = ((raw >> 2) & 0x1) > 0;
		heaterEnabled = ((raw >> 3) & 0x1) > 0;
		busy = ((raw >> 4) & 0x1) > 0;
		overtempOn = ((raw >> 5) & 0x1) > 0;
		overtempErr = ((raw >> 6) & 0x1) > 0;
		heaterOnOn = ((raw >> 7) & 0x1) > 0;

		temperature = dis.readUnsignedByte() * 0.5f - 75;
		lastContact = dis.readUnsignedByte();
		raw = dis.readUnsignedByte();
		picsRemaining = (raw >> 2);
		retryFails = raw & 0b11;
	}

	public boolean isPoweredOn() {
		return poweredOn;
	}

	public void setPoweredOn(boolean poweredOn) {
		this.poweredOn = poweredOn;
	}

	public boolean isHeaterOn() {
		return heaterOn;
	}

	public void setHeaterOn(boolean heaterOn) {
		this.heaterOn = heaterOn;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isHeaterEnabled() {
		return heaterEnabled;
	}

	public void setHeaterEnabled(boolean heaterEnabled) {
		this.heaterEnabled = heaterEnabled;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public boolean isOvertempOn() {
		return overtempOn;
	}

	public void setOvertempOn(boolean overtempOn) {
		this.overtempOn = overtempOn;
	}

	public boolean isOvertempErr() {
		return overtempErr;
	}

	public void setOvertempErr(boolean overtempErr) {
		this.overtempErr = overtempErr;
	}

	public boolean isHeaterOnOn() {
		return heaterOnOn;
	}

	public void setHeaterOnOn(boolean heaterOnOn) {
		this.heaterOnOn = heaterOnOn;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public int getLastContact() {
		return lastContact;
	}

	public void setLastContact(int lastContact) {
		this.lastContact = lastContact;
	}

	public int getPicsRemaining() {
		return picsRemaining;
	}

	public void setPicsRemaining(int picsRemaining) {
		this.picsRemaining = picsRemaining;
	}

	public int getRetryFails() {
		return retryFails;
	}

	public void setRetryFails(int retryFails) {
		this.retryFails = retryFails;
	}

}
