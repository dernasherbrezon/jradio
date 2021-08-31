package ru.r2cloud.jradio.aeneas;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class AeneasTimestamp {

	private int month;
	private int day;
	private int year;
	private int weekday;
	private int hours;
	private int minutes;
	private int seconds;
	
	public AeneasTimestamp() {
		//do nothing
	}

	public AeneasTimestamp(LittleEndianDataInputStream ldis) throws IOException {
		month = ldis.readUnsignedByte();
		day = ldis.readUnsignedByte();
		year = ldis.readUnsignedByte();
		weekday = ldis.readUnsignedByte();
		hours = ldis.readUnsignedByte();
		minutes = ldis.readUnsignedByte();
		seconds = ldis.readUnsignedByte();
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getWeekday() {
		return weekday;
	}

	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

}
