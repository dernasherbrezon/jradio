package ru.r2cloud.jradio.smogp;

public class AtlSpiStatus {

	private boolean spiMuxFunctional;
	private boolean msenCsPinSelectFailed;
	private boolean rtccCsPinSelectFailed;
	private boolean flashCsPinSelectFailed;
	private boolean allCsPinDeselectFailed;
	private boolean misoPinTestFailed;
	private boolean mosiPinTestFailed;
	private boolean sclkPinTestFailed;

	public AtlSpiStatus() {
		// do nothing
	}

	public AtlSpiStatus(int b) {
		spiMuxFunctional = ((b >> 7) & 0x1) > 0;
		msenCsPinSelectFailed = ((b >> 6) & 0x1) > 0;
		rtccCsPinSelectFailed = ((b >> 5) & 0x1) > 0;
		flashCsPinSelectFailed = ((b >> 4) & 0x1) > 0;
		allCsPinDeselectFailed = ((b >> 3) & 0x1) > 0;
		misoPinTestFailed = ((b >> 2) & 0x1) > 0;
		mosiPinTestFailed = ((b >> 1) & 0x1) > 0;
		sclkPinTestFailed = (b & 0x1) > 0;
	}

	public boolean isSpiMuxFunctional() {
		return spiMuxFunctional;
	}

	public void setSpiMuxFunctional(boolean spiMuxFunctional) {
		this.spiMuxFunctional = spiMuxFunctional;
	}

	public boolean isMsenCsPinSelectFailed() {
		return msenCsPinSelectFailed;
	}

	public void setMsenCsPinSelectFailed(boolean msenCsPinSelectFailed) {
		this.msenCsPinSelectFailed = msenCsPinSelectFailed;
	}

	public boolean isRtccCsPinSelectFailed() {
		return rtccCsPinSelectFailed;
	}

	public void setRtccCsPinSelectFailed(boolean rtccCsPinSelectFailed) {
		this.rtccCsPinSelectFailed = rtccCsPinSelectFailed;
	}

	public boolean isFlashCsPinSelectFailed() {
		return flashCsPinSelectFailed;
	}

	public void setFlashCsPinSelectFailed(boolean flashCsPinSelectFailed) {
		this.flashCsPinSelectFailed = flashCsPinSelectFailed;
	}

	public boolean isAllCsPinDeselectFailed() {
		return allCsPinDeselectFailed;
	}

	public void setAllCsPinDeselectFailed(boolean allCsPinDeselectFailed) {
		this.allCsPinDeselectFailed = allCsPinDeselectFailed;
	}

	public boolean isMisoPinTestFailed() {
		return misoPinTestFailed;
	}

	public void setMisoPinTestFailed(boolean misoPinTestFailed) {
		this.misoPinTestFailed = misoPinTestFailed;
	}

	public boolean isMosiPinTestFailed() {
		return mosiPinTestFailed;
	}

	public void setMosiPinTestFailed(boolean mosiPinTestFailed) {
		this.mosiPinTestFailed = mosiPinTestFailed;
	}

	public boolean isSclkPinTestFailed() {
		return sclkPinTestFailed;
	}

	public void setSclkPinTestFailed(boolean sclkPinTestFailed) {
		this.sclkPinTestFailed = sclkPinTestFailed;
	}

}
