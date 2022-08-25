package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswCal {

	private float[] qtrackerWrtBody;
	private float[] qtrackerWrtBodyEst;
	private float trackerAlignUsedRes1;
	private float trackerAlignUsedRes2;
	private float trackerAlignUsedRes3;
	private float trackerAlignEstRes1;
	private float trackerAlignEstRes2;
	private float trackerAlignEstRes3;
	private long rejectedTrackerEstCount;
	private int trackerN;
	private AlignMethod trackerAlignMethod;
	private AlignUsage trackerAlignUsage;
	private float[] gyroCalAlign;
	private float[] gyroCalAlignBest;
	private float gyroCalBestUnc;
	private float gyroCalCurrentUnc;
	private boolean gyroCalRunning;

	public FswCal() {
		// do nothing
	}

	public FswCal(DataInputStream dis) throws IOException {
		qtrackerWrtBody = new float[8];
		for (int i = 0; i < qtrackerWrtBody.length; i++) {
			qtrackerWrtBody[i] = StreamUtils.readUnsignedInt(dis) / 1e9f;
		}
		qtrackerWrtBodyEst = new float[8];
		for (int i = 0; i < qtrackerWrtBodyEst.length; i++) {
			qtrackerWrtBodyEst[i] = StreamUtils.readUnsignedInt(dis) / 1e9f;
		}
		trackerAlignUsedRes1 = StreamUtils.readUnsignedInt(dis) / 1e9f;
		trackerAlignUsedRes2 = StreamUtils.readUnsignedInt(dis) / 1e9f;
		trackerAlignUsedRes3 = StreamUtils.readUnsignedInt(dis) / 1e9f;
		trackerAlignEstRes1 = StreamUtils.readUnsignedInt(dis) / 1e9f;
		trackerAlignEstRes2 = StreamUtils.readUnsignedInt(dis) / 1e9f;
		trackerAlignEstRes3 = StreamUtils.readUnsignedInt(dis) / 1e9f;
		rejectedTrackerEstCount = StreamUtils.readUnsignedInt(dis);
		trackerN = dis.readUnsignedShort();
		trackerAlignMethod = AlignMethod.valueOfCode(dis.readUnsignedByte());
		trackerAlignUsage = AlignUsage.valueOfCode(dis.readUnsignedByte());
		gyroCalAlign = new float[8];
		for (int i = 0; i < gyroCalAlign.length; i++) {
			gyroCalAlign[i] = StreamUtils.readUnsignedInt(dis) / 1e9f;
		}
		gyroCalAlignBest = new float[8];
		for (int i = 0; i < gyroCalAlignBest.length; i++) {
			gyroCalAlignBest[i] = StreamUtils.readUnsignedInt(dis) / 1e9f;
		}
		gyroCalBestUnc = StreamUtils.readUnsignedInt(dis) / 2e9f;
		gyroCalCurrentUnc = StreamUtils.readUnsignedInt(dis) / 2e9f;
		gyroCalRunning = dis.readBoolean();
	}

	public float[] getQtrackerWrtBody() {
		return qtrackerWrtBody;
	}

	public void setQtrackerWrtBody(float[] qtrackerWrtBody) {
		this.qtrackerWrtBody = qtrackerWrtBody;
	}

	public float[] getQtrackerWrtBodyEst() {
		return qtrackerWrtBodyEst;
	}

	public void setQtrackerWrtBodyEst(float[] qtrackerWrtBodyEst) {
		this.qtrackerWrtBodyEst = qtrackerWrtBodyEst;
	}

	public float getTrackerAlignUsedRes1() {
		return trackerAlignUsedRes1;
	}

	public void setTrackerAlignUsedRes1(float trackerAlignUsedRes1) {
		this.trackerAlignUsedRes1 = trackerAlignUsedRes1;
	}

	public float getTrackerAlignUsedRes2() {
		return trackerAlignUsedRes2;
	}

	public void setTrackerAlignUsedRes2(float trackerAlignUsedRes2) {
		this.trackerAlignUsedRes2 = trackerAlignUsedRes2;
	}

	public float getTrackerAlignUsedRes3() {
		return trackerAlignUsedRes3;
	}

	public void setTrackerAlignUsedRes3(float trackerAlignUsedRes3) {
		this.trackerAlignUsedRes3 = trackerAlignUsedRes3;
	}

	public float getTrackerAlignEstRes1() {
		return trackerAlignEstRes1;
	}

	public void setTrackerAlignEstRes1(float trackerAlignEstRes1) {
		this.trackerAlignEstRes1 = trackerAlignEstRes1;
	}

	public float getTrackerAlignEstRes2() {
		return trackerAlignEstRes2;
	}

	public void setTrackerAlignEstRes2(float trackerAlignEstRes2) {
		this.trackerAlignEstRes2 = trackerAlignEstRes2;
	}

	public float getTrackerAlignEstRes3() {
		return trackerAlignEstRes3;
	}

	public void setTrackerAlignEstRes3(float trackerAlignEstRes3) {
		this.trackerAlignEstRes3 = trackerAlignEstRes3;
	}

	public long getRejectedTrackerEstCount() {
		return rejectedTrackerEstCount;
	}

	public void setRejectedTrackerEstCount(long rejectedTrackerEstCount) {
		this.rejectedTrackerEstCount = rejectedTrackerEstCount;
	}

	public int getTrackerN() {
		return trackerN;
	}

	public void setTrackerN(int trackerN) {
		this.trackerN = trackerN;
	}

	public AlignMethod getTrackerAlignMethod() {
		return trackerAlignMethod;
	}

	public void setTrackerAlignMethod(AlignMethod trackerAlignMethod) {
		this.trackerAlignMethod = trackerAlignMethod;
	}

	public AlignUsage getTrackerAlignUsage() {
		return trackerAlignUsage;
	}

	public void setTrackerAlignUsage(AlignUsage trackerAlignUsage) {
		this.trackerAlignUsage = trackerAlignUsage;
	}

	public float[] getGyroCalAlign() {
		return gyroCalAlign;
	}

	public void setGyroCalAlign(float[] gyroCalAlign) {
		this.gyroCalAlign = gyroCalAlign;
	}

	public float[] getGyroCalAlignBest() {
		return gyroCalAlignBest;
	}

	public void setGyroCalAlignBest(float[] gyroCalAlignBest) {
		this.gyroCalAlignBest = gyroCalAlignBest;
	}

	public float getGyroCalBestUnc() {
		return gyroCalBestUnc;
	}

	public void setGyroCalBestUnc(float gyroCalBestUnc) {
		this.gyroCalBestUnc = gyroCalBestUnc;
	}

	public float getGyroCalCurrentUnc() {
		return gyroCalCurrentUnc;
	}

	public void setGyroCalCurrentUnc(float gyroCalCurrentUnc) {
		this.gyroCalCurrentUnc = gyroCalCurrentUnc;
	}

	public boolean isGyroCalRunning() {
		return gyroCalRunning;
	}

	public void setGyroCalRunning(boolean gyroCalRunning) {
		this.gyroCalRunning = gyroCalRunning;
	}

}
