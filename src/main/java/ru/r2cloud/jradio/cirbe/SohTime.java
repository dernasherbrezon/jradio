package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

public class SohTime {

	private double taiSeconds;
	private boolean timeValid;
	private int rtc_osc_rst_count;
	private boolean rtc_init_time_at_boot;
	private boolean rtc_sync_stat;
	private boolean rtc_alive;
	private boolean rtc_power;

	public SohTime() {
		// do nothing
	}

	public SohTime(DataInputStream dis) throws IOException {
		taiSeconds = dis.readDouble();
		timeValid = dis.readBoolean();
		int raw = dis.readUnsignedByte();
		rtc_osc_rst_count = ((raw >> 4) & 0x3);
		rtc_init_time_at_boot = ((raw >> 3) & 0x1) > 0;
		rtc_sync_stat = ((raw >> 2) & 0x1) > 0;
		rtc_alive = ((raw >> 1) & 0x1) > 0;
		rtc_power = (raw & 0x1) > 0;
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

	public int getRtc_osc_rst_count() {
		return rtc_osc_rst_count;
	}

	public void setRtc_osc_rst_count(int rtc_osc_rst_count) {
		this.rtc_osc_rst_count = rtc_osc_rst_count;
	}

	public boolean isRtc_init_time_at_boot() {
		return rtc_init_time_at_boot;
	}

	public void setRtc_init_time_at_boot(boolean rtc_init_time_at_boot) {
		this.rtc_init_time_at_boot = rtc_init_time_at_boot;
	}

	public boolean isRtc_sync_stat() {
		return rtc_sync_stat;
	}

	public void setRtc_sync_stat(boolean rtc_sync_stat) {
		this.rtc_sync_stat = rtc_sync_stat;
	}

	public boolean isRtc_alive() {
		return rtc_alive;
	}

	public void setRtc_alive(boolean rtc_alive) {
		this.rtc_alive = rtc_alive;
	}

	public boolean isRtc_power() {
		return rtc_power;
	}

	public void setRtc_power(boolean rtc_power) {
		this.rtc_power = rtc_power;
	}

}
