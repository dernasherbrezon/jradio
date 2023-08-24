package ru.r2cloud.jradio.celesta;

public enum LastErrorCode {

	NOTHING(0x00), RADIO_HW_ERROR(0x11), TX_QUEUE_FULL(0x22), RX_QUEUE_FULL(0x33), TX_BUS_QUEUE_FULL(0x44), RX_BUS_QUEUE_FULL(0x55), OBC_TEMP_HW_ERROR(0x66), OBC_TEMP_H_LIMIT_ERROR(0x77), OBC_TEMP_L_LIMIT_ERROR(0x88), PA_TEMP_HW_ERROR(0x99), PA_TEMP_H_LIMIT_ERROR(0xAA), PA_TEMP_L_LIMIT_ERROR(0xBB),
	OBDH_NACK(0xCC), PF_RESET_REQ(0xDD), TTC_RESET_REQ(0xD1), RADIO_TASK_TIMEOUT(0xEE), RADIO_UNQUEUE(0xFF), OBDH_STATUS_REQ(0x01), OBDH_BDR_REQ(0x02), FRAM_ID_ERROR(0xA1), FRAM_HW_ERROR(0xA2), FRAM_READ_ERROR(0xA3), FRAM_WRITE_ERROR(0xA4), EVENT_QUEUE_READ_ERROR(0xA5), UNKNOWN(0x01);

	private final int code;

	private LastErrorCode(int code) {
		this.code = code;
	}

	public static LastErrorCode valueOfCode(int code) {
		for (LastErrorCode cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return UNKNOWN;
	}
}
