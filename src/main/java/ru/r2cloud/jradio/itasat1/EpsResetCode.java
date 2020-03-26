package ru.r2cloud.jradio.itasat1;

public enum EpsResetCode {

	UNKNOWN(0), DEDICATED_WDT(1), I2C_WDT(2), HARD_RESET(3), SOFT_RESET(4), STACK_OVERFLOW(5), TIMER_OVERFLOW(6), BROWNOUT_OR_PWR_ON(7), INTERNAL_WDT(8);

	private final int code;

	private EpsResetCode(int code) {
		this.code = code;
	}

	public static EpsResetCode valueOfCode(int code) {
		for (EpsResetCode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return EpsResetCode.UNKNOWN;
	}

}
