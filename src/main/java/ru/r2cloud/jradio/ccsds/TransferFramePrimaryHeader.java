package ru.r2cloud.jradio.ccsds;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

// as defined at CCSDS 132.0-B-3 Section 4.1.2
public class TransferFramePrimaryHeader {

	private int version;
	private int spacecraftId;
	private int virtualChannelId;
	private boolean ocf;
	private int masterChannelFrameCount;
	private int virtualChannelFrameCount;
	private TransferFrameDataFieldStatus fieldStatus;

	public TransferFramePrimaryHeader() {
		// do nothing
	}

	public TransferFramePrimaryHeader(BitInputStream bis) throws IOException {
		version = bis.readUnsignedInt(2);
		spacecraftId = bis.readUnsignedInt(10);
		virtualChannelId = bis.readUnsignedInt(3);
		ocf = bis.readBoolean();
		masterChannelFrameCount = bis.readUnsignedByte();
		virtualChannelFrameCount = bis.readUnsignedByte();
		fieldStatus = new TransferFrameDataFieldStatus(bis);
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getSpacecraftId() {
		return spacecraftId;
	}

	public void setSpacecraftId(int spacecraftId) {
		this.spacecraftId = spacecraftId;
	}

	public int getVirtualChannelId() {
		return virtualChannelId;
	}

	public void setVirtualChannelId(int virtualChannelId) {
		this.virtualChannelId = virtualChannelId;
	}

	public boolean isOcf() {
		return ocf;
	}

	public void setOcf(boolean ocf) {
		this.ocf = ocf;
	}

	public int getMasterChannelFrameCount() {
		return masterChannelFrameCount;
	}

	public void setMasterChannelFrameCount(int masterChannelFrameCount) {
		this.masterChannelFrameCount = masterChannelFrameCount;
	}

	public int getVirtualChannelFrameCount() {
		return virtualChannelFrameCount;
	}

	public void setVirtualChannelFrameCount(int virtualChannelFrameCount) {
		this.virtualChannelFrameCount = virtualChannelFrameCount;
	}

	public TransferFrameDataFieldStatus getFieldStatus() {
		return fieldStatus;
	}

	public void setFieldStatus(TransferFrameDataFieldStatus fieldStatus) {
		this.fieldStatus = fieldStatus;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(spacecraftId).append(" -> ").append(virtualChannelId).append(':').append(virtualChannelFrameCount);
		return result.toString();
	}

}
