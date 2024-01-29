package ru.r2cloud.jradio;

public class RxMetadata {

	private Float rssi;
	private Float snr;
	private Long frequencyError;
	private Integer baud;
	
	public Integer getBaud() {
		return baud;
	}
	
	public void setBaud(Integer baud) {
		this.baud = baud;
	}

	public Float getRssi() {
		return rssi;
	}

	public void setRssi(Float rssi) {
		this.rssi = rssi;
	}

	public Float getSnr() {
		return snr;
	}

	public void setSnr(Float snr) {
		this.snr = snr;
	}

	public Long getFrequencyError() {
		return frequencyError;
	}

	public void setFrequencyError(Long frequencyError) {
		this.frequencyError = frequencyError;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[");
		if (rssi != null) {
			result.append(" rssi=").append(rssi);
		}
		if (snr != null) {
			result.append(" snr=").append(snr);
		}
		if (frequencyError != null) {
			result.append(" frequencyError=").append(frequencyError);
		}
		if (baud != null) {
			result.append("baud=").append(baud);
		}
		result.append(" ]");
		return result.toString();
	}

}
