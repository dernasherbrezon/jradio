package ru.r2cloud.jradio.trace;

public class HdlcPresync {

	private int shiftRegister;
	private String rawModulatorInput;

	public int getShiftRegister() {
		return shiftRegister;
	}

	public void setShiftRegister(int shiftRegister) {
		this.shiftRegister = shiftRegister;
	}

	public String getRawModulatorInput() {
		return rawModulatorInput;
	}

	public void setRawModulatorInput(String rawModulatorInput) {
		this.rawModulatorInput = rawModulatorInput;
	}
}
