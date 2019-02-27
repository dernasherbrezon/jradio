package ru.r2cloud.jradio.rhw;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class CanStatistics {

	private long rxFrameCount;
	private long txFrameCount;
	private long errorCount;
	
	public CanStatistics() {
		//do nothing
	}

	public CanStatistics(LittleEndianDataInputStream dis) throws IOException {
		rxFrameCount = dis.readUnsignedInt();
		txFrameCount = dis.readUnsignedInt();
		errorCount = dis.readUnsignedInt();
	}

	public long getRxFrameCount() {
		return rxFrameCount;
	}

	public void setRxFrameCount(long rxFrameCount) {
		this.rxFrameCount = rxFrameCount;
	}

	public long getTxFrameCount() {
		return txFrameCount;
	}

	public void setTxFrameCount(long txFrameCount) {
		this.txFrameCount = txFrameCount;
	}

	public long getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(long errorCount) {
		this.errorCount = errorCount;
	}

}
