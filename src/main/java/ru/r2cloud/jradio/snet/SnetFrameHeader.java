package ru.r2cloud.jradio.snet;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import ru.r2cloud.jradio.util.BitInputStream;
import ru.r2cloud.jradio.util.LittleEndianUtils;

public class SnetFrameHeader {

	private int crc;
	private int fcidMajor;
	private int fcidSub;
	private boolean urgent;
	private boolean checkCrc;
	private boolean multiFrame;
	private boolean timeTaggedSetting;
	private boolean timeTagged;
	private int dataLength;
	private long timeTag;
	
	public SnetFrameHeader() {
		//do nothing
	}

	public SnetFrameHeader(BitInputStream bis) throws IOException {
		bis.skipBits(18);
		crc = bis.readUnsignedInt(14);
		fcidMajor = bis.readUnsignedInt(6);
		fcidSub = bis.readUnsignedInt(10);
		urgent = bis.readBoolean();
		bis.skipBits(1);
		checkCrc = bis.readBoolean();
		multiFrame = bis.readBoolean();
		timeTaggedSetting = bis.readBoolean();
		timeTagged = bis.readBoolean();
		dataLength = bis.readUnsignedInt(10);
		if (timeTagged) {
			long halfSecondsfrom2000 = LittleEndianUtils.convertUnsignedInt(bis.readUnsignedLong(32));
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			cal.set(Calendar.YEAR, 2000);
			cal.set(Calendar.MONTH, Calendar.JANUARY);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			timeTag = cal.getTimeInMillis() + halfSecondsfrom2000 * 500;
		}
	}

	public int getCrc() {
		return crc;
	}

	public void setCrc(int crc) {
		this.crc = crc;
	}

	public int getFcidMajor() {
		return fcidMajor;
	}

	public void setFcidMajor(int fcidMajor) {
		this.fcidMajor = fcidMajor;
	}

	public int getFcidSub() {
		return fcidSub;
	}

	public void setFcidSub(int fcidSub) {
		this.fcidSub = fcidSub;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public boolean isCheckCrc() {
		return checkCrc;
	}

	public void setCheckCrc(boolean checkCrc) {
		this.checkCrc = checkCrc;
	}

	public boolean isMultiFrame() {
		return multiFrame;
	}

	public void setMultiFrame(boolean multiFrame) {
		this.multiFrame = multiFrame;
	}

	public boolean isTimeTaggedSetting() {
		return timeTaggedSetting;
	}

	public void setTimeTaggedSetting(boolean timeTaggedSetting) {
		this.timeTaggedSetting = timeTaggedSetting;
	}

	public boolean isTimeTagged() {
		return timeTagged;
	}

	public void setTimeTagged(boolean timeTagged) {
		this.timeTagged = timeTagged;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public long getTimeTag() {
		return timeTag;
	}

	public void setTimeTag(long timeTag) {
		this.timeTag = timeTag;
	}

}
