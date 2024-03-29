package ru.r2cloud.jradio.trace;

import java.util.List;

public class HdlcFrameStats {

	private int beforeFlagsCount;
	private int afterFlagsCount;
	private boolean assistedHeaderWorked;
	private byte[] frame;
	private byte[] unpackedBits;
	private List<HdlcPresync> presyncStats;

	public List<HdlcPresync> getPresyncStats() {
		return presyncStats;
	}

	public void setPresyncStats(List<HdlcPresync> presyncStats) {
		this.presyncStats = presyncStats;
	}

	public boolean isAssistedHeaderWorked() {
		return assistedHeaderWorked;
	}

	public void setAssistedHeaderWorked(boolean assistedHeaderWorked) {
		this.assistedHeaderWorked = assistedHeaderWorked;
	}

	public byte[] getUnpackedBits() {
		return unpackedBits;
	}

	public void setUnpackedBits(byte[] unpackedBits) {
		this.unpackedBits = unpackedBits;
	}

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
