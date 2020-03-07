package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry1 {

	private long time;
	private Mppt[] mppt;
	private AckInfo[] ackInfo;

	public Telemetry1() {
		// do nothing
	}

	public Telemetry1(LittleEndianDataInputStream dis) throws IOException {
		time = dis.readUnsignedInt();
		mppt = new Mppt[6];
		for (int i = 0; i < mppt.length; i++) {
			mppt[i] = new Mppt(dis);
		}
		ackInfo = new AckInfo[3];
		for (int i = 0; i < ackInfo.length; i++) {
			ackInfo[i] = new AckInfo(dis);
		}
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Mppt[] getMppt() {
		return mppt;
	}

	public void setMppt(Mppt[] mppt) {
		this.mppt = mppt;
	}

	public AckInfo[] getAckInfo() {
		return ackInfo;
	}

	public void setAckInfo(AckInfo[] ackInfo) {
		this.ackInfo = ackInfo;
	}

}
