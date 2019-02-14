package ru.r2cloud.jradio.lume1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ccsds.PrimaryHeader;
import ru.r2cloud.jradio.csp.Header;
import ru.r2cloud.jradio.ecss.SecondaryHeader;
import ru.r2cloud.jradio.util.BitInputStream;

public class Lume1Beacon extends Beacon {

	private static final Logger LOG = LoggerFactory.getLogger(Lume1Beacon.class);

	private Header header;

	private int versionNumber; // Transfer Frame Version Number (TFVN)
	private int spacecraftId; // Spacecraft Identifier (SCID)
	private int virtualChannelId; // Virtual Channel Identifier (VCID)
	private int virtualChannelFrameCounter;
	private int firstHeaderPointer;
	private boolean emptyFrame;
	private boolean ocf;
	private int sequenceFlags;
	private boolean fixedLengthFrame;

	private PrimaryHeader primaryHeader;
	private SecondaryHeader secondaryHeader;

	private int id;
	private B1Obc b1obc;

	private int packetErrors;
	private int frameErrors;
	private int frameErrorControl;

	@Override
	public void readBeacon(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
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

		primaryHeader = new PrimaryHeader(bis);
		if (primaryHeader.isSecondaryHeader()) {
			secondaryHeader = new SecondaryHeader(bis);
		}

		id = bis.readUnsignedInt(16);
		switch (id) {
		case 1:
			b1obc = new B1Obc(bis);
			break;

		default:
			LOG.info("unknown id: " + id);
			int userDataSize = primaryHeader.getPacketDataLength() + 1;
			if (primaryHeader.isSecondaryHeader()) {
				userDataSize -= SecondaryHeader.LENGTH_BYTES;
			}
			userDataSize -= 2; // size of id
			bis.skipBits(userDataSize * 8);
			break;
		}

		packetErrors = bis.readUnsignedInt(16);
		frameErrors = bis.readUnsignedInt(16);
		frameErrorControl = bis.readUnsignedInt(16);
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
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

	public PrimaryHeader getPrimaryHeader() {
		return primaryHeader;
	}

	public void setPrimaryHeader(PrimaryHeader primaryHeader) {
		this.primaryHeader = primaryHeader;
	}

	public void setSecondaryHeader(SecondaryHeader secondaryHeader) {
		this.secondaryHeader = secondaryHeader;
	}

	public SecondaryHeader getSecondaryHeader() {
		return secondaryHeader;
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

	public B1Obc getB1obc() {
		return b1obc;
	}
	
	public void setB1obc(B1Obc b1obc) {
		this.b1obc = b1obc;
	}
}
