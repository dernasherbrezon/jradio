package ru.r2cloud.jradio.armadillo;

public enum BootCause {

	UNKNOWN_RESET(0), DEDICATED_WDT_RESET(1), I2C_WDT_RESET(2), HARD_RESET(3), SOFT_RESET(4), STACK_OVERFLOW(5), TIMER_OVERFLOW(6), BROWNOUT_OR_POWER_ON_RESET(7), INTERNAL_WDT_RESET(8);

	private int code;

	private BootCause(int code) {
		this.code = code;
	}

	public static BootCause valueOfCode(int code) {
		for (BootCause cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
