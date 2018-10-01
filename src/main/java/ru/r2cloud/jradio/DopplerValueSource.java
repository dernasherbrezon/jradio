package ru.r2cloud.jradio;

public abstract class DopplerValueSource implements FloatValueSource {

	private final long correctPeriodSamples;
	private final long correctPeriodMillis;
	private final long satelliteFrequency;
	private long currentSample = 0;
	private long currentShift = 0;
	private long currentTimeMillis;

	public DopplerValueSource(float sampleRate, long satelliteFrequency, long correctPeriodMillis, long startTimeMillis) {
		this.correctPeriodMillis = correctPeriodMillis;
		if (correctPeriodMillis > 1000) {
			this.correctPeriodSamples = (long) (sampleRate * (correctPeriodMillis / 1000));
		} else {
			this.correctPeriodSamples = (long) (sampleRate / correctPeriodMillis);
		}
		this.currentTimeMillis = startTimeMillis;
		this.satelliteFrequency = satelliteFrequency;
	}

	@Override
	public float getValue() {
		if (currentSample % correctPeriodSamples == 0) {
			currentShift = satelliteFrequency - getDopplerFrequency(satelliteFrequency, currentTimeMillis);
			currentTimeMillis += correctPeriodMillis;
		}
		currentSample++;
		return currentShift;
	}

	public abstract long getDopplerFrequency(long satelliteFrequency, long currentTimeMillis);

}
