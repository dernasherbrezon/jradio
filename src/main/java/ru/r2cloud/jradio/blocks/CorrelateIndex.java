package ru.r2cloud.jradio.blocks;

class CorrelateIndex {

	private long accessCode;
	private Long sourceSample;
	private long correlatedBitIndex;

	public long getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(long accessCode) {
		this.accessCode = accessCode;
	}

	public Long getSourceSample() {
		return sourceSample;
	}

	public void setSourceSample(Long sourceSample) {
		this.sourceSample = sourceSample;
	}

	public long getCorrelatedBitIndex() {
		return correlatedBitIndex;
	}

	public void setCorrelatedBitIndex(long correlatedBitIndex) {
		this.correlatedBitIndex = correlatedBitIndex;
	}

}
