package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswImu {

	private float avgChannelRates1;
	private float avgChannelRates2;
	private float avgChannelRates3;
	private float imuAvgVectorBody1;
	private float imuAvgVectorBody2;
	private float imuAvgVectorBody3;
	private long avgTimeTag;
	private int rawFirstPacketRates1;
	private int rawFirstPacketRates2;
	private int rawFirstPacketRates3;
	private int imuInvalidCount;
	private int newPacketCount;
	private int firstPacketId;
	private boolean imuVectorValid;
	private boolean imuVectorEnabled;
	private boolean imuTestMode;

	public FswImu() {
		// do nothing
	}

	public FswImu(DataInputStream dis) throws IOException {
		avgChannelRates1 = dis.readUnsignedShort() / 10471.98f;
		avgChannelRates2 = dis.readUnsignedShort() / 10471.98f;
		avgChannelRates3 = dis.readUnsignedShort() / 10471.98f;
		imuAvgVectorBody1 = dis.readUnsignedShort() / 10471.98f;
		imuAvgVectorBody2 = dis.readUnsignedShort() / 10471.98f;
		imuAvgVectorBody3 = dis.readUnsignedShort() / 10471.98f;
		avgTimeTag = StreamUtils.readUnsignedInt(dis);
		rawFirstPacketRates1 = dis.readUnsignedShort();
		rawFirstPacketRates2 = dis.readUnsignedShort();
		rawFirstPacketRates3 = dis.readUnsignedShort();
		imuInvalidCount = dis.readUnsignedShort();
		newPacketCount = dis.readUnsignedByte();
		firstPacketId = dis.readUnsignedByte();
		imuVectorValid = dis.readBoolean();
		imuVectorEnabled = dis.readBoolean();
		imuTestMode = dis.readBoolean();
	}

	public float getAvgChannelRates1() {
		return avgChannelRates1;
	}

	public void setAvgChannelRates1(float avgChannelRates1) {
		this.avgChannelRates1 = avgChannelRates1;
	}

	public float getAvgChannelRates2() {
		return avgChannelRates2;
	}

	public void setAvgChannelRates2(float avgChannelRates2) {
		this.avgChannelRates2 = avgChannelRates2;
	}

	public float getAvgChannelRates3() {
		return avgChannelRates3;
	}

	public void setAvgChannelRates3(float avgChannelRates3) {
		this.avgChannelRates3 = avgChannelRates3;
	}

	public float getImuAvgVectorBody1() {
		return imuAvgVectorBody1;
	}

	public void setImuAvgVectorBody1(float imuAvgVectorBody1) {
		this.imuAvgVectorBody1 = imuAvgVectorBody1;
	}

	public float getImuAvgVectorBody2() {
		return imuAvgVectorBody2;
	}

	public void setImuAvgVectorBody2(float imuAvgVectorBody2) {
		this.imuAvgVectorBody2 = imuAvgVectorBody2;
	}

	public float getImuAvgVectorBody3() {
		return imuAvgVectorBody3;
	}

	public void setImuAvgVectorBody3(float imuAvgVectorBody3) {
		this.imuAvgVectorBody3 = imuAvgVectorBody3;
	}

	public long getAvgTimeTag() {
		return avgTimeTag;
	}

	public void setAvgTimeTag(long avgTimeTag) {
		this.avgTimeTag = avgTimeTag;
	}

	public int getRawFirstPacketRates1() {
		return rawFirstPacketRates1;
	}

	public void setRawFirstPacketRates1(int rawFirstPacketRates1) {
		this.rawFirstPacketRates1 = rawFirstPacketRates1;
	}

	public int getRawFirstPacketRates2() {
		return rawFirstPacketRates2;
	}

	public void setRawFirstPacketRates2(int rawFirstPacketRates2) {
		this.rawFirstPacketRates2 = rawFirstPacketRates2;
	}

	public int getRawFirstPacketRates3() {
		return rawFirstPacketRates3;
	}

	public void setRawFirstPacketRates3(int rawFirstPacketRates3) {
		this.rawFirstPacketRates3 = rawFirstPacketRates3;
	}

	public int getImuInvalidCount() {
		return imuInvalidCount;
	}

	public void setImuInvalidCount(int imuInvalidCount) {
		this.imuInvalidCount = imuInvalidCount;
	}

	public int getNewPacketCount() {
		return newPacketCount;
	}

	public void setNewPacketCount(int newPacketCount) {
		this.newPacketCount = newPacketCount;
	}

	public int getFirstPacketId() {
		return firstPacketId;
	}

	public void setFirstPacketId(int firstPacketId) {
		this.firstPacketId = firstPacketId;
	}

	public boolean isImuVectorValid() {
		return imuVectorValid;
	}

	public void setImuVectorValid(boolean imuVectorValid) {
		this.imuVectorValid = imuVectorValid;
	}

	public boolean isImuVectorEnabled() {
		return imuVectorEnabled;
	}

	public void setImuVectorEnabled(boolean imuVectorEnabled) {
		this.imuVectorEnabled = imuVectorEnabled;
	}

	public boolean isImuTestMode() {
		return imuTestMode;
	}

	public void setImuTestMode(boolean imuTestMode) {
		this.imuTestMode = imuTestMode;
	}

}
