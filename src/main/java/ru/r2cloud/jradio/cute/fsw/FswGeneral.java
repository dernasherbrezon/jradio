package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswGeneral {

	private long softwareVersion1;
	private long softwareVersion2;
	private long hardwareVersion;
	private int asyncRunning;
	private boolean scrubStatusPart0;
	private boolean scrubStatusPart1;
	private boolean scrubStatusPart2;
	private boolean scrubStatusPart3;
	private boolean scrubStatusPart4;
	private boolean scrubStatusPart5;
	private boolean scrubStatusOverall;
	private int scrubCount;
	private int flashCmdSuccessCount;
	private int flashCmdFailCount;
	private int flashLastPart;
	private long flashLastOff;
	private long flashLastCheck;
	private int imageBooted;
	private int imageAutoFailover;
	private float[] inertia;
	private long fsLastOff;
	private long fsLastLen;
	private long fsLastCheck;
	private FsOpStatus fsOpStatus;
	private FsMounted fsMounted;
	private FsOp fsLastOp;
	private long ramSingleBitErrorCount;

	public FswGeneral() {
		// do nothing
	}

	public FswGeneral(DataInputStream dis) throws IOException {
		softwareVersion1 = StreamUtils.readUnsignedInt(dis);
		softwareVersion2 = StreamUtils.readUnsignedInt(dis);
		hardwareVersion = StreamUtils.readUnsignedInt(dis);
		asyncRunning = dis.readUnsignedByte();
		scrubStatusPart0 = (dis.readUnsignedByte() == 0);
		scrubStatusPart1 = (dis.readUnsignedByte() == 0);
		scrubStatusPart2 = (dis.readUnsignedByte() == 0);
		scrubStatusPart3 = (dis.readUnsignedByte() == 0);
		scrubStatusPart4 = (dis.readUnsignedByte() == 0);
		scrubStatusPart5 = (dis.readUnsignedByte() == 0);
		scrubStatusOverall = (dis.readUnsignedByte() == 0);
		scrubCount = dis.readUnsignedByte();
		flashCmdSuccessCount = dis.readUnsignedByte();
		flashCmdFailCount = dis.readUnsignedByte();
		flashLastPart = dis.readUnsignedByte();
		flashLastOff = StreamUtils.readUnsignedInt(dis);
		flashLastCheck = StreamUtils.readUnsignedInt(dis);
		imageBooted = dis.readUnsignedByte();
		imageAutoFailover = dis.readUnsignedByte();
		inertia = new float[9];
		for (int i = 0; i < inertia.length; i++) {
			inertia[i] = dis.readInt() / 10000.000300000009f;
		}
		fsLastOff = StreamUtils.readUnsignedInt(dis);
		fsLastLen = StreamUtils.readUnsignedInt(dis);
		fsLastCheck = StreamUtils.readUnsignedInt(dis);
		fsOpStatus = FsOpStatus.valueOfCode(dis.readUnsignedByte());
		fsMounted = FsMounted.valueOfCode(dis.readUnsignedByte());
		fsLastOp = FsOp.valueOfCode(dis.readUnsignedByte());
		ramSingleBitErrorCount = StreamUtils.readUnsignedInt(dis);
	}

	public long getSoftwareVersion1() {
		return softwareVersion1;
	}

	public void setSoftwareVersion1(long softwareVersion1) {
		this.softwareVersion1 = softwareVersion1;
	}

	public long getSoftwareVersion2() {
		return softwareVersion2;
	}

	public void setSoftwareVersion2(long softwareVersion2) {
		this.softwareVersion2 = softwareVersion2;
	}

	public long getHardwareVersion() {
		return hardwareVersion;
	}

	public void setHardwareVersion(long hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}

	public int getAsyncRunning() {
		return asyncRunning;
	}

	public void setAsyncRunning(int asyncRunning) {
		this.asyncRunning = asyncRunning;
	}

	public boolean isScrubStatusPart0() {
		return scrubStatusPart0;
	}

	public void setScrubStatusPart0(boolean scrubStatusPart0) {
		this.scrubStatusPart0 = scrubStatusPart0;
	}

	public boolean isScrubStatusPart1() {
		return scrubStatusPart1;
	}

	public void setScrubStatusPart1(boolean scrubStatusPart1) {
		this.scrubStatusPart1 = scrubStatusPart1;
	}

	public boolean isScrubStatusPart2() {
		return scrubStatusPart2;
	}

	public void setScrubStatusPart2(boolean scrubStatusPart2) {
		this.scrubStatusPart2 = scrubStatusPart2;
	}

	public boolean isScrubStatusPart3() {
		return scrubStatusPart3;
	}

	public void setScrubStatusPart3(boolean scrubStatusPart3) {
		this.scrubStatusPart3 = scrubStatusPart3;
	}

	public boolean isScrubStatusPart4() {
		return scrubStatusPart4;
	}

	public void setScrubStatusPart4(boolean scrubStatusPart4) {
		this.scrubStatusPart4 = scrubStatusPart4;
	}

	public boolean isScrubStatusPart5() {
		return scrubStatusPart5;
	}

	public void setScrubStatusPart5(boolean scrubStatusPart5) {
		this.scrubStatusPart5 = scrubStatusPart5;
	}

	public boolean isScrubStatusOverall() {
		return scrubStatusOverall;
	}

	public void setScrubStatusOverall(boolean scrubStatusOverall) {
		this.scrubStatusOverall = scrubStatusOverall;
	}

	public int getScrubCount() {
		return scrubCount;
	}

	public void setScrubCount(int scrubCount) {
		this.scrubCount = scrubCount;
	}

	public int getFlashCmdSuccessCount() {
		return flashCmdSuccessCount;
	}

	public void setFlashCmdSuccessCount(int flashCmdSuccessCount) {
		this.flashCmdSuccessCount = flashCmdSuccessCount;
	}

	public int getFlashCmdFailCount() {
		return flashCmdFailCount;
	}

	public void setFlashCmdFailCount(int flashCmdFailCount) {
		this.flashCmdFailCount = flashCmdFailCount;
	}

	public int getFlashLastPart() {
		return flashLastPart;
	}

	public void setFlashLastPart(int flashLastPart) {
		this.flashLastPart = flashLastPart;
	}

	public long getFlashLastOff() {
		return flashLastOff;
	}

	public void setFlashLastOff(long flashLastOff) {
		this.flashLastOff = flashLastOff;
	}

	public long getFlashLastCheck() {
		return flashLastCheck;
	}

	public void setFlashLastCheck(long flashLastCheck) {
		this.flashLastCheck = flashLastCheck;
	}

	public int getImageBooted() {
		return imageBooted;
	}

	public void setImageBooted(int imageBooted) {
		this.imageBooted = imageBooted;
	}

	public int getImageAutoFailover() {
		return imageAutoFailover;
	}

	public void setImageAutoFailover(int imageAutoFailover) {
		this.imageAutoFailover = imageAutoFailover;
	}

	public float[] getInertia() {
		return inertia;
	}

	public void setInertia(float[] inertia) {
		this.inertia = inertia;
	}

	public long getFsLastOff() {
		return fsLastOff;
	}

	public void setFsLastOff(long fsLastOff) {
		this.fsLastOff = fsLastOff;
	}

	public long getFsLastLen() {
		return fsLastLen;
	}

	public void setFsLastLen(long fsLastLen) {
		this.fsLastLen = fsLastLen;
	}

	public long getFsLastCheck() {
		return fsLastCheck;
	}

	public void setFsLastCheck(long fsLastCheck) {
		this.fsLastCheck = fsLastCheck;
	}

	public FsOpStatus getFsOpStatus() {
		return fsOpStatus;
	}

	public void setFsOpStatus(FsOpStatus fsOpStatus) {
		this.fsOpStatus = fsOpStatus;
	}

	public FsMounted getFsMounted() {
		return fsMounted;
	}

	public void setFsMounted(FsMounted fsMounted) {
		this.fsMounted = fsMounted;
	}

	public FsOp getFsLastOp() {
		return fsLastOp;
	}

	public void setFsLastOp(FsOp fsLastOp) {
		this.fsLastOp = fsLastOp;
	}

	public long getRamSingleBitErrorCount() {
		return ramSingleBitErrorCount;
	}

	public void setRamSingleBitErrorCount(long ramSingleBitErrorCount) {
		this.ramSingleBitErrorCount = ramSingleBitErrorCount;
	}

}
