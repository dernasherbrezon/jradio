package ru.r2cloud.jradio.eseo;

import java.util.HashMap;
import java.util.Map;

public enum MwmError {

	Deleted(0), NormalPowerOn(1), Softreset(2), WatchdogReset(3), CPUException(4), ParameterVerificationError(5), ProgramFlowError(6), CommunicationTimeout(7), BootLoaderStarted(8), ProgramCRCError(9), Idle(10), Idle2(11), ForbiddenInterrupt(12);

	private final int code;
	private final static Map<Integer, MwmError> typeByCode = new HashMap<>();

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
