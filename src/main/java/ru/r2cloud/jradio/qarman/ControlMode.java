package ru.r2cloud.jradio.qarman;

public enum ControlMode {

	NO_CONTROL(0), DETUMBLING_CONTROL(2), Y_MOMENTUM_STABILIZED_INITIAL_PITCH_ACQUISITION(3), Y_MOMENTUM_STABILIZED_STEADY_STATE(4);

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
		return null;
	}
	
}
