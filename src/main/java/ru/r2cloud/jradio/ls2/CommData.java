package ru.r2cloud.jradio.ls2;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;
import ru.r2cloud.jradio.util.StreamUtils;

public class CommData {

	private int rxCount;
	private int txCount;
	private long rxBytes;
	private long txBytes;

	public CommData() {
		// do nothing
	}

	public CommData(LittleEndianDataInputStream dis) throws IOException {
		rxCount = dis.getBigEndianDataInputStream().readUnsignedShort();
		txCount = dis.getBigEndianDataInputStream().readUnsignedShort();
		rxBytes = StreamUtils.readUnsignedInt(dis.getBigEndianDataInputStream());
		txBytes = StreamUtils.readUnsignedInt(dis.getBigEndianDataInputStream());
	}

	public int getRxCount() {
		return rxCount;
	}

	public void setRxCount(int rxCount) {
		this.rxCount = rxCount;
	}

	public int getTxCount() {
		return txCount;
	}

	public void setTxCount(int txCount) {
		this.txCount = txCount;
	}

	public long getRxBytes() {
		return rxBytes;
	}

	public void setRxBytes(long rxBytes) {
		this.rxBytes = rxBytes;
	}

	public long getTxBytes() {
		return txBytes;
	}

	public void setTxBytes(long txBytes) {
		this.txBytes = txBytes;
	}

}
