package ru.r2cloud.jradio.falconsat3;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoaderStatusLine {

	private static final Pattern PATTERN = Pattern.compile("^([IA]{1}) P:(.*?) o:(\\d+) l:(\\d+) f:(\\d+), d:(\\d+) st:(\\d+) e:(\\d+)$");

	private LoaderStatus status;
	private String address;
	private int o;
	private int l;
	private int f;
	private boolean digipeaterOn;
	private int taskNumber;
	private int errorCounter;

	public LoaderStatusLine() {
		// do nothing
	}

	public LoaderStatusLine(String message) throws IOException {
		Matcher m = PATTERN.matcher(message.trim());
		if (!m.find()) {
			throw new IOException("unsupported");
		}

		if (m.group(1).equalsIgnoreCase("I")) {
			status = LoaderStatus.IDLE;
		} else {
			status = LoaderStatus.ACTIVE;
		}

		address = m.group(2);
		o = Integer.parseInt(m.group(3));
		l = Integer.parseInt(m.group(4));
		f = Integer.parseInt(m.group(5));

		digipeaterOn = m.group(6).equalsIgnoreCase("1");
		taskNumber = Integer.parseInt(m.group(7));
		errorCounter = Integer.parseInt(m.group(8));
	}

	public LoaderStatus getStatus() {
		return status;
	}

	public void setStatus(LoaderStatus status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getO() {
		return o;
	}

	public void setO(int o) {
		this.o = o;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public boolean isDigipeaterOn() {
		return digipeaterOn;
	}

	public void setDigipeaterOn(boolean digipeaterOn) {
		this.digipeaterOn = digipeaterOn;
	}

	public int getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(int taskNumber) {
		this.taskNumber = taskNumber;
	}

	public int getErrorCounter() {
		return errorCounter;
	}

	public void setErrorCounter(int errorCounter) {
		this.errorCounter = errorCounter;
	}

}
