package ru.r2cloud.jradio.falconsat3;

public class Notification {

	private String message;

	public Notification() {
		// do nothing
	}

	public Notification(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
