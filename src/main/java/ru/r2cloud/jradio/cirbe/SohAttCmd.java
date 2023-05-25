package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class SohAttCmd {

	private long hrCycleSafeMode;
	private SunPointReason sunPointReason;
	private boolean recommendSunPoint;
	private int adcsMode;

	public SohAttCmd() {
		// do nothing
	}

	public SohAttCmd(DataInputStream dis) throws IOException {
		hrCycleSafeMode = StreamUtils.readUnsignedInt(dis);
		int raw = dis.readUnsignedByte();
		sunPointReason = SunPointReason.valueOfCode((raw >> 4) & 0x3);
		recommendSunPoint = ((raw >> 3) & 0x1) > 0;
		adcsMode = raw & 0x1;
	}

	public long getHrCycleSafeMode() {
		return hrCycleSafeMode;
	}

	public void setHrCycleSafeMode(long hrCycleSafeMode) {
		this.hrCycleSafeMode = hrCycleSafeMode;
	}

	public SunPointReason getSunPointReason() {
		return sunPointReason;
	}

	public void setSunPointReason(SunPointReason sunPointReason) {
		this.sunPointReason = sunPointReason;
	}

	public boolean isRecommendSunPoint() {
		return recommendSunPoint;
	}

	public void setRecommendSunPoint(boolean recommendSunPoint) {
		this.recommendSunPoint = recommendSunPoint;
	}

	public int getAdcsMode() {
		return adcsMode;
	}

	public void setAdcsMode(int adcsMode) {
		this.adcsMode = adcsMode;
	}

}
