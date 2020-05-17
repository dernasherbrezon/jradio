package ru.r2cloud.jradio.fox;

public enum IhuDownlinkState {

	NO_CHANGE(0), // No state change
	/* These are actual states */
	RELAY(1), // In relay or transponder mode
	TLM_FNSH(2), // Waiting for telemetry to finish after relay mode
	IDLE(3), IDLE_BCON(4), // Carrier on but silence at the start then 1 frame tlm
	IDLE_MSG(5), // Beacon voice message and one more frame of telemetry being sent
	IDLE_WAIT_TLM(6), // Beacon voice is done, but waiting for telem to finish.
	IDLE_WAIT_ID(7), // Beacon telemetry is done, but waiting for voice ID to finish
	IDLE_CAR2(8), // Silent carrier on at the end of the beacon
	SAFE(9), // Safe mode, no carrier
	SAFE_BCON(10), // Send safe mode carrier, and telemetry
	SAFE_MSG(11), // Send safe mode Voice ID
	SAFE_WAIT_TLM(12), // Beacon voice is done, but waiting for telem to finish.
	SAFE_WAIT_ID(13), // Beacon telemetry is done, but waiting for voice to finish
	SAFE_CAR2(14), // Silent carrier on at the end of the beacon
	DATA_MODE(15), UNEXPCTD(16);

	private final int code;

	private IhuDownlinkState(int code) {
		this.code = code;
	}

	public static IhuDownlinkState valueOfCode(int code) {
		for (IhuDownlinkState cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}
}
