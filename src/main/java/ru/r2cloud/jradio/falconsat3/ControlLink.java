package ru.r2cloud.jradio.falconsat3;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControlLink {

	private static final Pattern PATTERN = Pattern.compile("^CL:(\\d+)");

	private boolean linkIsWorking;

	public ControlLink() {
		// do nothing
	}

	public ControlLink(String message) throws IOException {
		Matcher m = PATTERN.matcher(message.trim());
		if (!m.find()) {
			throw new IOException("unsupported");
		}
		linkIsWorking = m.group(1).equals("0");
	}

	public boolean isLinkIsWorking() {
		return linkIsWorking;
	}

	public void setLinkIsWorking(boolean linkIsWorking) {
		this.linkIsWorking = linkIsWorking;
	}

}
