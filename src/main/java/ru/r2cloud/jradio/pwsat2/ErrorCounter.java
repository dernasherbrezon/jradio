package ru.r2cloud.jradio.pwsat2;

public class ErrorCounter {

	private int device;
	private int current;
	private int limit;
	private int increment;
	private int decrement;

	public int getDevice() {
		return device;
	}

	public void setDevice(int device) {
		this.device = device;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public int getDecrement() {
		return decrement;
	}

	public void setDecrement(int decrement) {
		this.decrement = decrement;
	}

}
