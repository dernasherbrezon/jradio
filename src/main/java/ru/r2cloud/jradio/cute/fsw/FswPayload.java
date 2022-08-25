package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswPayload {

	private long secSinceLastTlm;
	private int tlmRxCount;
	private int tlmTxCount;
	private int tlmNakCount;
	private int tlmTimeoutCount;

	public FswPayload() {
		// do nothing
	}

	public FswPayload(DataInputStream dis) throws IOException {
		secSinceLastTlm = StreamUtils.readUnsignedInt(dis);
		tlmRxCount = dis.readUnsignedShort();
		tlmTxCount = dis.readUnsignedShort();
		tlmNakCount = dis.readUnsignedShort();
		tlmTimeoutCount = dis.readUnsignedShort();
	}

	public long getSecSinceLastTlm() {
		return secSinceLastTlm;
	}

	public void setSecSinceLastTlm(long secSinceLastTlm) {
		this.secSinceLastTlm = secSinceLastTlm;
	}

	public int getTlmRxCount() {
		return tlmRxCount;
	}

	public void setTlmRxCount(int tlmRxCount) {
		this.tlmRxCount = tlmRxCount;
	}

	public int getTlmTxCount() {
		return tlmTxCount;
	}

	public void setTlmTxCount(int tlmTxCount) {
		this.tlmTxCount = tlmTxCount;
	}

	public int getTlmNakCount() {
		return tlmNakCount;
	}

	public void setTlmNakCount(int tlmNakCount) {
		this.tlmNakCount = tlmNakCount;
	}

	public int getTlmTimeoutCount() {
		return tlmTimeoutCount;
	}

	public void setTlmTimeoutCount(int tlmTimeoutCount) {
		this.tlmTimeoutCount = tlmTimeoutCount;
	}

}
