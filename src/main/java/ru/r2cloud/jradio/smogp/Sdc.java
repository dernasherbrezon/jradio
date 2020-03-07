package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Sdc {

	private int inputCurrent;
	private int outputCurrent;
	private float outputVoltage;

	public Sdc() {
		// do nothing
	}

	public Sdc(LittleEndianDataInputStream dis) throws IOException {
		inputCurrent = dis.readUnsignedShort();
		outputCurrent = dis.readUnsignedShort();
		outputVoltage = dis.readUnsignedShort() / 1000.0f;
	}

	public int getInputCurrent() {
		return inputCurrent;
	}

	public void setInputCurrent(int inputCurrent) {
		this.inputCurrent = inputCurrent;
	}

	public int getOutputCurrent() {
		return outputCurrent;
	}

	public void setOutputCurrent(int outputCurrent) {
		this.outputCurrent = outputCurrent;
	}

	public float getOutputVoltage() {
		return outputVoltage;
	}

	public void setOutputVoltage(float outputVoltage) {
		this.outputVoltage = outputVoltage;
	}

}
