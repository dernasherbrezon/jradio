package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswTlmProc {

	private int tlmTableUsed;
	private long filePointer;
	private int bufferIndex;
	private boolean streaming;

	public FswTlmProc() {
		// do nothing
	}

	public FswTlmProc(DataInputStream dis) throws IOException {
		tlmTableUsed = dis.readUnsignedByte();
		filePointer = StreamUtils.readUnsignedInt(dis);
		bufferIndex = dis.readUnsignedByte();
		streaming = dis.readBoolean();
	}

	public int getTlmTableUsed() {
		return tlmTableUsed;
	}

	public void setTlmTableUsed(int tlmTableUsed) {
		this.tlmTableUsed = tlmTableUsed;
	}

	public long getFilePointer() {
		return filePointer;
	}

	public void setFilePointer(long filePointer) {
		this.filePointer = filePointer;
	}

	public int getBufferIndex() {
		return bufferIndex;
	}

	public void setBufferIndex(int bufferIndex) {
		this.bufferIndex = bufferIndex;
	}

	public boolean isStreaming() {
		return streaming;
	}

	public void setStreaming(boolean streaming) {
		this.streaming = streaming;
	}

}
