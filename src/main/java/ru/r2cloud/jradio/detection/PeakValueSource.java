package ru.r2cloud.jradio.detection;

import java.util.List;

import ru.r2cloud.jradio.FloatValueSource;

public class PeakValueSource implements FloatValueSource {

	private final List<PeakInterval> peaks;
	private final FrequencyCorrection freqCorrection;
	private long currentSample = 0;
	private PeakInterval currentInterval = null;
	private int currentIndex = 0;
	private float currentFrequency = 0.0f;

	public PeakValueSource(List<PeakInterval> peaks, FrequencyCorrection freqCorrection) {
		if (peaks.isEmpty()) {
			throw new IllegalArgumentException("peaks are empty");
		}
		this.peaks = peaks;
		this.freqCorrection = freqCorrection;
	}

	@Override
	public float getValue() {
		if (currentInterval == null || currentInterval.getSampleEnd() < currentSample) {
			if (currentIndex < peaks.size()) {
				currentInterval = peaks.get(currentIndex);
				currentFrequency = freqCorrection.getPeakFrequencyOffset(currentInterval);
				currentIndex++;
			}
		}
		currentSample++;
		return currentFrequency;
	}
}
