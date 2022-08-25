package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswGps {

	private long time;
	private float positionEcef1;
	private float positionEcef2;
	private float positionEcef3;
	private float velocityEcef1;
	private float velocityEcef2;
	private float velocityEcef3;
	private long sampleUsecCt;
	private long cyclesSinceLatestData;
	private int gpsLockCount;
	private int msGtrackedSatellites;
	private int msGusedSatellites;
	private boolean msgDataValid;
	private boolean gpsValid;
	private boolean gpsEnable;
	private boolean newDataReceived;

	public FswGps() {
		// do nothing
	}

	public FswGps(DataInputStream dis) throws IOException {
		time = StreamUtils.readUnsignedInt(dis);
		positionEcef1 = StreamUtils.readUnsignedInt(dis) / 50000.0f;
		positionEcef2 = StreamUtils.readUnsignedInt(dis) / 50000.0f;
		positionEcef3 = StreamUtils.readUnsignedInt(dis) / 50000.0f;
		velocityEcef1 = StreamUtils.readUnsignedInt(dis) / 200000000.0f;
		velocityEcef2 = StreamUtils.readUnsignedInt(dis) / 200000000.0f;
		velocityEcef3 = StreamUtils.readUnsignedInt(dis) / 200000000.0f;
		sampleUsecCt = StreamUtils.readUnsignedInt(dis);
		cyclesSinceLatestData = StreamUtils.readUnsignedInt(dis);
		gpsLockCount = dis.readUnsignedShort();
		msGtrackedSatellites = dis.readUnsignedByte();
		msGusedSatellites = dis.readUnsignedByte();
		msgDataValid = dis.readBoolean();
		gpsValid = dis.readBoolean();
		gpsEnable = dis.readBoolean();
		newDataReceived = dis.readBoolean();
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public float getPositionEcef1() {
		return positionEcef1;
	}

	public void setPositionEcef1(float positionEcef1) {
		this.positionEcef1 = positionEcef1;
	}

	public float getPositionEcef2() {
		return positionEcef2;
	}

	public void setPositionEcef2(float positionEcef2) {
		this.positionEcef2 = positionEcef2;
	}

	public float getPositionEcef3() {
		return positionEcef3;
	}

	public void setPositionEcef3(float positionEcef3) {
		this.positionEcef3 = positionEcef3;
	}

	public float getVelocityEcef1() {
		return velocityEcef1;
	}

	public void setVelocityEcef1(float velocityEcef1) {
		this.velocityEcef1 = velocityEcef1;
	}

	public float getVelocityEcef2() {
		return velocityEcef2;
	}

	public void setVelocityEcef2(float velocityEcef2) {
		this.velocityEcef2 = velocityEcef2;
	}

	public float getVelocityEcef3() {
		return velocityEcef3;
	}

	public void setVelocityEcef3(float velocityEcef3) {
		this.velocityEcef3 = velocityEcef3;
	}

	public long getSampleUsecCt() {
		return sampleUsecCt;
	}

	public void setSampleUsecCt(long sampleUsecCt) {
		this.sampleUsecCt = sampleUsecCt;
	}

	public long getCyclesSinceLatestData() {
		return cyclesSinceLatestData;
	}

	public void setCyclesSinceLatestData(long cyclesSinceLatestData) {
		this.cyclesSinceLatestData = cyclesSinceLatestData;
	}

	public int getGpsLockCount() {
		return gpsLockCount;
	}

	public void setGpsLockCount(int gpsLockCount) {
		this.gpsLockCount = gpsLockCount;
	}

	public int getMsGtrackedSatellites() {
		return msGtrackedSatellites;
	}

	public void setMsGtrackedSatellites(int msGtrackedSatellites) {
		this.msGtrackedSatellites = msGtrackedSatellites;
	}

	public int getMsGusedSatellites() {
		return msGusedSatellites;
	}

	public void setMsGusedSatellites(int msGusedSatellites) {
		this.msGusedSatellites = msGusedSatellites;
	}

	public boolean isMsgDataValid() {
		return msgDataValid;
	}

	public void setMsgDataValid(boolean msgDataValid) {
		this.msgDataValid = msgDataValid;
	}

	public boolean isGpsValid() {
		return gpsValid;
	}

	public void setGpsValid(boolean gpsValid) {
		this.gpsValid = gpsValid;
	}

	public boolean isGpsEnable() {
		return gpsEnable;
	}

	public void setGpsEnable(boolean gpsEnable) {
		this.gpsEnable = gpsEnable;
	}

	public boolean isNewDataReceived() {
		return newDataReceived;
	}

	public void setNewDataReceived(boolean newDataReceived) {
		this.newDataReceived = newDataReceived;
	}

}
