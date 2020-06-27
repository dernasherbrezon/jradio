package ru.r2cloud.jradio.bsusat1;

public enum CanStatus {

	OK(0), ABORTED(1), TIMEOUT(2), UNKNOWN(3);

	private final int code;

	private CanStatus(int code) {
		this.code = code;
	}

	public static CanStatus valueOfCode(int code) {
		for (CanStatus cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
