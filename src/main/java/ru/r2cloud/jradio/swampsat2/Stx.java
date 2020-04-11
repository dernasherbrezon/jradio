package ru.r2cloud.jradio.swampsat2;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Stx {

	private double voltageBattery;
	private double currentBattery;
	private double voltagePoweramplifier;
	private double currentPoweramplifier;
	private double temperatureTop;
	private double temperatureBottom;
	private double temperaturePoweramplifier;
	private double synthOffset;
	private int bufferOverrun;
	private int bufferUnderrun;

	private boolean poweramplifierStatusFrequencylock;
	private boolean poweramplifierStatusPowergood;
	private double rfPoweroutput;

	public Stx() {
		// do nothing
	}

	public Stx(LittleEndianDataInputStream dis) throws IOException {
		voltageBattery = dis.readUnsignedShort() * 4e-3;
		currentBattery = dis.readUnsignedShort() * 40e-6;
		voltagePoweramplifier = dis.readUnsignedShort() * 4e-3;
		currentPoweramplifier = dis.readUnsignedShort() * 40e-6;
		temperatureTop = ((dis.readShort() >> 4) & 0b1111_1111_1111) * 0.0625;
		temperatureBottom = ((dis.readShort() >> 4) & 0b1111_1111_1111) * 0.0625;
		temperaturePoweramplifier = ((dis.readUnsignedByte() * 3.0 / 4096) - 0.5) * 100;
		synthOffset = dis.readUnsignedByte() * 0.5 + 2400;
		bufferOverrun = dis.readUnsignedShort();
		bufferUnderrun = dis.readUnsignedShort();

		int raw = dis.readUnsignedByte();
		poweramplifierStatusFrequencylock = ((raw) & 0x1) > 0;
		poweramplifierStatusPowergood = ((raw >> 1) & 0x1) > 0;

		rfPoweroutput = dis.readUnsignedShort() * 3.0 * 28 / 4096 / 18;
	}

	public double getVoltageBattery() {
		return voltageBattery;
	}

	public void setVoltageBattery(double voltageBattery) {
		this.voltageBattery = voltageBattery;
	}

	public double getCurrentBattery() {
		return currentBattery;
	}

	public void setCurrentBattery(double currentBattery) {
		this.currentBattery = currentBattery;
	}

	public double getVoltagePoweramplifier() {
		return voltagePoweramplifier;
	}

	public void setVoltagePoweramplifier(double voltagePoweramplifier) {
		this.voltagePoweramplifier = voltagePoweramplifier;
	}

	public double getCurrentPoweramplifier() {
		return currentPoweramplifier;
	}

	public void setCurrentPoweramplifier(double currentPoweramplifier) {
		this.currentPoweramplifier = currentPoweramplifier;
	}

	public double getTemperatureTop() {
		return temperatureTop;
	}

	public void setTemperatureTop(double temperatureTop) {
		this.temperatureTop = temperatureTop;
	}

	public double getTemperatureBottom() {
		return temperatureBottom;
	}

	public void setTemperatureBottom(double temperatureBottom) {
		this.temperatureBottom = temperatureBottom;
	}

	public double getTemperaturePoweramplifier() {
		return temperaturePoweramplifier;
	}

	public void setTemperaturePoweramplifier(double temperaturePoweramplifier) {
		this.temperaturePoweramplifier = temperaturePoweramplifier;
	}

	public double getSynthOffset() {
		return synthOffset;
	}
	
	public void setSynthOffset(double synthOffset) {
		this.synthOffset = synthOffset;
	}

	public int getBufferOverrun() {
		return bufferOverrun;
	}

	public void setBufferOverrun(int bufferOverrun) {
		this.bufferOverrun = bufferOverrun;
	}

	public int getBufferUnderrun() {
		return bufferUnderrun;
	}

	public void setBufferUnderrun(int bufferUnderrun) {
		this.bufferUnderrun = bufferUnderrun;
	}

	public boolean isPoweramplifierStatusFrequencylock() {
		return poweramplifierStatusFrequencylock;
	}

	public void setPoweramplifierStatusFrequencylock(boolean poweramplifierStatusFrequencylock) {
		this.poweramplifierStatusFrequencylock = poweramplifierStatusFrequencylock;
	}

	public boolean isPoweramplifierStatusPowergood() {
		return poweramplifierStatusPowergood;
	}

	public void setPoweramplifierStatusPowergood(boolean poweramplifierStatusPowergood) {
		this.poweramplifierStatusPowergood = poweramplifierStatusPowergood;
	}

	public double getRfPoweroutput() {
		return rfPoweroutput;
	}

	public void setRfPoweroutput(double rfPoweroutput) {
		this.rfPoweroutput = rfPoweroutput;
	}

}
