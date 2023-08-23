package ru.r2cloud.jradio.dhabi;

public enum ObcMode {

	INITIAL_CHECKOUT(0), NORMAL(1), SAFE(2), UNKNOWN(255);

	private final int code;

	private ObcMode(int code) {
		this.code = code;
	}

	public static ObcMode valueOfCode(int code) {
		for (ObcMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
