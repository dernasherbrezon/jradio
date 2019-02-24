package ru.r2cloud.jradio.snet;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianBitInputStream;

public class Battery {

	private float voltage;
	private float inputCurrent;
	private float outputCurrent;

	public Battery(LittleEndianBitInputStream bis) throws IOException {
		voltage = bis.readShort() / 2.0f;
		inputCurrent = bis.readShort() / 12.0f;
		outputCurrent = bis.readShort() / 6.0f;
	}

	public float getVoltage() {
		return voltage;
	}

	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}

	public float getInputCurrent() {
		return inputCurrent;
	}

	public void setInputCurrent(float inputCurrent) {
		this.inputCurrent = inputCurrent;
	}

	public float getOutputCurrent() {
		return outputCurrent;
	}

	public void setOutputCurrent(float outputCurrent) {
		this.outputCurrent = outputCurrent;
	}
	
}
