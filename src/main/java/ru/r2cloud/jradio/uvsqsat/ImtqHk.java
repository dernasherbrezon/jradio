package ru.r2cloud.jradio.uvsqsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class ImtqHk {

	private float digitaleVoltage;
	private float analogVoltage;
	private int digitalCurrent;
	private int analogCurrent;
	private float measureCoilXCurrent;
	private float measureCoilYCurrent;
	private float measureCoilZCurrent;
	private float measureCoilXTemperature;
	private float measureCoilYTemperature;
	private float measureCoilZTemperature;
	private float mcuTemperature;
	private int imtqSystemStateMode;
	private int imtqSystemStateError;
	private int imtqSystemStateConfiguration;
	private long imtqSystemStateUptime;

	public ImtqHk() {
		// do nothing
	}

	public ImtqHk(DataInputStream dis) throws IOException {
		digitaleVoltage = 2 * (2.5F / 4095) * dis.readUnsignedShort();
		analogVoltage = 2 * (2.5F / 4095) * dis.readUnsignedShort();
		digitalCurrent = dis.readUnsignedShort();
		analogCurrent = dis.readUnsignedShort();
		measureCoilXCurrent = 1000.0F * (((2.5F / 4095) * dis.readUnsignedShort()) - 1.03F) / 2.0F;
		measureCoilYCurrent = 1000.0F * ((2.5F / 4095) * dis.readUnsignedShort() - 1.03F) / 2.0F;
		measureCoilZCurrent = 1000.0F * ((2.5F / 4095) * dis.readUnsignedShort() - 1.03F) / 0.48F;
		measureCoilXTemperature = (((2.5F / 4095) * dis.readUnsignedShort()) - 1.567F) * -1.0F / 0.0081F;
		measureCoilYTemperature = (((2.5F / 4095) * dis.readUnsignedShort()) - 1.567F) * -1.0F / 0.0081F;
		measureCoilZTemperature = (((2.5F / 4095) * dis.readUnsignedShort()) - 1.567F) * -1.0F / 0.0081F;
		mcuTemperature = (((2.5F / 4095) * dis.readUnsignedShort()) - 0.680F) * -1.0F / 0.00225F;
		imtqSystemStateMode = dis.readUnsignedByte();
		imtqSystemStateError = dis.readUnsignedByte();
		imtqSystemStateConfiguration = dis.readUnsignedByte();
		imtqSystemStateUptime = StreamUtils.readUnsignedInt(dis);
	}

	public float getDigitaleVoltage() {
		return digitaleVoltage;
	}

	public void setDigitaleVoltage(float digitaleVoltage) {
		this.digitaleVoltage = digitaleVoltage;
	}

	public float getAnalogVoltage() {
		return analogVoltage;
	}

	public void setAnalogVoltage(float analogVoltage) {
		this.analogVoltage = analogVoltage;
	}

	public int getDigitalCurrent() {
		return digitalCurrent;
	}

	public void setDigitalCurrent(int digitalCurrent) {
		this.digitalCurrent = digitalCurrent;
	}

	public int getAnalogCurrent() {
		return analogCurrent;
	}

	public void setAnalogCurrent(int analogCurrent) {
		this.analogCurrent = analogCurrent;
	}

	public float getMeasureCoilXCurrent() {
		return measureCoilXCurrent;
	}

	public void setMeasureCoilXCurrent(float measureCoilXCurrent) {
		this.measureCoilXCurrent = measureCoilXCurrent;
	}

	public float getMeasureCoilYCurrent() {
		return measureCoilYCurrent;
	}

	public void setMeasureCoilYCurrent(float measureCoilYCurrent) {
		this.measureCoilYCurrent = measureCoilYCurrent;
	}

	public float getMeasureCoilZCurrent() {
		return measureCoilZCurrent;
	}

	public void setMeasureCoilZCurrent(float measureCoilZCurrent) {
		this.measureCoilZCurrent = measureCoilZCurrent;
	}

	public float getMeasureCoilXTemperature() {
		return measureCoilXTemperature;
	}

	public void setMeasureCoilXTemperature(float measureCoilXTemperature) {
		this.measureCoilXTemperature = measureCoilXTemperature;
	}

	public float getMeasureCoilYTemperature() {
		return measureCoilYTemperature;
	}

	public void setMeasureCoilYTemperature(float measureCoilYTemperature) {
		this.measureCoilYTemperature = measureCoilYTemperature;
	}

	public float getMeasureCoilZTemperature() {
		return measureCoilZTemperature;
	}

	public void setMeasureCoilZTemperature(float measureCoilZTemperature) {
		this.measureCoilZTemperature = measureCoilZTemperature;
	}

	public float getMcuTemperature() {
		return mcuTemperature;
	}

	public void setMcuTemperature(float mcuTemperature) {
		this.mcuTemperature = mcuTemperature;
	}

	public int getImtqSystemStateMode() {
		return imtqSystemStateMode;
	}

	public void setImtqSystemStateMode(int imtqSystemStateMode) {
		this.imtqSystemStateMode = imtqSystemStateMode;
	}

	public int getImtqSystemStateError() {
		return imtqSystemStateError;
	}

	public void setImtqSystemStateError(int imtqSystemStateError) {
		this.imtqSystemStateError = imtqSystemStateError;
	}

	public int getImtqSystemStateConfiguration() {
		return imtqSystemStateConfiguration;
	}

	public void setImtqSystemStateConfiguration(int imtqSystemStateConfiguration) {
		this.imtqSystemStateConfiguration = imtqSystemStateConfiguration;
	}

	public long getImtqSystemStateUptime() {
		return imtqSystemStateUptime;
	}

	public void setImtqSystemStateUptime(long imtqSystemStateUptime) {
		this.imtqSystemStateUptime = imtqSystemStateUptime;
	}

}
