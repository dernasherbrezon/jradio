package ru.r2cloud.jradio.hunity;

public class SampleTime {

	private Integer secondsAgo;
	private SampleTimeRelative relative;

	public Integer getSecondsAgo() {
		return secondsAgo;
	}

	public void setSecondsAgo(Integer secondsAgo) {
		this.secondsAgo = secondsAgo;
	}

	public SampleTimeRelative getRelative() {
		return relative;
	}

	public void setRelative(SampleTimeRelative relative) {
		this.relative = relative;
	}

	public static SampleTime valueOfByte(int unsignedByte) {
		SampleTime result = new SampleTime();
		if (unsignedByte < 126) {
			result.setSecondsAgo(unsignedByte);
		} else if (unsignedByte == 126) {
			result.setRelative(SampleTimeRelative.OLDER_THAN_125_SECONDS);
		} else {
			result.setRelative(SampleTimeRelative.INVALID);
		}
		return result;
	}

	public static SampleTime valueOf5Bit(int value) {
		value = value & 0x1F;
		SampleTime result = new SampleTime();
		if (value <= 25) {
			result.setSecondsAgo(value);
		} else if (value == 26) {
			result.setRelative(SampleTimeRelative.LESS_THAN_30_SECONDS);
		} else if (value == 27) {
			result.setRelative(SampleTimeRelative.LESS_THAN_40_SECONDS);
		} else if (value == 28) {
			result.setRelative(SampleTimeRelative.LESS_THAN_60_SECONDS);
		} else if (value == 29) {
			result.setRelative(SampleTimeRelative.LESS_THAN_120_SECONDS);
		} else if (value == 30) {
			result.setRelative(SampleTimeRelative.OLDER_THAN_120_SECONDS);
		} else {
			result.setRelative(SampleTimeRelative.INVALID);
		}
		return null;
	}

	public static SampleTime valueOf3Bit(int value) {
		value = value & 0xFF;
		value = value & 0x07;
		SampleTime result = new SampleTime();
		switch (value) {
		case 0:
		case 1:
			result.setSecondsAgo(value);
			break;
		case 2:
			result.setRelative(SampleTimeRelative.LESS_THAN_5_SECONDS);
			break;
		case 3:
			result.setRelative(SampleTimeRelative.LESS_THAN_10_SECONDS);
			break;
		case 4:
			result.setRelative(SampleTimeRelative.LESS_THAN_20_SECONDS);
			break;
		case 5:
			result.setRelative(SampleTimeRelative.LESS_THAN_20_SECONDS);
			break;
		case 6:
			result.setRelative(SampleTimeRelative.OLDER_THAN_60_SECONDS);
			break;
		default:
			result.setRelative(SampleTimeRelative.INVALID);
			break;
		}
		return result;
	}

}
