package ru.r2cloud.jradio.sat3cat1;

public class MetricValue {

	private long time;
	private float value;

	public MetricValue(long time, float value) {
		this.time = time;
		this.value = value;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

}
