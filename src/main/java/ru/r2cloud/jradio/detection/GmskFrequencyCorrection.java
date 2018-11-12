package ru.r2cloud.jradio.detection;

public class GmskFrequencyCorrection implements FrequencyCorrection {

	private final int tolerance;
	private final int symbolRate;

	public GmskFrequencyCorrection(int symbolRate, int tolerance) {
		this.tolerance = tolerance;
		this.symbolRate = symbolRate;
	}

	@Override
	public Float getPeakFrequencyOffset(PeakInterval interval) {
		double value = -120.0;
		Float result = null;
		for (Peak first : interval.getPeaks()) {
			for (Peak second : interval.getPeaks()) {
				if (Math.abs(Math.abs(first.getFrequency() - second.getFrequency()) - symbolRate) < tolerance) {
					double curValue = Math.max(first.getValue(), second.getValue());
					// just in case we have several matches, take with bigger decibel
					if (curValue > value) {
						result = ((float) second.getFrequency() + first.getFrequency()) / 2;
						value = curValue;
					}
				}
			}
		}
		return result;
	}

}
