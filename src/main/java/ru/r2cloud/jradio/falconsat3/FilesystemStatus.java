package ru.r2cloud.jradio.falconsat3;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilesystemStatus {

	private static final Pattern PATTERN = Pattern.compile("^B:\\s+(\\d+)");

	private long bytesSent;

	public FilesystemStatus() {
		// do nothing
	}

	public FilesystemStatus(String message) throws IOException {
		Matcher m = PATTERN.matcher(message.trim());
		if (!m.find()) {
			throw new IOException("unsupported");
		}
		bytesSent = Long.valueOf(m.group(1));
	}

	public long getBytesSent() {
		return bytesSent;
	}
	
	public void setBytesSent(long bytesSent) {
		this.bytesSent = bytesSent;
	}
}
