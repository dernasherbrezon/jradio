package ru.r2cloud.jradio.eseo;

import java.util.HashMap;
import java.util.Map;

public enum MwmError {

	DELETED(0), NORMAL_POWER_ON(1), SOFT_RESET(2), WATCHDOG_RESET(3), CPU_EXCEPTION(4), PARAMETER_VERIFICATION_ERROR(5), PROGRAM_FLOW_ERROR(6), COMMUNICATION_TIMEOUT(7), BOOTLOADER_STARTED(8), PROGRAM_CRC_ERROR(9), IDLE(10), IDLE2(11), FORBIDDEN_INTERRUPT(12);

	private final int code;
	private static final Map<Integer, MwmError> typeByCode = new HashMap<>();

	static {
		for (MwmError cur : MwmError.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private MwmError(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static MwmError valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
