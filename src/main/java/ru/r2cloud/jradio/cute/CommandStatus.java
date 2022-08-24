package ru.r2cloud.jradio.cute;

public enum CommandStatus {
	
	OK(0), BAD_APID(1), BAD_OPCODE(2), BAD_DATA(3), NOW_READING(4), DONE_READING(5), IDLE(6), NO_CMD_DATA(7), CMD_SRVC_OVERRUN(8), CMD_APID_OVERRUN(9), INCORRECT_WHEEL_MODE(10), BAD_ELEMENT(11), TABLES_BUSY(12), FLASH_NOT_ARMED(13), THRUSTERS_DISABLED(14), ATT_ERR_TOO_HIGH(15), ASYNC_REFUSED(16), DRIVER_ERROR(17), UNKNOWN(255);

	private final int code;

	private CommandStatus(int code) {
		this.code = code;
	}

	public static CommandStatus valueOfCode(int code) {
		for (CommandStatus cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
