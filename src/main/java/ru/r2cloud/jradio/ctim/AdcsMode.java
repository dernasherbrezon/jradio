package ru.r2cloud.jradio.ctim;

public enum AdcsMode {

	FINE_REF_POINT(0), SUN_POINT(1), UNKNOWN(255);

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
