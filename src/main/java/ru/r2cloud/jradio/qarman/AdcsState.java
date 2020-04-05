package ru.r2cloud.jradio.qarman;

public enum AdcsState {

	DISABLED(0), ENABLED(1), TRIGGERED(2);

	private final int code;

	private AdcsState(int code) {
		this.code = code;
	}

	public static AdcsState valueOfCode(int code) {
		for (AdcsState cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}
	
}
