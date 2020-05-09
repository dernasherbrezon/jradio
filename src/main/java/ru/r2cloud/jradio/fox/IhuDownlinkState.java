package ru.r2cloud.jradio.fox;

public enum IhuDownlinkState {

	NoChange(0), // No state change
	/* These are actual states */
	Relay(1), // In relay or transponder mode
	TlmFnsh(2), // Waiting for telemetry to finish after relay mode
	Idle(3), IdleBcon(4), // Carrier on but silence at the start then 1 frame tlm
	IdleMsg(5), // Beacon voice message and one more frame of telemetry being sent
	IdleWaitTlm(6), // Beacon voice is done, but waiting for telem to finish.
	IdleWaitID(7), // Beacon telemetry is done, but waiting for voice ID to finish
	IdleCar2(8), // Silent carrier on at the end of the beacon
	Safe(9), // Safe mode, no carrier
	SafeBcon(10), // Send safe mode carrier, and telemetry
	SafeMsg(11), // Send safe mode Voice ID
	SafeWaitTlm(12), // Beacon voice is done, but waiting for telem to finish.
	SafeWaitID(13), // Beacon telemetry is done, but waiting for voice to finish
	SafeCar2(14), // Silent carrier on at the end of the beacon
	DataMode(15), Unexpctd(16);

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
