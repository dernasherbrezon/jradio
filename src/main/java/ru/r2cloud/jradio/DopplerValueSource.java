package ru.r2cloud.jradio;

public abstract class DopplerValueSource implements FloatValueSource {

	private final float correctPeriodSamples;
	private final long correctPeriodMillis;
	private final long satelliteFrequency;
	private long currentSample = 0;
	private long nextShift = 0;
	private float currentShift = 0;
	private long currentTimeMillis;
	private float shiftPerSample;

	public DopplerValueSource(float sampleRate, long satelliteFrequency, long correctPeriodMillis, long startTimeMillis) {
		this.correctPeriodMillis = correctPeriodMillis;
		this.correctPeriodSamples = sampleRate * ((float)correctPeriodMillis / 1000);
		this.currentTimeMillis = startTimeMillis;
		this.satelliteFrequency = satelliteFrequency;
		this.nextShift = satelliteFrequency - getDopplerFrequency(satelliteFrequency, currentTimeMillis);
	}

	@Override
	public float getValue() {
		if (currentSample % correctPeriodSamples == 0) {
			currentShift = nextShift;
			currentTimeMillis += correctPeriodMillis;
			nextShift = satelliteFrequency - getDopplerFrequency(satelliteFrequency, currentTimeMillis);
			//linear shift between 2 frequency samples
			shiftPerSample = (nextShift - currentShift) / correctPeriodSamples;
			currentSample++;
			return currentShift;
		}
		currentSample++;
		currentShift += shiftPerSample;
		return currentShift;
	}

	public abstract long getDopplerFrequency(long satelliteFrequency, long currentTimeMillis);

}
