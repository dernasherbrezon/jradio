package ru.r2cloud.jradio.quetzal1;

public enum CdhsStatus {

	SUCCESS, ERROR, UNKNOWN;

	public static CdhsStatus valueOfCode(int code) {
		if (code == 0x53) {
			return SUCCESS;
		} else if (code == 0x45) {
			return ERROR;
		} else {
			return UNKNOWN;
		}
	}

}
