package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class SohGps {

	private long gpsCyclesSinceCrcData;
	private int gpsLockCount;
	private int msgUsedSatellites;
	private boolean gpsPosLock;
	private boolean gpsTimeLock;
	private boolean msgDataValid;
	private boolean gpsNewDataReceived;
	private boolean gpsEnable;
	private boolean gpsValid;

	public SohGps() {
		// do nothing
	}

	public SohGps(DataInputStream dis) throws IOException {
		gpsCyclesSinceCrcData = StreamUtils.readUnsignedInt(dis);
		gpsLockCount = dis.readUnsignedShort();
		msgUsedSatellites = dis.readUnsignedByte();
		int raw = dis.readUnsignedByte();
		gpsPosLock = ((raw >> 7) & 0x1) > 0;
		gpsTimeLock = ((raw >> 6) & 0x1) > 0;
		msgDataValid = ((raw >> 5) & 0x1) > 0;
		gpsNewDataReceived = ((raw >> 4) & 0x1) > 0;
		gpsEnable = ((raw >> 2) & 0x1) > 0;
		gpsValid = ((raw >> 1) & 0x1) > 0;
	}

	public long getGpsCyclesSinceCrcData() {
		return gpsCyclesSinceCrcData;
	}

	public void setGpsCyclesSinceCrcData(long gpsCyclesSinceCrcData) {
		this.gpsCyclesSinceCrcData = gpsCyclesSinceCrcData;
	}

	public int getGpsLockCount() {
		return gpsLockCount;
	}

	public void setGpsLockCount(int gpsLockCount) {
		this.gpsLockCount = gpsLockCount;
	}

	public int getMsgUsedSatellites() {
		return msgUsedSatellites;
	}

	public void setMsgUsedSatellites(int msgUsedSatellites) {
		this.msgUsedSatellites = msgUsedSatellites;
	}

	public boolean isGpsPosLock() {
		return gpsPosLock;
	}

	public void setGpsPosLock(boolean gpsPosLock) {
		this.gpsPosLock = gpsPosLock;
	}

	public boolean isGpsTimeLock() {
		return gpsTimeLock;
	}

	public void setGpsTimeLock(boolean gpsTimeLock) {
		this.gpsTimeLock = gpsTimeLock;
	}

	public boolean isMsgDataValid() {
		return msgDataValid;
	}

	public void setMsgDataValid(boolean msgDataValid) {
		this.msgDataValid = msgDataValid;
	}

	public boolean isGpsNewDataReceived() {
		return gpsNewDataReceived;
	}

	public void setGpsNewDataReceived(boolean gpsNewDataReceived) {
		this.gpsNewDataReceived = gpsNewDataReceived;
	}

	public boolean isGpsEnable() {
		return gpsEnable;
	}

	public void setGpsEnable(boolean gpsEnable) {
		this.gpsEnable = gpsEnable;
	}

	public boolean isGpsValid() {
		return gpsValid;
	}

	public void setGpsValid(boolean gpsValid) {
		this.gpsValid = gpsValid;
	}

}
