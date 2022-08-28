package ru.r2cloud.jradio.is1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class AdcsInfo {

	private boolean attValid;
	private boolean refValid;
	private boolean timeValid;
	private AdcsMode mode;
	private boolean recommendSunPoint;
	private SunPointState sunPointState;

	public AdcsInfo() {
		// do nothing
	}

	public AdcsInfo(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		attValid = ((raw >> 7) & 0x1) > 0;
		refValid = ((raw >> 6) & 0x1) > 0;
		timeValid = ((raw >> 5) & 0x1) > 0;
		mode = AdcsMode.valueOfCode(((raw >> 4) & 0x1));
		recommendSunPoint = ((raw >> 3) & 0x1) > 0;
		sunPointState = SunPointState.valueOfCode(raw & 0b111);
	}

	public boolean isAttValid() {
		return attValid;
	}

	public void setAttValid(boolean attValid) {
		this.attValid = attValid;
	}

	public boolean isRefValid() {
		return refValid;
	}

	public void setRefValid(boolean refValid) {
		this.refValid = refValid;
	}

	public boolean isTimeValid() {
		return timeValid;
	}

	public void setTimeValid(boolean timeValid) {
		this.timeValid = timeValid;
	}

	public AdcsMode getMode() {
		return mode;
	}

	public void setMode(AdcsMode mode) {
		this.mode = mode;
	}

	public boolean isRecommendSunPoint() {
		return recommendSunPoint;
	}

	public void setRecommendSunPoint(boolean recommendSunPoint) {
		this.recommendSunPoint = recommendSunPoint;
	}

	public SunPointState getSunPointState() {
		return sunPointState;
	}

	public void setSunPointState(SunPointState sunPointState) {
		this.sunPointState = sunPointState;
	}

}
