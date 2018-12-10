package ru.r2cloud.jradio.sat3cat1;

import java.io.DataInputStream;
import java.io.IOException;

class TimeSource {

	private final static int BEACON_SENSOR_DELTA_T = (60 * 2); // in seconds

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
		long delta_min_delay;
		if (firstIndex || type.equals(BeaconType.BEACON_TYPE_STATE)) {
			delta_min_delay = 0;
		} else {
			delta_min_delay = BEACON_SENSOR_DELTA_T;
		}
		int timeDelta = dis.readUnsignedByte();
		long time;
		if (timeDelta != 0) {
			long delta_time = timeDelta - 1 + delta_min_delay;
			if (firstIndex || type.equals(BeaconType.BEACON_TYPE_STATE)) {
				time = spacecraftTime - delta_time;
			} else {
				time = previousTime - delta_time;
			}
		} else {
			long delta_time = dis.readUnsignedByte() << 16 | dis.readUnsignedShort();
			if (delta_time == 0) {
				return null;
			}
			delta_time += delta_min_delay - 1;
			if (firstIndex || type.equals(BeaconType.BEACON_TYPE_STATE)) {
				time = spacecraftTime - delta_time;
			} else {
				time = previousTime - delta_time;
			}
		}
		previousTime = time;
		firstIndex = false;
		return time;
	}
}
