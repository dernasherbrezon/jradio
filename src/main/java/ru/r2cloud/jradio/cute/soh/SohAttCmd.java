package ru.r2cloud.jradio.cute.soh;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.cute.AdcsMode;
import ru.r2cloud.jradio.cute.SafeModeReason;
import ru.r2cloud.jradio.util.StreamUtils;

public class SohAttCmd {

	private long hrCycleSafeMode;
	private float rotisserieRate;
	private AdcsMode adcsMode;
	private SafeModeReason safeModeReason;
	private boolean recommendSunPoint;

	public SohAttCmd() {
		// do nothing
	}

	public SohAttCmd(DataInputStream dis) throws IOException {
		hrCycleSafeMode = StreamUtils.readUnsignedInt(dis);
		rotisserieRate = dis.readShort() * 4e-05f;
		adcsMode = AdcsMode.valueOfCode(dis.readUnsignedByte());
		safeModeReason = SafeModeReason.valueOfCode(dis.readUnsignedByte());
		recommendSunPoint = dis.readBoolean();
	}

	public long getHrCycleSafeMode() {
		return hrCycleSafeMode;
	}

	public void setHrCycleSafeMode(long hrCycleSafeMode) {
		this.hrCycleSafeMode = hrCycleSafeMode;
	}

	public float getRotisserieRate() {
		return rotisserieRate;
	}

	public void setRotisserieRate(float rotisserieRate) {
		this.rotisserieRate = rotisserieRate;
	}

	public AdcsMode getAdcsMode() {
		return adcsMode;
	}

	public void setAdcsMode(AdcsMode adcsMode) {
		this.adcsMode = adcsMode;
	}

	public SafeModeReason getSafeModeReason() {
		return safeModeReason;
	}

	public void setSafeModeReason(SafeModeReason safeModeReason) {
		this.safeModeReason = safeModeReason;
	}

	public boolean isRecommendSunPoint() {
		return recommendSunPoint;
	}

	public void setRecommendSunPoint(boolean recommendSunPoint) {
		this.recommendSunPoint = recommendSunPoint;
	}

}
