package ru.r2cloud.jradio;

public class RxMetadata {

	private float rssi;
	private float snr;
	private long frequencyError;

	public float getRssi() {
		return rssi;
	}

	public void setRssi(float rssi) {
		this.rssi = rssi;
	}

	public float getSnr() {
		return snr;
	}

	public void setSnr(float snr) {
		this.snr = snr;
	}

	public long getFrequencyError() {
		return frequencyError;
	}

	public void setFrequencyError(long frequencyError) {
		this.frequencyError = frequencyError;
	}

}
