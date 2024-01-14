package ru.r2cloud.jradio.randev;

public enum AdcsMode {

	DISABLED(0), ENABLED(1), TRIGGERED(2), UNKNOWN(255);

	private final int code;

	private AdcsMode(int code) {
		this.code = code;
	}

	public static AdcsMode valueOfCode(int code) {
		for (AdcsMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
