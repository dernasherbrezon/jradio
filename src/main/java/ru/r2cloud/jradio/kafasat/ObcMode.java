package ru.r2cloud.jradio.kafasat;

public enum ObcMode {

	NORMAL(2), SAFE(6), UNKNOWN(255);

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
