package ru.r2cloud.jradio.ccsds;

public class CcsdsFraming {

	private ScramblerType scrambler;
	private Coding coding;
	private int frameLength;

	public CcsdsFraming() {
		// do nothing
	}

	public CcsdsFraming(CcsdsFraming other) {
		this.scrambler = other.scrambler;
		this.coding = other.coding;
		this.frameLength = other.frameLength;
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
