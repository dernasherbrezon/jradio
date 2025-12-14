package ru.r2cloud.jradio.hunity;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Mptt {

	private SampleTime inputCurrentTime;
	private int inputCurrent;

	private SampleTime inputVoltageTime;
	private int inputVoltage;

	private SampleTime outputCurrentTime;
	private int outputCurrent;

	private SampleTime outputVoltageTime;
	private int outputVoltage;

	public Mptt() {
		// do nothing
	}

	public Mptt(LittleEndianDataInputStream dis) throws IOException {
		int u16 = dis.readUnsignedShort();
		inputCurrentTime = SampleTime.valueOf3Bit(u16);
		inputCurrent = u16 >> 3;

		u16 = dis.readUnsignedShort();
		inputVoltageTime = SampleTime.valueOf3Bit(u16);
		inputVoltage = u16 >> 3;

		u16 = dis.readUnsignedShort();
		outputCurrentTime = SampleTime.valueOf3Bit(u16);
		outputCurrent = u16 >> 3;

		u16 = dis.readUnsignedShort();
		outputVoltageTime = SampleTime.valueOf3Bit(u16);
		outputVoltage = u16 >> 3;
	}

	public SampleTime getInputCurrentTime() {
		return inputCurrentTime;
	}

	public void setInputCurrentTime(SampleTime inputCurrentTime) {
		this.inputCurrentTime = inputCurrentTime;
	}

	public int getInputCurrent() {
		return inputCurrent;
	}

	public void setInputCurrent(int inputCurrent) {
		this.inputCurrent = inputCurrent;
	}

	public SampleTime getInputVoltageTime() {
		return inputVoltageTime;
	}

	public void setInputVoltageTime(SampleTime inputVoltageTime) {
		this.inputVoltageTime = inputVoltageTime;
	}

	public int getInputVoltage() {
		return inputVoltage;
	}

	public void setInputVoltage(int inputVoltage) {
		this.inputVoltage = inputVoltage;
	}

	public SampleTime getOutputCurrentTime() {
		return outputCurrentTime;
	}

	public void setOutputCurrentTime(SampleTime outputCurrentTime) {
		this.outputCurrentTime = outputCurrentTime;
	}

	public int getOutputCurrent() {
		return outputCurrent;
	}

	public void setOutputCurrent(int outputCurrent) {
		this.outputCurrent = outputCurrent;
	}

	public SampleTime getOutputVoltageTime() {
		return outputVoltageTime;
	}

	public void setOutputVoltageTime(SampleTime outputVoltageTime) {
		this.outputVoltageTime = outputVoltageTime;
	}

	public int getOutputVoltage() {
		return outputVoltage;
	}

	public void setOutputVoltage(int outputVoltage) {
		this.outputVoltage = outputVoltage;
	}

}
