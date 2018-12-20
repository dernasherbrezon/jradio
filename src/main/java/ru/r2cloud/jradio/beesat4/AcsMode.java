package ru.r2cloud.jradio.beesat4;

import java.util.HashMap;
import java.util.Map;

public enum AcsMode {

	Suspend(0), InertialPointing(1), TargetPointing(2), EarthPointing(3), ZenitPointing(4), Damping(5), Slew(6), BlindDamping(7), Test(9), SlidingEarth(10), SlidingZenit(11), SlidingInertial(12), SlidingTarget(13);

	private final int code;
	private final static Map<Integer, AcsMode> typeByCode = new HashMap<>();

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
