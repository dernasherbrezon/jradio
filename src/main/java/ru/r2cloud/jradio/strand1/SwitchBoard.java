package ru.r2cloud.jradio.strand1;

public class SwitchBoard {

	private SwitchStatus status;
	private float current;
	private float voltage;

	public SwitchBoard() {
		// do nothing
	}

	public SwitchBoard(SwitchStatus status, float current, float voltage) {
		super();
		this.status = status;
		this.current = current;
		this.voltage = voltage;
	}

	public SwitchStatus getStatus() {
		return status;
	}

	public void setStatus(SwitchStatus status) {
		this.status = status;
	}

	public float getCurrent() {
		return current;
	}

	public void setCurrent(float current) {
		this.current = current;
	}

	public float getVoltage() {
		return voltage;
	}

	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}

}
