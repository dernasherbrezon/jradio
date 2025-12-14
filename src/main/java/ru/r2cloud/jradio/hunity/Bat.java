package ru.r2cloud.jradio.hunity;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Bat {

	private SampleTime voltageTime;
	private int voltage;
	private SampleTime currentTime;
	private int current;
	private SampleTime temperatureTime;
	private float temperature;
	private SampleTime panelTemperatureTime;
	private float panelTemperature;
	private SampleTime gyroXTime;
	private float gyroX;
	private SampleTime gyroYTime;
	private float gyroY;
	private SampleTime gyroZTime;
	private float gyroZ;
	private SampleTime magnetoXTime;
	private int magnetoX;
	private SampleTime magnetoYTime;
	private int magnetoY;
	private SampleTime magnetoZTime;
	private int magnetoZ;
	
	public Bat() {
		// do nothing
	}

	public Bat(LittleEndianDataInputStream dis) throws IOException {
		int u16 = dis.readUnsignedShort();
		voltageTime = SampleTime.valueOf3Bit(u16);
		voltage = u16 >> 3;
		int i16 = dis.readShort();
		currentTime = SampleTime.valueOf3Bit(i16);
		current = i16 >> 3;
		i16 = dis.readShort();
		temperatureTime = SampleTime.valueOf3Bit(i16);
		temperature = (i16 >> 3) / 10.0f;
		i16 = dis.readShort();
		panelTemperatureTime = SampleTime.valueOf3Bit(i16);
		panelTemperature = (i16 >> 3) / 10.0f;
	}

	public SampleTime getVoltageTime() {
		return voltageTime;
	}

	public void setVoltageTime(SampleTime voltageTime) {
		this.voltageTime = voltageTime;
	}

	public int getVoltage() {
		return voltage;
	}

	public void setVoltage(int voltage) {
		this.voltage = voltage;
	}

	public SampleTime getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(SampleTime currentTime) {
		this.currentTime = currentTime;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public SampleTime getTemperatureTime() {
		return temperatureTime;
	}

	public void setTemperatureTime(SampleTime temperatureTime) {
		this.temperatureTime = temperatureTime;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public SampleTime getPanelTemperatureTime() {
		return panelTemperatureTime;
	}

	public void setPanelTemperatureTime(SampleTime panelTemperatureTime) {
		this.panelTemperatureTime = panelTemperatureTime;
	}

	public float getPanelTemperature() {
		return panelTemperature;
	}

	public void setPanelTemperature(float panelTemperature) {
		this.panelTemperature = panelTemperature;
	}

	public SampleTime getGyroXTime() {
		return gyroXTime;
	}

	public void setGyroXTime(SampleTime gyroXTime) {
		this.gyroXTime = gyroXTime;
	}

	public float getGyroX() {
		return gyroX;
	}

	public void setGyroX(float gyroX) {
		this.gyroX = gyroX;
	}

	public SampleTime getGyroYTime() {
		return gyroYTime;
	}

	public void setGyroYTime(SampleTime gyroYTime) {
		this.gyroYTime = gyroYTime;
	}

	public float getGyroY() {
		return gyroY;
	}

	public void setGyroY(float gyroY) {
		this.gyroY = gyroY;
	}

	public SampleTime getGyroZTime() {
		return gyroZTime;
	}

	public void setGyroZTime(SampleTime gyroZTime) {
		this.gyroZTime = gyroZTime;
	}

	public float getGyroZ() {
		return gyroZ;
	}

	public void setGyroZ(float gyroZ) {
		this.gyroZ = gyroZ;
	}

	public SampleTime getMagnetoXTime() {
		return magnetoXTime;
	}

	public void setMagnetoXTime(SampleTime magnetoXTime) {
		this.magnetoXTime = magnetoXTime;
	}

	public int getMagnetoX() {
		return magnetoX;
	}

	public void setMagnetoX(int magnetoX) {
		this.magnetoX = magnetoX;
	}

	public SampleTime getMagnetoYTime() {
		return magnetoYTime;
	}

	public void setMagnetoYTime(SampleTime magnetoYTime) {
		this.magnetoYTime = magnetoYTime;
	}

	public int getMagnetoY() {
		return magnetoY;
	}

	public void setMagnetoY(int magnetoY) {
		this.magnetoY = magnetoY;
	}

	public SampleTime getMagnetoZTime() {
		return magnetoZTime;
	}

	public void setMagnetoZTime(SampleTime magnetoZTime) {
		this.magnetoZTime = magnetoZTime;
	}

	public int getMagnetoZ() {
		return magnetoZ;
	}

	public void setMagnetoZ(int magnetoZ) {
		this.magnetoZ = magnetoZ;
	}

}
