package ru.r2cloud.jradio.hunity;

public enum DataRate {

	AUTO, BPS_1250, BPS_2500, BPS_5000, BPS_12500, BPS_25000, UNKNOWN;

	public static DataRate valueOfCode(int code) {
		switch (code) {
		case -1:
			return AUTO;
		case 0:
			return BPS_1250;
		case 1:
			return BPS_2500;
		case 2:
			return BPS_5000;
		case 3:
			return BPS_12500;
		case 4:
			return BPS_25000;
		default:
			return UNKNOWN;
		}
	}
}
