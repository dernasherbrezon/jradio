package ru.r2cloud.jradio.skcube;

public enum AdcsMode {
	
	DEFAULT(0), BDOT_ON(1), BDOT_OFF(2);

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
		return null;
	}

}
