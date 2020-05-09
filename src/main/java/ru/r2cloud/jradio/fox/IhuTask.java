package ru.r2cloud.jradio.fox;

public enum IhuTask {

	Unknown(0), Audio(1), // = 1
	Telemetry(2), // 2,
	Control(3), // 3
	Command(4), // 4,
	Idle(5), // 5,
	Experiment(6); // 6,

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
		return Unknown;
	}
}
