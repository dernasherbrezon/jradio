package ru.r2cloud.jradio.pwsat2;

import java.util.HashMap;
import java.util.Map;

public enum DownlinkApid {

	PONG(0x01), OPERATION(0x2), ERROR_COUNTERS(0x3), PROGRAM_UPLOAD(0x4), PERIODIC_MESSAGE(0x5), PERSISTENT_STATE(0x6), BOOT_SLOTS_INFO(0x7), COMPILE_INFO(0x8), ERASE_FLASH(0x9), FILE_REMOVE(0xA), FILE_SEND(0xB), FILE_LIST(0xC), FORBIDDEN(0xD), PHOTO(0xE), SUNS(0xF), EXPERIMENT(0x10), ERROR_COUNTER_CONFIGURATION(0x11), PURGE_PHOTO(0x12), POWER_CYCLE(0x13), SAIL(0x14), TIME_CORRECTION(0x15), TIME_SET(0x16), COMM(0x17), SET_BITRATE(0x18), DISABLE_OVERHEAT_SUBMODE(0x19), I2C(0x1A), PERIODIC_SET(0x1B), SAIL_EXPERIMENT(
			0x1C), COPY_BOOT_TABLE(0x1D), SET_INTERNAL_DETUMBLING_MODE(0x1E), SET_ADCS_MODE(0x1F), STOP_SAIL_DEPLOYMENT(0x20), MEMORY_CONTENT(0x21), BEACON_ERROR(0x22), STOP_ANTENNA_DEPLOYMENT(0x23), TELEMETRY(0x3f);

	private final int code;
	private static final Map<Integer, DownlinkApid> typeByCode = new HashMap<>();

	static {
		for (DownlinkApid cur : DownlinkApid.values()) {
			typeByCode.put(cur.getCode(), cur);
		}
	}

	private DownlinkApid(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static DownlinkApid valueOfCode(int code) {
		return typeByCode.get(code);
	}
}
