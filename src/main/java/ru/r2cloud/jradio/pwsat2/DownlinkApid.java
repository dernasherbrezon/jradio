package ru.r2cloud.jradio.pwsat2;

import java.util.HashMap;
import java.util.Map;

public enum DownlinkApid {

	Pong(0x01), Operation(0x2), ErrorCounters(0x3), ProgramUpload(0x4), PeriodicMessage(0x5), PersistentState(0x6), BootSlotsInfo(0x7), CompileInfo(0x8), EraseFlash(0x9), FileRemove(0xA), FileSend(0xB), FileList(0xC), Forbidden(0xD), Photo(0xE), SunS(0xF), Experiment(0x10), ErrorCounterConfiguration(0x11), PurgePhoto(0x12), Powercycle(0x13), Sail(0x14), TimeCorrection(0x15), TimeSet(0x16), Comm(0x17), SetBitrate(0x18), DisableOverheatSubmode(0x19), I2C(0x1A), PeriodicSet(0x1B), SailExperiment(
			0x1C), CopyBootTable(0x1D), SetInternalDetumblingMode(0x1E), SetAdcsMode(0x1F), StopSailDeployment(0x20), MemoryContent(0x21), BeaconError(0x22), StopAntennaDeployment(0x23), Telemetry(0x3f);

	private final int code;
	private final static Map<Integer, DownlinkApid> typeByCode = new HashMap<>();

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
