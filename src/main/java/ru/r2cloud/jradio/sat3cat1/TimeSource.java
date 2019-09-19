package ru.r2cloud.jradio.sat3cat1;

import java.io.DataInputStream;
import java.io.IOException;

class TimeSource {

	private static final int BEACON_SENSOR_DELTA_T = (60 * 2); // in seconds

	private final long spacecraftTime;
	private final BeaconType type;
	private final DataInputStream dis;

	private boolean firstIndex = true;
	private long previousTime;

	TimeSource(long spacecraftTime, BeaconType type, DataInputStream dis) {
		this.spacecraftTime = spacecraftTime;
		this.type = type;
		this.dis = dis;
	}

	Long next() throws IOException {
		long deltaMinDelay;
		if (firstIndex || type.equals(BeaconType.BEACON_TYPE_STATE)) {
			deltaMinDelay = 0;
		} else {
			deltaMinDelay = BEACON_SENSOR_DELTA_T;
		}
		int timeDelta = dis.readUnsignedByte();
		long time;
		if (timeDelta != 0) {
			long deltaTime = timeDelta - 1 + deltaMinDelay;
			if (firstIndex || type.equals(BeaconType.BEACON_TYPE_STATE)) {
				time = spacecraftTime - deltaTime;
			} else {
				time = previousTime - deltaTime;
			}
		} else {
			long deltaTime = dis.readUnsignedByte() << 16 | dis.readUnsignedShort();
			if (deltaTime == 0) {
				return null;
			}
			deltaTime += deltaMinDelay - 1;
			if (firstIndex || type.equals(BeaconType.BEACON_TYPE_STATE)) {
				time = spacecraftTime - deltaTime;
			} else {
				time = previousTime - deltaTime;
			}
		}
		previousTime = time;
		firstIndex = false;
		return time;
	}
}
