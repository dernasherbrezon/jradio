package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswTime {

	private double taiSeconds;
	private double cycleTime;
	private long jdOfNowWrtTai;
	private long gpsUpdateCyclePeriod;
	private long gpsUpdateCycleCount;
	private boolean timeValid;

	public FswTime() {
		// do nothing
	}

	public FswTime(DataInputStream dis) throws IOException {
		taiSeconds = StreamUtils.readUnsignedInt(dis) * 0.2;
		cycleTime = StreamUtils.readUnsignedInt(dis) * 0.2;
		jdOfNowWrtTai = StreamUtils.readUnsignedInt(dis);
		gpsUpdateCyclePeriod = StreamUtils.readUnsignedInt(dis);
		gpsUpdateCycleCount = StreamUtils.readUnsignedInt(dis);
		timeValid = dis.readBoolean();
	}

	public double getTaiSeconds() {
		return taiSeconds;
	}

	public void setTaiSeconds(double taiSeconds) {
		this.taiSeconds = taiSeconds;
	}

	public double getCycleTime() {
		return cycleTime;
	}

	public void setCycleTime(double cycleTime) {
		this.cycleTime = cycleTime;
	}

	public long getJdOfNowWrtTai() {
		return jdOfNowWrtTai;
	}

	public void setJdOfNowWrtTai(long jdOfNowWrtTai) {
		this.jdOfNowWrtTai = jdOfNowWrtTai;
	}

	public long getGpsUpdateCyclePeriod() {
		return gpsUpdateCyclePeriod;
	}

	public void setGpsUpdateCyclePeriod(long gpsUpdateCyclePeriod) {
		this.gpsUpdateCyclePeriod = gpsUpdateCyclePeriod;
	}

	public long getGpsUpdateCycleCount() {
		return gpsUpdateCycleCount;
	}

	public void setGpsUpdateCycleCount(long gpsUpdateCycleCount) {
		this.gpsUpdateCycleCount = gpsUpdateCycleCount;
	}

	public boolean isTimeValid() {
		return timeValid;
	}

	public void setTimeValid(boolean timeValid) {
		this.timeValid = timeValid;
	}

}
