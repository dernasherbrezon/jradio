package ru.r2cloud.jradio.falconsat3;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UplinkStatus {

	private static final Pattern PATTERN = Pattern.compile("^Open (.*?)$");

	private boolean uplinkAAvailable;
	private boolean uplinkBAvailable;
	private boolean uplinkCAvailable;
	private boolean uplinkDAvailable;

	public UplinkStatus() {
		// do nothing
	}

	public UplinkStatus(String message) throws IOException {
		Matcher m = PATTERN.matcher(message.trim());
		if (!m.find()) {
			throw new IOException("unsupported");
		}
		String status = m.group(1).trim().toLowerCase(Locale.UK);
		uplinkAAvailable = status.contains("a");
		uplinkBAvailable = status.contains("b");
		uplinkCAvailable = status.contains("c");
		uplinkDAvailable = status.contains("d");
	}

	public boolean isUplinkAAvailable() {
		return uplinkAAvailable;
	}

	public void setUplinkAAvailable(boolean uplinkAAvailable) {
		this.uplinkAAvailable = uplinkAAvailable;
	}

	public boolean isUplinkBAvailable() {
		return uplinkBAvailable;
	}

	public void setUplinkBAvailable(boolean uplinkBAvailable) {
		this.uplinkBAvailable = uplinkBAvailable;
	}

	public boolean isUplinkCAvailable() {
		return uplinkCAvailable;
	}

	public void setUplinkCAvailable(boolean uplinkCAvailable) {
		this.uplinkCAvailable = uplinkCAvailable;
	}

	public boolean isUplinkDAvailable() {
		return uplinkDAvailable;
	}

	public void setUplinkDAvailable(boolean uplinkDAvailable) {
		this.uplinkDAvailable = uplinkDAvailable;
	}

}
