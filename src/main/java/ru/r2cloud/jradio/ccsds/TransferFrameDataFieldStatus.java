package ru.r2cloud.jradio.ccsds;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class TransferFrameDataFieldStatus {

	private boolean secondaryHeader;
	private boolean sync;
	private boolean packetOrder;
	private int segmentLengthId;
	// ‘11111111111’ - long packet span across several frames
	// ‘11111111110’ - only idle data
	private int firstHeaderPointer;

	public TransferFrameDataFieldStatus() {
		// do nothing
	}

	public TransferFrameDataFieldStatus(BitInputStream bis) throws IOException {
		secondaryHeader = bis.readBoolean();
		sync = bis.readBoolean();
		packetOrder = bis.readBoolean();
		segmentLengthId = bis.readUnsignedInt(2);
		firstHeaderPointer = bis.readUnsignedInt(11);
	}

	public boolean isSecondaryHeader() {
		return secondaryHeader;
	}

	public void setSecondaryHeader(boolean secondaryHeader) {
		this.secondaryHeader = secondaryHeader;
	}

	public boolean isSync() {
		return sync;
	}

	public void setSync(boolean sync) {
		this.sync = sync;
	}

	public boolean isPacketOrder() {
		return packetOrder;
	}

	public void setPacketOrder(boolean packetOrder) {
		this.packetOrder = packetOrder;
	}

	public int getSegmentLengthId() {
		return segmentLengthId;
	}

	public void setSegmentLengthId(int segmentLengthId) {
		this.segmentLengthId = segmentLengthId;
	}

	public int getFirstHeaderPointer() {
		return firstHeaderPointer;
	}

	public void setFirstHeaderPointer(int firstHeaderPointer) {
		this.firstHeaderPointer = firstHeaderPointer;
	}

}
