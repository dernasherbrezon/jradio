package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswClockSync {

	private long countsPerSec;
	private long gpsPpsTimeUsec;
	private long hrRunCount;
	private long hrTimeUsec;
	private long hrCycleNum;
	private long vhrCycleNum;
	private int hrExecTimeMs1;
	private int hrExecTimeMs2;
	private int hrExecTimeMs3;
	private int hrExecTimeMs4;
	private int hrExecTimeMs5;
	private boolean clockSyncEnable;

	public FswClockSync() {
		// do nothing
	}

	public FswClockSync(DataInputStream dis) throws IOException {
		countsPerSec = StreamUtils.readUnsignedInt(dis);
		gpsPpsTimeUsec = StreamUtils.readUnsignedInt(dis);
		hrRunCount = StreamUtils.readUnsignedInt(dis);
		hrTimeUsec = StreamUtils.readUnsignedInt(dis);
		hrCycleNum = StreamUtils.readUnsignedInt(dis);
		vhrCycleNum = StreamUtils.readUnsignedInt(dis);
		hrExecTimeMs1 = dis.readUnsignedByte();
		hrExecTimeMs2 = dis.readUnsignedByte();
		hrExecTimeMs3 = dis.readUnsignedByte();
		hrExecTimeMs4 = dis.readUnsignedByte();
		hrExecTimeMs5 = dis.readUnsignedByte();
		clockSyncEnable = dis.readBoolean();
	}

	public long getCountsPerSec() {
		return countsPerSec;
	}

	public void setCountsPerSec(long countsPerSec) {
		this.countsPerSec = countsPerSec;
	}

	public long getGpsPpsTimeUsec() {
		return gpsPpsTimeUsec;
	}

	public void setGpsPpsTimeUsec(long gpsPpsTimeUsec) {
		this.gpsPpsTimeUsec = gpsPpsTimeUsec;
	}

	public long getHrRunCount() {
		return hrRunCount;
	}

	public void setHrRunCount(long hrRunCount) {
		this.hrRunCount = hrRunCount;
	}

	public long getHrTimeUsec() {
		return hrTimeUsec;
	}

	public void setHrTimeUsec(long hrTimeUsec) {
		this.hrTimeUsec = hrTimeUsec;
	}

	public long getHrCycleNum() {
		return hrCycleNum;
	}

	public void setHrCycleNum(long hrCycleNum) {
		this.hrCycleNum = hrCycleNum;
	}

	public long getVhrCycleNum() {
		return vhrCycleNum;
	}

	public void setVhrCycleNum(long vhrCycleNum) {
		this.vhrCycleNum = vhrCycleNum;
	}

	public int getHrExecTimeMs1() {
		return hrExecTimeMs1;
	}

	public void setHrExecTimeMs1(int hrExecTimeMs1) {
		this.hrExecTimeMs1 = hrExecTimeMs1;
	}

	public int getHrExecTimeMs2() {
		return hrExecTimeMs2;
	}

	public void setHrExecTimeMs2(int hrExecTimeMs2) {
		this.hrExecTimeMs2 = hrExecTimeMs2;
	}

	public int getHrExecTimeMs3() {
		return hrExecTimeMs3;
	}

	public void setHrExecTimeMs3(int hrExecTimeMs3) {
		this.hrExecTimeMs3 = hrExecTimeMs3;
	}

	public int getHrExecTimeMs4() {
		return hrExecTimeMs4;
	}

	public void setHrExecTimeMs4(int hrExecTimeMs4) {
		this.hrExecTimeMs4 = hrExecTimeMs4;
	}

	public int getHrExecTimeMs5() {
		return hrExecTimeMs5;
	}

	public void setHrExecTimeMs5(int hrExecTimeMs5) {
		this.hrExecTimeMs5 = hrExecTimeMs5;
	}

	public boolean isClockSyncEnable() {
		return clockSyncEnable;
	}

	public void setClockSyncEnable(boolean clockSyncEnable) {
		this.clockSyncEnable = clockSyncEnable;
	}

}
