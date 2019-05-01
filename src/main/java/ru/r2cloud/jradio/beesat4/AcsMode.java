package ru.r2cloud.jradio.beesat4;

import java.util.HashMap;
import java.util.Map;

public enum AcsMode {

	SUSPEND(0), INERTIALPOINTING(1), TARGETPOINTING(2), EARTHPOINTING(3), ZENITPOINTING(4), DAMPING(5), SLEW(6), BLINDDAMPING(7), TEST(9), SLIDINGEARTH(10), SLIDINGZENIT(11), SLIDINGINERTIAL(12), SLIDINGTARGET(13);

	private final int code;
	private static final Map<Integer, AcsMode> typeByCode = new HashMap<>();

	static {
		for (AcsMode cur : AcsMode.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private AcsMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static AcsMode valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
