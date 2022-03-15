package ru.r2cloud.jradio.trace;

public class DemodulatorTrace {

	private int windowSize;

	public DemodulatorTrace(int windowSize) {
		this.windowSize = windowSize;
	}

	public int getWindowSize() {
		return windowSize;
	}

	public void setWindowSize(int windowSize) {
		this.windowSize = windowSize;
	}

}
