package ru.r2cloud.jradio.falconsat3;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PacsatHousekeepingTask {

	private static final Pattern PATTERN = Pattern.compile("^PHT: uptime is (\\d+)/(\\d+):(\\d+):(\\d+).  Time is (.+)\r?");

	private int days;
	private int hours;
	private int minutes;
	private int seconds;

	private long currentTime;

	public PacsatHousekeepingTask() {
		// do nothing
	}
	
	public PacsatHousekeepingTask(String message) throws IOException {
		Matcher m = PATTERN.matcher(message.trim());
		if (!m.find()) {
			throw new IOException("unsupported");
		}

		days = Integer.valueOf(m.group(1));
		hours = Integer.valueOf(m.group(2));
		minutes = Integer.valueOf(m.group(3));
		seconds = Integer.valueOf(m.group(4));

		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			currentTime = sdf.parse(m.group(5)).getTime();
		} catch (ParseException e) {
			throw new IOException("invalid format", e);
		}
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
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

	public long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

}
