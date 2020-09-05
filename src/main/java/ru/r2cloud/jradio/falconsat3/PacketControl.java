package ru.r2cloud.jradio.falconsat3;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PacketControl {

	private static final Pattern PATTERN = Pattern.compile("^CTRL: mode=(\\d+) torq(\\d+) elog=(\\d+) alog=(\\d+)$");

	private int mode;
	private boolean torqRodsOn;
	private boolean errorsLogged;
	private boolean attitudeControlLogged;

	public PacketControl() {
		// do nothing
	}

	public PacketControl(String message) throws IOException {
		Matcher m = PATTERN.matcher(message.trim());
		if (!m.find()) {
			throw new IOException("unsupported");
		}
		mode = Integer.valueOf(m.group(1));
		torqRodsOn = m.group(2).equals("1");
		errorsLogged = m.group(3).equals("1");
		attitudeControlLogged = m.group(4).equals("1");
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public boolean isTorqRodsOn() {
		return torqRodsOn;
	}

	public void setTorqRodsOn(boolean torqRodsOn) {
		this.torqRodsOn = torqRodsOn;
	}

	public boolean isErrorsLogged() {
		return errorsLogged;
	}

	public void setErrorsLogged(boolean errorsLogged) {
		this.errorsLogged = errorsLogged;
	}

	public boolean isAttitudeControlLogged() {
		return attitudeControlLogged;
	}

	public void setAttitudeControlLogged(boolean attitudeControlLogged) {
		this.attitudeControlLogged = attitudeControlLogged;
	}

}
