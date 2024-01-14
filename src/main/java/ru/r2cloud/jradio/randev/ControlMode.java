package ru.r2cloud.jradio.randev;

public enum ControlMode {

	DISABLED(0), DETUMBLING(1), Y_THOMSON(2), Y_WHEEL_MOMENTUM_STABILIZED_INITIAL(3), Y_WHEEL_MOMENTUM_STABILIZED_STEADY_STATE(4), XYZ_WHEEL(5), SUN_TRACKING(6), TARGET_TRACKING(7), VERY_FAST_DETUMBLING(8), FAST_DETUMBLING(9), USER1(10), USER2(11), RW_OFF(12), USER3(13), UNKNOWN(255);

	private final int code;

	private ControlMode(int code) {
		this.code = code;
	}

	public static ControlMode valueOfCode(int code) {
		for (ControlMode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}

}
