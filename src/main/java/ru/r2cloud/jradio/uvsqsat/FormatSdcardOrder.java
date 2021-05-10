package ru.r2cloud.jradio.uvsqsat;

public enum FormatSdcardOrder {

	ORDER_TO_FORMAT_SDCARD_0(0), ORDER_TO_FORMAT_SDCARD_1(1), ORDER_TO_NOT_FORMAT_SDCARD_0(0xAC), ORDER_TO_NOT_FORMAT_SDCARD_1(0xAD);

	private final int code;

	private FormatSdcardOrder(int code) {
		this.code = code;
	}

	public static FormatSdcardOrder valueOfCode(int code) {
		for (FormatSdcardOrder cur : values()) {
			if (cur.code == code) {
				return cur;
			}
		}
		return null;
	}

}
