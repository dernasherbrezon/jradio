package ru.r2cloud.jradio.fox;

public enum IhuTask {

	UNKNOWN(0), AUDIO(1), // = 1
	TELEMETRY(2), // 2,
	CONTROL(3), // 3
	COMMAND(4), // 4,
	IDLE(5), // 5,
	EXPERIMENT(6); // 6,

	private final int code;

	private IhuTask(int code) {
		this.code = code;
	}

	public static IhuTask valueOfCode(int code) {
		for (IhuTask cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
