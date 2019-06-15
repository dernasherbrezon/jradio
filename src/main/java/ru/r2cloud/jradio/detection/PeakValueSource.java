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
		if ((currentInterval == null || currentInterval.getSampleEnd() < currentSample) && currentIndex < peaks.size()) {
			currentInterval = peaks.get(currentIndex);
			Float newFreq = freqCorrection.getPeakFrequencyOffset(currentInterval);
			// do not change currentFrequency if offset not found
			if (newFreq != null) {
				// "-" for shift opposite direction
				currentFrequency = -newFreq;
			}
			currentIndex++;
		}
		currentSample++;
		return currentFrequency;
	}
}
