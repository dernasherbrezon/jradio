package ru.r2cloud.jradio.ccsds;

public class CcsdsFraming {

	private ScramblerType scrambler;
	private Coding coding;
	private int frameLength;
	private int syncwordThreshold;

	public CcsdsFraming() {
		// do nothing
	}

	public CcsdsFraming(CcsdsFraming other) {
		this.scrambler = other.scrambler;
		this.coding = other.coding;
		this.frameLength = other.frameLength;
		this.syncwordThreshold = other.syncwordThreshold;
	}
	
	public int getSyncwordThreshold() {
		return syncwordThreshold;
	}
	
	public void setSyncwordThreshold(int syncwordThreshold) {
		this.syncwordThreshold = syncwordThreshold;
	}

	public ScramblerType getScrambler() {
		return scrambler;
	}

	public void setScrambler(ScramblerType scrambler) {
		this.scrambler = scrambler;
	}

	public Coding getCoding() {
		return coding;
	}

	public void setCoding(Coding coding) {
		this.coding = coding;
	}

	public int getFrameLength() {
		return frameLength;
	}

	public void setFrameLength(int frameLength) {
		this.frameLength = frameLength;
	}

}
