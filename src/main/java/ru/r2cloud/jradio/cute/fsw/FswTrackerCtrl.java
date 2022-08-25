package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswTrackerCtrl {

	private float[] qbodyWrtEci;
	private boolean trackerAttValid1;
	private boolean trackerAttValid2;
	private boolean trackerUsageEnabled1;
	private boolean trackerUsageEnabled2;
	private boolean maxBackgroundTripped1;
	private boolean maxBackgroundTripped2;
	private boolean maxRotRateTripped1;
	private boolean maxRotRateTripped2;
	private int aidStatus1;
	private int aidStatus2;
	private int starIdStatus;

	public FswTrackerCtrl() {
		// do nothing
	}

	public FswTrackerCtrl(DataInputStream dis) throws IOException {
		qbodyWrtEci = new float[8];
		for (int i = 0; i < qbodyWrtEci.length; i++) {
			qbodyWrtEci[i] = StreamUtils.readUnsignedInt(dis) / 1e9f;
		}
		trackerAttValid1 = dis.readBoolean();
		trackerAttValid2 = dis.readBoolean();
		trackerUsageEnabled1 = dis.readBoolean();
		trackerUsageEnabled2 = dis.readBoolean();
		maxBackgroundTripped1 = dis.readBoolean();
		maxBackgroundTripped2 = dis.readBoolean();
		maxRotRateTripped1 = dis.readBoolean();
		maxRotRateTripped2 = dis.readBoolean();
		aidStatus1 = dis.readUnsignedByte();
		aidStatus2 = dis.readUnsignedByte();
		starIdStatus = dis.readUnsignedByte();
	}

	public float[] getQbodyWrtEci() {
		return qbodyWrtEci;
	}

	public void setQbodyWrtEci(float[] qbodyWrtEci) {
		this.qbodyWrtEci = qbodyWrtEci;
	}

	public boolean isTrackerAttValid1() {
		return trackerAttValid1;
	}

	public void setTrackerAttValid1(boolean trackerAttValid1) {
		this.trackerAttValid1 = trackerAttValid1;
	}

	public boolean isTrackerAttValid2() {
		return trackerAttValid2;
	}

	public void setTrackerAttValid2(boolean trackerAttValid2) {
		this.trackerAttValid2 = trackerAttValid2;
	}

	public boolean isTrackerUsageEnabled1() {
		return trackerUsageEnabled1;
	}

	public void setTrackerUsageEnabled1(boolean trackerUsageEnabled1) {
		this.trackerUsageEnabled1 = trackerUsageEnabled1;
	}

	public boolean isTrackerUsageEnabled2() {
		return trackerUsageEnabled2;
	}

	public void setTrackerUsageEnabled2(boolean trackerUsageEnabled2) {
		this.trackerUsageEnabled2 = trackerUsageEnabled2;
	}

	public boolean isMaxBackgroundTripped1() {
		return maxBackgroundTripped1;
	}

	public void setMaxBackgroundTripped1(boolean maxBackgroundTripped1) {
		this.maxBackgroundTripped1 = maxBackgroundTripped1;
	}

	public boolean isMaxBackgroundTripped2() {
		return maxBackgroundTripped2;
	}

	public void setMaxBackgroundTripped2(boolean maxBackgroundTripped2) {
		this.maxBackgroundTripped2 = maxBackgroundTripped2;
	}

	public boolean isMaxRotRateTripped1() {
		return maxRotRateTripped1;
	}

	public void setMaxRotRateTripped1(boolean maxRotRateTripped1) {
		this.maxRotRateTripped1 = maxRotRateTripped1;
	}

	public boolean isMaxRotRateTripped2() {
		return maxRotRateTripped2;
	}

	public void setMaxRotRateTripped2(boolean maxRotRateTripped2) {
		this.maxRotRateTripped2 = maxRotRateTripped2;
	}

	public int getAidStatus1() {
		return aidStatus1;
	}

	public void setAidStatus1(int aidStatus1) {
		this.aidStatus1 = aidStatus1;
	}

	public int getAidStatus2() {
		return aidStatus2;
	}

	public void setAidStatus2(int aidStatus2) {
		this.aidStatus2 = aidStatus2;
	}

	public int getStarIdStatus() {
		return starIdStatus;
	}

	public void setStarIdStatus(int starIdStatus) {
		this.starIdStatus = starIdStatus;
	}

}
