package ru.r2cloud.jradio.eirsat;

public enum AdcsMode {

	STANDBY(0), DETUMBLE(1), STABILISED(2), TEST(85), UNKNOWN(255);

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
