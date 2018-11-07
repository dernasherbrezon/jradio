package ru.r2cloud.jradio.detection;

import java.util.List;

public class PeakInterval {

	private long sampleEnd;
	private List<Peak> peaks;

	public long getSampleEnd() {
		return sampleEnd;
	}
	
	public void setSampleEnd(long sampleEnd) {
		this.sampleEnd = sampleEnd;
	}

	public List<Peak> getPeaks() {
		return peaks;
	}

	public void setPeaks(List<Peak> peaks) {
		this.peaks = peaks;
	}

}
