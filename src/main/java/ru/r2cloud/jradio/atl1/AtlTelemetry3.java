package ru.r2cloud.jradio.atl1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class AtlTelemetry3 {

	private long timestamp;
	private AtlAccuMeasurement[] accu;

	public AtlTelemetry3() {
		// do nothing
	}

	public AtlTelemetry3(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		accu = new AtlAccuMeasurement[4];
		for (int i = 0; i < accu.length; i++) {
			accu[i] = new AtlAccuMeasurement(i, dis);
		}
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public AtlAccuMeasurement[] getAccu() {
		return accu;
	}

	public void setAccu(AtlAccuMeasurement[] accu) {
		this.accu = accu;
	}

}
