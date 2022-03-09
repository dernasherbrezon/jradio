package ru.r2cloud.jradio.trace;

public class HdlcFrameStats {

	private int beforeFlagsCount;
	private int afterFlagsCount;

	private byte[] frame;

	public int getBeforeFlagsCount() {
		return beforeFlagsCount;
	}

	public void setBeforeFlagsCount(int beforeFlagsCount) {
		this.beforeFlagsCount = beforeFlagsCount;
	}

	public int getAfterFlagsCount() {
		return afterFlagsCount;
	}

	public void setAfterFlagsCount(int afterFlagsCount) {
		this.afterFlagsCount = afterFlagsCount;
	}

	public byte[] getFrame() {
		return frame;
	}

	public void setFrame(byte[] frame) {
		this.frame = frame;
	}

}
