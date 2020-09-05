package ru.r2cloud.jradio.falconsat3;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PortStatus {

	private static final Pattern PATTERN = Pattern.compile("^C0:(.{2}) C1:(.{2}) C2:(.{2}) C3:(.{2}) C4:(.{2}) C5:(.{2})$");

	private int ch0;
	private int ch1;
	private int ch2;
	private int ch3;
	private int ch4;
	private int ch5;

	public PortStatus() {
		// do nothing
	}

	public PortStatus(String message) throws IOException {
		Matcher m = PATTERN.matcher(message.trim());
		if (!m.find()) {
			throw new IOException("unsupported");
		}
		ch0 = Integer.parseInt(m.group(1), 16);
		ch1 = Integer.parseInt(m.group(2), 16);
		ch2 = Integer.parseInt(m.group(3), 16);
		ch3 = Integer.parseInt(m.group(4), 16);
		ch4 = Integer.parseInt(m.group(5), 16);
		ch5 = Integer.parseInt(m.group(6), 16);
	}

	public int getCh0() {
		return ch0;
	}

	public void setCh0(int ch0) {
		this.ch0 = ch0;
	}

	public int getCh1() {
		return ch1;
	}

	public void setCh1(int ch1) {
		this.ch1 = ch1;
	}

	public int getCh2() {
		return ch2;
	}

	public void setCh2(int ch2) {
		this.ch2 = ch2;
	}

	public int getCh3() {
		return ch3;
	}

	public void setCh3(int ch3) {
		this.ch3 = ch3;
	}

	public int getCh4() {
		return ch4;
	}

	public void setCh4(int ch4) {
		this.ch4 = ch4;
	}
	
	public int getCh5() {
		return ch5;
	}
	
	public void setCh5(int ch5) {
		this.ch5 = ch5;
	}

}
