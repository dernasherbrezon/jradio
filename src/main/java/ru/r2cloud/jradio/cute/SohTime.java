package ru.r2cloud.jradio.cute;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class SohTime {

	private double taiSeconds;
	private boolean timeValid;

	public SohTime() {
		// do nothing
	}

	public SohTime(DataInputStream dis) throws IOException {
		taiSeconds = StreamUtils.readUnsignedInt(dis) * 0.2;
		timeValid = dis.readBoolean();
	}

	public double getTaiSeconds() {
		return taiSeconds;
	}

	public void setTaiSeconds(double taiSeconds) {
		this.taiSeconds = taiSeconds;
	}

	public boolean isTimeValid() {
		return timeValid;
	}

	public void setTimeValid(boolean timeValid) {
		this.timeValid = timeValid;
	}

}
