package ru.r2cloud.jradio.blocks;

public class CorrelatedMarker {

	private long accessCode;
	private Long sourceSample;
	private Long endSample;
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

	public Long getEndSample() {
		return endSample;
	}
	
	public void setEndSample(Long endSample) {
		this.endSample = endSample;
	}
	
	public long getCorrelatedBitIndex() {
		return correlatedBitIndex;
	}

	public void setCorrelatedBitIndex(long correlatedBitIndex) {
		this.correlatedBitIndex = correlatedBitIndex;
	}

}
