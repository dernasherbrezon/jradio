package ru.r2cloud.jradio.astrocast;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class Telemetry {

	private static final Pattern COMMA = Pattern.compile(",");

	private Date time;
	private float voltage; // volts
	private float current; // mA
	private float temperature; // in ºC
	private int rssi; // in dB
	private long afc; // Hz
	private int downloadFormat;

	public Telemetry(String str) throws ParseException {
		String[] parts = COMMA.split(str);
		if (parts.length != 8) {
			throw new ParseException("invalid number of fields: " + parts.length, 0);
		}
		if (!parts[0].equalsIgnoreCase("$HK")) {
			throw new ParseException("invalid start of frame: " + parts[0], 0);
		}
		Long timeSince20160101 = (long) (Long.parseLong(parts[1].substring(2), 16) * (Math.pow(2, -16) * 1000));
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long startTime = cal.getTimeInMillis();
		time = new Date(startTime + timeSince20160101);
		voltage = Float.parseFloat(parts[2]);
		current = Float.parseFloat(parts[3]);
		temperature = Float.parseFloat(parts[4]);
		rssi = Integer.parseInt(parts[5]);
		afc = Long.parseLong(parts[6]);
		downloadFormat = Integer.parseInt(parts[7].substring(2, parts[7].length() - 1), 16);
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public float getVoltage() {
		return voltage;
	}

	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}

	public float getCurrent() {
		return current;
	}

	public void setCurrent(float current) {
		this.current = current;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public long getAfc() {
		return afc;
	}

	public void setAfc(int afc) {
		this.afc = afc;
	}

	public int getDownloadFormat() {
		return downloadFormat;
	}

	public void setDownloadFormat(int downloadFormat) {
		this.downloadFormat = downloadFormat;
	}

}
