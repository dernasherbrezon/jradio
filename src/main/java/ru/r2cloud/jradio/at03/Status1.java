package ru.r2cloud.jradio.at03;

public class Status1 {

	private boolean is3V31On;
	private boolean is3V32on;
	private boolean is3V33on;
	private boolean is3V3BackupOn;
	private boolean is5V1On;
	private boolean is5V2On;
	private boolean is5V3On;
	private boolean is5V4On;

	public Status1(int b) {
		is3V31On = ((b >> 7) & 1) > 0;
		is3V32on = ((b >> 6) & 1) > 0;
		is3V33on = ((b >> 5) & 1) > 0;
		is3V3BackupOn = ((b >> 4) & 1) > 0;
		is5V1On = ((b >> 3) & 1) > 0;
		is5V2On = ((b >> 2) & 1) > 0;
		is5V3On = ((b >> 1) & 1) > 0;
		is5V4On = (b & 1) > 0;
	}

	public boolean isIs3V31On() {
		return is3V31On;
	}

	public void setIs3V31On(boolean is3v31On) {
		is3V31On = is3v31On;
	}

	public boolean isIs3V32on() {
		return is3V32on;
	}

	public void setIs3V32on(boolean is3v32on) {
		is3V32on = is3v32on;
	}

	public boolean isIs3V33on() {
		return is3V33on;
	}

	public void setIs3V33on(boolean is3v33on) {
		is3V33on = is3v33on;
	}

	public boolean isIs3V3BackupOn() {
		return is3V3BackupOn;
	}

	public void setIs3V3BackupOn(boolean is3v3BackupOn) {
		is3V3BackupOn = is3v3BackupOn;
	}

	public boolean isIs5V1On() {
		return is5V1On;
	}

	public void setIs5V1On(boolean is5v1On) {
		is5V1On = is5v1On;
	}

	public boolean isIs5V2On() {
		return is5V2On;
	}

	public void setIs5V2On(boolean is5v2On) {
		is5V2On = is5v2On;
	}

	public boolean isIs5V3On() {
		return is5V3On;
	}

	public void setIs5V3On(boolean is5v3On) {
		is5V3On = is5v3On;
	}

	public boolean isIs5V4On() {
		return is5V4On;
	}

	public void setIs5V4On(boolean is5v4On) {
		is5V4On = is5v4On;
	}

}
