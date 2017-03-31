package ru.r2cloud.jradio.csp;

public enum Priority {

	CSP_PRIO_CRITICAL(0), CSP_PRIO_ALERT(1), CSP_PRIO_HIGH(2), CSP_PRIO_RESERVED(3), CSP_PRIO_NORM(4), CSP_PRIO_LOW(5), CSP_PRIO_BULK(6), CSP_PRIO_DEBUG(7);

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
