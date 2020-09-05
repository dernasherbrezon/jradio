package ru.r2cloud.jradio.falconsat3;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pblist {

	private static final Pattern PATTERN = Pattern.compile("^PB: (.+)");
	private static final Pattern SPACE = Pattern.compile(" ");

	private String[] activeCallsigns;

	public Pblist() {
		// do nothing
	}

	public Pblist(String message) throws IOException {
		Matcher m = PATTERN.matcher(message.trim());
		if (!m.find()) {
			throw new IOException("unsupported");
		}
		String callsign = m.group(1).trim();
		if (!callsign.startsWith("Empty")) {
			activeCallsigns = SPACE.split(callsign);
		}
	}

	public String[] getActiveCallsigns() {
		return activeCallsigns;
	}

	public void setActiveCallsigns(String[] activeCallsigns) {
		this.activeCallsigns = activeCallsigns;
	}
}
