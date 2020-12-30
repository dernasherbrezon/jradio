package ru.r2cloud.jradio;

import ru.r2cloud.jradio.blocks.CorrelatedMarker;

/**
 * Stores stream metadata. Shared object across different blocks
 *
 */
public class Context {

	private CorrelatedMarker currentMarker = null;
	private Long totalSamples;
	private float sampleRate;
	private int channels = 1;
	private int sampleSizeInBits;
	private LongValueSource currentSample;
	private Boolean softBits;

	public Context() {
		// do nothing
	}

	public Context(Context orig) {
		currentMarker = orig.currentMarker;
		totalSamples = orig.totalSamples;
		sampleRate = orig.sampleRate;
		channels = orig.channels;
		sampleSizeInBits = orig.sampleSizeInBits;
		currentSample = orig.currentSample;
		softBits = orig.softBits;
	}

	public void setCurrentMarker(CorrelatedMarker currentMarker) {
		this.currentMarker = currentMarker;
	}

	public CorrelatedMarker getCurrentMarker() {
		return currentMarker;
	}

	public Long getTotalSamples() {
		return totalSamples;
	}

	public void setTotalSamples(Long totalSamples) {
		this.totalSamples = totalSamples;
	}

	public int getSampleSizeInBits() {
		return sampleSizeInBits;
	}

	public void setSampleSizeInBits(int sampleSizeInBits) {
		this.sampleSizeInBits = sampleSizeInBits;
	}

	public float getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(float sampleRate) {
		this.sampleRate = sampleRate;
	}

	public int getChannels() {
		return channels;
	}

	public void setChannels(int channels) {
		this.channels = channels;
	}

	public LongValueSource getCurrentSample() {
		return currentSample;
	}

	public void setCurrentSample(LongValueSource currentSample) {
		this.currentSample = currentSample;
	}

	public void setSoftBits(boolean softBits) {
		this.softBits = softBits;
	}

	public Boolean getSoftBits() {
		return softBits;
	}

}
