package ru.r2cloud.jradio.hunity;

public enum Status {

	ON, OFF, TRIPPED, ERROR;

	public static Status valueOfCode(int code) {
		switch (code) {
		case 0:
			return OFF;
		case 1:
			return ON;
		case 2:
			return TRIPPED;
		default:
			return ERROR;
		}
	}
}
