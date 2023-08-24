package ru.r2cloud.jradio.celesta;

public enum LastResetCause {

	POWER_SUPPLY_RESET(0x11), WATCHDOG(0x22), OSCILLATOR_ERROR(0x33), RESET_PIN(0x44), DEBUGGER_RESET(0x55), SOFTWARE_RESET(0x77), UNKNOWN(0xFF);

	private final int code;

	private LastResetCause(int code) {
		this.code = code;
	}

	public static LastResetCause valueOfCode(int code) {
		for (LastResetCause cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
