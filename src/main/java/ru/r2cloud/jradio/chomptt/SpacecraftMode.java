package ru.r2cloud.jradio.chomptt;

public enum SpacecraftMode {

	DEPLOYMENT(0), BURNWIRE(1), IDLE(2), ACTIVITY(3), SAFE_MODE(4), OVERRIDE_MODE(5);

	private int code;

	private SpacecraftMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static SpacecraftMode valueOfCode(int code) {
		for (SpacecraftMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}
}
