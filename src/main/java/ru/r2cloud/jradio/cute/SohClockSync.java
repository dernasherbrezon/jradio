package ru.r2cloud.jradio.cute;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class SohClockSync {

	private long hrRunCount;
	private int hrExecTimeMs;

	public SohClockSync() {
		// do nothing
	}

	public SohClockSync(DataInputStream dis) throws IOException {
		hrRunCount = StreamUtils.readUnsignedInt(dis);
		hrExecTimeMs = dis.readUnsignedByte();
	}

	public long getHrRunCount() {
		return hrRunCount;
	}

	public void setHrRunCount(long hrRunCount) {
		this.hrRunCount = hrRunCount;
	}

	public int getHrExecTimeMs() {
		return hrExecTimeMs;
	}

	public void setHrExecTimeMs(int hrExecTimeMs) {
		this.hrExecTimeMs = hrExecTimeMs;
	}

}
