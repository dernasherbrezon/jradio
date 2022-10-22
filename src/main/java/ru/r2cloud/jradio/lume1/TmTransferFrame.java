package ru.r2cloud.jradio.lume1;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ccsds.PacketPrimaryHeader;
import ru.r2cloud.jradio.ecss.SecondaryHeader;
import ru.r2cloud.jradio.util.BitInputStream;

public class TmTransferFrame {

	private int versionNumber; // Transfer Frame Version Number (TFVN)
	private int spacecraftId; // Spacecraft Identifier (SCID)
	private int virtualChannelId; // Virtual Channel Identifier (VCID)
	private int virtualChannelFrameCounter;
	private int firstHeaderPointer;
	private boolean emptyFrame;
	private boolean ocf;
	private int sequenceFlags;
	private boolean fixedLengthFrame;

	private PacketPrimaryHeader primaryHeader;
	private SecondaryHeader secondaryHeader;

	private byte[] payload;

	private int pec;

	private int packetErrors;
	private int frameErrors;
	private int frameErrorControl;

	public TmTransferFrame() {
		// do nothing
	}

	public TmTransferFrame(DataInputStream dis) throws IOException {
		BitInputStream bis = new BitInputStream(dis);
		versionNumber = bis.readUnsignedInt(2);
		spacecraftId = bis.readUnsignedInt(10);
		virtualChannelId = bis.readUnsignedInt(4);
		virtualChannelFrameCounter = bis.readUnsignedInt(8);
		firstHeaderPointer = bis.readUnsignedInt(11);
		emptyFrame = bis.readBoolean();
		ocf = bis.readBoolean();
		sequenceFlags = bis.readUnsignedInt(2);
		fixedLengthFrame = bis.readBoolean();

		primaryHeader = new PacketPrimaryHeader(bis);
		if (primaryHeader.isSecondaryHeader()) {
			secondaryHeader = new SecondaryHeader(bis);
		}

		payload = new byte[dis.available() - 8];
		dis.readFully(payload);
		pec = dis.readUnsignedShort();

		packetErrors = dis.readUnsignedShort();
		frameErrors = dis.readUnsignedShort();
		frameErrorControl = dis.readUnsignedShort();
	}

	public int getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
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

	public int getVirtualChannelFrameCounter() {
		return virtualChannelFrameCounter;
	}

	public void setVirtualChannelFrameCounter(int virtualChannelFrameCounter) {
		this.virtualChannelFrameCounter = virtualChannelFrameCounter;
	}

	public int getFirstHeaderPointer() {
		return firstHeaderPointer;
	}

	public void setFirstHeaderPointer(int firstHeaderPointer) {
		this.firstHeaderPointer = firstHeaderPointer;
	}

	public boolean isEmptyFrame() {
		return emptyFrame;
	}

	public void setEmptyFrame(boolean emptyFrame) {
		this.emptyFrame = emptyFrame;
	}

	public boolean isOcf() {
		return ocf;
	}

	public void setOcf(boolean ocf) {
		this.ocf = ocf;
	}

	public int getSequenceFlags() {
		return sequenceFlags;
	}

	public void setSequenceFlags(int sequenceFlags) {
		this.sequenceFlags = sequenceFlags;
	}

	public boolean isFixedLengthFrame() {
		return fixedLengthFrame;
	}

	public void setFixedLengthFrame(boolean fixedLengthFrame) {
		this.fixedLengthFrame = fixedLengthFrame;
	}

	public PacketPrimaryHeader getPrimaryHeader() {
		return primaryHeader;
	}

	public void setPrimaryHeader(PacketPrimaryHeader primaryHeader) {
		this.primaryHeader = primaryHeader;
	}

	public SecondaryHeader getSecondaryHeader() {
		return secondaryHeader;
	}

	public void setSecondaryHeader(SecondaryHeader secondaryHeader) {
		this.secondaryHeader = secondaryHeader;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public int getPec() {
		return pec;
	}

	public void setPec(int pec) {
		this.pec = pec;
	}

	public int getPacketErrors() {
		return packetErrors;
	}

	public void setPacketErrors(int packetErrors) {
		this.packetErrors = packetErrors;
	}

	public int getFrameErrors() {
		return frameErrors;
	}

	public void setFrameErrors(int frameErrors) {
		this.frameErrors = frameErrors;
	}

	public int getFrameErrorControl() {
		return frameErrorControl;
	}

	public void setFrameErrorControl(int frameErrorControl) {
		this.frameErrorControl = frameErrorControl;
	}

}
