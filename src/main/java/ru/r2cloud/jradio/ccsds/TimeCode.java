package ru.r2cloud.jradio.ccsds;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

// as defined in CCSDS 301.0-B-4
public class TimeCode {

	private Integer day;
	private Long millisecondsOfTheDay;
	private Long subMillisecond;

	private Long time;
	private Long fractionalTime;

	public TimeCode() {
		// do nothing
	}

	public TimeCode(BitInputStream bis, PField pfield) throws IOException {
		switch (pfield.getType()) {
		case CDS:
			if (pfield.getLengthOfDaySegment() == 0) {
				day = bis.readUnsignedInt(16);
			} else {
				day = bis.readUnsignedInt(24);
			}
			millisecondsOfTheDay = bis.readUnsignedLong(32);
			if (pfield.getLengthOfSubmillisecondSegment() == 0b01) {
				subMillisecond = bis.readUnsignedLong(16);
			} else if (pfield.getLengthOfSubmillisecondSegment() == 0b10) {
				subMillisecond = bis.readUnsignedLong(32);
			}
			break;
		case CUC:
			time = bis.readUnsignedLong(pfield.getBasicTimeUnitOctets() * 8);
			if (pfield.getFractionalTimeUnitOctets() > 0) {
				fractionalTime = bis.readUnsignedLong(pfield.getFractionalTimeUnitOctets() * 8);
			}
			break;
		default:
			break;
		}
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Long getMillisecondsOfTheDay() {
		return millisecondsOfTheDay;
	}

	public void setMillisecondsOfTheDay(Long millisecondsOfTheDay) {
		this.millisecondsOfTheDay = millisecondsOfTheDay;
	}

	public Long getSubMillisecond() {
		return subMillisecond;
	}

	public void setSubMillisecond(Long subMillisecond) {
		this.subMillisecond = subMillisecond;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getFractionalTime() {
		return fractionalTime;
	}

	public void setFractionalTime(Long fractionalTime) {
		this.fractionalTime = fractionalTime;
	}

}
