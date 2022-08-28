package ru.r2cloud.jradio.is1;

public enum AdcsMode {

	SUN(0), FINE(1), UNKNOWN(255);

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
