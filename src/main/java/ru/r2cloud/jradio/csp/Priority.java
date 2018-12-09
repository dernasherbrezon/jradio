package ru.r2cloud.jradio.csp;

public enum Priority {

	CSP_PRIO_CRITICAL(0), CSP_PRIO_HIGH(1), CSP_PRIO_NORM(2), CSP_PRIO_LOW(3);

	private final int code;

	private Priority(int code) {
		this.code = code;
	}

	public static Priority valufOfCode(int code) {
		for (Priority cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
